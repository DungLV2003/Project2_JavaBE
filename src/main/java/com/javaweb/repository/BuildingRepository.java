package com.javaweb.repository;

import java.util.List;

import com.javaweb.Bean.BuildingBean;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentareaEntity;

public interface BuildingRepository {
	List<BuildingEntity> findAll(BuildingBean buildingBean);

	List<String> checkInnerJoin(BuildingBean buildingBean);

	String buildQuery(BuildingBean buildingBean);

}
