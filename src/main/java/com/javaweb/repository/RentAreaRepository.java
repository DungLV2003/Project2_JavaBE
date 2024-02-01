package com.javaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.repository.entity.RentareaEntity;

public interface RentAreaRepository extends JpaRepository<RentareaEntity, Integer> {
	//List<RentareaEntity> findRentareaByBuildingId(int buildingId);
}
