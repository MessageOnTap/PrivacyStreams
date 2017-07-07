package com.github.privacystreams;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MultiDex.install(this);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Button","Clicked");
                new MyAsyncTask().execute();
            }
        });
    }

    public void OAuth(Intent intent){
        startActivity(intent);

    }
    private class MyAsyncTask extends AsyncTask<Object, Object, Object> {
        @Override
        protected Object doInBackground(Object[] objects) {
            UseCases useCases = new UseCases(MainActivity.this);

//            useCases.getRecentCalledNames(2);

//            useCases.testImage();
//            useCases.testCurrentLocation();
//            useCases.testTextEntry();
//            useCases.testNotification();
//            useCases.testAudio();
//            useCases.testMockData();
//            useCases.testContacts();
//            useCases.testDeviceState();
//
//            useCases.testBrowserSearchUpdates();
//            useCases.testBrowserHistoryUpdates();
//
//            useCases.testAccEvents();
//
//            useCases.testIMUpdates();
 //           useCases.testEmailUpdates();
//            useCases.testEmailList();

//useCases.testUpdatesContact();
//            useCases.testWifiUpdates();
//            useCases.testIMUIUpdates();
//            useCases.testCalendarList();
            useCases.testEmailInfoList();
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.easilydo.com/v1/connect_email?api_key=29f043cde8b6636c395dc07fcac571f6&username=Mars&token=e50e70477d420f0de34b4ca8447d460b"));
//            startActivity(intent);



            return null;
        }
    }
}