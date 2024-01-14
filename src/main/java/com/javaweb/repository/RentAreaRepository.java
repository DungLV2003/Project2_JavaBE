package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.RentareaEntity;

public interface RentAreaRepository {
	List<RentareaEntity> findRentareaByBuildingId(int buildingId);
}
