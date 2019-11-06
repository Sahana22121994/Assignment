package api;

import junit.framework.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserAPI {
	RequestSpecification httpRequest;
	Response response;	

	public void insertBody(String reqBody) {
		httpRequest.body(reqBody);
	}

	public void sendPostRequest(String url, String reqBody) {
		httpRequest = RestAssured.given();		
		insertBody(reqBody);
		response = httpRequest.given().log().all().post(url);
	}

	public void sendGetRequest(String url) {
		httpRequest = RestAssured.given();
		response = httpRequest.given().log().all().get(url);
	}

	public void sendPutRequest(String url, String reqBody) {
		httpRequest = RestAssured.given();
		insertBody(reqBody);
		response = httpRequest.given().log().all().put(url);
	}

	public void responseStatusCode(int statuscode) {
		Assert.assertEquals(statuscode, response.getStatusCode());
		System.out.println(response.getStatusCode());
	}

	public void responseValidation(String responseBody) {		
		System.out.println(response.asString());
		Assert.assertTrue("Response is empty",response.asString()!=null);
		Assert.assertEquals(responseBody, response.asString());
	}
	
	public void respValidation(String responseBody){
		System.out.println(response.asString());
		Assert.assertTrue("Response donot match",
				response.asString().contains(responseBody));
	}

	public String resp() {
		String array[] = response.asString().split("\"");
		return array[3];

	}
}
