package com.khabalita.springBoot.service;


import com.khabalita.springBoot.dto.UserDto;
import com.khabalita.springBoot.entities.User;
import com.khabalita.springBoot.mapper.UserMapper;
import com.khabalita.springBoot.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


        @Mock
        private UserRepository userRepository;

        @Mock
        private UserMapper userMapper;

        @InjectMocks
        private UserService userService;

        private User user;
        private UserDto userDto;

        //Carga de datos para User y UserDto
        @BeforeEach
        void setUsers() {
            user = User.builder()
                    .id(1L)
                    .name("nico")
                    .email("nico@gmail.com")
                    .password("12345")
                    .build();

            userDto = UserDto.builder()
                    .id(1L)
                    .name("nico")
                    .email("nico@gmail.com")
                    .password("12345")
                    .build();
        }

        @Test
        void testNewUser() throws Exception {
            when(userMapper.userDtoToUser(any(UserDto.class))).thenReturn(user);
            when(userRepository.save(any(User.class))).thenReturn(user);

            User result = userService.newUser(userDto);

            assertNotNull(result);
            assertEquals("nico", result.getName());
            assertEquals("nico@gmail.com", result.getEmail());
            assertEquals("12345", result.getPassword());
        }

        @Test
        void testListAllUsers() throws Exception {
            List<User> users = new ArrayList<>();
            users.add(user);
            when(userRepository.findAll()).thenReturn(users);
            when(userMapper.UserToUserDto(any(User.class))).thenReturn(userDto);

            List<UserDto> result = userService.listAllUsers();

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals("nico", result.get(0).getName());
            assertEquals("nico@gmail.com", result.get(0).getEmail());
            assertEquals("12345", result.get(0).getPassword());
        }

        @Test
        void testUpdateUser() throws Exception {
            when(userRepository.findById(1L)).thenReturn(Optional.of(user));
            when(userMapper.UserToUserDto(any(User.class))).thenReturn(userDto);

            UserDto result = userService.updateUser(1L, userDto);

            assertNotNull(result);
            assertEquals("nico", result.getName());
            assertEquals("nico@gmail.com", result.getEmail());
            assertEquals("12345", result.getPassword());
        }

        @Test
        void testDeleteUser() throws Exception {
            when(userRepository.existsById(1L)).thenReturn(true);

            boolean result = userService.deleteUser(1L);

            assertTrue(result);
        }

        @Test
        void testFindUserById() throws Exception {
            when(userRepository.findById(1L)).thenReturn(Optional.of(user));
            when(userMapper.UserToUserDto(any(User.class))).thenReturn(userDto);

            UserDto result = userService.findUserById(1L);

            assertNotNull(result);
            assertEquals("nico", result.getName());
            assertEquals("nico@gmail.com", result.getEmail());
            assertEquals("12345", result.getPassword());
        }
}
