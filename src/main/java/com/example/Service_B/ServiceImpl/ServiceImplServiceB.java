package com.example.Service_B.ServiceImpl;

import com.example.Service_B.ServiceInter.ServiceInterServiceB;
import com.example.Service_B.dto.Dto;
import com.example.Service_B.entity.EntityB;
import com.example.Service_B.repository.RepositoryServiceB;
import com.example.Service_B.utility.ResponseStructure;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ServiceImplServiceB implements ServiceInterServiceB {

    @Autowired
    RepositoryServiceB repositoryServiceB;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    public void saveB(String message) {
        log.info("[ServiceB] Received message for processing.");

        ObjectMapper objectMapper = new ObjectMapper();
        Dto obj = null;

        try {
            obj = objectMapper.readValue(message, Dto.class);
            log.debug("[ServiceB] Successfully deserialized message into DtoB: {}", obj);
        } catch (JsonProcessingException e) {
            log.error("[ServiceB] Failed to deserialize message: {}", message, e);
            return;
        }

        EntityB entity = convertDtoToEntity(obj);
        if (entity == null) {
            log.warn("[ServiceB] DTO conversion resulted in null. Save aborted.");

            return;
        }

        repositoryServiceB.save(entity);
        log.info("[ServiceB] Entity saved to database: {}", entity);

    }

    public ResponseStructure<String> sendBToA(String message) {
        log.info("[ServiceB] Sending DtoB to ServiceA at /save endpoint.");
        log.debug("[ServiceB] Payload: {}", message);

        ResponseStructure<String> response = new ResponseStructure<>();
        try {
            String result = restTemplate.postForEntity("http://localhost:8082/save", message, String.class).getBody();
            response.setStatus(200);
            response.setMessage("Successfully sent to Service A");
            response.setData(result);
        } catch (Exception e) {
            log.error("[ServiceB] Failed to send to ServiceA", e);
            response.setStatus(502);
            response.setMessage("Failed to call ServiceA: " + e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ResponseStructure<List<Dto>> getAll() {
        log.info("[ServiceB] Fetching all entities from the database.");
        List<Dto> dtoList = repositoryServiceB.findAll().stream().map(this::convertEntityToDto).toList();

        ResponseStructure<List<Dto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Records fetched successfully");
        response.setData(dtoList);

        return response;
    }

    public Dto convertEntityToDto(EntityB entityB) {
        log.debug("[ServiceB] Converting EntityB to DtoB: {}", entityB);
        return modelMapper.map(entityB, Dto.class);
    }

    public EntityB convertDtoToEntity(Dto dto) {
        log.debug("[ServiceB] Converting DtoB to EntityB: {}", dto);
        return modelMapper.map(dto, EntityB.class);
    }
}
