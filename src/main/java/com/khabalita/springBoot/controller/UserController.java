package com.khabalita.springBoot.controller;

import com.khabalita.springBoot.dto.UserDto;
import com.khabalita.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws Exception{
        userService.newUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getOne/{id}")
    @ResponseBody
    public ResponseEntity<?> getUser(@PathVariable Long id) throws Exception{
        UserDto userDto = userService.findUserById(id);
        if(userDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

    @GetMapping("/listUsers")
    @ResponseBody
    public ResponseEntity<?> getAllUsers() throws Exception{
        List<UserDto> userDtoList = userService.listAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) throws Exception{
        UserDto updatedUser = userService.updateUser(id, userDto);
        if(updatedUser != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\":\"User not found}");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws Exception{
        boolean deleted = userService.deleteUser(id);
        if(deleted){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("/User not found");
        }
    }
}
