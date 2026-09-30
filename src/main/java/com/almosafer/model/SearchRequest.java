package com.almosafer.model;

import java.util.List;

public class SearchRequest {

    private List<RoomInfo> roomsInfo;
    private String checkIn;
    private String checkOut;
    private String query;

    // Getters and setters
    public List<RoomInfo> getRoomsInfo() {
        return roomsInfo;
    }
    public void setRoomsInfo(List<RoomInfo> roomsInfo) {
        this.roomsInfo = roomsInfo;
    }
    public String getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }
    public String getCheckOut() {
        return checkOut;
    }
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
    public String getQuery() {
        return query;
    }
    public void setQuery(String query) {
        this.query = query;
    }

}
