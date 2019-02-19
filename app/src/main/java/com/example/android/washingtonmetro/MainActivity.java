package com.example.android.washingtonmetro;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView mapView = (ImageView) findViewById(R.id.map_image_view);
        Bitmap bitmap = ((BitmapDrawable)mapView.getDrawable()).getBitmap();

        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                    int pixel = bitmap.getPixel(Math.round(event.getX()),Math.round(event.getY()));
                    String hexColor = String.format("#%06X", (0xFFFFFF & pixel));
                    float x = event.getX();
                    float y = event.getY();
                    Log.v("kjh", hexColor + " x = " + x + " y = " + y);
                    //TouchToStation.getStation(pixel);

                return true;
            }
        });

        //ApiQuery.fetchData(ApiQuery.testUriString);
    }

}
