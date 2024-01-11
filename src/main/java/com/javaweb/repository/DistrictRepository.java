package com.javaweb.repository;

import java.util.List;

import com.javaweb.Bean.BuildingBean;
import com.javaweb.repository.entity.DistrictEntity;

public interface DistrictRepository {
	List<DistrictEntity> findDistrictEntities(BuildingBean buildingBean);
}
