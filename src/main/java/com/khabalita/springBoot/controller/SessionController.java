package com.khabalita.springBoot.controller;

import com.khabalita.springBoot.dto.SessionDto;
import com.khabalita.springBoot.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/createSession")
    public ResponseEntity<?> createSession(@RequestBody SessionDto sessionDto) throws Exception{
        sessionService.newSession(sessionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getOne/{id}")
    @ResponseBody
    public ResponseEntity<?> getSession(@PathVariable Long id) throws Exception{
        SessionDto sessionDto = sessionService.findSessionById(id);
        if(sessionDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(sessionDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

    @GetMapping("/listSessions")
    @ResponseBody
    public ResponseEntity<?> getAllSessions() throws Exception{
        List<SessionDto> sessionDtoList = sessionService.listAllSession();
        return ResponseEntity.status(HttpStatus.OK).body(sessionDtoList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSession(@PathVariable Long id, @RequestBody SessionDto sessionDto) throws Exception{
        SessionDto updatedSession = sessionService.updateSession(id, sessionDto);
        if(updatedSession != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedSession);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\":\"Session not found}");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable Long id) throws Exception{
        boolean deleted = sessionService.deleteSession(id);
        if(deleted){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("/Session not found");
        }
    }
}
