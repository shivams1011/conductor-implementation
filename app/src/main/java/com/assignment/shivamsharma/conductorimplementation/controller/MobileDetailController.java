package com.assignment.shivamsharma.conductorimplementation.controller;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.shivamsharma.conductorimplementation.R;
import com.assignment.shivamsharma.conductorimplementation.controller.base.BaseController;
import com.bumptech.glide.Glide;

public class MobileDetailController extends BaseController {


    private ImageView imgMobileDetail;
    private TextView txtMobileDetail;
    private String name;
    private String image;
    private String description;

    public MobileDetailController(String name, String image, String description, int position) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public MobileDetailController() {
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.mobile_detail_controller, container, false);
    }

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);

        imgMobileDetail = view.findViewById(R.id.imgMobileDetail);
        txtMobileDetail = view.findViewById(R.id.txtMobileDetail);

        Glide.with(getActivity()).load(image).into(imgMobileDetail);
        txtMobileDetail.setText(description);

        ViewCompat.setTransitionName(imgMobileDetail, image);
        ViewCompat.setTransitionName(txtMobileDetail, description);

    }
}
