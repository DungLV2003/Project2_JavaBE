package com.javaweb.repository.entity;

import java.util.Date;

public class RentareaEntity {
	private Integer id;
	private Integer value;
	private Integer buildingId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "RentareaEntity [id=" + id + ", value=" + value + ", buildingId=" + buildingId + "]";
	}


}
