package com.example.crudapplication2.service;

import com.example.crudapplication2.dto.PostDto;
import com.example.crudapplication2.models.Post;
import com.example.crudapplication2.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements PostServiceInter {
    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(Long id, PostDto postdto) {

        Post post = postDtoToPost(postdto);
        Post savePost = postRepository.save(post);

        return postdto;
    }

    @Override
    public List<PostDto> getAllPostByUserId(Long id) {
        return null;
    }

    @Override
    public PostDto getPostByPostId(Long id) {
        Optional<Post> foundedPost = postRepository.findById(id);
        if (!foundedPost.isPresent()) {
            System.out.println("Post not found by id: " + id);
        }
        PostDto postDto = postToPostDto(foundedPost.get());
        return postDto;
    }

    @Override
    public void updatePostDescription(Long id, String description) {

    }

    @Override
    public void deletePostById(Long id) {
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isPresent()) {
            postRepository.deleteById(id);
        } else {
            System.out.println("Post not found by id: " + id);
        }
    }

    private Post postDtoToPost(PostDto postDto) {
        Post post = Post.builder()
                .description(postDto.getDescription())
                .user(postDto.getUser())
                .updateCount(0)
                .createDate(LocalDateTime.now())
                .build();

        return post;
    }

    private PostDto postToPostDto(Post post) {
        PostDto postDto = PostDto.builder()
                .description(post.getDescription())
                .user(post.getUser())
                .build();

        return postDto;
    }
}
