package com.example.nightwalker;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.livequery.ParseLiveQueryClient;
import com.parse.livequery.SubscriptionHandling;

import java.util.List;


public class NightWalkActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = "NightWalkActivity";

    private GoogleMap mMap;
    private BottomNavigationView bottomNavigationView;
    ParseQuery<PostLocation> query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_walk);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        Intent a = new Intent(NightWalkActivity.this,MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.action_location:
                        Intent b = new Intent(NightWalkActivity.this,MapsActivity.class);
                        startActivity(b);
                        break;
                    case R.id.action_social:
                        Intent c = new Intent(NightWalkActivity.this, SocialActivity.class);
                        startActivity(c);
                        break;
                    case R.id.action_NightWalk:
                        break;
                }
                return true;
            }
        });
    }
    


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
//    private void queryPosts() {
//        //create a coniditonal so that this person can only access locations with that key
//        query =  ParseQuery.getQuery(PostLocation.class);
//        query.include(PostLocation.KEY_USERNAME);
//        query.findInBackground(new FindCallback<PostLocation>() {
//            if (ParseUser.getCurrentUser() == List<PostLocation.getTrackerKey()>)
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



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


}
