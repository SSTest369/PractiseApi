package com.almosafer.activators;

import static com.almosafer.utils.ApiConstants.CAROUSEL;
import static com.almosafer.utils.ApiConstants.TOKEN;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.almosafer.model.CarouselRequest;
import com.almosafer.model.SearchRequest;

import io.restassured.http.Method;
import io.restassured.response.Response;

public class CarouselRequestActivator extends BaseApiActivator {
	
	public CarouselRequest buildCarouselRequest() {
	CarouselRequest request = CarouselRequest.builder()
	        .cityId(1)
	        .checkIn("2026-10-10")
	        .checkOut("2026-10-15")
	        .addRoom(CarouselRequest.RoomInfos.builder()
	                .adultsCount(2)
	                .kidsAges(List.of(5, 8))
	                .build())
//	        .addRoom(CarouselRequest.RoomInfos.builder()
//	                .adultsCount(1)
//	                .build())
	        .build();
	return request;
	}
    public Response sendPostRequest(CarouselRequest requestBody) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("TOKEN", "skdjfh73273$7268u2j89s");
        setHeaders(headerMap);
        setRequestBody(convertPojoToString(requestBody));
        return sendAPIRequest(Method.POST, CAROUSEL);
    }


}
