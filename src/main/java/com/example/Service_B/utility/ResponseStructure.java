package com.example.Service_B.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = "Service_A")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure<T> {
    int status;
    String message;
    T data;
}
