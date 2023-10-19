package com.demo.demoApi.Service;

import com.demo.demoApi.Mapper.MessageMapper;
import com.demo.demoApi.Model.Message;
import com.demo.demoApi.Model.MessageDTO;
import com.demo.demoApi.Repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageMapper messageMapper;

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;

    public MessageDTO addMessage(MessageDTO messageDTO) {
        if(userService.userExists(messageDTO.getSenderId()) || userService.userExists(messageDTO.getReceiverId())) {
            throw new IllegalArgumentException("User does not exist");
        }
        Message message = messageRepository.save(messageMapper.mapToMessage(messageDTO));
        return messageMapper.mapToDTO(message);
    }

    public List<MessageDTO> getDiscussion(int userId, int contactId) {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(message -> {
            if(message.getSenderId() == userId && message.getReceiverId() == contactId ||
                    message.getSenderId() == contactId && message.getReceiverId() == userId) {
                messages.add(message);
            }
        });
        messages.sort(Comparator.comparing(Message::getTimestamp));
        return messages.stream().map(messageMapper::mapToDTO).toList();
    }
}
