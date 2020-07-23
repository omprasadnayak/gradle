package com.infy.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.infy.model.Vehicle;
import com.infy.resources.VehicleConfig;
import com.infy.service.VehicleService;


@Controller
@RequestMapping(value="/Vehicle")
public class VehicleController {
	
	static final Logger logger=Logger.getLogger(VehicleController.class);
	
	@Autowired(required=true)
	private VehicleService service;
	
	
	@RequestMapping(value="/welcome")
	public String welcome(){
		return "welcome";
	}
	
	
	@RequestMapping("/add")
	public String addVehicle(Model model){
		Integer i=service.getNextSequence("id");
		Vehicle vehicle=new Vehicle();
		vehicle.setModelId(i);
		model.addAttribute("vehicle",vehicle);
		return "addVehicle";
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/insert/vehicle")
	public String insert(@ModelAttribute Vehicle vehicle, Model model){
		service.insertvehicle(vehicle);
		
		model.addAttribute(vehicle);
		return "redirect:/Vehicle/getAll";
	}

	
	@RequestMapping(method=RequestMethod.GET, value="/getAll")
	public String getAll(ModelMap map) throws Exception{
		try{
		map.put("vehicleList", service.getAll());
		return "vehicleList";
		
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getAll " + VehicleConfig.getMessageFromProperties(e.getMessage()));
			throw e;
		}
	}
	
	
	@RequestMapping(value="/update")
	public String updateForm(){
		return "updateForm";
	}
	
	
	@GetMapping("/modelid/{modelId}")
	public String edit(@PathVariable("modelId") Integer modelId,Model model) throws Exception{
		
		try{
		Vehicle vehicle=service.getByModelId(modelId);
		model.addAttribute("vehicle", vehicle);
    	return "updateVehicle";
    	
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " edit " + VehicleConfig.getMessageFromProperties(e.getMessage()));
			throw e;
		}
    	
	}
	
	@GetMapping("/view/{modelId}")
	public String view(@PathVariable("modelId") Integer modelId,Model model) throws Exception{
		try{
		Vehicle vehicle=service.getByModelId(modelId);
    	model.addAttribute("vehicle", vehicle);
    	return "vehicleDetail";
    	
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " view " + VehicleConfig.getMessageFromProperties(e.getMessage()));
			throw e;
		}
	}
	
	
	@RequestMapping(value ="/vehicle", method = RequestMethod.POST)
	public String update(@ModelAttribute Vehicle vehicle){
	    service.saveVehicle(vehicle);
	    return "redirect:/Vehicle/getAll";
	}
	
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable("id") String id){
		service.deleteVehicle(id);
		return "redirect:/Vehicle/getAll";
	}
	
	@GetMapping(value="/getByColour/{colour}")
	public String getByColour(@PathVariable("colour") String colour,ModelMap modelMap) throws Exception{
		
		try {
			List<Vehicle> list=service.getByColour(colour);
			modelMap.put("vehicleList",list );
			return "vehicleList";
			
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getByColour " + VehicleConfig.getMessageFromProperties(e.getMessage()));
			throw e;
		}
		
	}
	
	@GetMapping(value="/getByPrice/{maxPrice}")
	public String getByPrice(@PathVariable("maxPrice") Double maxPrice, ModelMap modelMap) throws Exception{
		
		try{
		List<Vehicle> list=service.getByPrice(maxPrice);
		modelMap.put("vehicleList",list );
		return "vehicleList";
		
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getByPrice " + VehicleConfig.getMessageFromProperties(e.getMessage()));
			throw e;
		}
	}
	

}
