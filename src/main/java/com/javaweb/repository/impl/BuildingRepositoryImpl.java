package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtils;

@Repository

public class BuildingRepositoryImpl implements BuildingRepository {

	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Integer staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			sql.append(" INNER JOIN assignmentbuilding a ON a.buildingid = b.id  ");
		}
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if (typeCode != null && typeCode.size() != 0) {
			sql.append(" INNER JOIN buildingrenttype brt ON brt.buildingid = b.id ");
			sql.append(" INNER JOIN renttype rt ON rt.id = brt.renttypeid ");
		}

	}

	public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {

		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
						&& !fieldName.startsWith("rentPrice")) {
					Object value = item.get(buildingSearchBuilder);
					if (value != null) {
						if (item.getType().getName().equals("java.lang.Integer") || item.getType().getName().equals("java.lang.Long")
								||  item.getType().getName().equals("java.lang.Float")) {
							where.append(" AND b." + fieldName + " = " + value);
						} else if (item.getType().getName().equals("java.lang.String")){
							where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		Integer staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			where.append(" AND a.staffid = " + staffId);
		}
		Integer rentAreaTo = buildingSearchBuilder.getAreaTo();
		Integer rentAreaFrom =buildingSearchBuilder.getAreaFrom();
		if (rentAreaTo != null || rentAreaFrom != null) {
			where.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE b.id = ra.buildingid ");
			if (rentAreaFrom != null) {
				where.append(" AND ra.value >= " + rentAreaFrom);
			}
			if (rentAreaTo != null) {
				where.append(" AND ra.value <= " + rentAreaTo);
			}
			where.append(" ) ");
		}

		Integer rentPriceTo = buildingSearchBuilder.getRentPriceTo();
		Integer rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
		if (rentPriceTo != null || rentPriceFrom != null) {
			if (rentPriceFrom != null) {
				where.append(" AND b.rentprice >=" + rentPriceFrom);
			}
			if (rentPriceTo != null) {
				where.append(" AND b.rentprice <=" + rentPriceTo);
			}
		}

		// Java 7
//		if (typeCode != null && typeCode.size() != 0) {
//			List<String> code = new ArrayList<>();
//			for (String item : typeCode) {
//				code.add("'" + item + "'");
//			}
//			where.append(" AND rt.code IN(" + String.join(",", code) + ") ");
//		}

		// Java 8
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if (typeCode != null && typeCode.size() != 0) {
			where.append(" AND ( ");
			String sql = typeCode.stream().map(it -> "rt.code LIKE " + "'%" + it + "%'")
					.collect(Collectors.joining(" OR "));
			where.append(sql);
			where.append(" ) ");
		}

	}

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder(
				"SELECT DISTINCT b.id, b.name, b.districtid, b.ward, b.street, b.numberofbasement, b.floorarea, b.rentprice,"
						+ " b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee FROM building b ");

		joinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		queryNormal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);

		sql.append(where);

		List<BuildingEntity> result = new ArrayList<>();

		try (Connection conn = ConnectionJDBCUtils.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());) {
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
				building.setBrokerageFee(rs.getInt("brokeragefee"));
				building.setServiceFee(rs.getString("servicefee"));
				result.add(building);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed...");
		}
		return result;
	}

}
