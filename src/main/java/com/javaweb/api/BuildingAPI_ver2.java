package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.Bean.BuildingBean;
import com.javaweb.model.BuildingDTO;
import com.javaweb.service.impl.BuildingService;

@RestController
public class BuildingAPI_ver2 {

	@Autowired
	private BuildingService buildingService;

	@GetMapping(value = "/api/building/")
	public List<BuildingDTO> getBuilding(@RequestBody(required = false) BuildingBean buildingBean) {			
		List<BuildingDTO> result = buildingService.findAll(buildingBean);
		return result;
	}
}