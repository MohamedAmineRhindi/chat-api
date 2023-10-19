package com.demo.demoApi.Service;

import com.demo.demoApi.Exception.ResourceNotFoundException;
import com.demo.demoApi.Mapper.MessageMapper;
import com.demo.demoApi.Model.Message;
import com.demo.demoApi.Model.MessageDTO;
import com.demo.demoApi.Repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageMapper messageMapper;

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;

    public MessageDTO addMessage(MessageDTO messageDTO) {
        int senderId = messageDTO.getSenderId();
        int receiverId = messageDTO.getReceiverId();
        if (userService.userExists(senderId)) {
            throw new ResourceNotFoundException("User %d (sender) not found".formatted(senderId));
        }
        if (userService.userExists(receiverId)) {
            throw new ResourceNotFoundException("User %d (receiver) not found".formatted(receiverId));
        }
        Message message = messageRepository.save(messageMapper.mapToMessage(messageDTO));
        return messageMapper.mapToDTO(message);
    }

    public List<MessageDTO> getDiscussion(int userId, int contactId) {
        List<MessageDTO> sentMessages = messageRepository.findBySenderIdAndReceiverId(userId, contactId).stream().map(messageMapper::mapToDTO).toList();
        List<MessageDTO> receivedMessages = messageRepository.findBySenderIdAndReceiverId(contactId, userId).stream().map(messageMapper::mapToDTO).toList();
        receivedMessages.forEach(messageDTO -> {
            messageDTO.setRead(true);
            messageRepository.save(messageMapper.mapToMessage(messageDTO));
        });
        return Stream.concat(sentMessages.stream(), receivedMessages.stream()).sorted(Comparator.comparing(MessageDTO::getTimestamp)).toList();
    }

    public int getDiscussionNewMessagesCount(int userId, int contactId) {
        return messageRepository.findBySenderIdAndReceiverId(contactId, userId).stream().filter(message -> !message.isRead()).toList().size();
    }
}
