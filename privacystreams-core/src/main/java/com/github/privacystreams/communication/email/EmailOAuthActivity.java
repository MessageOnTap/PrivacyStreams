package com.github.privacystreams.communication.email;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;


/**
 * Created by mars on 08/07/2017.
 */

public class EmailOAuthActivity extends Activity {

    private String PREF_IS_EMAILINFO_AUTHORIZED = "isEmailinfoAuthorized";
    private Intent mIntent;
    private String mApiKey;
    private String mUser;
    private String mToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiKey = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("ApiKey", null);
        mUser = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("Username", null);
        mToken = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("Token", null);

        mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.easilydo.com/v1/connect_email?api_key="+mApiKey
                +"&username="+mUser+"&token="+mToken+""));
        startActivity(mIntent);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString(PREF_IS_EMAILINFO_AUTHORIZED,"true");
        editor.apply();
    }
}
