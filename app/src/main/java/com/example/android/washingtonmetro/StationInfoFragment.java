package com.example.android.washingtonmetro;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class StationInfoFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Train>> {

    public static ArrayList<Train> trains = new ArrayList<>();
    public static FragmentRVAdapter fragmentRVAdapter;

    private static String stringUrl;

    public StationInfoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static StationInfoFragment newInstance(String url) {
        StationInfoFragment fragment = new StationInfoFragment();
        Bundle args = new Bundle();
        stringUrl = url;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_station_info, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        fragmentRVAdapter = new FragmentRVAdapter(trains);
        recyclerView.setAdapter(fragmentRVAdapter);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, this).forceLoad();
        return view;
    }


    @NonNull
    @Override
    public Loader<ArrayList<Train>> onCreateLoader(int id, @Nullable Bundle args) {
        return new TrainsLoader(getActivity(), stringUrl);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Train>> loader, ArrayList<Train> newTrains) {
        trains.clear();
        trains = newTrains;
        fragmentRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Train>> loader) {

    }

}
