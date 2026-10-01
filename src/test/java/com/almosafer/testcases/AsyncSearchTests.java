package com.almosafer.testcases;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.almosafer.activators.AsyncSearchApiActivator;
import com.almosafer.model.SearchRequest;
import com.almosafer.utils.TestData;

import io.restassured.response.Response;

public class AsyncSearchTests extends AsyncSearchApiActivator {

	private static final Logger log = LogManager.getLogger(AsyncSearchTests.class);
	
	@ParameterizedTest
	@MethodSource("loadCity")
    public void verifyAsyncSearchResponse(String destination){		
        SearchRequest requestBody = buildSearchRequest(TestData.CHECK_IN,TestData.CHECK_OUT,destination);
        Response response  = sendPostRequest(requestBody);
        assertThatStatusCodeEquals(response,200);
        response.then().body(matchesJsonSchemaInClasspath("response.json"));
    }
	
	@Test
	 public void verifyNegativeAsyncSearchResponse(){		
	        SearchRequest requestBody = buildSearchRequest(TestData.daysFromToday(-10),TestData.daysFromToday(-5),TestData.QUERY);
	        Response response  = sendPostRequest(requestBody);
	        assertThatStatusCodeEquals(response,400);
	        
	    }
		
	
	static Stream<Arguments> loadCity(){
		return Stream.of(
				Arguments.of("Dubai"),
				Arguments.of("London")
				);
	}

}
