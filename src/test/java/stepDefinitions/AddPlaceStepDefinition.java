package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.testDataBuild;
import resources.utils;

public class AddPlaceStepDefinition extends utils {
	RequestSpecification reqs;
	ResponseSpecification resspec;
	Response response;
	static String place_id;

	testDataBuild data = new testDataBuild();

	@Given("add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String Address) throws IOException {

		reqs = given().spec(requestspecifications()).body(data.addPlacePayload(name, language, Address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		APIResources resourceAPI = APIResources.valueOf(resource);

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST"))
			response = reqs.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = reqs.when().get(resourceAPI.getResource());
	}

	@Then("response with api call is success with status code {int}")
	public void response_with_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.statusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectValue) {

		assertEquals(getJsonPath(response, keyValue), expectValue);
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id = getJsonPath(response, "place_id");
		reqs = given().spec(requestspecifications()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String name = getJsonPath(response, "name");
		assertEquals(name, expectedName);

	}
	@Given("delete place payload")
	public void delete_place_payload() throws IOException {
		reqs = given().spec(requestspecifications()).body(data.deletePlacePayload(place_id));
	}


}
