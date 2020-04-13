package com.example.nightwalker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SocialActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        TextView words = (TextView) findViewById(R.id.textView2);
        words.setText("This is social activity.");


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        Intent a = new Intent(SocialActivity.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.action_location:
                        Intent b = new Intent(SocialActivity.this, LocationActivity.class);
                        startActivity(b);
                        break;
                    case R.id.action_social:
                        // Intent c = new Intent(SocialActivity.this,SocialActivity.class);
                        //  startActivity(c);
                        break;
                }
                return true;
            }
        });
    }
}