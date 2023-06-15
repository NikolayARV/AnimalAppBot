package com.example.demoanimalbot.repository;

import com.example.demoanimalbot.model.pets.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findByUserId(long userId);
}
