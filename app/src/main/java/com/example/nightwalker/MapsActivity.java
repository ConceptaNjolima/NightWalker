package com.example.nightwalker;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import com.google.android.material.textfield.TextInputEditText;
import com.parse.Parse;
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
                String userNW = UserNW.getText().toString();
                if (userNW.isEmpty()){
                    Toast.makeText(MapsActivity.this,"You must input a key", Toast.LENGTH_SHORT).show();
                    return;
                }
                recordLocation (currentlocation.getLatitude(), currentlocation.getLongitude(),  userNW);
                Toast.makeText(MapsActivity.this, "Your Key has been input!", Toast.LENGTH_SHORT).show();
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
                        Intent c = new Intent(MapsActivity.this,SocialActivity.class);
                        startActivity(c);
                        break;
                }
                return true;
            }
        });
    }

    private void recordLocation(double latitude, double longitude, String trackerKey) {
        PostLocation postLocation = new PostLocation();
        postLocation.setLatitude(latitude);
        postLocation.setLongitude(longitude);
        postLocation.setTrackerKey (trackerKey);
        postLocation.setUser(ParseUser.getCurrentUser());
        postLocation.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error while saving location data", e);
                    Toast.makeText(MapsActivity.this, "Error while saving location data", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Post saved properly");
                UserNW.setText("");
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


}
