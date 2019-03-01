package com.example.android.washingtonmetro.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.android.washingtonmetro.network.ApiQuery;
import com.example.android.washingtonmetro.R;
import com.example.android.washingtonmetro.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView mapView = findViewById(R.id.map_image_view);
        Bitmap bitmap = ((BitmapDrawable) mapView.getDrawable()).getBitmap();

        mapView.setImageBitmap(bitmap);

        mapView.setOnTouchListener((view, event) -> {
            int roundX = Math.round(event.getX());
            int roundY = Math.round(event.getY());

            int rgbColor = Utils.findColor(view, roundX, roundY);

            Log.d(TAG, "onTouch: -> " + rgbColor);

            ArrayList arrayList = ApiQuery.fetchData(ApiQuery.testUriString);

            return true;
        });

    }

}
