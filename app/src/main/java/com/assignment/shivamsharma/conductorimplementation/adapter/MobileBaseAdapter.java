package com.assignment.shivamsharma.conductorimplementation.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.shivamsharma.conductorimplementation.R;
import com.assignment.shivamsharma.conductorimplementation.model.Mobile;
import com.bumptech.glide.Glide;

import java.util.List;

public class MobileBaseAdapter extends BaseAdapter {

    private Context context;
    private List<Mobile> mobileList;

    public MobileBaseAdapter(Context context, List<Mobile> mobileList) {

        this.context = context;
        this.mobileList = mobileList;
    }

    @Override
    public int getCount() {
        return mobileList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_mobile_item, parent, false);

        }

        ImageView img = convertView.findViewById(R.id.imageMobile);
        TextView txtname = convertView.findViewById(R.id.txtMobileName);

        Mobile m = mobileList.get(position);
        Glide.with(context).load(m.getImage()).into(img);
        txtname.setText(m.getName());

        return convertView;
    }
}
