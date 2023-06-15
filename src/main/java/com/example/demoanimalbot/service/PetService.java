package com.example.demoanimalbot.service;

import com.example.demoanimalbot.model.pets.Cat;
import com.example.demoanimalbot.model.pets.Dog;
import com.example.demoanimalbot.model.pets.Status;
import com.example.demoanimalbot.model.users.UserCat;
import com.example.demoanimalbot.model.users.UserDog;
import com.example.demoanimalbot.repository.CatRepository;
import com.example.demoanimalbot.repository.DogRepository;
import com.example.demoanimalbot.repository.UserCatRepository;
import com.example.demoanimalbot.repository.UserDogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final CatRepository catRepository;
    private final DogRepository dogRepository;
    private final UserCatRepository userCatRepository;
    private final UserDogRepository userDogRepository;

    public PetService(CatRepository catRepository, DogRepository dogRepository, UserCatRepository userCatRepository, UserDogRepository userDogRepository) {
        this.catRepository = catRepository;
        this.dogRepository = dogRepository;
        this.userCatRepository = userCatRepository;
        this.userDogRepository = userDogRepository;
    }

    public Cat createCat(String name, int age, String breed) {
        Cat cat = new Cat(name, age, breed);
        return catRepository.save(cat);
    }

    public Dog createDog(String name, int age, String breed) {
        return dogRepository.save(new Dog(name, age, breed));
    }

    public Optional<Cat> findCat(long id) {
        return catRepository.findById(id);
    }

    public Optional<Dog> findDog(long id) {
        return dogRepository.findById(id);
    }

    /**
     * Метод позволяет забрать кота из приюта.
     * При этом меняется статус кота с SHELTER на PROBATION
     * И происходит заполнение поля user
     *
     * @param catId  - идентификатор кота, которого забирают из приюта
     * @param userId - идентификатор пользователя, который забирает кота
     * @return возвращает отредактированный объект класса Cat
     */
    public Cat takeCatAtHome(Long catId, Long userId) {
        Optional<Cat> cat = catRepository.findById(catId);
        Optional<UserCat> userCat = Optional.of(userCatRepository.findById(userId).orElse(new UserCat()));
        cat.get().setStatus(Status.PROBATION);
        cat.get().setUser(userCat.get());

        return catRepository.save(cat.get());
    }
    /**
     * Метод позволяет забрать собаку из приюта.
     * При этом меняется статус питомца с SHELTER на PROBATION
     * И происходит заполнение поля user
     *
     * @param dogId  - идентификатор собаки, которую забирают из приюта
     * @param userId - идентификатор пользователя, который забирает собаку
     * @return возвращает отредактированный объект класса Dog
     */
    public Dog takeDogAtHome(Long dogId, Long userId) {
        Optional<Dog> dog = dogRepository.findById(dogId);
        Optional<UserDog> userDog = Optional.of(userDogRepository.findById(userId).orElse(new UserDog()));
        dog.get().setStatus(Status.PROBATION);
        dog.get().setUser(userDog.get());

        return dogRepository.save(dog.get());
    }

    /**
     * Методы позволяют найти список питомцев, забранных Юзером из приюта
     *
     * @param userId идентификатор Юзера
     * @return список питомцев
     */
    public List<Cat> findCatsByUserId(long userId) {
        return catRepository.findByUserId(userId);
    }

    public List<Dog> findDogsByUserId(long userId) {
        return dogRepository.findByUserId(userId);
    }


}
