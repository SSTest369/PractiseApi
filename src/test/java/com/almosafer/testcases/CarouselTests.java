package com.almosafer.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.almosafer.activators.CarouselRequestActivator;
import com.almosafer.model.CarouselRequest;

import io.restassured.response.Response;

public class CarouselTests extends CarouselRequestActivator {
	private static final Logger log = LogManager.getLogger(AsyncSearchTests.class);
    @Test
    public void verifyCaroselRequest(){
    	CarouselRequest requestBody = buildCarouselRequest();
        Response response  = sendPostRequest(requestBody);
        assertThatStatusCodeEquals(response,200);
        
    }
	
}
