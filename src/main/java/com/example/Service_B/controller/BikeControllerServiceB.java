package com.example.Service_B.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service_B.ServiceInter.BikeServiceInterServiceB;
import com.example.Service_B.dto.BikeDtoB;


@RestController
public class BikeControllerServiceB {
	
	@Autowired
	BikeServiceInterServiceB bikeServiceInterServiceB;
	
	@PostMapping("/saveBikeB")
	public BikeDtoB saveBikeB(@RequestBody BikeDtoB bikeDtoB) {
		bikeServiceInterServiceB.saveBikeB(bikeDtoB);
		return bikeDtoB;
	}

}
