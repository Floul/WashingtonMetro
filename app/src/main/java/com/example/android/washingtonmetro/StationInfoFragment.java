package com.example.android.washingtonmetro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class StationInfoFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Train>> {

    private static final String URL_KEY = "url";
    private String stringUrl;
    public FragmentRVAdapter fragmentRVAdapter;

    public StationInfoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static StationInfoFragment newInstance(String url) {
        StationInfoFragment fragment = new StationInfoFragment();
        Bundle args = new Bundle();
        args.putString(URL_KEY, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentRVAdapter = new FragmentRVAdapter();
        stringUrl = getArguments().getString(URL_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_station_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(fragmentRVAdapter);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.destroyLoader(0);
        loaderManager.initLoader(0, null, this).forceLoad();
    }

    @NonNull
    @Override
    public Loader<ArrayList<Train>> onCreateLoader(int id, @Nullable Bundle args) {
        return new TrainsLoader(getActivity(), stringUrl);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Train>> loader, ArrayList<Train> newTrains) {
        Log.d("TAG", "onLoadFinished: " + newTrains);
        fragmentRVAdapter.setData(newTrains);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Train>> loader) {

    }

}
