package com.example.blogapp.impl;

import com.example.blogapp.entities.Category;
import com.example.blogapp.entities.Post;
import com.example.blogapp.entities.User;
import com.example.blogapp.exceptions.ResourseNotFoundException;
import com.example.blogapp.payloads.PostDto;
import com.example.blogapp.repositories.CategoryRepository;
import com.example.blogapp.repositories.PostRepository;
import com.example.blogapp.repositories.UserRepository;
import com.example.blogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourseNotFoundException("User","UserId",userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourseNotFoundException("Category","CategoryId",categoryId));
        Post post = modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);

        Post newPost = postRepository.save(post);

        return modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
       Post post = postRepository.findById(postId).orElseThrow(()->new ResourseNotFoundException("Post","postId",postId));
       post.setTitle(postDto.getTitle());
       post.setContent(postDto.getContent());
       post.setImageName(postDto.getImageName());
       Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourseNotFoundException("Post"," postId",postId));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Post> postPage  = postRepository.findAll(pageable);
        List<Post> allpost = postPage.getContent();
        List<PostDto> postDtos = allpost.stream().map((post) -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourseNotFoundException("Post"," postId",postId));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                ()->new ResourseNotFoundException("Category","CategoryId",categoryId));

        List<Post> posts = postRepository.findByCategory(category);

        List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new ResourseNotFoundException("User"," userId",userId));

        List<Post> posts = postRepository.findByUser(user);

        List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
