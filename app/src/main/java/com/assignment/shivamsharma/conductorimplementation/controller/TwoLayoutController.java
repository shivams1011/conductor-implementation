package com.assignment.shivamsharma.conductorimplementation.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.shivamsharma.conductorimplementation.R;
import com.assignment.shivamsharma.conductorimplementation.adapter.MobileAdapter;
import com.assignment.shivamsharma.conductorimplementation.adapter.MobileBaseAdapter;
import com.assignment.shivamsharma.conductorimplementation.controller.base.BaseController;
import com.assignment.shivamsharma.conductorimplementation.model.Mobile;
import com.assignment.shivamsharma.conductorimplementation.model.MobileList;
import com.assignment.shivamsharma.conductorimplementation.rest.ApiClient;
import com.assignment.shivamsharma.conductorimplementation.rest.ApiInterface;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.linearlistview.LinearListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TwoLayoutController extends BaseController implements SecondController.SelectCategoryListener {

    private ViewGroup container1, container2;
    private TextView txtQR;
    private RecyclerView listView;
    private LinearListView linearListView;
    private String[] data = {"Mobile", "Dth", "Data Card", "Airtel", "Vodaphone", "Idea", "Jio"};
    private List<Mobile> mobileList = new ArrayList<>();
    private MobileBaseAdapter adapter;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.layout_two_controller, container, false);
    }

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);

        container1 = view.findViewById(R.id.frameLayout1);
//        listView = view.findViewById(R.id.listQR);
//        listView.setNestedScrollingEnabled(false);
        linearListView = view.findViewById(R.id.linearListView);
//        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        txtQR = view.findViewById(R.id.txtQr);
//        container2 = view.findViewById(R.id.frameLayout2);

        setAdapter();
        setupPager();
        setupQR();

        linearListView.setOnItemClickListener(new LinearListView.OnItemClickListener() {
            @Override
            public void onItemClick(LinearListView parent, View view, int position, long id) {

            }
        });



    }

    private void setupQR() {

        fetchList();
//        Router childRouter1 = getChildRouter(container2).setPopsLastView(false);
//        if (!childRouter1.hasRootController()){
//            childRouter1.setRoot(RouterTransaction.with(new SecondController(TwoLayoutController.this)));
//        }

    }

    private void fetchList() {


//           fetchList progressBar.setVisibility(View.VISIBLE);
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<MobileList> mobiles = apiService.fetchMobileList();

            mobiles.enqueue(new Callback<MobileList>() {
                @Override
                public void onResponse(Call<MobileList> call, Response<MobileList> response) {
                    mobileList = response.body().getMobileList();
                    setAdapter();
//                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<MobileList> call, Throwable t) {

                }
            });

    }

    private void setAdapter() {
        adapter = new MobileBaseAdapter(getActivity(), mobileList);
        linearListView.setAdapter(adapter);

    }

    private void setupPager() {
        Router childRouter = getChildRouter(container1).setPopsLastView(false);
        if (!childRouter.hasRootController()){
            childRouter.setRoot(RouterTransaction.with(new PagerController()).tag("pager"));
        }
    }

    @Override
    public void setCategoryType(String type) {
        Controller controller = getChildRouter(container1).getControllerWithTag("pager");

        switch (type){
            case "mobile":
                ((PagerController) controller).setPager(0);
                break;
            case "DTH":
                ((PagerController) controller).setPager(1);
                break;
            case "DataCard":
                ((PagerController) controller).setPager(2);
                break;
        }



        Toast.makeText(getActivity(), type, Toast.LENGTH_SHORT).show();

    }
}
