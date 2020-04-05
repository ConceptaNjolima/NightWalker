package com.example.nightwalker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseUser;


public class LoginActivity extends AppCompatActivity {

        public static final String TAG ="LoginActivity";
        private EditText Username;
        private EditText Password;
        private Button Loginbutton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        Loginbutton = findViewById(R.id.Loginbutton);

        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Login button");
                String username = Username.getText().toString();
                String password = Password.getText().toString();
                loginUser(username, password);
            }
        });
    }

        private void loginUser(String username, String password){
            Log.i(TAG, "Attempting to login user" + username);
            // TO DO: We should go to mainActivity once user is signed in. Sama
        }


    }
}
