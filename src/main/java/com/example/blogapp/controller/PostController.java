package com.example.blogapp.controller;

import com.example.blogapp.impl.PostServiceImpl;
import com.example.blogapp.payloads.ApiResponse;
import com.example.blogapp.payloads.PostDto;
import com.example.blogapp.payloads.PostResponse;
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
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir
    ){
      PostResponse postResponse = postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId){
        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ApiResponse deletePostById(@PathVariable("postId") Integer postId){
        postService.deletePost(postId);
        return new ApiResponse("Post is successfully delete!!",true);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("postId") Integer postId){
        PostDto updatedPost = postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

   //search

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords")String keywords){
        List<PostDto> postDtos = postService.searchPosts(keywords);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
}
