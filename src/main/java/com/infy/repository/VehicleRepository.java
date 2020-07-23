package com.infy.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.infy.model.Vehicle;


@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String>  {
	Vehicle findByModelId(Integer modelId);
	List<Vehicle> findByColour(String colour);
	List<Vehicle> findByPriceLessThan(Double maxPrice);

}
