package com.javaweb.model;

//DT0 = Data transfer Object (đối tượng luân chuyển dữ liệu)
public class BuildingDTO {

	private String name;
	private String address; //District, ward, street 
	private Integer numberOfBasement;
	private String managerName;
	private String managerPhone;
	private Integer floorArea;
	private Integer emptyArea;
	private String rentArea;
	private Integer rentPrice;
	private String serviceFee;
	private Integer brokerageFee;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}

	public Integer getEmptyArea() {
		return emptyArea;
	}

	public void setEmptyArea(Integer emptyArea) {
		this.emptyArea = emptyArea;
	}
	
	public String getRentArea() {
		return rentArea;
	}

	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}

	public Integer getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Integer rentPrice) {
		this.rentPrice = rentPrice;
	}


	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public Integer getBrokerageFee() {
		return brokerageFee;
	}

	public void setBrokerageFee(Integer brokerageFee) {
		this.brokerageFee = brokerageFee;
	}



	

}
