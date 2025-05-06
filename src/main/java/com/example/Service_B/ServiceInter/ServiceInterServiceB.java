package com.example.Service_B.ServiceInter;

import com.example.Service_B.dto.Dto;
import com.example.Service_B.utility.ResponseStructure;

import java.util.List;

public interface ServiceInterServiceB {

    public void saveB(String dtoB);

    public ResponseStructure<Dto>  sendBToA(String message);

    public ResponseStructure<List<Dto>> getAll();
}
