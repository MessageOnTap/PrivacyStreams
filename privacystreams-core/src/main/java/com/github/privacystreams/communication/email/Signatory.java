package com.github.privacystreams.communication.email;

import java.security.SignatureException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Signatory {
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

	private String msecretKey;

	public Signatory(String secretKey) {
		this.msecretKey = secretKey;
	}

	public String generateSignature(String httpMethod, String path, Map<String, Object> params) {
		StringBuilder baseString = new StringBuilder();
		baseString.append(httpMethod.toUpperCase()).append("&").append(path);

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		for(String key: keys) {
			baseString.append("&").append(key).append("=").append(params.get(key));
		}

		String signature = signData(baseString.toString(), msecretKey);
		return signature;
	}

	/**
	 * Converts a byte array to a hex string.
	 * @param bytes
	 * The byte array to be converted.
	 * @return
	 * The converted hex string.
	 */
	private static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];

		for (int j = 0; j < bytes.length; j++)
		{
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}

		return new String(hexChars).toLowerCase();
	}

	/**
	 * Computes hex HMAC-SHA1 signature for use with Sift APIs.
	 * @param data
	 * The data to be signed.
	 * @param key
	 * The signing key.
	 * @return
	 * The hex HMAC signature.
	 * @throws
	 * SignatureException when signature generation fails
	 */
	private static String signData(String data, String key) {
		String result;

		try {
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(Charset.forName("UTF-8")), HMAC_SHA1_ALGORITHM);
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes(Charset.forName("UTF-8")));
			result = bytesToHex(rawHmac);
		} catch (Exception ex) {
			throw new RuntimeException("Failed to generate HMAC sig", ex);
		}

		return result;
	}
}
