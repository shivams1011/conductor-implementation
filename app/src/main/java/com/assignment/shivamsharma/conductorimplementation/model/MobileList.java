package com.assignment.shivamsharma.conductorimplementation.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MobileList {
    @SerializedName("data")
    private List<Mobile> mobileList = new ArrayList<>();

    public List<Mobile> getMobileList() {
        return mobileList;
    }

    public void setMobileList(List<Mobile> mobileList) {
        this.mobileList = mobileList;
    }
}
