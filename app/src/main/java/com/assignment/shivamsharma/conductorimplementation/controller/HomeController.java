package com.assignment.shivamsharma.conductorimplementation.controller;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assignment.shivamsharma.conductorimplementation.R;
import com.assignment.shivamsharma.conductorimplementation.controller.base.BaseController;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

public class HomeController extends BaseController {

    private TextView textView;
    private TextView txtPager;
    private TextView txtChildController;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {

        return inflater.inflate(R.layout.controller_home, container, false);
    }

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);

        textView = view.findViewById(R.id.txtHomeController);
        txtPager = view.findViewById(R.id.txtPagerController);

        txtChildController = view.findViewById(R.id.txtChildController);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRouter().pushController(RouterTransaction.with(new SecondController())
                        .pushChangeHandler(new HorizontalChangeHandler())
                        .popChangeHandler(new FadeChangeHandler()));
            }
        });

        txtPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRouter().pushController(RouterTransaction.with(new PagerController())
                        .pushChangeHandler(new HorizontalChangeHandler())
                        .popChangeHandler(new HorizontalChangeHandler()));
            }
        });


        txtChildController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRouter().pushController(RouterTransaction.with(new TwoLayoutController())
                        .pushChangeHandler(new HorizontalChangeHandler())
                        .popChangeHandler(new HorizontalChangeHandler()));
            }
        });

    }

}
