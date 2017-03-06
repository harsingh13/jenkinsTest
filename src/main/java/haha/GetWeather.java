package haha;

import haha.Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.management.RuntimeErrorException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import org.apache.log4j.Logger;
import org.json.JSONObject;

//import org.apache.log4j.Logger;


//@JsonIgnoreProperties(ignoreUnknown = true)
public class GetWeather {

	private static final Logger logger = Logger.getLogger(GetWeather.class);

	public static void main(String[] args) throws IOException{
		String url = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=532a84a74eb1e41fd0a703e7250ea183";
		logger.info("Svc Endpoint:::" + url);
	
		URL endpoint = new URL(url);
		HttpURLConnection conn =  (HttpURLConnection) endpoint.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		if(conn.getResponseCode()!=200){
			throw new RuntimeException("not successful. Response Code"+conn.getResponseCode());
		}
			
		/*
		 * Method 1: Using Buffered Reader and JSONObject
		 * 
		 * BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String output;
		
		logger.info("response from svc::::::::::");
		
		while((output=br.readLine())!=null){
			System.out.println(output);
		}
		
		JSONObject obj = new JSONObject (output);
		String city = obj.get("name").toString();

		logger.info("City name is:::"+city);*/
		
		/*
		 * Method 2: Using ObjectMapper
		 * 
		 * ObjectMapper objectMapper = new ObjectMapper();	
		JsonNode node = objectMapper.readTree(conn.getInputStream());
		String city = node.get("name").toString();
		logger.info("City name is:::"+city);*/
		
		
		ObjectMapper objectMapper = new ObjectMapper();	
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Weather weather = objectMapper.readValue(conn.getInputStream(), Weather.class);
		String city = weather.getName();
		String id = weather.getId();
		logger.info("City name is:::"+city);
		logger.info("ID is:::"+id);
		
		
		conn.disconnect();
			
			
			
		
	}

}
