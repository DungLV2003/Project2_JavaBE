package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
	private String name;
	private Integer districtId;
	private List<String> typeCode = new ArrayList<>();
	private String ward;
	private String street;
	private Integer floorArea;
	private String level;
	private String direction;
	private Integer numberOfBasement;
	private Integer areaFrom;
	private Integer areaTo;
	private Integer rentPriceFrom;
	private Integer rentPriceTo;
	private String managerName;
	private String managerPhoneNumber;
	private Integer staffId;
	
	private BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.floorArea = builder.floorArea;
		this.ward = builder.ward;
		this.street = builder.street;
		this.districtId = builder.districtId;
		this.numberOfBasement = builder.numberOfBasement;
		this.typeCode = builder.typeCode;
		this.level = builder.level;
		this.direction = builder.direction;
		this.areaFrom = builder.areaFrom;
		this.areaTo = builder.areaTo;
		this.rentPriceFrom = builder.rentPriceFrom;
		this.rentPriceTo = builder.rentPriceTo;
		this.managerName = builder.managerName;
		this.managerPhoneNumber = builder.managerPhoneNumber;
		this.staffId = builder.staffId;
	}
	
	
	public String getName() {
		return name;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public List<String> getTypeCode() {
		return typeCode;
	}
	public String getWard() {
		return ward;
	}
	public String getStreet() {
		return street;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public String getLevel() {
		return level;
	}
	public String getDirection() {
		return direction;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public Integer getAreaFrom() {
		return areaFrom;
	}
	public Integer getAreaTo() {
		return areaTo;
	}
	public Integer getRentPriceFrom() {
		return rentPriceFrom;
	}
	public Integer getRentPriceTo() {
		return rentPriceTo;
	}
	public String getManagerName() {
		return managerName;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public Integer getStaffId() {
		return staffId;
	}
	
	public static class Builder{
		private String name;
		private Integer districtId;
		private List<String> typeCode = new ArrayList<>();
		private String ward;
		private String street;
		private Integer floorArea;
		private String level;
		private String direction;
		private Integer numberOfBasement;
		private Integer areaFrom;
		private Integer areaTo;
		private Integer rentPriceFrom;
		private Integer rentPriceTo;
		private String managerName;
		private String managerPhoneNumber;
		private Integer staffId;
		
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setDistrictId(Integer districtId) {
			this.districtId = districtId;
			return this;
		}
		public Builder setTypeCode(List<String> typeCode) {
			this.typeCode = typeCode;
			return this;
		}
		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}
		public Builder setFloorArea(Integer floorArea) {
			this.floorArea = floorArea;
			return this;
		}
		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}
		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}
		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}
		public Builder setAreaFrom(Integer areaFrom) {
			this.areaFrom = areaFrom;
			return this;
		}
		public Builder setAreaTo(Integer areaTo) {
			this.areaTo = areaTo;
			return this;
		}
		public Builder setRentPriceFrom(Integer rentPriceFrom) {
			this.rentPriceFrom = rentPriceFrom;
			return this;
		}
		public Builder setRentPriceTo(Integer rentPriceTo) {
			this.rentPriceTo = rentPriceTo;
			return this;
		}
		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}
		public Builder setManagerPhoneNumber(String managerPhoneNumber) {
			this.managerPhoneNumber = managerPhoneNumber;
			return this;
		}
		public Builder setStaffId(Integer staffId) {
			this.staffId = staffId;
			return this;
		}
		
		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
		
	}
	
}
