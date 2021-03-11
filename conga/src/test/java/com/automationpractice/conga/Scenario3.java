package com.automationpractice.conga;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Random;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Scenario3 {
	
	static String apiKey;
	
	JSONObject requestStation1 = new JSONObject();
	JSONObject requestStation2 = new JSONObject();	
	String[] id = new String[2];

	@BeforeTest
	public static void setStation() {
		baseURI = "http://api.openweathermap.org";
		apiKey = "b1c0c7b628e6d98b2735cbde40d09cfc";
	}

	@Test(enabled = true, priority=0)
	public void registerWithoutApiPOST()
	{
		JSONObject requestStation = new JSONObject();
		requestStation.put("external_id" , "DEMO_TEST001");
		requestStation.put("name", "Interview Station999");
		requestStation.put("latitude", 33.33);
		requestStation.put("longitude", -111.43);
		requestStation.put("altitude", 444);
		
		given()
			.header("Content-Type", "application/json")
			.body(requestStation.toString())
		.when()
			.post("/data/3.0/stations")
		.then()
			.statusCode(401)
			.body("message", equalTo("Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."))
			.log().body();
		 
	}

	@Test(enabled = true, priority=1)
	public void registerWithApiPOST()
	{
		Random rand = new Random();

		requestStation1.put("external_id" , "DEMO_TEST001");
		requestStation1.put("name", "Interview Station"+rand.nextInt(1000));
		requestStation1.put("latitude", 33.33);
		requestStation1.put("longitude", -111.43);
		requestStation1.put("altitude", 444);
		
		requestStation2.put("external_id" , "Interview1");
		requestStation2.put("name", "Interview Station"+rand.nextInt(1000));
		requestStation2.put("latitude", 33.44);
		requestStation2.put("longitude", -12.44);
		requestStation2.put("altitude", 444);
		
		Response response1 = given()
								.queryParam("appid", apiKey)
								.header("Content-Type", "application/json")
								.body(requestStation1.toString())
								.post("/data/3.0/stations");
		
		Response response2 = given()
								.queryParam("appid", apiKey)
								.header("Content-Type", "application/json")
								.body(requestStation2.toString())
								.post("/data/3.0/stations");
		
		Assert.assertEquals(response1.statusCode(), 201);
		Assert.assertEquals(response2.statusCode(), 201);
		
		System.out.println(response1.body().asString());
		System.out.println(response2.body().asString());
		
		//Capture registration id of stations
		id[0] = response1.path("ID");
		id[1] = response2.path("ID");
		
	}
	
	@Test(enabled=true, priority=2)
	public void verifyRegisteredStationsGET()
	{
		given()
			.param("appid", apiKey)
			.header("Content-Type", "application/json")
		.when()
			.get("/data/3.0/stations/"+id[0])
		.then()
			.body("external_id", equalTo(requestStation1.getString("external_id")))
			.body("latitude", equalTo(requestStation1.getFloat("latitude")))
			.body("longitude", equalTo(requestStation1.getFloat("longitude")))
			.body("altitude", equalTo(requestStation1.getInt("altitude")))
			.log().body();		
		
		given()
			.param("appid", apiKey)
			.header("Content-Type", "application/json")
		.when()	
			.get("/data/3.0/stations/"+id[1])
		.then()
			.body("external_id", equalTo(requestStation2.getString("external_id")))
			.body("latitude", equalTo(requestStation2.getFloat("latitude")))
			.body("longitude", equalTo(requestStation2.getFloat("longitude")))
			.body("altitude", equalTo(requestStation2.getInt("altitude")))
			.log().body();
	}
	
	@Test(enabled=true, priority=3)
	public void deleteStationDELETE()
	{	
		given()
			.param("appid", apiKey)
			.header("Content-Type", "application/json")
			.delete("/data/3.0/stations/"+id[0])
		.then()
			.statusCode(204)
			.log().body();
		
		given()
			.param("appid", apiKey)
			.header("Content-Type", "application/json")
			.delete("/data/3.0/stations/"+id[1])
		.then()
			.statusCode(204)
			.log().body();
		
	}
	
	@Test(enabled=true, priority=4)
	public void deleteDeletedStationDELETE()
	{	
		given()
			.param("appid", apiKey)
			.header("Content-Type", "application/json")
			.delete("/data/3.0/stations/"+id[0])
		.then()
			.statusCode(404)
			.body("message",equalTo("Station not found"))
			.log().body();
		
		given()
			.param("appid", apiKey)
			.header("Content-Type", "application/json")
			.delete("/data/3.0/stations/"+id[1])
		.then()
			.statusCode(404)
			.body("message",equalTo("Station not found"))
			.log().body();
		
	}
	
}
