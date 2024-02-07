package com.example.crudapplication2.service;

import com.example.crudapplication2.dto.PostDto;
import com.example.crudapplication2.models.Post;

import java.util.List;

public interface PostServiceInter {
    PostDto createPost(Long id, PostDto post);
    List<PostDto> getAllPostByUserId(Long id);
    PostDto getPostByPostId(Long id);
    void updatePostDescription(Long id, String description);
    void deletePostById(Long id);
}
