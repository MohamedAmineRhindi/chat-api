package com.demo.demoApi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDTO {

    private int id;
    private int senderId;
    private int receiverId;
    private String content;
    private Long timestamp;
    private boolean isRead;
}
