package com.example.blogapp.controller;

import com.example.blogapp.payloads.PostDto;
import com.example.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId,@PathVariable("categoryId") Integer categoryId){
       PostDto newPost = postService.createPost(postDto,userId,categoryId);
       return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }
}
