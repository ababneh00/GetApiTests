package main;

import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;


public class BaseClass {
	
	@BeforeClass
	public static void setUp() {
		baseURI="https://api.github.com/";
		basePath="search/";
		
	}

}
