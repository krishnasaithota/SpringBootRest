package com.foodapp.springrest.model;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtil {

	/**
	 * Method to convert object to json bytes.
	 * 
	 *  
	 * @param object   
	 * 
	 * @return byte[], returns the array of bytes from the object.
	 */
	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

	/**
	 * Method to convert object to string.
	 * 
	 *  
	 * @param receipeMoReturnList, list of receipe objects   
	 * 
	 * @return String, returns the json String from the list object.
	 * @throws Exception 
	 */
	public static String convertObjectToString(List receipeMoReturnList) throws Exception {
		final StringWriter stringWriter = new StringWriter();
		final ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(stringWriter, receipeMoReturnList);
		} catch (IOException e) {
			throw new Exception("Exception while conversion from object to string");
		}
		return stringWriter.toString();
	}
	
	/**
	 * Method to populate header fields with JSON type.
	 * 
	 *  
	 * @param    
	 * 
	 * @return HttpHeaders.
	 */
	public static HttpHeaders populateHeadersFieldWithJSONContentType() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return headers;
	}
	
	/**
	 * Method to populate header fields with text type.
	 * 
	 *  
	 * @param    
	 * 
	 * @return HttpHeaders.
	 */
	public static HttpHeaders populateHeadersFieldWithTextContentType() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/plain; charset=utf-8");
		return headers;
	}

}

