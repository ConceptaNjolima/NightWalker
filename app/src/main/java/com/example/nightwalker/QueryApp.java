//package com.example.nightwalker;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.widget.TextView;
//
//import com.parse.ParseObject;
//import com.parse.ParseQuery;
//import com.parse.ParseUser;
//import com.parse.livequery.ParseLiveQueryClient;
//import com.parse.livequery.SubscriptionHandling;
//
//public class QueryApp extends AppCompatActivity {
//    TextView tvNW;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.activity_query_app);
//        tvNW = findViewById(R.id.tv_NightWalk);
//
//        // Message - Live Query
//        ParseLiveQueryClient parseLiveQueryClient = null ;
//        if (parseLiveQueryClient != null) {
//            final ParseQuery<PostLocation> parseQuery = ParseQuery.getQuery(PostLocation.class);
//            parseQuery.whereEqualTo("trackerKey", ParseUser.getCurrentUser());
//            SubscriptionHandling<PostLocation> subscriptionHandling = parseLiveQueryClient.subscribe(parseQuery);
//
////            subscriptionHandling.handleEvent(SubscriptionHandling.Event.CREATE, new SubscriptionHandling.HandleEventCallback<ParseObject>() {
////                @Override
////                public void onEvent(final ParseQuery<ParseObject> query, final ParseObject object) {
////                    Handler handler = new Handler(Looper.getMainLooper());
////                    handler.post(new Runnable() {
////                        public void run() {
////                            tvNW.setText(query.getClassName());
////                            //numPokes++;
////                           // if(numPokes == 1) {
////                                //pokeText.setText("Poked " + numPokes + " time.");
////                          //  }
////                          //  else {
////                           //     pokeText.setText("Poked " + numPokes + " times.");
////                            //}
////                        }
////                    });
////                }
////            });
////        }
//    }
//
