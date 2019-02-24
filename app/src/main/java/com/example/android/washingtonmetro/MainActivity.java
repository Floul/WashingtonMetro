package com.example.android.washingtonmetro;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements StationInfoFragment.OnFragmentInteractionListener {

    private FrameLayout fragmentContainer;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentContainer = findViewById(R.id.fragment_container);

        ImageView mapView = findViewById(R.id.map_image_view);
        Bitmap bitmap = ((BitmapDrawable) mapView.getDrawable()).getBitmap();
        int mPhotoWidth = bitmap.getWidth();
        int mPhotoHeight = bitmap.getHeight();
        mapView.setImageBitmap(bitmap);


        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                int pixel = bitmap.getPixel(Math.round(event.getX()), Math.round(event.getY()));
                String hexColor = String.format("#%06X", (0xFFFFFF & pixel));
                float x = event.getX();
                float y = event.getY();
                Log.v("Color check", "Color " + hexColor + " x = " + x + " y = " + y);
                //TouchToStation.getStation(pixel);

                openFragment();

                return true;
            }
        });


        //ApiQuery.fetchData(ApiQuery.testUriString);
    }

    public void openFragment(){
        StationInfoFragment stationInfoFragment = StationInfoFragment.newInstance(null,null);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_up,R.anim.slide_out_down,R.anim.slide_in_up,R.anim.slide_out_down);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fragment_container,stationInfoFragment,"stationInfoFragment").commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
