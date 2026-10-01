package com.almosafer.testcases;

import org.junit.jupiter.api.Test;

import com.almosafer.activators.PackagesApiActivator;
import com.almosafer.activators.SearchPollingApiActivator;
import com.almosafer.model.PackagesRequest;
import com.almosafer.model.SearchRequest;
import com.almosafer.utils.TestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.restassured.response.Response;

public class PackagePollingTests extends PackagesApiActivator{
	
	@Test
	public void verifyPackagePollingResponse() throws JsonMappingException, JsonProcessingException {
		SearchRequest body = asyncSearchApiActivator.buildSearchRequest(TestData.CHECK_IN, TestData.CHECK_OUT, TestData.QUERY);
		
		SearchPollingApiActivator searchPollingApiActivator = new SearchPollingApiActivator();
		String sId = asyncSearchApiActivator.sendPostRequest(body).getBody().jsonPath().getString("sId").toString();
		
		PackagesRequest pkgReq = buildPackagesRequest(sId, TestData.HOTEL_ID);
		Response pollResponse = sendGetPackagesPollRequest(sendPackagesPostRequest(pkgReq).jsonPath().getString("pId"));
		assertThatStatusCodeEquals(pollResponse, 200);
		

	}

}
