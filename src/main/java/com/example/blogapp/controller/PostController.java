package com.example.blogapp.controller;

import com.example.blogapp.impl.PostServiceImpl;
import com.example.blogapp.payloads.PostDto;
import com.example.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostServiceImpl postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId,@PathVariable("categoryId") Integer categoryId){
       PostDto newPost = postService.createPost(postDto,userId,categoryId);
       return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId){
        List<PostDto> posts = postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") Integer categoryId){
        List<PostDto> posts = postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPost(){
        List<PostDto> postDtos = postService.getAllPost();
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId){
        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
}
