package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Bean.BuildingBean;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentareaEntity;

@Service
public class BuildingServiceImpl implements BuildingService {

	// Có autowired mới gọi trực tiếp được đến interface (Xem buổi 9). Trương hợp
	// này đang theo mô hình 3 layer và dùng các annotaion nên phải dùng anotation
	// @autowired

	// K gọi thông qua interface mà gọi đến java class Imple của nó cũng đc. Thì k
	// cần dùng autowired chỉ cần khởi tạo đối tượng của class đấy thôi

	// Nhưng mà khuyến khích sử dụng interface để phân tầng code cho clean hơn, có
	// nói trong clean code và đi làm dùng interface rất nhiều
	@Autowired
	private BuildingRepository buildingRepository;


	@Override
	public List<BuildingDTO> findAll(BuildingBean buildingBean) {
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();

		List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingBean);
	
		
		for (BuildingEntity item : buildingEntities) {
			BuildingDTO building = new BuildingDTO();
			building.setName(item.getName());

			 DistrictEntity district = buildingRepository.findDistrictById(item.getDistrictId());
	            if (district != null) {
	                building.setAddress(item.getStreet() + " , " + item.getWard() + " , " + district.getName());
	            }


			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerName(item.getManagerName());
			building.setManagerPhone(item.getManagerPhoneNumber());
			building.setFloorArea(item.getFloorArea());

			List<RentareaEntity> rentareaEntities = buildingRepository.findRentareaByBuildingId(item.getId());
            StringJoiner rentStringBuilder = new StringJoiner(", ");
            for (RentareaEntity rentArea : rentareaEntities) {
                rentStringBuilder.add(Integer.toString(rentArea.getValue()));
            }
            building.setRentArea(rentStringBuilder.toString());
		
			building.setRentPrice(item.getRentPrice());
			building.setServiceFee(item.getServiceFee());
			building.setBrokerageFee(item.getBrokerageFee());
			result.add(building);
		}
		return result;
	}

}
