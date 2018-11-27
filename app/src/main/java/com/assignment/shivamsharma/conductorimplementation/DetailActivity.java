package com.assignment.shivamsharma.conductorimplementation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.shivamsharma.conductorimplementation.model.Mobile;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Mobile mobile = (Mobile) this.getIntent().getSerializableExtra("data");

        ImageView imageView = findViewById(R.id.imageMobile);
        TextView txtView = findViewById(R.id.txtMobileName);

        txtView.setText(mobile.getDescription());
        Glide.with(this).load(mobile.getImage()).into(imageView);

    }

}
