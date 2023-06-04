package com.example.demoanimalbot.controller;

import com.example.demoanimalbot.model.Cat;
import com.example.demoanimalbot.model.Dog;
import com.example.demoanimalbot.model.Status;
import com.example.demoanimalbot.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    /**
     * Контроллер для создания объекта Кот
     *
     * @param name  - имя кота
     * @param age - возраст кота
     * @param breed - порода кота
     * @return возвращает объект Кот
     */
    @PostMapping("/cat")
    public Cat createCat(String name, int age, String breed) {
        return petService.createCat(name, age, breed);
    }
    /**
     * Контроллер для создания объекта Собака
     *
     * @param name  - имя собаки
     * @param age - возраст собаки
     * @param breed - порода собаки
     * @return возвращает объект Собака
     */
    @PostMapping("/dog")
    public Dog createDog(String name, int age, String breed) {
        return petService.createDog(name, age, breed);
    }

    /**
     * Контроллер для поиска кота по идентификатору
     * @param catId - идентификатор кота
     * @return - объект Кот
     */
    @GetMapping("/cat")
    public Optional<Cat> findCotById(long catId) {
        return petService.findCat(catId);
    }
    /**
     * Контроллер для поиска собаки по идентификатору
     * @param dogId - идентификатор собаки
     * @return - объект Собака
     */
    @GetMapping("/dog")
    public Optional<Dog> findDogById(long dogId) {
        return petService.findDog(dogId);
    }
    /**
     * Контроллер позволяет забрать кота из приюта.
     * При этом меняется статус кота с SHELTER на PROBATION
     * И происходит заполнение поля user
     *
     * @param catId  - идентификатор кота, которого забирают из приюта
     * @param userId - идентификатор пользователя, который забирает кота
     * @return возвращает отредактированный объект класса Cat
     */
    @PutMapping("/cat")
    public Cat takeCatAtHome(Long catId, Long userId) {
        return petService.takeCatAtHome(catId, userId);
    }
    /**
     * Контроллер позволяет забрать собаку из приюта.
     * При этом меняется статус питомца с SHELTER на PROBATION
     * И происходит заполнение поля user
     *
     * @param dogId  - идентификатор собаки, которую забирают из приюта
     * @param userId - идентификатор пользователя, который забирает собаку
     * @return возвращает отредактированный объект класса Dog
     */
    @PutMapping("/dog")
    public Dog takeDogAtHome(Long dogId, Long userId) {
        return petService.takeDogAtHome(dogId, userId);
    }

    /**
     * Контроллеры позволяют найти список кошек, забранных Юзером из приюта
     *
     * @param userId идентификатор Юзера
     * @return список питомцев
     */
    @Operation(summary = "Поиск Cat в базе данных по идентификатору владельца",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Искомый Cat по идентификатору владельца",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "user-cats"
    )
    @GetMapping("/user-cats")
    public List<Cat> findCatsByUserId(long userId) {
        return petService.findCatsByUserId(userId);
    }
    /**
     * Контроллеры позволяют найти список собак, забранных Юзером из приюта
     *
     * @param userId идентификатор Юзера
     * @return список питомцев
     */
    @GetMapping("/user-dogs")
    public List<Dog> findDogsByUserId(long userId) {
        return petService.findDogsByUserId(userId);
    }
}

