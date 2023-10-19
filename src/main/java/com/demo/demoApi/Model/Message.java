package com.demo.demoApi.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "chat_messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="message_id", nullable = false, unique = true, updatable = false, columnDefinition = "int")
    private int id;

    @Column(name="sender_id", nullable = false, columnDefinition = "int")
    private int senderId;

    @Column(name="receiver_id", nullable = false, columnDefinition = "int")
    private int receiverId;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "timestamp", nullable = false, columnDefinition = "TIMESTAMP")
    private Long timestamp;

    @Column(name = "is_read", nullable = false, columnDefinition = "BOOLEAN")
    private boolean isRead;

    public Message(int senderId, int receiverId, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = new Date().getTime();
        this.isRead = false;
    }
}
