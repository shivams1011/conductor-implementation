package com.assignment.shivamsharma.conductorimplementation.controller;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.assignment.shivamsharma.conductorimplementation.MainActivity;
import com.assignment.shivamsharma.conductorimplementation.R;
import com.assignment.shivamsharma.conductorimplementation.controller.base.BaseController;
import com.bluelinelabs.conductor.Controller;

public class OperatorListController extends BaseController {

    private ListView listOpeerator;
    private EditText edetSearch;
    private ImageView imgSearch, imgCross;

    private ArrayAdapter<String> adapter;

    private String[] operatorList = {"Airtel", "Idea", "BSNL", "Vodaphone", "Jio", "DOCOMO"};

    public <T extends Controller & OperatorListInterface> OperatorListController(T tragetController) {
        setTargetController(tragetController);
    }

    public OperatorListController() {
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.operator_list_controller, container, false);
    }

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);

        listOpeerator = view.findViewById(R.id.operatorList);
        edetSearch = view.findViewById(R.id.searchEdt);
        imgSearch = view.findViewById(R.id.imgSearch);
        imgCross = view.findViewById(R.id.imgCross);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, operatorList);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        listOpeerator.setAdapter(adapter);

        edetSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() > 0){
                    imgCross.setVisibility(View.VISIBLE);
                    imgSearch.setVisibility(View.GONE);
                }else {
                    imgSearch.setVisibility(View.VISIBLE);
                    imgCross.setVisibility(View.GONE);
                }

            }
        });

        imgCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edetSearch.setText("");
            }
        });

        listOpeerator.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                sendOpertaor(adapter.getItem(position));

            }
        });
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
    }

    private void sendOpertaor(String s) {

        Controller targetController = getTargetController();

        if (targetController != null){
            ((OperatorListInterface)targetController).operatorName(s);
            getRouter().popController(this);
        }

    }

    public interface OperatorListInterface{
        void operatorName(String operator);
    }


}
