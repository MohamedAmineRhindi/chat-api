package com.demo.demoApi.Mapper;

import com.demo.demoApi.Model.Message;
import com.demo.demoApi.Model.MessageDTO;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public MessageDTO mapToDTO(Message message) {
        return new MessageDTO(
                message.getId(),
                message.getSenderId(),
                message.getReceiverId(),
                message.getContent(),
                message.getTimestamp(),
                message.isRead()
        );
    }

    public Message mapToMessage(MessageDTO messageDTO) {
        return new Message(
                messageDTO.getSenderId(),
                messageDTO.getReceiverId(),
                messageDTO.getContent()
        );
    }
}
