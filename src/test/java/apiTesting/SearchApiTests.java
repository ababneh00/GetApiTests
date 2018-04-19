package apiTesting;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import main.BaseClass;
import static org.hamcrest.Matchers.*;


public class SearchApiTests extends BaseClass {
	
	@Test
	public void searchReposByFullRepoName() throws URISyntaxException {	
		         String s = given().param("q", "SeleniumHQ/selenium")
				.accept(ContentType.JSON)
				.when()
				.get(new URI("repositories"))
				.thenReturn()
				.asString();	         
		         JsonPath json = new JsonPath(s);
		         Assert.assertTrue(json.getList("items.full_name").contains("SeleniumHQ/selenium"));
	}

	@Test
	public void searchReposByLanguage() throws URISyntaxException {
		Response response = given().param("q", "selenium+language:java").accept(ContentType.JSON).when()
				.get(new URI("repositories")).thenReturn();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(HttpStatus.SC_OK, statusCode);
		String expectedLanguage = "Java";
		List<String> jsonResponse = response.jsonPath().getList("items.language");
		for (String language : jsonResponse) {
			Assert.assertEquals(language, expectedLanguage);

    }	
}
	@Test
	public void searchArchivedReposOnly() throws URISyntaxException {        		
		        Response response = given()
		        				   .param("q", "selenium+archived:true+page:1")
		        				   .accept(ContentType.JSON)
		        				   .when()
		        				   .get(new URI("repositories"))
		        				   .thenReturn();
		    int statusCode = response.getStatusCode();
		    Assert.assertEquals(HttpStatus.SC_OK, statusCode);
		    List<Object> jsonResponse = response.jsonPath().getList("items.archived");
		    	for (Object archived : jsonResponse) {
		        Assert.assertEquals(archived, true);
	}
}
										
		        	
	// need to fix this one its not getting the dates 	        	
	@Test
	public void searchReposByCreatedDate() throws URISyntaxException {
		Response response = given()
				.param("q", "q=selenium+created:2018-04-01")
				.accept(ContentType.JSON)
				.when()
				.get(new URI("repositories"))
				.thenReturn();	         	
         	int statusCode = response.getStatusCode();
         	Assert.assertEquals(HttpStatus.SC_OK, statusCode);
         	String expectedDate = "2018-04-01"; 
         	List<String> jsonResponse = response.jsonPath().getList("items.created_at");
         		for (String date : jsonResponse) {
         			Assert.assertEquals(date, expectedDate);

   }	
	
}

	@Test
	public void searchReposByLicense() throws URISyntaxException {	
				Response response = given()
						.param("q", "java+license:apache-2.0+page:1")
						.accept(ContentType.JSON)
						.when()
						.get(new URI("repositories"))
						.thenReturn();	         	
		     int statusCode = response.getStatusCode();
		     Assert.assertEquals(HttpStatus.SC_OK, statusCode);
		     String expectedLanguage = "Apache License 2.0"; 
		     List<String> jsonResponse = response.jsonPath().getList("items.license.name");
		         	for (String license : jsonResponse) {
		         		Assert.assertEquals(license, expectedLanguage);
}
	}

	@Test
	public void searchPublicRepos() throws URISyntaxException {	
			    Response response = given()
					               .param("q", "selenium+is:public+page:1")
					               .accept(ContentType.JSON)
					               .when()
					               .get(new URI("repositories"))
					               .thenReturn();
			   int statusCode = response.getStatusCode();
         	   Assert.assertEquals(HttpStatus.SC_OK, statusCode);
	         	List<Object> jsonResponse = response.jsonPath().getList("items.private");
	         		for (Object isPrivate : jsonResponse) {
	         			Assert.assertEquals(isPrivate, false);		
      }
	}
	         		@Test
	         		public void searchPrivateRepos() throws URISyntaxException {	
	         				                        given()
	         						               .param("q", "selenium+is:private")
	         						               .accept(ContentType.JSON)
	         						               .when()
	         						               .get(new URI("repositories"))
	         						               .then()
	         						               .body("total_count", equalTo(0));
	         				             			
}
	
	@Test
	public void searchReposWithMirror() throws URISyntaxException {	
	         	Response response = given()
	         						.param("q", "java+mirror:true+page:1")
	         						.accept(ContentType.JSON)
	         						.when()
	         						.get(new URI("repositories"))
	         						.thenReturn();
	         				        int statusCode = response.getStatusCode();
	         	Assert.assertEquals(HttpStatus.SC_OK, statusCode);
	         	List<Object> jsonResponse = response.jsonPath().getList("items.mirror_url");
	         		for (Object mirror : jsonResponse) {
	         			Assert.assertNotEquals(mirror,  null);
	  }
}
	
	@Test
	public void searchReposByUserName() throws URISyntaxException {	
	         						given().param("q", "selenium+user:ipjohnson")
	         						.accept(ContentType.JSON)
	         						.when()
	         						.get(new URI("repositories"))
	         						.then()
	         						.body("items.owner.login", hasItem("ipjohnson"))
	         						.body("items.name", hasItem("SeleniumFixture"))
	         						.body("items.id", hasItem(27095129));
	}
}



	
	

		
	






	

