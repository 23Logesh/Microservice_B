package com.example.Service_B.controller;

import com.example.Service_B.ServiceInter.ServiceInterServiceB;
import com.example.Service_B.dto.Dto;
import com.example.Service_B.utility.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Service B Controller", description = "APIs for Service B operations")
@RestController
public class ControllerServiceB {

    @Autowired
    ServiceInterServiceB serviceInterServiceB;
    @Operation(summary = "Send Data to Service A using RestTemplate")
    @PostMapping("/sendBToA")
    public ResponseStructure<Dto>  sendBToA(@RequestParam String message) {
        return serviceInterServiceB.sendBToA(message);

    }
    @Operation(summary = "To Check Data's Stored in the Service B DB ")
    @PostMapping("/getAllMessage")
    public ResponseStructure<List<Dto>> getAll() {
        return serviceInterServiceB.getAll();
    }
}
