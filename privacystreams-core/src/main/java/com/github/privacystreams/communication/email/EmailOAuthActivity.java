package com.github.privacystreams.communication.email;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by mars on 08/07/2017.
 */

public class EmailOAuthActivity extends Activity {

    private Intent mIntent;
    EmailOAuthActivity(Intent intent){
        this.mIntent = intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(mIntent);
    }
}
