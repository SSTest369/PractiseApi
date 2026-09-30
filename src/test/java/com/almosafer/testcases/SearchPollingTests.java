package com.almosafer.testcases;
import org.junit.jupiter.api.Test;

import com.almosafer.activators.SearchPollingApiActivator;
import com.almosafer.model.SearchRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.restassured.response.Response;

public class SearchPollingTests extends SearchPollingApiActivator {

	@Test
	public void verifySearchPollingResponse() throws JsonMappingException, JsonProcessingException {
		SearchRequest body = asyncSearchApiActivator.buildSearchRequest("2026-10-25", "2026-10-26", "Dubai");
		Response response = sendGetRequest(asyncSearchApiActivator.sendPostRequest(body).getBody().jsonPath().getString("sId"));
		assertThatStatusCodeEquals(response, 200);

	}

}
