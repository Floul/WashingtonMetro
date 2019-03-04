package com.example.android.washingtonmetro.network;

import android.util.Log;

import com.example.android.washingtonmetro.Train;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiQuery {

    public final static String testUriString = "https://api.wmata.com/StationPrediction.svc/json/GetPrediction/A10";

    public static String fakeJSON ="{\"Trains\":[{\"Car\":\"6\",\"Destination\":\"SilvrSpg\",\"DestinationCode\":\"B08\",\"DestinationName\":\"Silver Spring\",\"Group\":\"1\",\"Line\":\"RD\",\"LocationCode\":\"A01\",\"LocationName\":\"Metro Center\",\"Min\":\"3\"},{\"Car\":\"6\",\"Destination\":\"Grsvnor\",\"DestinationCode\":\"A11\",\"DestinationName\":\"Grosvenor-Strathmore\",\"Group\":\"2\",\"Line\":\"RD\",\"LocationCode\":\"A01\",\"LocationName\":\"Metro Center\",\"Min\":\"4\"},{\"Car\":\"6\",\"Destination\":\"Shady Gr\",\"DestinationCode\":\"A15\",\"DestinationName\":\"Shady Grove\",\"Group\":\"2\",\"Line\":\"RD\",\"LocationCode\":\"A01\",\"LocationName\":\"Metro Center\",\"Min\":\"6\"},{\"Car\":\"8\",\"Destination\":\"Glenmont\",\"DestinationCode\":\"B11\",\"DestinationName\":\"Glenmont\",\"Group\":\"1\",\"Line\":\"RD\",\"LocationCode\":\"A01\",\"LocationName\":\"Metro Center\",\"Min\":\"8\"},{\"Car\":\"6\",\"Destination\":\"SilvrSpg\",\"DestinationCode\":\"B08\",\"DestinationName\":\"Silver Spring\",\"Group\":\"1\",\"Line\":\"RD\",\"LocationCode\":\"A01\",\"LocationName\":\"Metro Center\",\"Min\":\"9\"},{\"Car\":\"6\",\"Destination\":\"Grsvnor\",\"DestinationCode\":\"A11\",\"DestinationName\":\"Grosvenor-Strathmore\",\"Group\":\"2\",\"Line\":\"RD\",\"LocationCode\":\"A01\",\"LocationName\":\"Metro Center\",\"Min\":\"9\"}]}\n";

    private static final String LOG_TAG = ApiQuery.class.getSimpleName();

    private static String jsonStringResponse = "";

    static ArrayList<Train> trainsToStation = new ArrayList<>();

    private ApiQuery() {
    }

    public static ArrayList fetchData(String url) {
        return makeHttpRequest(createUrl(url));
    }

    private static URL createUrl(String urlString) {
        Log.v(LOG_TAG, "Creating a URL");
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static ArrayList makeHttpRequest(URL url) {
        Log.v(LOG_TAG, "Making a Http Request");
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("api_key", "e13626d03d8e4c03ac07f95541b3091b")
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.v(LOG_TAG, "Http request failure");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    jsonStringResponse = response.body().string();
                    Log.v(LOG_TAG, "API responded with code = " + response.code()+response.body());
                    trainsToStation = parseJSON(jsonStringResponse);
                } else {
                    Log.e(LOG_TAG, "API responded with code = " + response.code()+response.body());
                }
            }

        });
        return trainsToStation;

    }

    private static ArrayList parseJSON(String stringJson) {

        try {
            JSONObject jsonObject = new JSONObject(stringJson);
            JSONArray trains = jsonObject.getJSONArray("Trains");
            for (int i = 0; i < trains.length(); i++) {
                JSONObject train = trains.getJSONObject(i);
                String currentTrainDestination = train.getString("Destination");
                String currentTrainLine = train.getString("Line");
                String currentTrainMin = train.getString("Min");
                Log.v(LOG_TAG, currentTrainDestination+currentTrainLine+currentTrainMin);
                Train trainObject = new Train(currentTrainDestination, currentTrainLine, currentTrainMin);
                trainsToStation.add(trainObject);
            }
            Log.v(LOG_TAG, "Amount of trains to current station = " + trainsToStation.size());

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Failed to parse JSON data");
            e.printStackTrace();
        }
        return trainsToStation;
    }
}
