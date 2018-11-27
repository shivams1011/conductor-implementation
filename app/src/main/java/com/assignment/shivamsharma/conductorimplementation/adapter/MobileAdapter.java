package com.assignment.shivamsharma.conductorimplementation.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assignment.shivamsharma.conductorimplementation.DetailActivity;
import com.assignment.shivamsharma.conductorimplementation.R;
import com.assignment.shivamsharma.conductorimplementation.controller.MobileController;
import com.assignment.shivamsharma.conductorimplementation.model.Mobile;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

public class MobileAdapter extends RecyclerView.Adapter<MobileAdapter.ViewHolder> {


    private Context context;
    private List<Mobile> mobileList;

    public MobileAdapter(Context context, List<Mobile> mobileList) {
        this.context = context;
        this.mobileList = mobileList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_mobile_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final Mobile mob = mobileList.get(i);

        viewHolder.txtMobileName.setText(mob.getName());
        Glide.with(context).load(mob.getImage()).into(viewHolder.imgMobile);

        viewHolder.linearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("data", (Serializable) mob);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context, viewHolder.imgMobile, "image");
                context.startActivity(intent, options.toBundle());

            }
        });


    }

    @Override
    public int getItemCount() {
        return mobileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtMobileName;
        public ImageView imgMobile;
        public LinearLayout linearView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearView = itemView.findViewById(R.id.linearView);
            txtMobileName = itemView.findViewById(R.id.txtMobileName);
            imgMobile = itemView.findViewById(R.id.imageMobile);
        }
    }
}
