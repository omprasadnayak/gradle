package com.infy.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Vehicle_Col")
public class Vehicle {
	
	@Id
	private String id;
	private Integer modelId;
	private String name;
	private String colour;
	private Double price;
	
	public Vehicle() {
		super();
	}
	
	

	public Vehicle(Integer modelId,String name, String colour, Double price) {
		super();
		this.modelId=modelId;
		this.name = name;
		this.colour = colour;
		this.price = price;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
