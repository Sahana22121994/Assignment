Feature: Weather API Automation

  #1--------------------------------------------------------------------------------------------------------------------------------------
 
 Scenario: Verify Get call for all request users
 Given a valid url to fetch all "request users"
 When the "GET" request is sent to the API
 Then the response should be generated for "all users" and should return 200 status code
 
  Scenario: Verify Get call for required request users
 Given a valid url to fetch "request users" with id as "1"
 When the "GET" request is sent to the API
 Then the response should be generated for "required users" and should return 200 status code

   Scenario: Verify Post call for all request users
 Given a valid url to fetch all "request users"
 When the "POST" request is sent to the API
 Then the response should return 201 status code with message "id"
 
    Scenario: Verify Post call for register users
 Given a valid url to fetch all "register users"
 When the "POST" request is sent to the API
 Then the response should return 400 status code with message "Missing email or username"
 
     Scenario: Verify Put call for  required request users
 Given a valid url to fetch "request users" with id as "2"
 When the "PUT" request is sent to the API
  Then the response should return 200 status code with message "updatedAt"