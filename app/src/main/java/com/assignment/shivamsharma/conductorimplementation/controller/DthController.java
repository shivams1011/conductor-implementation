package com.assignment.shivamsharma.conductorimplementation.controller;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.shivamsharma.conductorimplementation.R;
import com.assignment.shivamsharma.conductorimplementation.controller.base.BaseController;

public class DthController extends BaseController {

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.dth_controller, container, false);
    }

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);
    }
}
