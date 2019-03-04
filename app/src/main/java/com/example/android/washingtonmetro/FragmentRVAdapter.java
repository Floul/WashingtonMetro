package com.example.android.washingtonmetro;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FragmentRVAdapter extends RecyclerView.Adapter<TrainViewHolder> {

    private ArrayList<Train> mTrains = new ArrayList<>();

    public FragmentRVAdapter(ArrayList<Train> trains) {
        mTrains = trains;
    }

    @NonNull
    @Override
    public TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View rvListItem = inflater.inflate(R.layout.rv_list_item, parent, false);

        return new TrainViewHolder(rvListItem);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {
        Train train = mTrains.get(position);
        holder.lineTextView.setText(train.getmLine());
        holder.destinationTextView.setText(train.getmDestination());
        holder.timeTextView.setText(train.getmMinToArrival());
    }

    @Override
    public int getItemCount() {
        return mTrains == null ? 0 : mTrains.size();
    }
}

class TrainViewHolder extends RecyclerView.ViewHolder {

    TextView lineTextView;
    TextView destinationTextView;
    TextView timeTextView;

    public TrainViewHolder(View itemView) {
        super(itemView);
        lineTextView = itemView.findViewById(R.id.line_text_view);
        destinationTextView = itemView.findViewById(R.id.destination_text_view);
        timeTextView = itemView.findViewById(R.id.time_text_view);
    }
}
