package com.example.nightwalker;
import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.livequery.ParseLiveQueryClient;

import java.net.URI;
import java.net.URISyntaxException;


public class ParseApp extends Application {    // Creating parse application. Sama
    @Override
    public void onCreate() {
        super.onCreate();
        //Register your parse models
        ParseObject.registerSubclass(PostLocation.class);

        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("night-walker-application") // should correspond to APP_ID env variable
                .clientKey("nightwalkerprotection")  // set explicitly unless clientKey is explicitly configured on Parse server
                .server("https://night-walker-application.herokuapp.com/parse/").build());

        ParseLiveQueryClient parseLiveQueryClient = null;

        try {
            parseLiveQueryClient = ParseLiveQueryClient.Factory.getClient(new URI("wss://https://night-walker-application.herokuapp.com/parse/"));
        } catch (
                URISyntaxException e) {
            e.printStackTrace();

        }
    }
}
