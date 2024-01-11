package com.javaweb.repository;

import java.util.List;

import com.javaweb.Bean.BuildingBean;
import com.javaweb.repository.entity.RentareaEntity;

public interface RentAreaRepository {
	List<RentareaEntity> findRentareaEntities(BuildingBean buildingBean);
}
