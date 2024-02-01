package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentareaEntity;

@Component // đánh dấu 1 java class là 1 bean (k có hàm khởi tạo nếu dùng phải autowried)
public class BuildingConverter {

	@Autowired
	private ModelMapper modelMapper;

	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
		building.setAddress(item.getStreet() + " , " + item.getWard() + " , " + item.getDistrict().getName());
		List<RentareaEntity> rentareaEntities = item.getRentarea();
		String areaResult = rentareaEntities.stream().map(it -> it.getValue().toString())
				.collect(Collectors.joining(","));
		building.setRentArea(areaResult);

		return building;
	}
}