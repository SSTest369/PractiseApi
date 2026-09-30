package com.almosafer.activators;

import com.almosafer.utils.ApiConstants;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;

import static com.almosafer.utils.ApiConstants.*;

public class SearchPollingApiActivator extends BaseApiActivator {

	public AsyncSearchApiActivator asyncSearchApiActivator;

	@BeforeEach
	void init() {
		asyncSearchApiActivator = new AsyncSearchApiActivator();
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "application/json");
		headerMap.put(TOKEN, "skdjfh73273$7268u2j89s");
		setHeaders(headerMap);
	}

	public Response sendGetRequest(String sId) {
		Map<String, String> pathParam = new HashMap<>();
		pathParam.put(SID, sId);
		setPathParameters(pathParam);
		return pollUntilSearchComplete();
	}

	public Response oldPollUntilSearchComplete() {
		Response response;
		String searchStatus;
		do {
			response = sendAPIRequest(Method.GET, ApiConstants.SEARCH_POLLING);
			JSONObject json = new JSONObject(response.getBody().asString());
			searchStatus = json.getString(SEARCH_STATUS);
//          A short sleep to avoid hammering the API
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignored) {
			}
		} while ("IN_PROGRESS".equals(searchStatus));
		return response;
	}

	public Response pollUntilSearchComplete() {

	    int maxAttempts = 10;
	    int pollingInterval = 1000;
	    String status;
	    for (int attempt = 1; attempt <= maxAttempts; attempt++) {

	        Response response = sendAPIRequest(Method.GET, ApiConstants.SEARCH_POLLING);
	        JSONObject json = new JSONObject(response.getBody().asString());
	        status = json.getString(SEARCH_STATUS);

	        if (!"IN_PROGRESS".equals(status)) {
	            return response;
	        }

	        try {
	            Thread.sleep(pollingInterval);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            throw new RuntimeException("Polling interrupted", e);
	        }
	    }

	    throw new RuntimeException(
	            "Search did not complete within expected time"
	    );
	}
}
