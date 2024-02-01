package com.example.crudapplication2.service;

import com.example.crudapplication2.dto.PostDto;
import com.example.crudapplication2.models.Post;

import java.util.List;

public interface PostServiceInter {
    String createPost(Long id, Post post);
    List<PostDto> getAllPostByUserId(Long id);
    PostDto getPostByPostId(Long id);
    void updatePostDescription(Long id, String description);
    void deletePostById(Long id);
}
