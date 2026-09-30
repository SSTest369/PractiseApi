package com.almosafer.activators;

import com.almosafer.model.InnerPackageRequest;
import com.almosafer.model.PackagesRequest;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.almosafer.utils.ApiConstants.*;

public class PackagesApiActivator extends BaseApiActivator {

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
        headerMap.put(TOKEN, "skdjfh73273$7268u2j89s");
        setHeaders(headerMap);
        setRequestBody(convertPojoToString(body));
        return sendAPIRequest(Method.POST, PACKAGES);
    }

    public Response sendPackagesPollRequest(String pkgSId) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put(TOKEN, "skdjfh73273$7268u2j89s");
        setHeaders(headerMap);
        return sendAPIRequest(Method.GET, PACKAGES_POLL + pkgSId);
    }
}