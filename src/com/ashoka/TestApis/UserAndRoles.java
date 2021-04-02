package com.ashoka.TestApis;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.MultiPartSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ashoka.apiresource.ApiParams;
import com.ashoka.apiresource.CreateRoleWithPermission;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UserAndRoles {
	// given - all input details
	// when - submit the API -resource,http method
	// Then - validate the response
	// https://www.tutorialspoint.com/how-to-check-multiple-regex-patterns-against-an-input-using-java
	// https://howtodoinjava.com/java/regex/java-regex-validate-email-address/

	//String token;

	
	public String userLogin(String user, String password,String tenant) {

		RestAssured.baseURI = "https://qm4.bifreedom.com/Login";
		String response = given().log().all().auth().preemptive().basic(user, password)
				.queryParam("tenantId", tenant).queryParam("environment", "dev")
				.header("Content-Type", "application/json").header("Accept-Encoding", "gzip").when().post("/v1.0/Login")
				.then().log().all().assertThat().statusCode(200).header("x-amz-apigw-id", matchesPattern("^(.+){16}$"))
				.extract().response().asString();
		//System.out.println(response);
		JsonPath js = new JsonPath(response);
		//token = js.getString("tokens.accessToken");
		//System.out.println();
		// String arr[] = js.getString("accessToken").split("=");
		// System.out.println(arr[0]);
		// token = arr[1];

		// token(token);
		return js.getString("tokens.accessToken");
	}

	@Test(enabled = false)
	public void createRoleWithPermission() {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://qm4.bifreedom.com/configurator";
		String response = given().log().all().header("Authorization", userLogin("Sathish","Sathish@88","SAT10004")).header("Accept", "application/json")
				.header("Content-Type", "application/json").body(CreateRoleWithPermission.permissionPayload).when()
				.post(CreateRoleWithPermission.ResourceURI).then().log().all().assertThat().statusCode(200)
				.body("uuid", matchesPattern("^[0-9]+$")).header("x-amz-apigw-id", matchesPattern("^(.+){16}$"))
				.extract().response().asString();
		System.out.println(response);
	}

	@Test(enabled = false)
	public void updateRoleWithPermission() {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://qm4.bifreedom.com/configurator";
		String response = given().log().all().header("Authorization", userLogin("Sathish","Sathish@88","SAT10004")).header("Accept", "application/json")
				.header("Content-Type", "application/json").body(CreateRoleWithPermission.permissionPayload).when()
				.post(CreateRoleWithPermission.ResourceURI).then().log().all().assertThat().statusCode(200)
				.body("uuid", matchesPattern("^[0-9]+$")).header("x-amz-apigw-id", matchesPattern("^(.+){16}$"))
				.extract().response().asString();
		System.out.println(response);
	}

	@Test(enabled = false)
	public void createRoleWithoutPermission() {
		RestAssured.baseURI = "https://qm4.bifreedom.com/configurator";
		String response = given().log().all().header("Authorization", userLogin("Sathish","Sathish@88","SAT10004")).header("Accept", "application/json")
				.header("Content-Type", "application/json").header("Accept-Encoding", "gzip")
				.body(CreateRoleWithPermission.permissionPayload).when().post(CreateRoleWithPermission.ResourceURI)
				.then().log().all().assertThat().statusCode(202).body("uuid", matchesPattern("^[0-9]+$"))
				.header("x-amz-apigw-id", matchesPattern("^(.+){16}$")).extract().response().asString();
		System.out.println(response);
	}

	@Test(enabled = false)
	public void permissionDetails() throws JsonProcessingException {
		RestAssured.baseURI = "https://qm4.bifreedom.com/configurator";
		String response = given().log().all().header("Authorization", userLogin("Sathish","Sathish@88","SAT10004")).header("Content-Type", "application/json")
				.header("Accept-Encoding", "gzip").when().get("/v1.0/user/permission").then().log().all().assertThat()
				.statusCode(200).header("x-amz-apigw-id", matchesPattern("^(.+){16}$")).extract().response().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		int len = js.getInt("data.size()");
		System.out.println("Length of data " + len);
		HashMap<Integer, String> map = new HashMap<>();
		for (int i = 0; i < len; i++) {
			System.out.println("data[" + i + "].id");
			int id = js.get("data[" + i + "].id");
			String name = js.get("data[" + i + "].name");
			ArrayList<String> permissionarr = new ArrayList<String>();
			permissionarr = js.get("data[" + i + "].childPermissions");
			System.out.println("permission " + permissionarr);
			String permission = permissionarr.toString();
			int start = permission.indexOf('[') + 1;
			int end = permission.indexOf(']') + 1;
			System.out.println(start + " " + end);
			String permissionDetails = "\"permission\" :" + permission.substring(start, end);
			System.out.println("permissions " + permissionDetails);
			map.put(id, name);
			if (!permissionarr.isEmpty()) {
				JsonPath js1 = new JsonPath(permissionDetails);
				int id1 = js1.getInt("id");
				String name1 = js1.getString("name");
				map.put(id1, name1);
			}
			// String json = new ObjectMapper().writeValueAsString(map);
			// JSONPObject jsonobj = new JSONPObject(json,map);

//			for(int i=0; i < array.length(); i++)   
//			{  
//			JSONObject object = array.getJSONObject(i);  
//			System.out.println(json);

		}
		String json = new ObjectMapper().writeValueAsString(map);
		System.out.println(json);
	}

	@Test(enabled = false)
	public void createUserProperty() {
		RestAssured.baseURI = "https://qm4.bifreedom.com/configurator";
		String response = given().log().all().header("Authorization", userLogin("Sathish","Sathish@88","SAT10004")).header("Content-Type", "application/json")
				.header("Accept-Encoding", "gzip").body(CreateRoleWithPermission.propertyPayload).when()
				.post("/v1.0/userproperties").then().log().all().assertThat().statusCode(200)
				.body("data", matchesPattern("^(.+)$")).header("x-amz-apigw-id", matchesPattern("^(.+){16}$")).extract()
				.response().asString();
		System.out.println(response);

	}

	@Test(enabled = false)
	public void getUserProperty() {
		RestAssured.baseURI = "https://qm4.bifreedom.com/configurator";
		String response = given().log().all().header("Authorization", userLogin("Sathish","Sathish@88","SAT10004")).header("Content-Type", "application/json")
				.header("Accept-Encoding", "gzip").when().get("/v1.0/user/permission").then().log().all().assertThat()
				.statusCode(200).body("data", matchesPattern("^(.+)$"))
				.header("x-amz-apigw-id", matchesPattern("^(.+){16}$")).extract().response().asString();
		System.out.println(response);

	}

	@Test(enabled = false, priority = 1)
	public void importUser() throws FileNotFoundException {
		// MultiPartSpecification file = (MultiPartSpecification) new
		// FileInputStream(System.getProperty("user.dir")+"\\Resource\\bulk.csv");
		RestAssured.baseURI = "https://qm4.bifreedom.com/configurator";
		String response = given().log().all().header("Authorization", userLogin("Sathish","Sathish@88","SAT10004"))
				.header("Content-Type", "multipart/form-data").header("Accept-Encoding", "gzip")
				.multiPart("bulk", new File(System.getProperty("user.dir") + "\\Resource\\bulk.csv")).when()
				.post("/v1.0/user/bulkuser").then().log().all().assertThat().statusCode(202)
				.body("data", matchesPattern("^(.+)$")).header("x-amz-apigw-id", matchesPattern("^(.+){16}$")).extract()
				.response().asString();
		System.out.println(response);

	}

	@Test(enabled = false, priority = 2)
	public void RequestQueueStatus() {
		// MultiPartSpecification file = (MultiPartSpecification) new
		// FileInputStream(System.getProperty("user.dir")+"\\Resource\\bulk.csv");
		RestAssured.baseURI = "https://qm4.bifreedom.com/configurator";
		String response = given().log().all().header("Authorization", userLogin("Sathish","Sathish@1988","SAT10004"))
				.header("Content-Type", "application/x-www-form-urlencoded").header("Accept-Encoding", "gzip").when()
				.get("/v1.0/apirequest/2228").then().log().all().assertThat().statusCode(200)
				.body("data", matchesPattern("^(.+)$")).header("x-amz-apigw-id", matchesPattern("^(.+){16}$")).extract()
				.response().asString();
		System.out.println(response);

	}

	@Test(enabled = false)
	public void Assign_User_to_Role() {
		// MultiPartSpecification file = (MultiPartSpecification) new
		// FileInputStream(System.getProperty("user.dir")+"\\Resource\\bulk.csv");
		RestAssured.baseURI = "https://qm4.bifreedom.com/configurator";
		String response = given().log().all().header("Authorization", userLogin("Sathish","Sathish@88","SAT10004"))
				.header("Content-Type", "application/x-www-form-urlencoded").header("Accept-Encoding", "gzip").when()
				.put("/v1.0/user/Sathish20/Tenant Admin/assignRole").then().log().all().assertThat().statusCode(200)
				.body("data", matchesPattern("^(.+)$")).header("x-amz-apigw-id", matchesPattern("^(.+){16}$")).extract()
				.response().asString();
		System.out.println(response);

	}

	@Test(enabled = false)
	public void Assign_MultipleUser_to_Role() {
		// MultiPartSpecification file = (MultiPartSpecification) new
		// FileInputStream(System.getProperty("user.dir")+"\\Resource\\bulk.csv");
		RestAssured.baseURI = "https://qm4.bifreedom.com/configurator";
		String response = given().log().all().header("Authorization", userLogin("Sathish","Sathish@88","SAT10004"))
				.header("Content-Type", "application/x-www-form-urlencoded").header("Accept-Encoding", "gzip")
				.body(CreateRoleWithPermission.multipleUserPayload).when().put("/v1.0/user/Tenant Admin/assignRole")
				.then().log().all().assertThat().statusCode(200).body("data", matchesPattern("^(.+)$"))
				.header("x-amz-apigw-id", matchesPattern("^(.+){16}$")).extract().response().asString();
		System.out.println(response);

	}

	@Test(enabled = false)
	public void getRoleDetails() throws IOException {
		// MultiPartSpecification file = (MultiPartSpecification) new
		// FileInputStream(System.getProperty("user.dir")+"\\Resource\\bulk.csv");
		String location = System.getProperty("user.dir")+"\\Resource\\Role Permissions.xls";
		System.out.println("file loacation "+location);
		File file = new File(location);
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = new HSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("Development");
		int size = sheet.getLastRowNum();
		System.out.println(size);
		System.out.println(sheet.getRow(2).getCell(3).getStringCellValue());
		
//		for (int i = 0; i < size; i++) {
//			System.out.println(sheet.getRow(2).getCell(4).getStringCellValue());
//		}
		RestAssured.baseURI = "https://qm4.bifreedom.com/configurator";
		String response = given().log().all().header("Authorization", userLogin("jannu","Sonam@786","TEC10003"))
				.when().get("v1.0/role/Developer").then().log()
				.all().assertThat().statusCode(200)
				.header("x-amz-apigw-id", matchesPattern("^(.+){16}$")).extract().response().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		//JsonObject jsonObject = js.get("detail.permissions");
		//ArrayList<String> array = new ArrayList<String>();
		//array = js.get("detail.permissions");
		//Map m = new LinkedHashMap(4); 
	//	m = new LinkedHashMap(2); 
        //m.putAll(js.get("detail.permissions"));
		System.out.println(js.get("detail.permissions").toString());
		ArrayList<JsonElement> jsonElement = js.get("detail.permissions");
		System.out.println("jsonElement"+jsonElement);
		String jsonElementGet1 = String.valueOf(jsonElement.get(1));
		System.out.println("jsonElementGet1"+jsonElementGet1);
		HashMap<String,String> map = new HashMap<String, String>(); 
		//map.put("id", "val1");
		//map.put("id1", "val1");
		//map.put("id2", "val1");
		//map.put("id3", "val1");
		map.putAll(map);
		System.out.println(map);
//		 Iterator itr2 = ja.iterator(); 
//         
//	        while (itr2.hasNext())  
//	        { 
//	               // Map.Entry pair = itr1.next(); 
//	                System.out.println(js.getString("permissionId") + " : " +js.getString("access")); 
//	          
//	        } 
		//Iterator itr = array.iterator();
		//array.addAll(js.get("detail.permissions"));
		//LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
//		for(String str: array)
//		{
//			System.out.println("Value "+str);
//		}
		//int len = jsonObject.size();
		//System.out.println("permission "+jsonObject);
		//System.out.println("length "+len);
//		for (int i = 0; i < len; i++) {
//			int id = js.get("permissions["+i+"].permissionId");
//			int access = js.get("permissions["+i+"].access");
//			System.out.println("SNO "+i);
//			System.out.print(id);
//			System.out.print(access);
//		}
		wb.close();
	}
	
	@Test(enabled = false)
	public void uploadToDrive() throws IOException {
		// MultiPartSpecification file = (MultiPartSpecification) new
		// FileInputStream(System.getProperty("user.dir")+"\\Resource\\bulk.csv");
		File file = new File("C:\\Users\\AppsFreedom3\\Desktop\\sapjco3.dll");
		RestAssured.baseURI = "http://drive.afm-dev.s3.amazonaws.com/FRA110087/sapjco3.dll?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEBUaCXVzLWVhc3QtMSJHMEUCIQDOcIVgrAFXS7vf6nmJ20i92cgGeXVzhoZuE1M%2BdPhI1AIgcw1HTsGuyKC0s3og5s7SI%2B9%2BjWv7XZ3%2FkwE%2F9w5aH%2FkqtAMIXhAAGgw4NTgxNzgwNjE5NTYiDHRSP8%2BOVmqdW7v5QyqRA0sp40skEPGCg6YzyPe5p6e1nmr1q7g%2BWIu3t67fiB9Zl5VC7MznLFQMpLTcPszb8XcuPmcTJqKtsQcyvCpGXJSubkypkKu5JD6DxXi3mppdKLRBzYxoiwALO4vprbykTUtdUzPo3cbqTbTv6WJ59mcdHsP0oZOyqWqb%2BIaqagN1iN%2BcyAIQHb33etCBVRfZ56JscY2TsxMv%2BQ2HVftmVorBfbUPtB%2Be1uq1HD3OKnknZ4lzxgD0REl9l3P52LxNzaF7lE%2BHKFZErPKIzFRwwbyQWonGZyG7z37wnPVmLDwGk2WR2mKTDCe56u8z72ZsCYOt9CPLZmI9TLiGJtQ%2F9W%2BPfvmDoSfflRyj1K8PIhCTAHLJs%2B%2BAVxKVyVYwHNgesbscVi%2B36t4jnMuLDtch79apanNan6wxbkpv%2B1pIDOTmyccvkR%2BP9JVqf5odkFtOu%2Ftnb9acaWOzPyN5bDujN5XklMpInzLGMn5OPR%2BxzwarhFhYCNsLPvnsN10ska49KaHmiRee4k%2BNW5H7TF8kKH%2FZMJeYh4MGOusBNH4xZBDRJ8FVzJpxfbtf760mwn7cK69LDpbWRPhamxRIUzGGoN4BMRuyksOPW864clzkKbypFUor6P8gNP4I9X%2FaHiQdMIfa9Fafm2AKOaOY87CLsNIlh2dFro6A2dUX1tckKZ5Ynzf1PGzPM26jFz3PX%2FaOic%2F1%2BMEnTAFW7%2BNPTs80IDInYazxdkOWCTjYog6sufwL14vot09WUAOragcvyBqK1xUb5u4mQqj%2FFYi8TeVngCo8jUAN0zNQSez3qoXU28I9ieJPTgpQavHQB4HZ66JyZ2T9ElfGHks%2BA5qmSM5XM5JanhPR%2Fw%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20210329T134144Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3599&X-Amz-Credential=ASIA4PT3GA2CPLNW4B74%2F20210329%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=81e4d7d7e11d05af700558bb8fdbad67fb311d5942d0876d4a4a2969bf5e9d36";
		String response = given().log().all()
				.multiPart("sapjco3", file)
				.when().get().then().log()
				.all().assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println(response);
		//JsonPath js = new JsonPath(response);

	}
	
	@Test(enabled = true)
	public void Update_Project_Details() {
		// MultiPartSpecification file = (MultiPartSpecification) new
		// FileInputStream(System.getProperty("user.dir")+"\\Resource\\bulk.csv");
		RestAssured.baseURI = "https://qm4.bifreedom.com/abap";
		String response = given().log().all().header("Authorization", userLogin("Sathish","Sathish@1988","SAT10004"))
				.header("Content-Type", "application/x-www-form-urlencoded").header("Accept-Encoding", "gzip")
				.body("{\r\n" + 
						"					  \"projectId\":\"12\",\r\n" + 
						"					  \"pluginId\":\"1\",\r\n" + 
						"					  \"rfcId\":\"1\",\r\n" + 
						"					  \"name\":\"test\",\r\n" + 
						"					  \"id\":\"1\"\r\n" + 
						"					}").when().post("/v1.0/abap/updateProjDetails")
				.then().log().all().assertThat().statusCode(200).body("update response", matchesPattern("^(.+)$"))
				.header("x-amz-apigw-id", matchesPattern("^(.+){16}$")).extract().response().asString();
		System.out.println(response);

	}
}
