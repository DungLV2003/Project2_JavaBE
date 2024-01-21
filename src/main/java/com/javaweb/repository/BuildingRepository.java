package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.Bean.BuildingBean;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);

}
