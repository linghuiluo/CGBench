package onlinechat.model;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepository extends PagingAndSortingRepository<UserMessage, Long> {

	List<UserMessage> findFirst15ByOrderByCreatedDesc();

	List<UserMessage> findFirst15ByChannelOrderByCreatedDesc(String channel);
}
