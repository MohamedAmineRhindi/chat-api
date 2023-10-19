package com.demo.demoApi.Controller;

import com.demo.demoApi.Model.MessageDTO;
import com.demo.demoApi.Service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public MessageDTO addMessage(@RequestBody MessageDTO messageDTO) {
        return messageService.addMessage(messageDTO);
    }

    @GetMapping(value = "/discussion", produces = "application/json")
    public List<MessageDTO> getDiscussion(@RequestParam(name = "user_id") int userId, @RequestParam(name = "contact_id") int contactId) {
        return messageService.getDiscussion(userId, contactId);
    }

    @GetMapping(value = "/discussion-new-messages-count", produces = "application/json")
    public int getDiscussionNewMessagesCount(@RequestParam(name = "user_id") int userId, @RequestParam(name = "contact_id") int contactId) {
        return messageService.getDiscussionNewMessagesCount(userId, contactId);
    }

}
