package com.example.demoanimalbot.service;

import com.example.demoanimalbot.model.UserCat;
import com.example.demoanimalbot.model.UserDog;
import com.example.demoanimalbot.repository.UserCatRepository;
import com.example.demoanimalbot.repository.UserDogRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserCatRepository userCatRepository;
    private final UserDogRepository userDogRepository;

    public UserService(UserCatRepository userCatRepository, UserDogRepository userDogRepository) {
        this.userCatRepository = userCatRepository;
        this.userDogRepository = userDogRepository;
    }

    /**
     * метод для создания пользователя приюта для собак
     * @param name - имя пользователя
     * @param email - электронный адрес пользователя
     * @param phoneNumber - телефонный номер
     * @return - позвращает созданный объект
     */
    public UserDog createUserDog(String name, String email, String phoneNumber) {
        return userDogRepository.save(new UserDog(name, email, phoneNumber));
    }

    /**
     * метод для поиска пользователя приюта для собак по идентификатору
     * @param id - идентификатор пользователя в БД
     * @return - объект
     */
    public Optional<UserDog> findUserDog(long id) {
        return userDogRepository.findById(id);
    }
    /**
     * метод для создания пользователя приюта для котов
     * @param name - имя пользователя
     * @param email - электронный адрес пользователя
     * @param phoneNumber - телефонный номер
     * @return - позвращает созданный объект
     */
    public UserCat createUserCat(String name, String email, String phoneNumber) {
        return userCatRepository.save(new UserCat(name, email, phoneNumber));
    }
    /**
     * метод для поиска пользователя приюта для кошек по идентификатору
     * @param id - идентификатор пользователя в БД
     * @return - объект
     */
    public Optional<UserCat> findUserCat(long id) {
        return userCatRepository.findById(id);
    }
}
