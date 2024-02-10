package com.example.crudapplication2.contoller;

import com.example.crudapplication2.dto.PostDto;
import com.example.crudapplication2.dto.UserDto;
import com.example.crudapplication2.models.Post;
import com.example.crudapplication2.service.PostServiceInter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostServiceInter postServiceInter;

    public PostController(PostServiceInter postServiceInter) {
        this.postServiceInter = postServiceInter;
    }

    @PostMapping("{id}")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") Long userid) {
        PostDto createdPost = postServiceInter.createPost(userid, postDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostByPostId(@PathVariable(name = "id") Long id) {
        PostDto postByPostId = postServiceInter.getPostByPostId(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(postByPostId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePosById(@PathVariable(name = "id") Long id) {
        postServiceInter.deletePostById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
