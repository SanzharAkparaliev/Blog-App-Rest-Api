package com.example.blogapp.payloads;

import com.example.blogapp.entities.Category;
import com.example.blogapp.entities.Comment;
import com.example.blogapp.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedData;
    private CategoryDto category;
    private UserDto user;
    private Set<CommentDto> comments = new HashSet<>();
}
