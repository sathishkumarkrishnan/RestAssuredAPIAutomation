package com.ashoka.TestApis;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;

import org.testng.annotations.Test;

import com.ashoka.apiresource.ApiParams;
import com.ashoka.apiresource.CreateRoleWithPermission;
import org.testng.annotations.BeforeTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Login{
	
	static String token;
	
	@BeforeTest
	public void userLogin()
	{
		ApiParams apiParams = new ApiParams();
		RestAssured.baseURI="https://qm4.bifreedom.com/Login";
		String response=given().log().all().auth().preemptive().basic("Sathish", "Sathish@88")
		.queryParam("tenantId", "SAT10002")
		.queryParam("environment", "dev")
		.header("Content-Type", "application/json")
		.header("Accept-Encoding","gzip")
		.when().post("/v1.0/Login")
		.then().log().all().assertThat().statusCode(200).body("tokens", matchesPattern("^(.+)$"))
		.header("x-amz-apigw-id", matchesPattern("^(.+){16}$"))
		.extract().response().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		token = js.getString("accessToken");
		//token(token);
	}
	
	public static String token() 
	{
		String myToken = token;
		return myToken;
	}
}
