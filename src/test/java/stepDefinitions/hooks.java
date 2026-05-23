package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class hooks {
	
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		AddPlaceStepDefinition m=new AddPlaceStepDefinition();
		if(AddPlaceStepDefinition.place_id==null)
		{
		m.add_place_payload_with("Shetty", "Bengali", "Bandra");
		m.user_calls_with_http_request("AddPlaceAPI", "post");
		m.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
		}
	}

}
