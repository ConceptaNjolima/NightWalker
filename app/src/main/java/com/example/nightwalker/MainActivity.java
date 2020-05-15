package com.example.nightwalker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //Created the mainActivity which connects to activity_main. Sama

    public static final String TAG = "MainActivity";
    private static final int REQUEST_CALL=1;
    public static final String CALL_NUMBER="8599791217";
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageCall=findViewById(R.id.image_call);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        imageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        Intent a = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(a);
                        Toast toast = Toast.makeText(getApplicationContext(),"home",Toast. LENGTH_SHORT);
                        toast.show();
                        break;
                    case R.id.action_location:
                        Intent b = new Intent(MainActivity.this,MapsActivity.class);
                        startActivity(b);
                        Toast toast_location = Toast.makeText(getApplicationContext(),"location",Toast. LENGTH_SHORT);
                        toast_location.show();
                        break;
                    case R.id.action_social:
                        Intent c = new Intent(MainActivity.this,SocialActivity.class);
                        startActivity(c);
                        Toast toast_social = Toast.makeText(getApplicationContext(),"social",Toast. LENGTH_SHORT);
                        toast_social.show();
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

}
