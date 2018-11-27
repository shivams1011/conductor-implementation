package com.assignment.shivamsharma.conductorimplementation.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.shivamsharma.conductorimplementation.R;
import com.assignment.shivamsharma.conductorimplementation.controller.base.BaseController;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.support.RouterPagerAdapter;

public class PagerController extends BaseController {

    private TabLayout tabLayout;
    private ViewPager pager;

    private RouterPagerAdapter pagerAdapter;

    public PagerController() {

        pagerAdapter = new RouterPagerAdapter(this) {
            @Override
            public void configureRouter(@NonNull Router router, int position) {

                if (!router.hasRootController()) {
                    switch (position) {
                        case 0:
                            Controller controller = new MobileController();
                            router.setRoot(RouterTransaction.with(controller));
                            break;
                        case 1:
                            Controller controller1 = new DthController();
                            router.setRoot(RouterTransaction.with(controller1));
                            break;
                        case 2:
                            Controller controller2 = new DatacardController();
                            router.setRoot(RouterTransaction.with(controller2));
                            break;
                    }
                }

            }

            @Override
            public int getCount() {
                return 3;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {

                switch (position){
                    case 0:
                        return  "Mobile";
                    case 1:
                        return  "DTH";
                    case 2:
                        return  "Data Card";
                }

                return super.getPageTitle(position);
            }
        };

    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.pager_controller, container, false);
    }

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);

        tabLayout = view.findViewById(R.id.tabLayout);
        pager = view.findViewById(R.id.pager);

        pager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(pager);

    }

    void setPager(int position){

        pager.setCurrentItem(position);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        if (!getActivity().isChangingConfigurations()){
            pager.setAdapter(null);
        }
        tabLayout.setupWithViewPager(null);
        super.onDestroyView(view);
    }
}
