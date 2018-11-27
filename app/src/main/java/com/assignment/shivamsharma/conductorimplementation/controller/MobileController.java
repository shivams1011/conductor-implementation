package com.assignment.shivamsharma.conductorimplementation.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.assignment.shivamsharma.conductorimplementation.DetailActivity;
import com.assignment.shivamsharma.conductorimplementation.R;
import com.assignment.shivamsharma.conductorimplementation.adapter.MobileAdapter;
import com.assignment.shivamsharma.conductorimplementation.changeHandler.ArcFadeMoveChangeHandlerCompat;
import com.assignment.shivamsharma.conductorimplementation.controller.base.BaseController;
import com.assignment.shivamsharma.conductorimplementation.model.Mobile;
import com.assignment.shivamsharma.conductorimplementation.model.MobileList;
import com.assignment.shivamsharma.conductorimplementation.rest.ApiClient;
import com.assignment.shivamsharma.conductorimplementation.rest.ApiInterface;
import com.assignment.shivamsharma.conductorimplementation.utils.RecyclerviewItemClickListener;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.bluelinelabs.conductor.changehandler.TransitionChangeHandlerCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.assignment.shivamsharma.conductorimplementation.utils.Util.loadJSONFromAsset;

public class MobileController extends BaseController {

    private RecyclerView recyclerMobile;
    private MobileAdapter adapter;
    private List<Mobile> mobileList = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.mobile_controller, container, false);
    }

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);

        progressBar = view.findViewById(R.id.progress_mobileController);
        recyclerMobile = view.findViewById(R.id.recycler_mobile);
        recyclerMobile.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        setAdapter();
//
//        recyclerMobile.addOnItemTouchListener(new RecyclerviewItemClickListener(getApplicationContext(), recyclerMobile, new RecyclerviewItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//
////                onModelCall(mobileList.get(position),position);
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));

        if (mobileList.size()==0) {
            fetchList();
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("data", (Serializable) mobileList);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mobileList = (List<Mobile>) savedInstanceState.getSerializable("data");
    }

    @Override
    protected void onRestoreViewState(@NonNull View view, @NonNull Bundle savedViewState) {
        super.onRestoreViewState(view, savedViewState);

    }

    @Override
    protected void onSaveViewState(@NonNull View view, @NonNull Bundle outState) {
        super.onSaveViewState(view, outState);
    }

    private void setAdapter() {
        adapter = new MobileAdapter(getActivity(), mobileList);
        recyclerMobile.setAdapter(adapter);

    }

    private void onModelCall(Mobile mobile, int position) {



//        getRouter().pushController(RouterTransaction.with(new MobileDetailController(mobile.getName(),
//                mobile.getImage(), mobile.getDescription(), position))
//        .pushChangeHandler(new ArcFadeMoveChangeHandlerCompat(mobile.getName(), mobile.getImage()))
//        .popChangeHandler(new ArcFadeMoveChangeHandlerCompat(mobile.getName(),mobile.getImage())));

    }

    private void fetchList() {

        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MobileList> mobiles = apiService.fetchMobileList();

        mobiles.enqueue(new Callback<MobileList>() {
            @Override
            public void onResponse(Call<MobileList> call, Response<MobileList> response) {
                mobileList = response.body().getMobileList();
                setAdapter();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<MobileList> call, Throwable t) {

            }
        });


//       fetchListfromLocal();

    }

    private void fetchListfromLocal() {
        String strResponse = loadJSONFromAsset(getActivity(), "mobileList");

        try {
            JSONObject jsonObject = new JSONObject(strResponse);

            JSONArray array = jsonObject.getJSONArray("data");

            for (int i = 0; i < array.length(); i++) {

                JSONObject obj = array.getJSONObject(i);

                mobileList.add(new Mobile(obj.getString("name"), obj.getString("description")
                        , obj.getString("image")));
            }

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
