package stepDefinitions;

import java.io.FileInputStream;


import api.UserAPI;

import java.util.HashMap;
import java.util.Properties;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserAPISteps {
	static FileInputStream systemInput;
	static Properties prop = new Properties();
	UserAPI userAPI = new UserAPI();
	String url;	

	@Given("^a valid url to fetch all \"([^\"]*)\"$")
    public void a_valid_url_to_fetch_all_request_users(String requestUrl) throws Throwable {
		systemInput = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\files\\API.properties");
		prop.load(systemInput);		
		switch(requestUrl){
		case "request users":url=prop.getProperty("User.URL");
			break;
		case "register users":url=prop.getProperty("Register.URL");
		break;
		}
    }

    @When("^the \"([^\"]*)\" request is sent to the API$")
    public void the_something_request_is_sent_to_the_api(String method) throws Throwable {
    	systemInput = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\files\\API.properties");
		prop.load(systemInput);
       switch(method){
       case "GET":userAPI.sendGetRequest(url);
    	   break;
       case "POST":if(url.contains("users")){
    	   userAPI.sendPostRequest(url, prop.getProperty("User.body"));
       }else{
    	   userAPI.sendPostRequest(url, prop.getProperty("Register.body"));
       }
	   break;
       case "PUT":userAPI.sendPutRequest(url,prop.getProperty("User.body"));
	   break;
       }
    }

    @Then("^the response should be generated for \"([^\"]*)\" and should return (\\d+) status code$")
    public void the_response_should_contain_6_data_objects_and_should_return_200_status_code(String respbody,int responsecode) throws Throwable {
    	userAPI.responseStatusCode(responsecode);
    	systemInput = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\files\\API.properties");
		prop.load(systemInput);
    	switch(respbody){
    	case "all users":userAPI.responseValidation(prop.getProperty("User.Response1"));
    	break;
    	case "required users":userAPI.responseValidation(prop.getProperty("User.Response2"));
    	break;
    	
    	}    	
    }
    
    @Given("^a valid url to fetch \"([^\"]*)\" with id as \"([^\"]*)\"$")
    public void a_valid_url_to_fetch_something_with_id_as_something(String requestUrl, String id) throws Throwable {
    	systemInput = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\files\\API.properties");
		prop.load(systemInput);		
		switch(requestUrl){
		case "request users":url=prop.getProperty("User.URL")+"/"+id;
			break;		
		}
    }
    
    @Then("^the response should return (\\d+) status code with message \"([^\"]*)\"$")
    public void the_response_should_return_201_status_code_with_message_something(int responsecode,String responseBody) throws Throwable {
    	userAPI.responseStatusCode(responsecode);
    	userAPI.respValidation(responseBody);
    }
}
