package com.khabalita.springBoot.mapper;

import com.khabalita.springBoot.dto.SessionDto;
import com.khabalita.springBoot.entities.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Session sessionDtoToSession(SessionDto sessionDto){
        Session session = modelMapper.map(sessionDto, Session.class);
        return session;
    }

    public SessionDto sessionToSessionDto(Session session){
        SessionDto sessionDto = modelMapper.map(session, SessionDto.class);
        return sessionDto;
    }
}
