package com.example.demoanimalbot.service;

import com.example.demoanimalbot.model.users.UserCat;
import com.example.demoanimalbot.model.users.UserDog;
import com.example.demoanimalbot.repository.UserCatRepository;
import com.example.demoanimalbot.repository.UserDogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserCatRepository userCatRepositoryMock;
    @Mock
    private UserDogRepository userDogRepositoryMock;

    @InjectMocks
    private UserService out;
    UserDog userDog = new UserDog("b", "m", "d");
    UserCat userCat = new UserCat("b", "m", "d");

    @Test
    void   createUserDog() {
        when(userDogRepositoryMock.save(userDog))
                .thenReturn(userDog);
        assertEquals(out.createUserDog("b", "m", "d"), userDog);
        verify(userDogRepositoryMock, times(1)).save(new UserDog("b", "m", "d"));
    }

    @Test
    void findUserDog() {
        userDog.setId(1l);
        when(userDogRepositoryMock.findById(1l))
                .thenReturn(Optional.of(userDog));
        assertEquals(out.findUserDog(1l), Optional.of(userDog));
        verify(userDogRepositoryMock, times(1)).findById(1l);
    }

    @Test
    void createUserCat() {
        when(userCatRepositoryMock.save(userCat))
                .thenReturn(userCat);
        assertEquals(out.createUserCat("b", "m", "d"), userCat);
        verify(userCatRepositoryMock, times(1)).save(new UserCat("b", "m", "d"));
    }

    @Test
    void findUserCat() {
        userCat.setId(1l);
        when(userCatRepositoryMock.findById(1l))
                .thenReturn(Optional.of(userCat));
        assertEquals(out.findUserCat(1l), Optional.of(userCat));
        verify(userCatRepositoryMock, times(1)).findById(1l);
    }
}