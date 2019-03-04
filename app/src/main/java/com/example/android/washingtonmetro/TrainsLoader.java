package com.example.android.washingtonmetro;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.washingtonmetro.network.ApiQuery;

import java.util.ArrayList;
import java.util.List;

public class TrainsLoader extends AsyncTaskLoader<ArrayList<Train>> {

    String mStringUrl;

    public TrainsLoader(Context context,String stringUrl) {
        super(context);
        mStringUrl = stringUrl;
    }

    @Override
    public ArrayList<Train> loadInBackground() {
        // Don't perform the request if there are no URLs, or the first URL is null
        if (mStringUrl == null) {
            return null;
        }

        return ApiQuery.fetchData(mStringUrl);
    }
}
