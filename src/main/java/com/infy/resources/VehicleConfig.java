package com.infy.resources;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;


public class VehicleConfig {
	
	static final Logger LOGGER=Logger.getLogger(VehicleConfig.class);
	
	public static String getMessageFromProperties(String exceptionName){
		Properties properties = new Properties();
		String message=properties.getProperty(exceptionName);
		if(message==null){
		message=getErrorMessage(exceptionName);
		}
		return message;
	
	}

	private static String getErrorMessage(String exceptionName){
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(
							"com/infy/resources/exceptions.properties"));
		} catch (IOException e) {
			LOGGER.error("VehicleConfig"+"getErrorMessage" + e.toString());	
			
		}
		return properties.getProperty(exceptionName);
	}

}
