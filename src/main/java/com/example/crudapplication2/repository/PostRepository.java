package com.example.crudapplication2.repository;
import com.example.crudapplication2.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {



}
