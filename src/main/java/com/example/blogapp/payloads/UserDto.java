package com.example.blogapp.payloads;

import com.example.blogapp.entities.User;
import com.example.blogapp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto  {
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
