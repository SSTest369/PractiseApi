package com.almosafer.activators;

import com.almosafer.model.RoomInfo;
import com.almosafer.model.SearchRequest;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.almosafer.utils.ApiConstants.ASYNC_SEARCH;
import static com.almosafer.utils.ApiConstants.TOKEN;

public class AsyncSearchApiActivator extends BaseApiActivator {

    public SearchRequest buildSearchRequest(String checkIn, String checkOut, String query) {
        SearchRequest request = new SearchRequest();
        request.setCheckIn(checkIn);
        request.setCheckOut(checkOut);
        request.setQuery(query);

        RoomInfo room = new RoomInfo();
        room.setAdultsCount(2);
        room.setKidsAges(new ArrayList<>()); // or pass actual kids ages

        List<RoomInfo> rooms = new ArrayList<>();
        rooms.add(room);
        request.setRoomsInfo(rooms);

        return request;
    }

    public Response sendPostRequest(SearchRequest body) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put(TOKEN, "skdjfh73273$7268u2j89s");
        setHeaders(headerMap);
        setRequestBody(convertPojoToString(body));
        return sendAPIRequest(Method.POST, ASYNC_SEARCH);
    }


}
