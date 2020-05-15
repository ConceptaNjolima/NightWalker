package com.example.nightwalker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapActivity" ;
    Location currentlocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private static final int SEND_SMS_REQUEST_CODE = 1;
    private BottomNavigationView bottomNavigationView;
    private Button ShareLtn;
    private Button InviteNW;
    private EditText UserNW;
    private double destination_longitude, destination_latitude;
    private  PostLocation postLocation;
    public String userNW;




    public boolean checkPermission(String permission){
        int check= ContextCompat.checkSelfPermission(this,permission);
        return (check==PackageManager.PERMISSION_GRANTED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ShareLtn=findViewById(R.id.btnShareLtn);
        InviteNW = findViewById (R.id.btnInviteNW);
        UserNW = findViewById(R.id.etuserNW);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();

        postLocation = new PostLocation();


/*
        ContentResolver resolver =getContentResolver();
        Cursor cursor= resolver.query(ContactsContract.Contacts.CONTENT_URI,null,
                null,null,null);
        while(cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ? ",new String[]{id},null);
            Log.i(TAG, "CONTACTS"+ id +"name"+name);
        }
*/
        ShareLtn.setEnabled(false);
        if(checkPermission(Manifest.permission.SEND_SMS)){
            ShareLtn.setEnabled(true);
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},SEND_SMS_REQUEST_CODE);
        }
        ShareLtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSend(v);
            }
        });
        // button to share location to parse server
        InviteNW.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //METHOD THAT TELLS PARSE TO RECORD LAT AND LONG
                userNW = UserNW.getText().toString();
                if (userNW.isEmpty()){
                    Toast.makeText(MapsActivity.this,"You must input a key", Toast.LENGTH_SHORT).show();
                    return;
                }
                LocationThread thread = new LocationThread(userNW);
                thread.start();


            }
        });


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        Intent a = new Intent(MapsActivity.this,MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.action_location:
                        break;
                    case R.id.action_social:
                        Intent b = new Intent(MapsActivity.this,SocialActivity.class);
                        startActivity(b);
                        break;
                    case R.id.action_NightWalk:
                        Intent c = new Intent(MapsActivity.this,NightWalkActivity.class);
                        startActivity(c);
                        break;
                }
                return true;
            }
        });
    }
    public void onSend(View v){
        String PhoneNumber="8599791217";
        String message= currentlocation.getLatitude()+ ""+ currentlocation.getLongitude();
        if(checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager= SmsManager.getDefault();
            smsManager.sendTextMessage(PhoneNumber,null,message,null, null);
            Toast.makeText(this, "Sending location "+ currentlocation.getLatitude()+""+currentlocation.getLongitude(), Toast.LENGTH_SHORT).show();


        }
        else{
            Toast.makeText(this, "Failed to send message", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
    }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    currentlocation = location;
                    //Toast.makeText(getApplicationContext(), currentlocation.getLatitude()+""+currentlocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment)
                            getSupportFragmentManager().findFragmentById(R.id.map);
                    supportMapFragment.getMapAsync(MapsActivity.this);
                }



            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng latLng = new LatLng(currentlocation.getLatitude(), currentlocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("My Current Location.");

        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
        googleMap.addMarker(markerOptions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }
    }

class LocationThread extends Thread{
        /**This class makes saving location run in the background. It keeps updating the parse server with the latest location. It uses threads to run in the background.**/
        int i = 0;
        String userNw;
        LocationThread(String userNw){
            this.userNw = userNw;
        }
        public void run(){
            postLocation.setTrackerKey (userNw);
            postLocation.setUser(ParseUser.getCurrentUser());
            while (currentlocation.getLatitude() != destination_latitude || currentlocation.getLongitude() != destination_longitude){
                fetchLastLocation();
                postLocation.setLatitude(currentlocation.getLatitude());
                postLocation.setLongitude(currentlocation.getLongitude());
                postLocation.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Log.e(TAG, "Error while saving location data", e);
                            Toast.makeText(MapsActivity.this, "Error while saving location data", Toast.LENGTH_SHORT).show();
                        }
                        //Log.i(TAG, "Location saved properly to parse server");
                        //Toast.makeText(MapsActivity.this, "Location Saved Properly to Parse Server", Toast.LENGTH_SHORT).show();
                        UserNW.setText("");
                    }
                });
                Log.i(TAG, "LocationThread: " +  i++ );
                Log.i(TAG, " run: Latitude"+ currentlocation.getLatitude()+"longitude"+currentlocation.getLongitude());
                try {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }
        }}
}

