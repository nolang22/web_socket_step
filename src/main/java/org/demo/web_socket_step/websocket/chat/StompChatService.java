package org.demo.web_socket_step.websocket.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StompChatService {

    private final StompChatRepository stompChatRepository;

    // 1. 스프링에서 제공하는 메세지 전동 도구 ()
    // - 특정 경로(/sum/...)를 구독하고 있는 클라이언트에게 메세지를 푸시(방송)할 수 있습니다.
    private final SimpMessagingTemplate simpMessagingTemplate;

    // 채팅 비즈니스 로직 --> 저장하고 뿌린다.
    public void saveAndBroadcast(String message, String sender) {
        Chat chat = Chat.builder()
                .message(message)
                .sender(sender)
                .build();
        stompChatRepository.save(chat);
        // json --> 우리만에 형식 --> sender:내용
        String formattedMessage = sender + ":" + message;

        simpMessagingTemplate.convertAndSend("/sub/chat/room1", formattedMessage);
    }

    // 전체 조회
    public List<Chat> findAll() {
        return stompChatRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

}
