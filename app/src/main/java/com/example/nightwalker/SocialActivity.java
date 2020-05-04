package com.example.nightwalker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SocialActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        TextView social = (TextView) findViewById(R.id.social_text);
        social.setText("This is social activity.");


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        Intent a = new Intent(SocialActivity.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.action_location:
                        Intent b = new Intent(SocialActivity.this, MapsActivity.class);
                        startActivity(b);
                        break;
                    case R.id.action_social:
                        break;
                }
                return true;
            }
        });
    }
}