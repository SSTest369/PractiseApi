package com.almosafer.testcases;
import com.almosafer.activators.AsyncSearchApiActivator;
import com.almosafer.model.SearchRequest;
import io.restassured.response.Response;

import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class AsyncSearchTests extends AsyncSearchApiActivator {

	private static final Logger log = LogManager.getLogger(AsyncSearchTests.class);
	
	@ParameterizedTest
	@MethodSource("loadCity")
    public void verifyAsyncSearchResponse(String destination){
        SearchRequest requestBody = buildSearchRequest("2026-10-25","2026-10-26",destination);
        Response response  = sendPostRequest(requestBody);
        assertThatStatusCodeEquals(response,200);
        
    }
	
	static Stream<Arguments> loadCity(){
		return Stream.of(
				Arguments.of("Dubai"),
				Arguments.of("London")
				);
	}

}
