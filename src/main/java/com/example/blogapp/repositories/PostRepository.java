package com.example.blogapp.repositories;

import com.example.blogapp.entities.Category;
import com.example.blogapp.entities.Post;
import com.example.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post>findByCategory(Category category);
    @Query("select p from Post p where p.title like :keyWord")
    List<Post> searchByTitle(String keyWord);
}
