package com.github.privacystreams.communication.email;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.github.privacystreams.core.providers.MStreamProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import static com.github.privacystreams.communication.email.Signatory.randomString;

/**
 * Created by mars on 03/07/2017.
 */

public class EmailInfoProvider extends MStreamProvider {

    /**
     * beginTime and endTime are all in ms.
     * maxResult denotes the max number of
     * return email allowed for one query.
     */

    public static final String API_ENDPOINT = "https://api.easilydo.com";
    private String PREF_IS_EMAILINFO_AUTHORIZED = "isEmailinfoAuthorized";

    private String mApiKey;
    private Signatory mSignatory;
    private String mUser;
    private String mToken;
    private String mApiSecret;

    String mPath;
    JSONObject mJsonObject;

    /**
     * Sole constructor
     * @param apiKey	sift developer's api key
     * @param secretKey	sift developer's secret key
     */
    public EmailInfoProvider(String apiKey, String secretKey) {
        this.mApiKey = apiKey;
        this.mApiSecret = secretKey;
        this.mSignatory = new Signatory(secretKey);
    }

    /**
     * Get a list of sifts for the given user
     * @param username	username of the user to fetch sifts for
     * @return 	list of sifts in descending order of last update time
     */
    public List<EmailInfoEntity> listEmailInfoEntity(String username) {
        List<EmailInfoEntity> sifts = null;
        try {
            sifts =  listEmailInfoEntity(username, null, null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sifts;
    }

    /**
     * Get a list of sifts for the given user since a certain date
     * @param username	username of the user to fetch sifts for
     * @param lastUpdateTime	only return sifts updated since this date
     * @return 	list of sifts in descending order of last update time
     */
    public List<EmailInfoEntity> listEmailInfoEntity(String username, Date lastUpdateTime) {
        List<EmailInfoEntity> sifts = null;
        try {
            sifts = listEmailInfoEntity(username, lastUpdateTime, null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sifts;
    }

    /**
     * Get a list of sifts for the given user since a certain date
     * @param username	username of the user to fetch sifts for
     * @param lastUpdateTime	only return sifts updated since this date
     * @param offset	used for paging, where to start
     * @param limit	maximum number of results to return
     * @return 	list of sifts in descending order of last update time
     */
    public List<EmailInfoEntity> listEmailInfoEntity(String username, Date lastUpdateTime, Integer offset, Integer limit) throws JSONException {
        List<EmailInfoEntity> sifts = new ArrayList<EmailInfoEntity>();
        mPath = String.format("/v1/users/%s/sifts", username);

        Map<String,Object> params = new HashMap<String,Object>();

        if(lastUpdateTime != null) {
            params.put("last_update_time", getEpochSecs(lastUpdateTime));
        }

        if(offset != null) {
            params.put("offset", offset);
        }

        if(limit != null) {
            params.put("limit", limit);
        }

        addCommonParams("GET", mPath, params);

        JSONObject results = null;
        JSONArray jsonArray = null;


        try {
            String url = API_ENDPOINT + mPath + "?";
            for(String str:params.keySet()){
                url = url + "&" + str + "=" + params.get(str);
            }
            results = new FetchEmailInfoTask().execute(url).get();      //EmailInfoEntity
            jsonArray = new JSONArray(results.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        //JSONObject jsonObject = new JSONObject(results.toString());

        if(results == null)
            sifts = null;
        else {
            //System.out.println(results);

            for (int i = 0; i < jsonArray.length(); i++) {
                //System.out.println(Integer.toBinaryString(i) + " " + results.get((i)));
                EmailInfoEntity emailInfoEntity = EmailInfoEntity.unmarshallEmailInfoEntity(jsonArray.get((i)));
                this.output(emailInfoEntity);
                sifts.add(emailInfoEntity);
            }
        }


        return sifts;
    }


    protected Map<String,Object> addCommonParams(String method, String path, Map<String,Object> params) {
        params.put("api_key", mApiKey);
        params.put("timestamp", System.currentTimeMillis() / 1000);
        params.put("signature", mSignatory.generateSignature(method, path, params));

        return params;
    }

    protected static long getEpochSecs(Date date) {
        return date.getTime() / 1000;
    }

    public class FetchEmailInfoTask extends AsyncTask<String,String,JSONObject> {

        private String getResponseText(InputStream in){
            return new Scanner(in).useDelimiter("\\A").next();
        }
        @Override
        //func get JSON Respond
        //Do in Background
        //Get authorization
        //Input URLs you want to get or post in String
        //Output: JSONObject(func Flight_JSON_To_FlightContent in edu.cmu.chimps.messageontap.utility.GenericUtils will help to change that into String)
        protected JSONObject doInBackground(String... url) {

            JSONObject jsonObject = null;
            HttpURLConnection urlconnection = null;
            try {

                //create connection
                URL urlToRequest = new URL(url[0]);
                urlconnection = (HttpURLConnection) urlToRequest.openConnection();

                if(url[0].contains("sifts"))
                    urlconnection.setRequestMethod("GET");
                else
                    urlconnection.setRequestMethod("POST");


                int statusCode = urlconnection.getResponseCode();
                if(statusCode == HttpURLConnection.HTTP_UNAUTHORIZED){
                    return null;
                }
                else if(statusCode != HttpURLConnection.HTTP_OK){
                    return null;

                }
                else
                {
                    Log.e("aaa",urlconnection.getResponseMessage()+"");
                    InputStream in = new BufferedInputStream(urlconnection.getInputStream());
                    jsonObject = new JSONObject(getResponseText(in));
                    if(url[0].contains("sifts"))
                        return jsonObject.getJSONObject("result");
                    else
                        return jsonObject;
                }

            } catch (MalformedURLException e) {
                // URL is invalid
                e.printStackTrace();
            } catch (SocketTimeoutException e) {
                // data retrieval or connection timed out
                e.printStackTrace();
            } catch (IOException e) {
                // could not read response body
                // (could not create input stream)
                e.printStackTrace();
            } catch (JSONException e) {
                // response body is no valid JSON string
                e.printStackTrace();
            } finally {
                if (urlconnection != null) {
                    urlconnection.disconnect();
                }
            }

            return null;

        }




        //Exit
        //Back to main Thread
        //Input JSONObject
        //Series of operation
        protected void onPostExecute(JSONObject jsonObject_private) {
            mJsonObject = jsonObject_private;

        }
    }

    /**
     * For queries in all other times later on, when the app does not need to get
     * authorization and permission from the activity all over again.
     */

    public boolean addUser(String username, String locale) {
        boolean flag = false;
        String path = "/v1/users";
        Map<String, Object> params = new HashMap();
        params.put("username", username);
        params.put("locale", locale);
        addCommonParams("POST", path, params);
        String url = API_ENDPOINT + path + "?";
        for(String str:params.keySet()){
            url = url + "&" + str + "=" + params.get(str);
        }
        try {
            JSONObject results = new FetchEmailInfoTask().execute(url).get();        //addUser
            flag = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return flag;
    }

    protected void provide() {
        String isAuthorized = PreferenceManager.getDefaultSharedPreferences(getContext())
                .getString(PREF_IS_EMAILINFO_AUTHORIZED, null);
        if(isAuthorized == null) {
            authorizeUser();
        }
        else {
            listEmailInfoEntity(mUser);
        }
    }

    /**
     * When the app just got the authorization and permission from the activity, it goes to this callback.
     */
    public void onSuccess() {
        listEmailInfoEntity(mUser);
    }

    public void authorizeUser(){
        mUser = randomString();
        addUser(mUser, "en_US");
        mToken = getToken();

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
        editor.putString("ApiKey",mApiKey);
        editor.putString("Username", mUser);
        editor.putString("Token", mToken);
        editor.apply();
        final Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.easilydo.com/v1/connect_email?api_key="+mApiKey
                +"&username="+mUser+"&token="+mToken+""));
        getContext().startActivity(browserIntent);

    }

    public String getToken(){

        String resource = "/v1/connect_token";
        String url = "https://api.easilydo.com" + resource;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("api_key", mApiKey);
        params.put("timestamp", System.currentTimeMillis() / 1000L);
        params.put("username", mUser);

        String signature = new Signatory(mApiSecret).generateSignature("POST", resource, params);
        params.put("signature", signature);

        url = url + "?";
        for(String str:params.keySet()){
            url = url + "&" + str + "=" + params.get(str);
        }

        try {
            JSONObject response = new FetchEmailInfoTask().execute(url).get();
            JSONObject jsonObject = new JSONObject(response.toString());
            jsonObject = jsonObject.getJSONObject("result");
            String token = jsonObject.getString("connect_token");
            return token;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }


}
