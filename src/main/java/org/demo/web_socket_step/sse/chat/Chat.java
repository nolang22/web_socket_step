package org.demo.web_socket_step.sse.chat;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "SseChat")
@Table(name = "chat_sse_tb")
@Getter
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;

    @Column(nullable = false)
    private String message;

    @Builder
    public Chat(Long id, String sender, String message) {
        this.id = id;
        this.sender = sender;
        this.message = message;
    }
}
