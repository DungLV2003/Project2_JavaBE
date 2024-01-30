package com.javaweb.api;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.impl.BuildingService;

@RestController
@Transactional
public class BuildingAPI_ver2 {

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private BuildingService buildingService;

	@PersistenceContext
	private EntityManager entityManager;

	@GetMapping(value = "/api/building/")
	public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params,
			@RequestParam(name = "typeCode", required = false) List<String> typeCode) {
		List<BuildingDTO> result = buildingService.findAll(params, typeCode);
		return result;
	}

//	@GetMapping(value = "/api/building/{name}")
//	// Khi làm phải thông qua BuildingDTO chứ k đc dùng building entity như này
//	public BuildingDTO getBuildingByName(@PathVariable String name) {
//		BuildingDTO result = new BuildingDTO();
//		List<BuildingEntity> building = buildingRepository.findByNameContaining(name);
//		return result;
//	}
	
	@GetMapping(value = "/api/building/{name}/{street}")
	// Khi làm phải thông qua BuildingDTO chứ k đc dùng building entity như này
	public BuildingDTO getBuildingByName(@PathVariable String name, @PathVariable String street) {
		BuildingDTO result = new BuildingDTO();
		List<BuildingEntity> building = buildingRepository.findByNameContainingAndStreet(name, street);
		return result;
	}
	
//	@GetMapping(value = "/api/building/{id}")
//	// Khi làm phải thông qua BuildingDTO chứ k đc dùng building entity như này
//	public BuildingDTO getBuildingById(@PathVariable Integer id) {
//		BuildingDTO result = new BuildingDTO();
//		BuildingEntity building = buildingRepository.findById(id).get();
//		return result;
//	}
//	
	//SpringDataJPA
//	@DeleteMapping(value = "/api/building/{id}")
//	public void deleteBuilding(@PathVariable Integer id) {
//		buildingRepository.deleteById(id);
//	}
	
	@DeleteMapping(value = "/api/building/{ids}")
	public void deleteBuilding(@PathVariable Integer[] ids) {
		buildingRepository.deleteByIdIn(ids);
	}

	@PostMapping(value = "/api/building/")
	public void createBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setName(buildingRequestDTO.getName());
		buildingEntity.setStreet(buildingRequestDTO.getStreet());
		buildingEntity.setWard(buildingRequestDTO.getWard());
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingRequestDTO.getDistrictId());
		buildingEntity.setDistrict(districtEntity);
		entityManager.persist(buildingEntity);
		System.out.println("ok");
	}

//	@PutMapping(value = "/api/building/")
//	public void updateBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
//		BuildingEntity buildingEntity = new BuildingEntity();
//		buildingEntity.setId(1);
//		buildingEntity.setName(buildingRequestDTO.getName());
//		buildingEntity.setStreet(buildingRequestDTO.getStreet());
//		buildingEntity.setWard(buildingRequestDTO.getWard());
//		DistrictEntity districtEntity = new DistrictEntity();
//		districtEntity.setId(buildingRequestDTO.getDistrictId());
//		buildingEntity.setDistrict(districtEntity);
//		entityManager.merge(buildingEntity);
//		System.out.println("ok");
//	}
	
	@PutMapping(value = "/api/building/")
	public void updateBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildingEntity = buildingRepository.findById(buildingRequestDTO.getId()).get();
		buildingEntity.setName(buildingRequestDTO.getName());
		buildingEntity.setStreet(buildingRequestDTO.getStreet());
		buildingEntity.setWard(buildingRequestDTO.getWard());
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingRequestDTO.getDistrictId());
		buildingEntity.setDistrict(districtEntity);
		buildingRepository.save(buildingEntity);
		System.out.println("ok");
	}


	//JPA
//	@DeleteMapping(value = "/api/building/{id}")
//	public void deleteBuilding(@PathVariable Integer id) {
//		BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
//		entityManager.remove(buildingEntity);
//		System.out.print("ok");
//	}

}