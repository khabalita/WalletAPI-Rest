package com.khabalita.springBoot.mapper;


import com.khabalita.springBoot.dto.UserDto;
import com.khabalita.springBoot.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;

    public User userDtoToUser(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto UserToUserDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
