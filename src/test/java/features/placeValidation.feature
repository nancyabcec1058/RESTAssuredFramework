Feature: Validating Place API

@AddPlace @Regression
Scenario Outline: Verify place API is successfully added
  Given add place payload with "<name>" "<language>" "<Address>"
  When user calls "AddPlaceAPI" with "post" http request
  Then response with api call is success with status code 200
  And "status" in response body is "OK"
  And "scope" in response body is "APP"
  And verify place_id created maps to "<name>" using "getPlaceAPI"
  
  Examples:
    |name    |language  |Address  |
    |Rahul   |Kannada   |Kerala   |
   #|Mannat  |Bhojpuri  |merut    |
 
 @DeletePlace @Regression 
 Scenario: Verify place API is successfully deleted
  Given delete place payload 
  When user calls "deletePlaceAPI" with "post" http request
  Then response with api call is success with status code 200
  And "status" in response body is "OK"