package com.khabalita.springBoot.service;

import com.khabalita.springBoot.dto.SessionDto;
import com.khabalita.springBoot.dto.TransactionDto;
import com.khabalita.springBoot.entities.Session;
import com.khabalita.springBoot.entities.Transaction;
import com.khabalita.springBoot.mapper.SessionMapper;
import com.khabalita.springBoot.mapper.TransactionMapper;
import com.khabalita.springBoot.repository.SessionRepository;
import com.khabalita.springBoot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionMapper sessionMapper;

    @Transactional
    public Session newSession(SessionDto sessionDto) throws Exception{
        try{
            Session session = sessionMapper.sessionDtoToSession(sessionDto);
            Session savedSession = sessionRepository.save(session);
            return savedSession;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Transactional
    public List<SessionDto> listAllSession() throws Exception{
        try{
            List<Session> sessionsList = sessionRepository.findAll();
            List<SessionDto> sessionsDtoList = new ArrayList<>();
            for(Session session: sessionsList){
                sessionsDtoList.add(sessionMapper.sessionToSessionDto(session));
            }
            return sessionsDtoList;
        } catch (Exception e) {
            throw new Exception (e.getMessage());
        }
    }

    @Transactional
    public SessionDto updateSession(Long id, SessionDto sessionDto) throws Exception {
        try{
            Session existingSession = sessionRepository.findById(id)
                    .orElseThrow(() -> new Exception("ID de sesion no encontrado" + id));
            existingSession.setStartSession(sessionDto.getStartSession());
            existingSession.setEndSession(sessionDto.getEndSession());
            return sessionMapper.sessionToSessionDto(existingSession);
        }catch(Exception e){
            throw new Exception ("No se pudo actualizar la sesion" + e.getMessage());
        }
    }

    @Transactional
    public boolean deleteSession(Long id) throws Exception{
        try{
            if(sessionRepository.existsById(id)){
                sessionRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("ID not found" + id);
            }
        }catch (Exception e){
            throw new Exception ("No se pudo eliminar la sesion" + e.getMessage());
        }
    }

    @Transactional
    public SessionDto findSessionById(Long id) throws Exception{
        try{
            Session session = sessionRepository.findById(id)
                    .orElseThrow(() -> new Exception("ID de sesion no encontrado" + id));
            return sessionMapper.sessionToSessionDto(session);
        }catch (Exception e){
            throw new Exception("No se pudo traer la sesion" + e.getMessage());
        }
    }
}
