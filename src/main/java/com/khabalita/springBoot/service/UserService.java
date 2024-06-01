package com.khabalita.springBoot.service;


import com.khabalita.springBoot.dto.UserDto;
import com.khabalita.springBoot.entities.User;
import com.khabalita.springBoot.mapper.UserMapper;
import com.khabalita.springBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public User newUser(UserDto userDto) throws Exception{
        try{
            User user = userMapper.userDtoToUser(userDto);
            User savedUser = userRepository.save(user);
            return savedUser;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<UserDto> listAllUsers() throws Exception{
        try{
            List<User> userList = userRepository.findAll();
            List<UserDto> userDtoList = new ArrayList<>();
            for(User user: userList){
                userDtoList.add(userMapper.authorToAuthorDto(user));
            }
            return userDtoList;
        } catch (Exception e) {
            throw new Exception (e.getMessage());
        }
    }

    public UserDto updateUser(Long id, UserDto userDto) throws Exception {
        try{
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new Exception("ID de user no encontrado" + id));
            existingUser.setName(userDto.getName());
            existingUser.setEmail(userDto.getEmail());
            return userMapper.authorToAuthorDto(existingUser);
        }catch(Exception e){
            throw new Exception ("No se pudo actualizar el usuario" + e.getMessage());
        }
    }

    public boolean deleteUser(Long id) throws Exception{
        try{
            if(userRepository.existsById(id)){
                userRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("ID not found" + id);
            }
        }catch (Exception e){
            throw new Exception ("No se pudo eliminar el user" + e.getMessage());
        }
    }

    public UserDto findUserById(Long id) throws Exception{
        try{
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new Exception("ID de autor no encontrado" + id));
            return userMapper.authorToAuthorDto(user);
        }catch (Exception e){
            throw new Exception("No se pudo traer el autor" + e.getMessage());
        }
    }
}
