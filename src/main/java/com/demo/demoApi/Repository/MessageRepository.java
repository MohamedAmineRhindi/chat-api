package com.demo.demoApi.Repository;

import com.demo.demoApi.Model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findBySenderIdAndReceiverId(int senderId, int receiverId);
}
