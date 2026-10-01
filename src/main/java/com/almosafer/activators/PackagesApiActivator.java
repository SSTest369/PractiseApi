package com.almosafer.activators;

import com.almosafer.model.InnerPackageRequest;
import com.almosafer.model.PackagesRequest;
import com.almosafer.utils.ApiConstants;

import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import static com.almosafer.utils.ApiConstants.*;

public class PackagesApiActivator extends BaseApiActivator {
	
	public AsyncSearchApiActivator asyncSearchApiActivator;
	
	
	@BeforeEach
	void init() {
		asyncSearchApiActivator = new AsyncSearchApiActivator();
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "application/json");
		headerMap.put(TOKEN, "skdjfh73273$7268u2j89s");
		setHeaders(headerMap);
	}

    public PackagesRequest buildPackagesRequest(String sId, long hotelId) {
        PackagesRequest request = new PackagesRequest();
        request.setsId(sId);

        InnerPackageRequest inner = new InnerPackageRequest();
        inner.setHotelId(hotelId);

        request.setPackagesRequest(inner);
        return request;
    }

    public Response sendPackagesPostRequest(PackagesRequest body) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put(TOKEN,getDefaultAuthTokenViaEnvFile());
        setHeaders(headerMap);
        setRequestBody(convertPojoToString(body));
        return sendAPIRequest(Method.POST, PACKAGES);
    }
   
//aproach1 > used in same packagetest
    public Response sendPackagesPollRequest(String pkgSId) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put(TOKEN, getDefaultAuthTokenViaEnvFile());
        setHeaders(headerMap);
        return sendAPIRequest(Method.GET, PACKAGES_POLL + pkgSId);
    }
    
  //aproach2 > create new polling request test  
    public Response sendGetPackagesPollRequest(String pkgSId) {
    	Map<String, String> pathParam = new HashMap<>();
		pathParam.put(PID, pkgSId);
		setPathParameters(pathParam);
		return pkgPollUntilSearchComplete();
    }
    public Response pkgPollUntilSearchComplete() {
		Response response;
		String searchStatus;
		do {
			response = sendAPIRequest(Method.GET, ApiConstants.PACKAGES_POLL);
			JSONObject json = new JSONObject(response.getBody().asString());
			searchStatus = json.getString(POLLING_STATUS);
//          A short sleep to avoid hammering the API
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignored) {
			}
		} while ("IN_PROGRESS".equals(searchStatus));
		return response;
	}
    
}