package com.example.blogapp.service;

import com.example.blogapp.entities.Comment;
import com.example.blogapp.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId);
}
