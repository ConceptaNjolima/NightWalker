//package com.example.nightwalker;
//
//import android.util.Log;
//
//import com.parse.FindCallback;
//import com.parse.ParseException;
//import com.parse.ParseQuery;
//
//import java.util.List;
//
//class UpdateLocation  {
//
//    private void queryPosts() {
//        //create a coniditonal so that this person can only access locations with that key
//        ParseQuery<PostLocation> query =  ParseQuery.getQuery(PostLocation.class);
//        query.include(PostLocation.KEY_USERNAME);
//        query.findInBackground(new FindCallback<PostLocation>() {
//            @Override
//            public void done(List<PostLocation> postLocations, ParseException e) {
//                if (e!= null){
//                    Log.i(TAG, "Issue with getting locations");
//                    return;
//                }
//                for (PostLocation postLocation : postLocations){
//                    Log.e(TAG, "User: "+ postLocation.getUser()+" Latitude " +postLocation.getLatitude() + " Longitude "+ postLocation.getLongitude(),e);
//                }
//            }
//        });
//    }
//    }
//
//
