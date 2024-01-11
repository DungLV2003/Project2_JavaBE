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
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "dung0325325380";

	@Override
	public List<DistrictEntity> findDistrictEntities(BuildingBean buildingBean) {
		List<DistrictEntity> list = new ArrayList<DistrictEntity>();

		StringBuilder sql = new StringBuilder("select * from district where 1 = 1");

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());) {
			while (rs.next()) {
				DistrictEntity district = new DistrictEntity();
				district.setId(rs.getInt("id"));
				district.setCode(rs.getString("code"));
				district.setName(rs.getString("name"));
				list.add(district);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed...");
		}
		return list;
	}
}
