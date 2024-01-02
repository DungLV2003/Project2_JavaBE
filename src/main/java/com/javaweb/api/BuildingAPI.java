package com.javaweb.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;

import customexception.FieldRequiredException;

//ctrl + shift + o là tự import

@RestController // Cách để nó biết đây là phần RESTful api web service
public class BuildingAPI {
//	@GetMapping(value = "/api/building/")

	// Khi mà rơi vào api trên thì nó sẽ xử lí hàm bên dưới
	// nameBuiding sẽ hứng cái vái value từ name bên client(potman)
	// phải dùng lớp grapher Integer chứ không đc dùng int vì int k thể nhận giá trị
	// null

	public List<BuildingDTO> getBuilding(@RequestParam(value = "name", required = false) String nameBuilding,
			@RequestParam(value = "numberOfBasement", required = false) Integer numberOfBasement,
			@RequestParam(value = "ward", required = false) String ward) {
		// Xu ly duoi db
		List<BuildingDTO> listBuildings = new ArrayList<>();
		BuildingDTO buildingDTO1 = new BuildingDTO();
		buildingDTO1.setName("ABC Building");
		buildingDTO1.setNumberOfBasement(3);
//		buildingDTO1.setWard("Tân Mai");

		BuildingDTO buildingDTO2 = new BuildingDTO();
		buildingDTO2.setName("ACM Tower");
		buildingDTO2.setNumberOfBasement(2);
//		buildingDTO2.setWard("Da Cao");

		listBuildings.add(buildingDTO1);
		listBuildings.add(buildingDTO2);
		return listBuildings;
//		System.out.print(nameBuilding + " " + numberOfBasement + " " + ward);
	}

//	@RequestMapping(value = "/api/building/", method = RequestMethod.POST)
//	public void getBuilding2(@RequestParam Map<String, Object> params) {
//		System.out.print("oke");
//	}

	// Thường dùng map cho param còn java bean cho body

//	@RequestMapping(value = "/api/building/", method = RequestMethod.POST)
//	public void getBuilding3(@RequestBody BuildingDTO buidingDTO) {
//		System.out.print("oke");
//	}

	@DeleteMapping(value = "/api/building/{id}")
	public void deleteBuiding(@PathVariable Integer id) {
		System.out.print("Da xoa toa nha có id = " + id + " rồi nhé!");
	}

	@PostMapping(value = "/api/building/")
	public Object getBuilding3(@RequestBody BuildingDTO building) {
//		System.out.print(5/0);
		valiDate(building);
		return building;
	}

	public void valiDate(BuildingDTO buildingDTO) {
		if (buildingDTO.getName() == null || buildingDTO.getName().equals("")
				|| buildingDTO.getNumberOfBasement() == null) {
			throw new FieldRequiredException("name or numberofbasement is null");
		}
	}
}
