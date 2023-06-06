package com.example.demoanimalbot.repository;

import com.example.demoanimalbot.model.Cat;
import com.example.demoanimalbot.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {
    List<Dog> findByUserId(long userId);
}
