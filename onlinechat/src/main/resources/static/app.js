var stompClient = null;
var channel = null;
var userName = null;
var token = null;

function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	}
	else {
		$("#conversation").hide();
	}
	$("#messages").html("");
}

function connect() {
	return new Promise((resolve) => {
		var socket = new SockJS('/onlineChat');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			setConnected(true);
			console.log('Connected: ' + frame);
			stompClient.subscribe('/topic/messages', parseServerMessage);
			resolve();
		});
	});
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function login() {
	userName = $("#name").val();
	token = readCookie("token");
	if (!token){
		token =	uuidv4();
	} 
	stompClient.subscribe('/topic/messages/' + token, parseServerMessage);
	stompClient.subscribe('/topic/error/' + token, ()=>{
		$("#registerForm").show();
		$("#messageForm").hide();
		$("#enterChannelForm").hide();
		$("#name").focus();
		eraseCookie("userName");
		eraseCookie("token");
		userName = null;
		token = null;
		showMessage({content: "Username already in use!"});
	});

	stompClient.send("/app/login", {}, JSON.stringify({ 'name': userName, token: token }));
	$("#registerForm").hide();
	$("#messageForm").show();
	$("#enterChannelForm").show();
	$("#message").focus();
	createCookie("userName", userName, 20);
	createCookie("token", token, 20);
}

function sendMessage() {
	stompClient.send("/app/chat", {}, JSON.stringify({
		'name': $("#name").val(),
		'message': $("#message").val()
	}));
	$("#message").val("");
}

function sendPrivateMessage() {
	stompClient.send("/app/encryptedChat", {}, JSON.stringify({
		'name': $("#name").val(),
		'message': $("#privateMessage").val(),
		channel: channel
	}));
	$("#privateMessage").val("");
}

function parseServerMessage(serverMessage) {
	let body = JSON.parse(serverMessage.body);
	if ($.isArray(body)) {
		for (let message of body) {
			showMessage(message);
		}
	} else {
		showMessage(body);
	}
}

function showMessage(message) {
	let messages = $("#messages");
	if (message.content) {
		messages.append("<div class='greeting'>" + message.content + "</div>");
	} else if (message.name) {
		var options = { hour: '2-digit', minute: '2-digit', second: '2-digit' };
		let created = new Date(message.created).toLocaleDateString("de-DE", options);
		let own = message.name == userName;
		messages.append("<div class='message" + (own ? " own" : "") + "'><div class='created'>" + created + "</div><div class='name'>" + message.name + "</div><div class='messageContent'>" + message.message + "</div></div>");
	}
	var h = $('#conversation').get(0).scrollHeight;
	$('#conversation').animate({ scrollTop: h });
}

function createChannel() {
	channel = uuidv4();
	enterChannel();
	stompClient.send("/app/createChannel", {}, channel);
}

function enterChannel() {
	stompClient.subscribe('/topic/channels/' + channel, parseServerMessage);
	stompClient.subscribe('/topic/channels/' + channel + "/" + token, parseServerMessage);
	stompClient.send("/app/channel", {}, JSON.stringify({ 'name': $("#name").val(), token: token, channel: channel }));
	$("#messages").empty();
	$("#messageForm").hide();
	$("#privateMessageForm").show();
	$("#channelDisplay").text(channel);
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(connect);
	$("#disconnect").click(disconnect);
	$("#login").click(login);
	$("#send").click(sendMessage);
	$("#messageForm").hide();
	$("#privateMessageForm").hide();
	$("#enterChannelForm").hide();

	$("#createChannel").click(createChannel);
	$("#sendPrivate").click(sendPrivateMessage);
	$("#enterChannel").click(() => {
		channel = $("#channelId").val();
		enterChannel();
	});
	$('#sendFile').click(() => {
		var fileForm = document.getElementById('fileForm');
		let formData = new FormData(fileForm);
		formData.append("userName", userName);
		formData.append("channel", channel);
		$.ajax({
			type: "POST",
			url: "upload",
			xhr: function() {
				var myXhr = $.ajaxSettings.xhr();
				return myXhr;
			},
			success: function(data) {
				fileForm.reset();
				let message = {
					content: "I sent a file"
				};
				showMessage(message);
			},
			error: function(error) {
				// handle error
			},
			async: true,
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			timeout: 60000
		});
	});

	connect().then(() => {
		let nameCookie = readCookie("userName");
		if (nameCookie && nameCookie != "") {
			userName = nameCookie;
			$("#name").val(nameCookie);
			login();
		}
	});
});

function createCookie(name, value, days) {
	var expires;
	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		expires = "; expires=" + date.toGMTString();
	} else {
		expires = "";
	}
	document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value) + expires + "; path=/";
}

function readCookie(name) {
	var nameEQ = encodeURIComponent(name) + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) === ' ')
			c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) === 0)
			return decodeURIComponent(c.substring(nameEQ.length, c.length));
	}
	return null;
}

function eraseCookie(name) {
	createCookie(name, "", -1);
}

function uuidv4() {
	return ([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g, c =>
		(c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
	)
}

