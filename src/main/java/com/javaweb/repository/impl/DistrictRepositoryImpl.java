package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectionJDBCUtils;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {


	@Override
	 public DistrictEntity findDistrictById(int districtId) {
        String sql = "SELECT d.name FROM district d WHERE d.id = " + districtId + ";" ;
        DistrictEntity districtEntity = new DistrictEntity();
        try (Connection conn = ConnectionJDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        		ResultSet rs = pstmt.executeQuery(sql)) {
        	while(rs.next()) {
        		districtEntity.setName(rs.getString("name"));
        	}
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching district by ID");
        }

        return districtEntity;
    }
}
