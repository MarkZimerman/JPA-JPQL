package com.example.demo.repositories;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, String>{
	Stream<UserEntity> findAllByIdIn(List<String> list);
}
