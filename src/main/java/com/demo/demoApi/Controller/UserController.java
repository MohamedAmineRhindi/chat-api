package com.demo.demoApi.Controller;

import com.demo.demoApi.Model.UserDTO;
import com.demo.demoApi.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = "application/json")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public UserDTO updateUser(@PathVariable int id, @RequestBody Map<String, String> userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
