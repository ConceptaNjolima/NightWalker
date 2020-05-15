package com.example.nightwalker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    //Created the mainActivity which connects to activity_main. Sama

    public static final String TAG = "MainActivity";
    private static final int REQUEST_CALL=1;
    public static final String CALL_NUMBER="8599791217";
    private BottomNavigationView bottomNavigationView;
    private Button btnLogout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageCall=findViewById(R.id.image_call);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        btnLogout = findViewById(R.id.btnLogout);
        imageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                goLogoutActivity();
            }

        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        break;
                    case R.id.action_location:
                        Intent a = new Intent(MainActivity.this,MapsActivity.class);
                        startActivity(a);
                        break;
                    case R.id.action_social:
                        Intent b = new Intent(MainActivity.this,SocialActivity.class);
                        startActivity(b);
                        break;
                    case R.id.action_NightWak:
                        Intent c = new Intent(MainActivity.this,NightWalkActivity.class);
                        startActivity(c);
                        break;
                }
                return true;
            }

        });

    }

    private void makePhoneCall() {
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }else{
            String dial="tel:"+CALL_NUMBER;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==REQUEST_CALL){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else{
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT);
            }
        }
    }

    private void goLogoutActivity() {
        Intent i = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(i);
    }


}
