package com.almosafer.model;

import java.util.List;

public class RoomInfo {
    private int adultsCount;
    private List<Integer> kidsAges;

    // Getters and setters
    public int getAdultsCount() {
        return adultsCount;
    }
    public void setAdultsCount(int adultsCount) {
        this.adultsCount = adultsCount;
    }
    public List<Integer> getKidsAges() {
        return kidsAges;
    }
    public void setKidsAges(List<Integer> kidsAges) {
        this.kidsAges = kidsAges;
    }
}
