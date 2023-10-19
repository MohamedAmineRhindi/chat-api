package com.demo.demoApi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Optional;

@Data
@AllArgsConstructor
public class UserDTO {

    private int id;
    private String name;
    private String email;
}
