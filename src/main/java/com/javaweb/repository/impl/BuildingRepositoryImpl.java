package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.Bean.BuildingBean;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository

public class BuildingRepositoryImpl implements BuildingRepository {

	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "dung0325325380";

	@Override
	public List<BuildingEntity> findAll(BuildingBean buildingBean) {

		String sql = buildQuery(buildingBean);

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getInt("id"));
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setNumberOfBasement(rs.getInt("numberofbasement"));
				building.setDistrictId(rs.getInt("districtid"));
				building.setFloorArea(rs.getInt("floorarea"));
				building.setServiceFee(rs.getString("servicefee"));
				building.setRentPrice(rs.getInt("rentprice"));
				building.setManagerName(rs.getString("managername"));
				building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
				result.add(building);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed...");
		}
		return result;
	}

	@Override
	public List<String> checkInnerJoin(BuildingBean buildingBean) {
		List<String> innerJoin = new ArrayList<>();

		if (buildingBean.getStaffId() != null) {
			innerJoin.add(" INNER JOIN assignmentbuilding a ON a.buildingid = b.id AND a.staffid = "
					+ buildingBean.getStaffId());
		}
		
		if(buildingBean.getAreaFrom() != null || buildingBean.getAreaTo() != null) {
			innerJoin.add(" INNER JOIN rentarea ra on ra.buildingid = b. id ");
			if(buildingBean.getAreaTo() != null) {
				innerJoin.add(" AND ra.value <= " + buildingBean.getAreaTo());
			}
			if(buildingBean.getAreaFrom() != null) {
				innerJoin.add(" AND ra.value >= " + buildingBean.getAreaFrom());
			}
		}
		
		

		if (buildingBean.getTypecode() != null && !buildingBean.getTypecode().isEmpty()) {
			innerJoin.add(" INNER JOIN buildingrenttype brt ON brt.buildingid = b.id");
			innerJoin.add(" INNER JOIN renttype rt ON rt.id = brt.renttypeid");

			if (buildingBean.getStaffId() == null) {
				innerJoin.add(" WHERE 1 = 1");
			}

			List<String> typeCodeConditions = new ArrayList<>();
			for (String typeCode : buildingBean.getTypecode()) {
				typeCodeConditions.add(" rt.code LIKE '%" + typeCode + "%'");
			}

			innerJoin.add(" AND (" + String.join(" OR ", typeCodeConditions) + ")");
		}

		

		return innerJoin;

	}

	public String buildQuery(BuildingBean buildingBean) {
		StringBuilder sql = new StringBuilder("SELECT DISTINCT b.id, b.name, b.street, b.ward, b.numberofbasement," + 
				"b.districtid, b.floorarea, b.servicefee, b.rentprice, b.managername, b.managerphonenumber FROM building b ");
		boolean check = false;

		List<String> innerJoinTable = checkInnerJoin(buildingBean);

		if (innerJoinTable != null && !innerJoinTable.isEmpty()) {
			check = true;
			for (String innerJoin : innerJoinTable) {
				sql.append(innerJoin);
			}
		}

		if (!check) {
			sql.append(" where 1 = 1 ");
		}

		if (buildingBean.getName() != null && !buildingBean.getName().equals("")) {
			sql.append("AND b.name like '%" + buildingBean.getName() + "%' ");
		}
		if (buildingBean.getDistrictId() != null) {
			sql.append("AND b.districtid = " + buildingBean.getDistrictId() + " ");
		}
		if (buildingBean.getFloorArea() != null) {
			sql.append("AND b.floorarea = " + buildingBean.getFloorArea() + " ");
		}
		if (buildingBean.getWard() != null && !buildingBean.getWard().equals("")) {
			sql.append("AND b.ward like '%" + buildingBean.getWard() + "%' ");
		}
		if (buildingBean.getStreet() != null && !buildingBean.getStreet().equals("")) {
			sql.append("AND b.street like '%" + buildingBean.getStreet() + "%' ");
		}
		if (buildingBean.getNumberOfBasement() != null) {
			sql.append("AND b.numberofbasement = " + buildingBean.getNumberOfBasement() + " ");
		}
		if (buildingBean.getDirection() != null && !buildingBean.getDirection().equals("")) {
			sql.append("AND b.direction like '%" + buildingBean.getDirection() + "%' ");
		}
		if (buildingBean.getLevel() != null && !buildingBean.getLevel().equals("")) {
			sql.append("AND b.level like '%" + buildingBean.getLevel() + "%' ");
		}
		if (buildingBean.getRentPriceFrom() != null) {
			sql.append("AND b.rentprice >= " + buildingBean.getRentPriceFrom() + " ");
		}
		if (buildingBean.getRentPriceTo() != null) {
			sql.append("AND b.rentprice <= " + buildingBean.getRentPriceTo() + " ");
		}

		if (buildingBean.getManagerPhoneNumber() != null && !buildingBean.getManagerPhoneNumber().equals("")) {
			sql.append("AND b.managerphonenumber like '%" + buildingBean.getManagerPhoneNumber() + "%' ");
		}
		if (buildingBean.getManagerName() != null && !buildingBean.getManagerName().equals("")) {
			sql.append("AND b.managername like '%" + buildingBean.getManagerName() + "%' ");
		}

		return sql.toString();
	}

}
