package com.example.demoanimalbot.controller;

import com.example.demoanimalbot.model.users.UserCat;
import com.example.demoanimalbot.model.users.UserDog;
import com.example.demoanimalbot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
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

    @Operation(summary = "Создание в базе данных владельца Cat",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Создан владелец Cat",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "userCat"
    )
    @PostMapping("/userCat")
    public UserCat createUserCat(String name, String email, String phoneNumber) {
        return userService.createUserCat(name, email, phoneNumber);
    }
    @Operation(summary = "Создание в базе данных владельца Dog",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Создан владелец Dog",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "userDog"
    )
    @PostMapping("/userDog")
    public UserDog createUserDog(String name, String email, String phoneNumber) {
        return userService.createUserDog(name, email, phoneNumber);
    }

    @Operation(summary = "Поиск владельца Cat в базе данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Искомый владелец Cat по идентификатору",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "userCat"
    )
    /** Контроллер для поиска пользователя приюта для котов по идентификатору
     * @param id - идентификатор пользователя в БД
     * @return объект
     */
    @GetMapping("/userCat")
    public Optional<UserCat> findUserCatById(long id) {
        return userService.findUserCat(id);
    }

    @Operation(summary = "Поиск владельца Dog в базе данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Искомый владелец Dog по идентификатору",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "userDog"
    )
    /** Контроллер для поиска пользователя приюта для собак по идентификатору
     * @param id - идентификатор пользователя в БД
     * @return объект
     */
    @GetMapping("/userDog")
    public Optional<UserDog> findUserDogById(long id) {
        return userService.findUserDog(id);
    }

}
