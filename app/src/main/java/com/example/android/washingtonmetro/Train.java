package com.example.android.washingtonmetro;

public class Train {

    String mDestination = "";
    String mLine = "";
    String mMinToArrival = "";

    public Train (String destination,String line, String minToArrival){
        mDestination = destination;
        mLine = line;
        mMinToArrival = minToArrival;
    }

    public String getmDestination() {
        return mDestination;
    }

    public String getmLine() {
        return mLine;
    }

    public String getmMinToArrival() {
        return mMinToArrival;
    }
}
