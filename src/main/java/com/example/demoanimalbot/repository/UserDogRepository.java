package com.example.demoanimalbot.repository;

import com.example.demoanimalbot.model.UserDog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDogRepository extends JpaRepository<UserDog, Long> {
}