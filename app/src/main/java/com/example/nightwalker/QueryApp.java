//package com.example.nightwalker;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.media.browse.MediaBrowser;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.util.Log;
//
//import com.parse.FindCallback;
//import com.parse.Parse;
//import com.parse.ParseException;
//import com.parse.ParseObject;
//import com.parse.ParseQuery;
//import com.parse.ParseUser;
//import com.parse.livequery.ParseLiveQueryClient;
//import com.parse.livequery.SubscriptionHandling;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.List;
//
//public class QueryApp extends AppCompatActivity {
//    private static final String TAG = "QueryApp";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        //setContentView(R.layout.activity_query_app);
//
//        // Message - Live Query
//
//        if (parseLiveQueryClient != null) {
//            ParseQuery<PostLocation> parseQuery = new ParseQuery("PostLocation");
//            parseQuery.whereEqualTo("Latitude", "Longitude");
//            SubscriptionHandling<PostLocation> subscriptionHandling = parseLiveQueryClient.subscribe(parseQuery);
//
//            subscriptionHandling.handleEvent(SubscriptionHandling.Event.CREATE, new SubscriptionHandling.HandleEventCallback<PostLocation>() {
//                @Override
//                public void onEvent(ParseQuery<PostLocation> query, final PostLocation object) {
//                    Handler handler = new Handler(Looper.getMainLooper());
//                    handler.post(new Runnable() {
//                        public void run() {
//                            query =  ParseQuery.getQuery(PostLocation.class);
//                           query.include(PostLocation.KEY_USERNAME);
//                            query.findInBackground(new FindCallback<PostLocation>() {
//
//            @Override
//            public void done(List<PostLocation> postLocations, ParseException e) {
//                if (e!= null){
//                    Log.i(TAG, "Issue with getting locations");
//                    return;
//                }
//                for (PostLocation postLocation : postLocations){
//                    Log.e(TAG, "User: "+ postLocation.getUser()+" Latitude " +postLocation.getLatitude() + " Longitude "+ postLocation.getLongitude(),e);
//                }
//                            //if
////                            EditText pokeText = findViewById(R.id.pokeText);
////                            numPokes++;
////                            if(numPokes == 1) {
////                                pokeText.setText("Poked " + numPokes + " time.");
////                            }
////                            else {
////                                pokeText.setText("Poked " + numPokes + " times.");
////                            }
//                        }
//                    });
//                }
//            });
//        }
//    }
//}
