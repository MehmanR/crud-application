package com.example.crudapplication2.repository;

import com.example.crudapplication2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {




}
