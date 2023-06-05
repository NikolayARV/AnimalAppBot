package com.example.demoanimalbot.controller;

import com.example.demoanimalbot.model.Cat;
import com.example.demoanimalbot.model.Dog;
import com.example.demoanimalbot.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @Operation(summary = "Создание в базе данных объекта Cat",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Созданный объект Cat",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "Cat"
    )
    /**
     * Контроллер для создания объекта Кот
     *
     * @param name  - имя кота
     * @param age - возраст кота
     * @param breed - порода кота
     * @return возвращает объект Кот
     */
    @PostMapping("/cat")
    public Cat createCat(@Parameter(description = "Имя питомца") @RequestParam(required = true) String name,
                         @Parameter(description = "Возраст питомца") @RequestParam(required = false) int age,
                         @Parameter(description = "Порода питомца") @RequestParam(required = true) String breed) {
        return petService.createCat(name, age, breed);
    }
    @Operation(summary = "Создание в базе данных объекта Dog",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Созданный объект Dog",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "Dog")
    /**
     * Контроллер для создания объекта Собака
     *
     * @param name  - имя собаки
     * @param age   - возраст собаки
     * @param breed - порода собаки
     * @return возвращает объект Собака
     */
    @PostMapping("/dog")
    public Dog createDog(String name, int age, String breed) {
        return petService.createDog(name, age, breed);
    }

    @Operation(summary = "Поиск объекта Cat в базе данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Искомый объект Cat по идентификатору",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "Cat"
    )
    /**
     * Контроллер для поиска кота по идентификатору
     *
     * @param catId - идентификатор кота
     * @return - объект Кот
     */
    @GetMapping("/cat")
    public Optional<Cat> findCatById(@Parameter (description = "Идентификатор объекта Cat", example = "1") long catId) {
        return petService.findCat(catId);
    }
    @Operation(summary = "Поиск объекта Dog в базе данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Искомый объект Dog по идентификатору",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "Dog"
    )
    /**
     * Контроллер для поиска собаки по идентификатору
     *
     * @param dogId - идентификатор собаки
     * @return - объект Собака
     */
    @GetMapping("/dog")
    public Optional<Dog> findDogById(long dogId) {
        return petService.findDog(dogId);
    }

    @Operation(summary = "Усыновление объекта Cat и внесение соответствующих изменений в базу данных: " +
            "изменение статуса и заполнение поля user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Объект Cat с измененным статусом и с заполненным полем user",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "Cat"
    )
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
    public Cat takeCatAtHome(@Parameter(description = "Идентификатор объекта Cat") Long catId,
                             @Parameter(description = "Идентификатор объекта UserCat") Long userId) {
        return petService.takeCatAtHome(catId, userId);
    }
    @Operation(summary = "Усыновление объекта Dog и внесение соответствующих изменений в базу данных: " +
            "изменение статуса и заполнение поля user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Объект Dog с измененным статусом и с заполненным полем user",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "Dog"
    )
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
    @Operation(summary = "Поиск Dog в базе данных по идентификатору владельца",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Искомый Dog по идентификатору владельца",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "user-dogs"
    )
    @GetMapping("/user-dogs")
    public List<Dog> findDogsByUserId(long userId) {
        return petService.findDogsByUserId(userId);
    }
}

