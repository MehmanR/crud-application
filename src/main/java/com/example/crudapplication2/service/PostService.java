package com.example.crudapplication2.service;

import com.example.crudapplication2.dto.PostDto;
import com.example.crudapplication2.models.Post;

import java.util.List;

public class PostService implements PostServiceInter{
    @Override
    public PostDto createPost(Long id, Post post) {
        return null;
    }

    @Override
    public List<PostDto> getAllPostByUserId(Long id) {
        return null;
    }

    @Override
    public PostDto getPostByPostId(Long id) {
        return null;
    }

    @Override
    public void updatePostDescription(Long id, String description) {

    }

    @Override
    public void deletePostById(Long id) {

    }
}
