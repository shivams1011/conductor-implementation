package com.assignment.shivamsharma.conductorimplementation.controller;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.shivamsharma.conductorimplementation.R;
import com.assignment.shivamsharma.conductorimplementation.controller.base.BaseController;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

public class SecondController extends BaseController implements OperatorListController.OperatorListInterface {

    private static final String TAG = SecondController.class.getSimpleName();
    private TextView txtSecondController, txtMobile, txtDTH, txtDataCard;
    private String data = "Second Controller";
    private ListView listView;

    private String[] data1 = {"hello", "world", "Mobile", "DTH", "Data Card"};

    public <T extends Controller & SelectCategoryListener>SecondController(Controller twoLayoutControllerClass) {
        setTargetController(twoLayoutControllerClass);
    }

    public SecondController() {

    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_second, container, false);
    }

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);

        txtSecondController = view.findViewById(R.id.txtSecondController);
        txtMobile = view.findViewById(R.id.txtSecondControllerMobile);
        txtDTH = view.findViewById(R.id.txtSecondControllerDth);
        txtDataCard = view.findViewById(R.id.txtSecondControllerDataCard);

        listView = view.findViewById(R.id.listSecond);

        showList();
        showText();

        txtSecondController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRouter().pushController(RouterTransaction.with(new OperatorListController(SecondController.this))
                        .popChangeHandler(new HorizontalChangeHandler())
                        .pushChangeHandler(new HorizontalChangeHandler()));
            }
        });

        txtMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller controller = getTargetController();
                ((SelectCategoryListener)controller).setCategoryType("mobile");
            }
        });

        txtDTH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller controller = getTargetController();
                ((SelectCategoryListener)controller).setCategoryType("DTH");
            }
        });

        txtDataCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller controller = getTargetController();
                ((SelectCategoryListener)controller).setCategoryType("DataCard");
            }
        });

    }

    private void showList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data1);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onChangeEnded(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        super.onChangeEnded(changeHandler, changeType);
        Log.d(TAG, "onChangeEnded: Called here");
    }

    @Override
    protected void onChangeStarted(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        super.onChangeStarted(changeHandler, changeType);
        Log.d(TAG, "onChangeStarted: Called here");
    }

    @Override
    public void operatorName(String operator) {
        data = operator;
        showText();

    }

    private void showText() {
        if (txtSecondController != null) {
            txtSecondController.setText(data);
        }else {
            Toast.makeText(getApplicationContext(), "Not Set", Toast.LENGTH_SHORT).show();
        }
    }

    public interface SelectCategoryListener{
        void setCategoryType(String type);
    }

}
