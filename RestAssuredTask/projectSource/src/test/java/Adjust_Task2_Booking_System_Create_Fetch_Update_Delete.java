import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import org.apache.tools.ant.types.Assertions;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.json.simple.JSONObject;
import org.junit.Before;

public class Adjust_Task2_Booking_System_Create_Fetch_Update_Delete {
	
	int id;
	String token;
	
	@BeforeClass
	public void setup() {
		//Set the baseURI that will used throughout the methods
	   RestAssured.baseURI = "https://automationintesting.online/";
	}
	
	@Test
	void test_1a_cannot_create_booking_with_bigger_start_date() {
		
	//Verify booking can not be created when checkin date is greater than checkout date
	
	File jsonfile2 = new File(System.getProperty("user.dir") + File.separator + "restassured_test2.json");	
	  
	given().
    	header("Content-Type", "application/json").
     	body(jsonfile2).
    when().
    	post("/booking/").
    then().
    	statusCode(409);
	}
		
	
	@Test
	void test_1b_create_successful_booking() {

	//Validate successful booking creation and check response code and values from response
		
	File jsonfile1 = new File(System.getProperty("user.dir") + File.separator + "restassured_test1.json");

	Response response1 =
    given().
    	header("Content-Type", "application/json").
     	body(jsonfile1).
    when().
    	post("/booking/").
    then().
    	statusCode(201).
    	assertThat().
        body("booking.firstname", equalTo("Cinthia")).
		body("booking.lastname", equalTo("Pereira")).
		body("booking.roomid", equalTo(1)).
		body("booking.bookingdates.checkin", equalTo("2020-12-28")).
		body("booking.bookingdates.checkout", equalTo("2020-12-29")).
    extract().
    	response();
	//store booking id for further use
	id = response1.path("bookingid");
	
	//Verify same booking cannot be created again (same room, same date)
	
	given().
		header("Content-Type", "application/json").
		body(jsonfile1).
	when().
		post("/booking/").
	then().
		statusCode(409);
}


	@Test
	void test_2_fetch_created_booking_by_id() {

		//Fetch created booking by id and verify values from response JSON
	    
	    given().
			get("/booking/" + id).
	    then().
			statusCode(200).
			assertThat().
			body("firstname", equalTo("Cinthia")).
			body("lastname", equalTo("Pereira")).
			body("roomid", equalTo(1)).
			body("bookingdates.checkin", equalTo("2020-12-28")).
			body("bookingdates.checkout", equalTo("2020-12-29"));
	}
	
	@Test
	void test_3_at_least_one_booking_returned_when_fetching_all_bookings() {
		
		//Verify that at least one booking (by 'bookingid' occurrences on response JSON) is returned when fetching all bookings
		
		given().
			get("/booking/").
	    then().
			statusCode(200).
			assertThat().
			body("bookings.bookingid",
					hasSize(greaterThanOrEqualTo(1)));
	}
	
	@Test
	void test_4_update_created_booking() {
		
		//Update an existing booking (by booking id) and verify new updated value
		//Currently API returns 403 (forbidden)
		
		File jsonfile4 = new File(System.getProperty("user.dir") + File.separator + "restassured_test4.json");
		
	    given()
	    .auth()
	    .basic("admin", "password123")
	    	.header("Content-Type", "application/json").
	     	body(jsonfile4).
	    when().
	    	put("/booking/" + id).
	    then().
	    	statusCode(200).
	    	assertThat()
	        .body("booking.lastname", equalTo("Barbosa"));
	}
	
	@Test
	void test_5_delete_created_booking() {
		
		//Delete the existing booking by id
		//Currently API returns 403 (forbidden)
		
		 given()
		 .auth()
		    .basic("admin", "password123").
		     //contentType("application/json").	
		    delete("/booking/" + id).
	     then().
			statusCode(200);
		 
		 //Now, fetch deleted booking by id and verify response code to be 404 as booking was not found
		 
		 given().
			get("/booking/" + id).
	    then().
			statusCode(404);	
	}
	
	
}
