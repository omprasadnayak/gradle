/*package com.infy.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.infy.model.Vehicle;

@Component
public class DBSeeder implements CommandLineRunner {
	private VehicleRepository vehicleRepository;
	
	


	public DBSeeder(VehicleRepository vehicleRepository) {
		super();
		this.vehicleRepository = vehicleRepository;
	}


	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		Vehicle vehicle=new Vehicle(1,"Royal Enfield classic", "Black", 130000.0);
		Vehicle vehicle1=new Vehicle(2,"Bajaj Pulsar 180", "Red", 80000.0);
		Vehicle vehicle2=new Vehicle(3,"Yamaha FZ-S", "Green", 85000.0);
		Vehicle vehicle3=new Vehicle(4,"Kawasaki H2", "Black", 2900000.0);
		Vehicle vehicle4=new Vehicle(5,"Suzuki GSX1300R Hayabusa", "Silver", 1400000.0);
		Vehicle vehicle5=new Vehicle(6,"BMW S1000RR", "Grey", 2755000.0);
		
		//drop all vehicles
		this.vehicleRepository.deleteAll();
		
		//add new vehicles
		List<Vehicle> vehicles=Arrays.asList(vehicle,vehicle1,vehicle2,vehicle3,vehicle4,vehicle5);
		this.vehicleRepository.insert(vehicles);
		
	}
	
	

}
*/