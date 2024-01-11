package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.Bean.BuildingBean;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentareaEntity;


@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository{
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "dung0325325380";
	

	@Override
	public List<RentareaEntity> findRentareaEntities(BuildingBean buildingBean) {
		List<RentareaEntity> list = new ArrayList<RentareaEntity>();

		StringBuilder sql = new StringBuilder("select * from rentarea where 1 = 1");

		if (buildingBean.getAreaFrom() != null) {
			sql.append(" and value >= " + buildingBean.getAreaFrom());
		}
		if (buildingBean.getAreaTo() != null) {
			sql.append(" and value <= " + buildingBean.getAreaTo());
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());) {
			while (rs.next()) {
				RentareaEntity rentAreaEntity = new RentareaEntity();
				rentAreaEntity.setId(rs.getInt("id"));
				rentAreaEntity.setValue(rs.getInt("value"));
				rentAreaEntity.setBuildingId(rs.getInt("buildingid"));
				list.add(rentAreaEntity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args) {
		BuildingBean buildingBean = new BuildingBean();
		
		RentAreaRepositoryImpl rentAreaRepositoryImpl = new RentAreaRepositoryImpl();
		List<RentareaEntity> list = rentAreaRepositoryImpl.findRentareaEntities(buildingBean);
		
		for(RentareaEntity iEntity : list) {
			System.out.println(iEntity);
		}
	}
	
}
