package com.almosafer.testcases;
import com.almosafer.activators.AsyncSearchApiActivator;
import com.almosafer.model.SearchRequest;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class AsyncSearchTests extends AsyncSearchApiActivator {

	private static final Logger log = LogManager.getLogger(AsyncSearchTests.class);
    @Test
    public void verifyAsyncSearchResponse(){
        SearchRequest requestBody = buildSearchRequest("2026-10-25","2026-10-26","Dubai");
        Response response  = sendPostRequest(requestBody);
        assertThatStatusCodeEquals(response,200);
        
    }

}
