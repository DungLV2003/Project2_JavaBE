package com.javaweb.repository.entity;

import java.util.Date;

public class RentareaEntity {
	private Integer id;
	private Integer value;
	private Integer buildingId;
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modidiedBy;

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModidiedBy() {
		return modidiedBy;
	}

	public void setModidiedBy(String modidiedBy) {
		this.modidiedBy = modidiedBy;
	}

	@Override
	public String toString() {
		return "RentareaEntity [id=" + id + ", value=" + value + ", buildingId=" + buildingId + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy + ", modidiedBy="
				+ modidiedBy + "]";
	}

}
