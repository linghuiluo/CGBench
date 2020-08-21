package onlinechat.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import onlinechat.crypto.KeyManagment;
import onlinechat.crypto.SymmetricEnc;
import onlinechat.model.Greeting;
import onlinechat.model.LoginMessage;
import onlinechat.model.MessageRepository;
import onlinechat.model.User;
import onlinechat.model.UserMessage;

@Controller
public class ChatController {

	@Autowired
	private MessageSendingOperations<String> messageSender;

	@Autowired
	private EntityManager em;

	private Map<String, SecretKey> channels = new HashMap<>();

	@Autowired
	private MessageRepository messages;

	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public UserMessage chat(Message<UserMessage> message) throws Exception {
		UserMessage userMessage = message.getPayload();
		userMessage.setCreated(new Date());
		userMessage.setMessage(HtmlUtils.htmlEscape(userMessage.getMessage()));
		messages.save(userMessage);
		return userMessage;
	}

	@MessageMapping("/encryptedChat")
	public void encryptedChat(Message<UserMessage> wsMessage) throws Exception {
		UserMessage privateMessage = wsMessage.getPayload();
		// encrypt the message we store into the database
		UserMessage messageToStore = new UserMessage(privateMessage.getName(),
				HtmlUtils.htmlEscape(privateMessage.getMessage()));
		messageToStore.setChannel(privateMessage.getChannel());
		encryptMessage(messageToStore, channels.get(messageToStore.getChannel()));
		messages.save(messageToStore);
		// send original message back to channel
		privateMessage.setCreated(new Date());
		messageSender.convertAndSend("/topic/channels/" + privateMessage.getChannel(), privateMessage);
	}

	@MessageMapping("/channel")
	public void enterChannel(Message<LoginMessage> login) throws Exception {
		String channel = login.getPayload().getChannel();
		if (!channels.containsKey(channel)) {
			createChannel(channel);
		}
		List<UserMessage> channelMessages = messages.findFirst15ByChannelOrderByCreatedDesc(channel);
		for (UserMessage message : channelMessages) {
			decryptMessage(message, channels.get(channel));
		}
		messageSender.convertAndSend("/topic/channels/" + channel + "/" + login.getPayload().getToken(),
				channelMessages);
		messageSender.convertAndSend("/topic/channels/" + channel,
				new Greeting(HtmlUtils.htmlEscape(login.getPayload().getName()) + " entered the channel!"));
	}

	@MessageMapping("/login")
	@Transactional
	public void login(Message<LoginMessage> login) throws Exception {
		User user = new User();
		user.setName(login.getPayload().getName());
		user.setToken(login.getPayload().getToken());
		try {
			try {

				TypedQuery<User> findUser = em
						.createQuery("Select u from User u where u.token = '" + user.getToken() + "'", User.class);
				// user with token exists
				user = findUser.getSingleResult();
			} catch (NoResultException nre) {
				// user with token does not exist, trying to create
				em.createNativeQuery(
						"Insert into User (token, name) values ('" + user.getToken() + "','" + user.getName() + "');")
						.executeUpdate();
			}
			List<UserMessage> previousMessages = messages.findFirst15ByChannelOrderByCreatedDesc(null);
			Collections.sort(previousMessages);
			messageSender.convertAndSend("/topic/messages/" + user.getToken(), previousMessages);
			messageSender.convertAndSend("/topic/messages",
					new Greeting(HtmlUtils.htmlEscape(login.getPayload().getName()) + " logged in!"));
		} catch (Exception e) {
			messageSender.convertAndSend("/topic/error/" + user.getToken(), "name already in use");
		}
	}

	@MessageMapping("/createChannel")
	public void createChannel(Message<String> channel) throws GeneralSecurityException {
		createChannel(channel.getPayload());
	}

	private void createChannel(String channelId) throws GeneralSecurityException {
		byte[] salt = new byte[16];
		SecureRandom.getInstanceStrong().nextBytes(salt);
		char[] password = { '3', '3', 'd', '6', 'c', '3', '1', 'd', '6', 'a', '3', '3', 'd', '8', 'c', '5', '3', 'd',
				'6', 'x', };
		channels.put(channelId, KeyManagment.getKey(password, salt));
	}

	@PostMapping("/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("userName") String user,
			@RequestParam("channel") String channel, @RequestParam("file") MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		if ("null".equals(channel)) {
			channel = null;
		}

		try {
			if (file.isEmpty()) {
				throw new IllegalArgumentException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				// This is a security check
				throw new IllegalArgumentException(
						"Cannot store file with relative path outside current directory " + filename);
			}
			Path folderPath;
			if (channel != null) {
				folderPath = Paths.get("channels", channel);
			} else {
				folderPath = Paths.get("channels", "mainChannel");
			}
			Path filePath = folderPath.resolve(filename);
			try (InputStream inputStream = file.getInputStream()) {
				Files.createDirectories(folderPath);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			}
			String destination = channel != null ? ("/topic/channels/" + channel) : "/topic/messages";
			UserMessage message = new UserMessage(user, "Uploaded a file:<a href='/file?fileName="
					+ filePath.toString().replaceAll("\\\\", "/") + "'>" + filename + "</a>");
			message.setChannel(channel);
			messages.save(message);
			messageSender.convertAndSend(destination, message);
			return ResponseEntity.ok("success");
		} catch (IOException e) {
			throw new IllegalArgumentException("Failed to store file " + filename, e);
		}

	}

	@GetMapping("/file")
	public void getFile(@RequestParam String fileName, HttpServletResponse response) throws IOException {
		try (InputStream is = new FileInputStream(fileName)) {
			IOUtils.copy(is, response.getOutputStream());
			response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
			response.setContentType("text/plain; name=\"" + fileName + "\"");
			response.flushBuffer();
		}
	}

	private void encryptMessage(UserMessage message, SecretKey encryptionKey)
			throws GeneralSecurityException, UnsupportedEncodingException {
		SymmetricEnc symEnc = new SymmetricEnc();

		String encMessage = symEnc.encrypt(message.getMessage(), encryptionKey);
		message.setMessage(encMessage);
	}

	private void decryptMessage(UserMessage message, SecretKey encryptionKey) throws GeneralSecurityException {
		SymmetricEnc symEnc = new SymmetricEnc();

		String decMessage = symEnc.decrypt(message.getMessage(), encryptionKey);
		message.setMessage(decMessage);
	}

}
