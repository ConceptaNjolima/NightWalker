package com.example.nightwalker;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

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
    Location nwlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_walk);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        Intent a = new Intent(NightWalkActivity.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.action_location:
                        Intent b = new Intent(NightWalkActivity.this, MapsActivity.class);
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
    queryPosts();
    }
        private void queryPosts() {
        //create a conditional so that this person can only access locations with that key
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        ParseQuery<PostLocation> query =  ParseQuery.getQuery(PostLocation.class);
        query.include(PostLocation.KEY_USERNAME);
        query.include(PostLocation.KEY_TRACKERKEY);
        query.whereEqualTo("trackerKey", ParseUser.getCurrentUser().getUsername()) ;
        //while (query.whereEqualTo("trackerKey", ParseUser.getCurrentUser()) != null){
        query.findInBackground(new FindCallback<PostLocation>() {
            @Override
            public void done(List<PostLocation> postLocations, ParseException e) {
                /*if (e!= null){
                    Log.i(TAG, "Issue with getting locations");
                    return;
                }*/

                for (PostLocation postLocation : postLocations){
                    Log.i(TAG, "done: PostLocation" +"User: "+ postLocation.getTrackerKey());
                    Log.i(TAG, "done: Parse user" +"User: "+ ParseUser.getCurrentUser().getUsername());
                    String user= postLocation.getTrackerKey();
                    String receiver=ParseUser.getCurrentUser().getUsername();
                    if(receiver == user){
                        Log.e(TAG, "User: "+ postLocation.getUser()+" Latitude " +postLocation.getLatitude() + " Longitude "+ postLocation.getLongitude(),e);
                        Toast.makeText(NightWalkActivity.this, "Found a shared location", Toast.LENGTH_SHORT).show();
                        nwlocation.setLatitude(postLocation.getLatitude());
                        nwlocation.setLongitude(postLocation.getLongitude());
                        return;
                }
                    else {
                        Toast.makeText(NightWalkActivity.this, "No location being shared", Toast.LENGTH_SHORT).show();
                    }
                    }
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




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (nwlocation != null){

        LatLng latLng = new LatLng(nwlocation.getLatitude(), nwlocation.getLongitude());
        Log.e(TAG, "onMapReady: night walker location"+nwlocation.getLatitude()+"  "+nwlocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("My Current Location.");

        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
        googleMap.addMarker(markerOptions);
    }}


}
