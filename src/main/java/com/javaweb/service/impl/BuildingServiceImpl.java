package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
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

	@Autowired // vì k có hàm khởi tạo
	private BuildingConverter buildingConverter;

	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(params,typeCode);

		for (BuildingEntity item : buildingEntities) {
			BuildingDTO building = buildingConverter.toBuildingDTO(item);
			result.add(building);
		}
		return result;
	}

}
