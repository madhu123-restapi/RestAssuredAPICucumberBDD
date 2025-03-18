package com.testautomation.apitesting.stepdefs;

//import static org.testng.Assert.assertEquals;

import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
//import com.jayway.jsonpath.JsonPath;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
//import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import static org.junit.Assert.*;

import org.testng.Assert;

import com.testautomation.apitesting.listener.RestAssuredListener;
import com.testautomation.apitesting.utility.BaseTest;


public class BookingAPI extends BaseTest {
	
	public RequestSpecification httpRequest;
	public Response response;
	public int ResponseCode;
	public ResponseBody body;
	public static int bookingId;
	public static JSONObject booking;
	public static JSONObject bookingDates;
	public JSONObject tokenDetails;
	public static String token;
	
	private static final Logger logger = LogManager.getLogger(BookingAPI.class);
	
	//get the bookingids
	@Given("hit the url of get booking ids api endpoint")
	public void hit_the_url_of_get_booking_ids_api_endpoint() {
			    
		RestAssured.baseURI = "https://restful-booker.herokuapp.com/";	
	}

	@When("pass the url of getbookingid in the request")
	public void pass_the_url_of_getbookingid_in_the_request() {
	   
		httpRequest = RestAssured.given();
		httpRequest.filter(new AllureRestAssured())
					.filter(new RestAssuredListener());
		response = httpRequest.get("booking");		
		JsonPath jsnpath = response.jsonPath();
		bookingId = jsnpath.getJsonObject("bookingid[0]");
		//System.out.println("BookingId : " + bookingId);
		
	}
	
	//get api call
	@Given("hit the url of get booking details api endpoint")
	public void hit_the_url_of_get_booking_details_api_endpoint() {		
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking/";		
		
	}
	
	@When("pass the url of bookingid in the request")
	public void pass_the_url_of_bookingid_in_the_request() {
		
		Allure.epic("BookingAPI");
		httpRequest = RestAssured.given()
					.filter(new AllureRestAssured())
					.filter(new RestAssuredListener());
		//System.out.println("BookingId : " + bookingId);
		response = httpRequest.get("{bookingId}", bookingId);
		body = response.getBody();
		//convert response body to string
		String responseBody = body.asString();
		//System.out.println(responseBody);
		JsonPath jsnpath = response.jsonPath();
		String firstName = jsnpath.getJsonObject("firstname").toString();
		//System.out.println("First Name : " + firstName);
	    
	}
	
	@Then("receive the response code as {int}")
	public void receive_the_response_code_as(Integer int1) {
		
		ResponseCode = response.getStatusCode();		
		Assert.assertEquals(ResponseCode, 200);
		
	}
	
	@Then("verify that the firstname for the passed bookingid is {}")
	public void verify_that_the_firstname_for_the_passed_bookingid_is(String firstname) {
		
		//Allure.parameter("FirstName", firstname);
		ResponseCode = response.getStatusCode();		
		Assert.assertEquals(ResponseCode, 200);
		
		//String responseBody = body.asString();		
		//System.out.println("Response : " + responseBody);
		
		JsonPath jsnpath = response.jsonPath();
		String fName = jsnpath.getJsonObject("firstname").toString();		
		//System.out.println("First Name : " + fName);
		
		Assert.assertEquals(firstname, fName);		
		
	}
 	
	//post api call
	@Given("hit the url of post booking api endpoint")
	public void hit_the_url_of_post_booking_api_endpoint() {
		
		logger.info("BookingAPI test execution started....");
		RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
		
	}

	@When("pass the url of create booking in the request")
	public void pass_the_url_of_create_booking_in_the_request() {
		
		//prepare request body
		booking = new JSONObject();
		bookingDates = new JSONObject();		
		
		httpRequest = RestAssured.given()
				.contentType(ContentType.JSON);				
		
	}

	@And("pass the request body of bookingAPI {}")
	public void pass_the_request_body_of_bookingAPI(String firstname) {
		
		//Allure.parameter("FirstName", firstname);
		
		booking.put("firstname", firstname);
		booking.put("lastname", "tutorial");
		booking.put("totalprice", 1000);
		booking.put("depositpaid", true);
		booking.put("additionalneeds", "breakfast");
		booking.put("bookingdates", bookingDates);
		
		bookingDates.put("checkin", "2023-03-25");
		bookingDates.put("checkout", "2023-03-30");		
		
		httpRequest.filter(new AllureRestAssured())
					.filter(new RestAssuredListener())
					.body(booking.toString());
		response = httpRequest.post("booking");
		
		body = response.getBody();
		
		JsonPath jsnpath = response.jsonPath();
		bookingId = jsnpath.getJsonObject("bookingid");
		
		//System.out.println(response.getStatusLine());
		//System.out.println(body.asString());
	}

	//token generation
	@Given("hit the url of token generation api endpoint")
	public void hit_the_url_of_token_generation_api_endpoint() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com/auth";
	}

	@When("pass the url of token generation in the request")
	public void pass_the_url_of_token_generation_in_the_request() {
		
		//prepare request body
		tokenDetails = new JSONObject();	
		
		httpRequest = RestAssured.given()
					.contentType(ContentType.JSON);	   
		
	}
	
	@And("pass the request body of token generation")
	public void pass_the_request_body_of_token_generation() {
		
		tokenDetails.put("username", "admin");
		tokenDetails.put("password", "password123");
		
		httpRequest.filter(new AllureRestAssured())
					.filter(new RestAssuredListener())
					.body(tokenDetails.toString());
		response = httpRequest.post();
		
		JsonPath jsnpath = response.jsonPath();
		token = jsnpath.getJsonObject("token").toString();
		
		//System.out.println("Token : "+token);
	}

	//put api call
	@Given("hit the url of put booking api endpoint")
	public void hit_the_url_of_put_booking_api_endpoint() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking";
				
	}

	@When("pass the url of booking api in the request")
	public void pass_the_url_of_booking_api_in_the_request() {		
		
		httpRequest = RestAssured.given();
		httpRequest.contentType(ContentType.JSON);	
		httpRequest.header("Cookie", "token="+token);
		
	}
	
	@And("pass the request body of put bookingAPI {}")
	public void pass_the_request_body_of_put_booking_api(String fname) {
		
		//Allure.parameter("FirstName", fname);
		
		booking.put("firstname", fname);
		booking.put("lastname", "Selenium C#");
		booking.put("totalprice", 111);
		booking.put("depositpaid", true);
		booking.put("additionalneeds", "super bowls");
		booking.put("bookingdates", bookingDates);
		
		bookingDates.put("checkin", "2018-01-01");
		bookingDates.put("checkout", "2019-01-01");
		
		httpRequest.filter(new AllureRestAssured())
					.filter(new RestAssuredListener())
					.body(booking.toString());
		response = httpRequest.put("/{bookingId}",bookingId);
		
		body = response.getBody();
		
		//System.out.println(body.asString());
		
		JsonPath jsnpath = response.jsonPath();
		String fName = jsnpath.getJsonObject("firstname").toString();		
		//System.out.println("First Name : " + fName);
		
		Assert.assertEquals(fName, fname);
	}

	//delete api call
	@Given("hit the url of delete booking details api endpoint")
	public void hit_the_url_of_delete_booking_details_api_endpoint() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking";
		
	}
	
	@Then("receive the delete response code as {int}")
	public void receive_the_delete_response_code_as(Integer int2) {	 
		
		logger.info("BookingAPI test execution ended...");
		ResponseCode = response.getStatusCode();		
		Assert.assertEquals(ResponseCode, 201);
		
	}

	@When("pass the url of delete booking api in the request")
	public void pass_the_url_of_delete_booking_api_in_the_request() {
	    
		httpRequest = RestAssured.given().filter(new AllureRestAssured())
				.filter(new RestAssuredListener())
				.contentType(ContentType.JSON)
				.header("Cookie", "token="+token);
		//System.out.println("Token : " + token);
		//System.out.println("BookingID to delete : " + bookingId);
		response = httpRequest.delete("/{bookingId}", bookingId);
				
	}
}
