package com.demo.demoApi.Service;

import com.demo.demoApi.Exception.BadRequestException;
import com.demo.demoApi.Exception.ResourceNotFoundException;
import com.demo.demoApi.Model.User;
import com.demo.demoApi.Model.UserDTO;
import com.demo.demoApi.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getUsers() {
        List<UserDTO> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail());
            users.add(userDTO);
        });
        return users;
    }

    public UserDTO addUser(UserDTO userDTO) {
        if (userDTO.getEmail() == null || userDTO.getName() == null)
            throw new BadRequestException("Invalid request body");
        User user;
        try {
            user = userRepository.save(new User(userDTO.getName(), userDTO.getEmail()));
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Email already exists");
        }
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    public UserDTO updateUser(int id, Map<String, String> userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User %d not found".formatted(id)));
        if (userDTO.isEmpty()) throw new BadRequestException("Invalid request body");
        if (userDTO.containsKey("id")) throw new BadRequestException("Cannot update id");
        try {
            user.setName(userDTO.getOrDefault("name", user.getName()));
            user.setEmail(userDTO.getOrDefault("email", user.getEmail()));
        } catch (ClassCastException e) {
            throw new BadRequestException("Invalid data type");
        }
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Email already exists");
        }
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public boolean userExists(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.isEmpty();
    }
}
