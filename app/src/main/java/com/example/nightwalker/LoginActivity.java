package com.example.nightwalker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends AppCompatActivity {

        public static final String TAG ="LoginActivity";
        private EditText Username;
        private EditText Password;
        private Button Loginbutton;
        private Button Sign_up;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // if a user is already signed in, goes directly to mainActivity. Sama
        if (ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        Loginbutton = findViewById(R.id.Loginbutton);
        Sign_up = findViewById(R.id.Sign_up);


        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Login button");
                String username = Username.getText().toString();
                String password = Password.getText().toString();
                loginUser(username, password);
            }
        });
        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Sign Up button");
                String username = Username.getText().toString();
                String password = Password.getText().toString();
                signupUser(username, password);
            }
        });
    }

        private void loginUser(String username, String password){
            // We should go to mainActivity once user is signed in. Sama
            Log.i(TAG, "Attempting to login user" + username);
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (e != null) {
                                Log.e(TAG, "There are issues with login", e);
                                Toast.makeText(LoginActivity.this, "There are issues with login!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            goMainActivity();
                            Toast.makeText(LoginActivity.this, "Login success!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        // goMainActivity takes us from LoginActivity to MainActivity if login is successful. Sama
        private void goMainActivity(){
            Intent i = new Intent (this, MainActivity.class);
            startActivity(i);
    }


    // Immanuela Wrote the next section

    private void signupUser (final String username, final String password){
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            @Override

            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    loginUser(username, password);
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e(TAG,"Sign Up failed.");
                }
            }
        });
    }
}
