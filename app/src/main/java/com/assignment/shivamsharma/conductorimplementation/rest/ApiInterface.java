package com.assignment.shivamsharma.conductorimplementation.rest;

import com.assignment.shivamsharma.conductorimplementation.model.Mobile;
import com.assignment.shivamsharma.conductorimplementation.model.MobileList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("mobile")
    Call<MobileList> fetchMobileList();

}
