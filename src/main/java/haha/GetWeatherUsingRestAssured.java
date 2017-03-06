package haha;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

//import org.apache.log4j.Logger;


//@JsonIgnoreProperties(ignoreUnknown = true)
public class GetWeatherUsingRestAssured {

	private static final Logger logger = Logger.getLogger(GetWeatherUsingRestAssured.class);

	@Test
	public void testResponse() throws IOException{
		String url = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=532a84a74eb1e41fd0a703e7250ea183";
		logger.info("Svc Endpoint:::" + url);
		
		expect().body("name", equalTo("London")).when().get(url);	
		Response response = get(url).then().contentType(ContentType.JSON).extract().response();
		
//		JSONObject obj = new JSONObject(response);
		logger.info("Response:::" + response.asString());
			
		
	}

}
