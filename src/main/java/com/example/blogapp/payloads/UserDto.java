package com.example.blogapp.payloads;

import com.example.blogapp.entities.User;
import com.example.blogapp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto  {
    private int id;
    @NotEmpty
    @Size(min = 4,message = "username must be min of 4 characters!!")
    private String name;
    @Email(message = "Email address is not valid !!")
    private String email;
    @NotEmpty
    @Size(min = 3,max = 10,message = "Password must be min of 3 chars and max of 10 chars !!")
    private String password;
    @NotNull
    private String about;
}
