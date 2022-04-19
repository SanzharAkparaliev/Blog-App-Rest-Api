package com.example.blogapp.service;

import com.example.blogapp.entities.Post;
import com.example.blogapp.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    Post updatePost(PostDto postDto,Integer postId);
    void deletePost(Integer postId);
    List<Post> getAllPost();
    Post getPostById(Integer postId);
    List<Post> getPostByCategory(Integer categoryId);
    List<Post> getPostByUser(Integer userId);
    List<Post> searchPosts(String keyword);

}
