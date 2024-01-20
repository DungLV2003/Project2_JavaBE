package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentareaEntity;
import com.javaweb.utils.ConnectionJDBCUtils;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

	@Override
	public List<RentareaEntity> findRentareaByBuildingId(int buildingId) {
		String sql = "SELECT * FROM rentarea WHERE rentarea.buildingid = " + buildingId;
		List<RentareaEntity> rentAreas = new ArrayList<>();

		try (Connection conn = ConnectionJDBCUtils.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				RentareaEntity rentArea = new RentareaEntity();
				rentArea.setId(rs.getInt("id"));
				rentArea.setValue(rs.getInt("value"));
				rentAreas.add(rentArea);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error fetching rent areas by building ID");
		}
		return rentAreas;
	}
}
