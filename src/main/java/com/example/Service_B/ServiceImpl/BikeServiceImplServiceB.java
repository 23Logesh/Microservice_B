package com.example.Service_B.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.Service_B.ServiceInter.BikeServiceInterServiceB;
import com.example.Service_B.dto.BikeDtoB;
import com.example.Service_B.entity.BikeEntityB;
import com.example.Service_B.repository.BikeRepositoryServiceB;


@Service
public class BikeServiceImplServiceB implements BikeServiceInterServiceB {
	
	@Autowired
	BikeRepositoryServiceB bikeRepositoryServiceB;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BikeDtoB saveBikeB(BikeDtoB bikeDtoB) {
		
		bikeRepositoryServiceB.save(convertDtoToEntity(bikeDtoB));
		BikeDtoB bikeResponse =restTemplate.postForEntity("http://localhost:8885/saveBikeA", bikeDtoB, BikeDtoB.class).getBody();
		return bikeResponse;
	}
	
	public BikeDtoB convertEntityToDto(BikeEntityB bikeEntityB) {
		return modelMapper.map(bikeEntityB, BikeDtoB.class);
	}
	
	public BikeEntityB convertDtoToEntity(BikeDtoB bikeDtoB) {
		return modelMapper.map(bikeDtoB, BikeEntityB.class);
	}


}
