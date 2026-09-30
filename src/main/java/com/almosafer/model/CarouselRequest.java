package com.almosafer.model;

import java.util.ArrayList;
import java.util.List;

public class CarouselRequest {

    private int cityId;
    private String checkIn;
    private String checkOut;
    private List<RoomInfos> roomsInfo;

    public CarouselRequest() {}

    private CarouselRequest(Builder b) {
        this.cityId = b.cityId;
        this.checkIn = b.checkIn;
        this.checkOut = b.checkOut;
        this.roomsInfo = b.roomsInfo;
    }

    // getters and setters (unchanged)
    public int getCityId() { return cityId; }
    public void setCityId(int cityId) { this.cityId = cityId; }
    public String getCheckIn() { return checkIn; }
    public void setCheckIn(String checkIn) { this.checkIn = checkIn; }
    public String getCheckOut() { return checkOut; }
    public void setCheckOut(String checkOut) { this.checkOut = checkOut; }
    public List<RoomInfos> getRoomsInfo() { return roomsInfo; }
    public void setRoomsInfo(List<RoomInfos> roomsInfo) { this.roomsInfo = roomsInfo; }

    public static Builder builder() {
        return new Builder();
    }

    // ---------- Outer Builder ----------
    public static class Builder {
        private int cityId;
        private String checkIn;
        private String checkOut;
        private List<RoomInfos> roomsInfo = new ArrayList<>();

        public Builder cityId(int cityId) {
            this.cityId = cityId;
            return this;
        }

        public Builder checkIn(String checkIn) {
            this.checkIn = checkIn;
            return this;
        }

        public Builder checkOut(String checkOut) {
            this.checkOut = checkOut;
            return this;
        }

        // replace the whole list
        public Builder roomsInfo(List<RoomInfos> roomsInfo) {
            this.roomsInfo = roomsInfo;
            return this;
        }

        // add one room at a time
        public Builder addRoom(RoomInfos room) {
            this.roomsInfo.add(room);
            return this;
        }

        public CarouselRequest build() {
            return new CarouselRequest(this);
        }
    }

    // ---------- Inner class (now static) ----------
    public static class RoomInfos {

        private int adultsCount;
        private List<Integer> kidsAges;

        public RoomInfos() {}

        private RoomInfos(Builder b) {
            this.adultsCount = b.adultsCount;
            this.kidsAges = b.kidsAges;
        }

        public int getAdultsCount() { return adultsCount; }
        public void setAdultsCount(int adultsCount) { this.adultsCount = adultsCount; }
        public List<Integer> getKidsAges() { return kidsAges; }
        public void setKidsAges(List<Integer> kidsAges) { this.kidsAges = kidsAges; }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            private int adultsCount;
            private List<Integer> kidsAges = new ArrayList<>();

            public Builder adultsCount(int adultsCount) {
                this.adultsCount = adultsCount;
                return this;
            }

            public Builder kidsAges(List<Integer> kidsAges) {
                this.kidsAges = kidsAges;
                return this;
            }

            public RoomInfos build() {
                return new RoomInfos(this);
            }
        }
    }
}