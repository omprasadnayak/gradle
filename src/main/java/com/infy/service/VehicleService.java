package com.infy.service;


import java.util.List;

import com.infy.model.Vehicle;

public interface VehicleService {
	
	void insertvehicle(Vehicle vehicle);
	
	Vehicle saveVehicle(Vehicle vehicle);
	
	Iterable<Vehicle> getAll() throws Exception;
	
	Vehicle getByModelId(Integer modelId) throws Exception;
	
	void deleteVehicle(String id);
	
	List<Vehicle> getByColour(String colour) throws Exception;
	
	List<Vehicle> getByPrice(Double maxPrice) throws Exception;
	
	Integer getNextSequence(String seqName);

}
