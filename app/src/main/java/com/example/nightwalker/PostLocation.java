package com.example.nightwalker;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("PostLocation")
public class PostLocation extends ParseObject {
    public static final String KEY_USERNAME = "username";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";
    public static final String KEY_TRACKERKEY = "trackerKey";

    public double getLatitude(){
        return getDouble(KEY_LATITUDE);
    }
    public void setLatitude (double latitude){
        put(KEY_LATITUDE, latitude);
    }
    public double getLongitude(){
        return getDouble(KEY_LONGITUDE);
    }
    public void setLongitude (double longitude){
        put(KEY_LONGITUDE, longitude);
    }


   public void setUser(ParseUser currentUser) {
        put(KEY_USERNAME, currentUser);
    }
    public String getUser(){
        return getString(KEY_USERNAME);
    }

    public void setTrackerKey(String trackerKey) {
        put(KEY_TRACKERKEY, trackerKey);
    }
    public String getTrackerKey(){
        return getString(KEY_TRACKERKEY);
    }
}
