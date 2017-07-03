package com.github.privacystreams.communication.email.api;


import android.os.AsyncTask;
import android.util.Log;

import com.github.privacystreams.communication.email.crypto.Signatory;
import com.github.privacystreams.communication.EmailInfoEntity;

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


/**
 * Primary class in the Sift Java SDK; used for interacting with the sift server.
 * After constructing an instance of the class with their api and secret keys,
 * the sift developer can make sift api calls by invoking the corresponding method.
 * All apis in the <a href="https://developer.easilydo.com/sift/documentation">Sift API docs</a>
 * are implemented, with the exception of the Connect Tokens and Connect Emails, which are intended
 * for a front end web flow.
 * <p>
 * This class handles all the common parameter packaging as well as request signature generation.
 * Any errors that occur during the api call (timeouts, http errors, etc.) 
 * are thrown as {@link ApiException}.
 */
public class ApiManager {

	public static final String API_ENDPOINT = "https://api.easilydo.com";

	private String mApiKey;
	private Signatory mSignatory;

	String mPath;
	JSONArray mJsonArray;

	/**
	* Sole constructor
	* @param apiKey	sift developer's api key
	* @param secretKey	sift developer's secret key
	*/
	public ApiManager(String apiKey, String secretKey) {
		this.mApiKey = apiKey;
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

		JSONArray results = null;


		try {
			results = new RequestWebTask().execute(params).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}


		//JSONObject jsonObject = new JSONObject(results.toString());

		if(results == null)
			sifts = null;
		else {
			System.out.println(results);

			for (int i = 0; i < results.length(); i++) {
				System.out.println(results.get((i)));
				sifts.add(EmailInfoEntity.unmarshallEmailInfoEntity(results.get((i))));
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


		public class RequestWebTask extends AsyncTask<Map<String,Object>,String,JSONArray> {

			private String getResponseText(InputStream in){
				return new Scanner(in).useDelimiter("\\A").next();
			}
			@Override
			//func get JSON Respond
			//Do in Background
			//Get authorization
			//Input URLs you want to get or post in String
			//Output: JSONObject(func Flight_JSON_To_FlightContent in edu.cmu.chimps.messageontap.utility.GenericUtils will help to change that into String)
			protected JSONArray doInBackground(Map<String,Object>... params) {

				String url = API_ENDPOINT + mPath + "?";
				for(String str:params[0].keySet()){
					url = url + "&" + str + "=" + params[0].get(str);
				}
				System.out.println(url);
				JSONObject jsonObject = null;
				HttpURLConnection urlconnection = null;
				try {

					//create connection
					URL urlToRequest = new URL(url);
					urlconnection = (HttpURLConnection) urlToRequest.openConnection();

					urlconnection.setRequestMethod("GET");


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
						return jsonObject.getJSONArray("result");
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
			protected void onPostExecute(JSONArray jsonArray_private) {
				mJsonArray = jsonArray_private;

			}
		}


	}
