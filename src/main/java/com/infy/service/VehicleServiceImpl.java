package com.infy.service;


import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.infy.model.CustomSequences;
import com.infy.model.Vehicle;
import com.infy.repository.VehicleRepository;
import com.infy.resources.VehicleConfig;

@Service
public class VehicleServiceImpl implements VehicleService {
	static final Logger log=Logger.getLogger(VehicleServiceImpl.class);
	
	@Autowired 
	private MongoOperations mongo;
	
	@Autowired(required=true)
	private VehicleRepository vehicleRepository;
	
	@Override
	public Vehicle saveVehicle(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}
	
	@Override
	public List<Vehicle> getAll() throws Exception{
		
		try {
		List<Vehicle> vehicles= vehicleRepository.findAll();
		if(vehicles.isEmpty()){
			throw new Exception("VehicleService.No_Vehicle");
		}
		return vehicles;
		
		} catch (Exception e) {
			if (e.getMessage().contains("Service")) {
				log.error(this.getClass().getName() + " getAll " + VehicleConfig.getMessageFromProperties(e.getMessage()));
			}
			throw e;
		}
	}
	
	
	@Override
	public Vehicle getByModelId(Integer modelId) throws Exception{
		
		try {		
		Vehicle vehicle=vehicleRepository.findByModelId(modelId);
		if(vehicle==null){
			throw new Exception("VehicleService.No_Vehicle_modelId");
		}
		return vehicle;
		
		} catch (Exception e) {
			if (e.getMessage().contains("Service")) {
				log.error(this.getClass().getName() + " getByModelId " + VehicleConfig.getMessageFromProperties(e.getMessage()));
			}
			throw e;
		}
	}

	
	@Override
	public void deleteVehicle(String id) {
		this.vehicleRepository.delete(id);
		
	}

	
	@Override
	public List<Vehicle> getByColour(String colour) throws Exception {	
		try {
			List<Vehicle> vehicles= vehicleRepository.findByColour(colour);
			if(vehicles.isEmpty()){
				throw new Exception("VehicleService.No_Vehicle_colour");
			}
			return vehicles;
			
		} catch (Exception e) {
			if (e.getMessage().contains("Service")) {
				log.error(this.getClass().getName() + " getByColour " + VehicleConfig.getMessageFromProperties(e.getMessage()));
			}
			throw e;
		}
		
		
	}

	@Override
	public List<Vehicle> getByPrice(Double maxPrice) throws Exception {
		try {
		List<Vehicle> vehicles= vehicleRepository.findByPriceLessThan(maxPrice);
		if(vehicles.isEmpty()){
			throw new Exception("VehicleService.No_Vehicle_price");
		}
		return vehicles;
		
		} catch (Exception e) {
			if (e.getMessage().contains("Service")) {
				log.error(this.getClass().getName() + " getByPrice " + VehicleConfig.getMessageFromProperties(e.getMessage()));
			}
			throw e;
		}
	}
	

	@Override
	public void insertvehicle(Vehicle vehicle) {
		/*Integer i=getNextSequence("modelId");
		vehicle.setModelId(i);*/
		vehicleRepository.insert(vehicle);
	}
	
	
	public Integer getNextSequence(String seqName)
    {
        CustomSequences counter = mongo.findAndModify(
            query(where("_id").is(seqName)),
            new Update().inc("seq",1),
            options().returnNew(true).upsert(true),
            CustomSequences.class);
        return counter.getSeq();
    }

	

}