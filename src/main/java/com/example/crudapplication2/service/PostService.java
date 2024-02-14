package com.example.crudapplication2.service;

import com.example.crudapplication2.dto.PostDto;
import com.example.crudapplication2.dto.UserDto;
import com.example.crudapplication2.models.Post;
import com.example.crudapplication2.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService implements PostServiceInter {
    private final PostRepository postRepository;
    private final UserServiceInter userServiceInter;

    public PostService(PostRepository postRepository, UserServiceInter userServiceInter) {
        this.postRepository = postRepository;
        this.userServiceInter = userServiceInter;
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public PostDto createPost(Long id, PostDto postdto) {
        UserDto userById = userServiceInter.getUserById(id);
        postdto.setUser(userById);

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

    public static Post postDtoToPost(PostDto postDto) {
        Post post = Post.builder()
                .description(postDto.getDescription())
                .user( UserService.userDtoToUser(postDto.getUser()))
                .updateCount(0)
                .createDate(LocalDateTime.now())
                .build();

        return post;
    }

    public static List<Post> ListPostDtoToPostList(List<PostDto> postDtoList){
//        List<Post> postList = new ArrayList<>();

        List<Post> list = postDtoList.stream().map(PostService::postDtoToPost).toList();
        return list;
    }

    public static PostDto postToPostDto(Post post) {
        PostDto postDto = PostDto.builder()
                .description(post.getDescription())
                .user(UserService.userToUserDto(post.getUser()))
                .build();

        return postDto;
    }
}
