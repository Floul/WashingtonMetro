package com.example.android.washingtonmetro.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.android.washingtonmetro.R;
import com.example.android.washingtonmetro.StationInfoFragment;

public class MainActivity extends AppCompatActivity {

    public StationInfoFragment stationInfoFragment;

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String URL_BASE = "https://api.wmata.com/StationPrediction.svc/json/GetPrediction/";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*      ImageView mapView = findViewById(R.id.map_image_view);

        mapView.setOnTouchListener((view, event) -> {
            int roundX = Math.round(event.getX());
            int roundY = Math.round(event.getY());

            int rgbColor = Utils.findColor(view, roundX, roundY);

            Log.d(TAG, "onTouch: -> " + rgbColor);

            ArrayList arrayList = ApiQuery.fetchData(ApiQuery.testUriString);

            return true;
        });
*/
    }

    public void buttons(View view) {
        String stationName = getResources().getResourceEntryName(view.getId());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(URL_BASE);
        stringBuilder.append(stationName);
        String stringUrl = stringBuilder.toString();
        openFragment(stringUrl);
    }

    private void openFragment(String stringUrl) {
        if (stationInfoFragment == null) {
            stationInfoFragment = StationInfoFragment.newInstance(stringUrl);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_down, R.anim.slide_in_up, R.anim.slide_out_down);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.add(R.id.fragment_container, stationInfoFragment);
            fragmentTransaction.commit();

        } else {
            stationInfoFragment.onDataChanged(stringUrl);
        }
    }
}
