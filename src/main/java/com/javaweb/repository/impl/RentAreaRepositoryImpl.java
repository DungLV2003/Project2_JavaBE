package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentareaEntity;
import com.mysql.jdbc.PreparedStatement;


@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository{
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "dung0325325380";
	

	@Override
	public List<RentareaEntity> findRentareaByBuildingId(int buildingId) {
		String sql = "SELECT * FROM rentarea WHERE buildingid = ?";
		List<RentareaEntity> rentAreas = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql)) {

			pstmt.setInt(1, buildingId);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					RentareaEntity rentArea = new RentareaEntity();
					rentArea.setId(rs.getInt("id"));
					rentArea.setValue(rs.getInt("value"));
					rentAreas.add(rentArea);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error fetching rent areas by building ID");
		}

		return rentAreas;
	}
	
}
