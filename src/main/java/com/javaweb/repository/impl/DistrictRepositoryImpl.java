package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "dung0325325380";

	@Override
	 public DistrictEntity findDistrictById(int districtId) {
        String sql = "SELECT * FROM district WHERE id = ?";
        DistrictEntity district = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, districtId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    district = new DistrictEntity();
                    district.setId(rs.getInt("id"));
                    district.setName(rs.getString("name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching district by ID");
        }

        return district;
    }
}
