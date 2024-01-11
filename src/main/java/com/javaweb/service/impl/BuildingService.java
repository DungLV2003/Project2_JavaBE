package com.javaweb.service.impl;

import java.util.List;

import com.javaweb.Bean.BuildingBean;
import com.javaweb.model.BuildingDTO;

public interface BuildingService {
	List<BuildingDTO> findAll(BuildingBean buildingBean);
}
