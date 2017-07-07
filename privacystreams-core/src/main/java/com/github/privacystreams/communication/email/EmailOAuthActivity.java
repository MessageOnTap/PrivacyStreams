package com.github.privacystreams.communication.email;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;


/**
 * Created by mars on 08/07/2017.
 */

public class EmailOAuthActivity extends Activity {

    private String PREF_IS_EMAILINFO_AUTHORIZED = "isEmailinfoAuthorized"
    private Intent mIntent;
    EmailOAuthActivity(Intent intent){
        this.mIntent = intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(mIntent);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString(PREF_IS_EMAILINFO_AUTHORIZED,"true");
        editor.apply();
    }
}
