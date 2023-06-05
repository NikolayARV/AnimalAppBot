package com.example.demoanimalbot.controller;

import com.example.demoanimalbot.model.*;
import com.example.demoanimalbot.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/userCat")
    public UserCat createUserCat(String name, String email, String phoneNumber) {
        return userService.createUserCat(name, email, phoneNumber);
    }

    @PostMapping("/userDog")
    public UserDog createUserDog(String name, String email, String phoneNumber) {
        return userService.createUserDog(name, email, phoneNumber);
    }

    /** Контроллер для поиска пользователя приюта для котов по идентификатору
     * @param id - идентификатор пользователя в БД
     * @return объект
     */
    @GetMapping("/userCat")
    public Optional<UserCat> findUserCatById(long id) {
        return userService.findUserCat(id);
    }

    /** Контроллер для поиска пользователя приюта для собак по идентификатору
     * @param id - идентификатор пользователя в БД
     * @return объект
     */
    @GetMapping("/userDog")
    public Optional<UserDog> findUserDogById(long id) {
        return userService.findUserDog(id);
    }
}
