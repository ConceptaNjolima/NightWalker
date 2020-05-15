package com.example.nightwalker;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

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



//
//public class SocialActivity extends AppCompatActivity {
//    private static final String TAG = "SocialActivity";
//    private BottomNavigationView bottomNavigationView;
//
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_social);
//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setItemIconTintList(null);
//        TextView social = (TextView) findViewById(R.id.social_text);
//        social.setText("This is social activity.");
//
//
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.action_home:
//                        Intent a = new Intent(SocialActivity.this, MainActivity.class);
//                        startActivity(a);
//                        break;
//                    case R.id.action_location:
//                        Intent b = new Intent(SocialActivity.this, MapsActivity.class);
//                        startActivity(b);
//                        break;
//                    case R.id.action_social:
//                        break;
//                }
//                return true;
//            }
//        });
//    }
//}