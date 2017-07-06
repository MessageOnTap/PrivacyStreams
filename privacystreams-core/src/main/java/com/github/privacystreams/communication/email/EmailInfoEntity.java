package com.github.privacystreams.communication.email;


import com.github.privacystreams.core.Item;
import com.github.privacystreams.core.providers.MStreamProvider;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import static com.github.privacystreams.utils.JSONUtils.getJsonLeafNodes;


public class EmailInfoEntity extends Item{

	private static final String API_KEY = "29f043cde8b6636c395dc07fcac571f6";
	private static final String API_SECRET = "54ce16efb4d67116ac485eb540ac3bb20907ef07";

	private int mEmailInfoEntityId;
	private String mMimeId;
	private String mMid;
	private String mFid;
	private String mAccountId;
	private EmailInfoProvider mEmailInfoProvider;

	private final String DOMAIN = "domain";
	private final String PAYLOAD = "payload";



	public Domain getDomain() {
		return Domain.UNKNOWN;
	}

	public long getemailInfoEntityId() {
		return mEmailInfoEntityId;
	}

	public void setemailInfoEntityId(int emailInfoEntityId) {
		this.mEmailInfoEntityId = emailInfoEntityId;
	}

	public String getMimeId() {
		return mMimeId;
	}

	public void setMimeId(String mimeId) {
		this.mMimeId = mimeId;
	}

	public String getMid() {
		return mMid;
	}

	public void setMid(String mid) {
		this.mMid = mid;
	}

	public String getFid() {
		return mFid;
	}

	public void setFid(String fid) {
		this.mFid = fid;
	}

	public String getAccountId() {
		return mAccountId;
	}

	public void setAccountId(String accountId) {
		this.mAccountId = accountId;
	}

	public static EmailInfoEntity unmarshallEmailInfoEntity(Object result_obj) throws JSONException {
		JSONObject result = new JSONObject(result_obj.toString());
		//System.out.println(result);
		JSONObject payload = result.has("@type") ? result : result.getJSONObject("payload");
		String type = payload.get("@type").toString();

		if (type.startsWith("x-")) {
			type = type.substring(2);
		}

		EmailInfoEntity emailInfoEntity;
		try {
			//emailInfoEntity = (emailInfoEntity) objectMapper.treeToValue((TreeNode) payload, Class.forName("com.easilydo.emailInfoEntity.model.gen." + type));
			emailInfoEntity = (EmailInfoEntity) new Gson().fromJson(String.valueOf(payload), Class.forName("com.github.privacystreams.communication.email.gen." + type));
		} catch (ClassNotFoundException cnfex) {
			emailInfoEntity = new EmailInfoEntity();
		}

		if (result.has("sift_id")) {
			emailInfoEntity.setemailInfoEntityId((Integer) result.get("sift_id"));
			emailInfoEntity.setMimeId((String) result.get("mime_id"));
			emailInfoEntity.setMid((String) result.get("mid"));
			emailInfoEntity.setFid((String) result.get("fid"));
			emailInfoEntity.setAccountId((String) result.get("account_id"));
			emailInfoEntity.setFieldValue("id", result.get("sift_id")) ;
			emailInfoEntity.setFieldValue("domain", result.get("domain")) ;
			Map<String, String> additionalProperties = getJsonLeafNodes(result.getString("payload"));
			for(String str: additionalProperties.values()){
				emailInfoEntity.setFieldValue(str, additionalProperties.get(str));
			}
		}

		return emailInfoEntity;
	}

	public static MStreamProvider getAll() {
		return new EmailInfoProvider(API_KEY, API_SECRET);
	}


}
