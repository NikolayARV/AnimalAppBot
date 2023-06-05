package com.example.demoanimalbot.controller;

import com.example.demoanimalbot.model.Cat;
import com.example.demoanimalbot.model.Dog;
import com.example.demoanimalbot.model.Status;
import com.example.demoanimalbot.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @return возвращает объект Собакааиптп
     */
    @PostMapping("/dog")
    public Dog createDog(@Parameter(description = "Имя питомца") @RequestParam(required = true) String name,
                         @Parameter(description = "возраст питомца") @RequestParam(required = true) int age,
                         @Parameter(description = "Порода собаки") @RequestParam(required = true) String breed) {
        return petService.createDog(name, age, breed);
    }

    /**
     * Контроллер для поиска кота по идентификатору
     *
     * @param catId - идентификатор кота
     * @return - объект Кот
     */
    @GetMapping("/cat")
    public Optional<Cat> findCotById(long catId) {
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
    public Optional<Dog> findDogById(@Parameter(description = "Идентификатор объекта Dog", example = "1") long dogId) {
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
    public Dog takeDogAtHome(@Parameter(description = "Идентификатор объекта Dog") Long dogId,
                             @Parameter(description = "Идентификатор объекта UserDog") Long userId) {
        return petService.takeDogAtHome(dogId, userId);
    }

    /**
     * Контроллеры позволяют найти список кошек, забранных Юзером из приюта
     *
     * @param userId идентификатор Юзера
     * @return список питомцев
     */
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

