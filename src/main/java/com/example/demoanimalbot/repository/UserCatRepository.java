package com.example.demoanimalbot.repository;

import com.example.demoanimalbot.model.users.UserCat;
import com.example.demoanimalbot.model.users.UserDog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCatRepository extends JpaRepository<UserCat, Long> {
    UserCat findByChatId(long chatId);

}
