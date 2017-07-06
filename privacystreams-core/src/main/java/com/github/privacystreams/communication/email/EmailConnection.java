package com.github.privacystreams.communication.email;

/**
 * Created by mars on 06/07/2017.
 */

public class EmailConnection{

//    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
//    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
//    private static final String username = "dummy_username";
//    private static final String resource = "/v1/connect_token";
//    private static final String url = "https://api.easilydo.com" + resource;
//    private static final String apiKey = "dummy_api_key";
//    private static final String apiSecret = "dummy_api_secret";
//
//    public static String bytesToHex(byte[] bytes)
//    {
//        char[] hexChars = new char[bytes.length * 2];
//
//        for (int j = 0; j < bytes.length; j++)
//        {
//            int v = bytes[j] & 0xFF;
//            hexChars[j * 2]     = hexArray[v >>> 4];
//            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
//        }
//
//        return new String(hexChars).toLowerCase();
//    }
//
//    public static String signData(String data, String key)
//            throws java.security.SignatureException
//    {
//        String result;
//        try {
//            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
//            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
//            mac.init(signingKey);
//            byte[] rawHmac = mac.doFinal(data.getBytes());
//            result = bytesToHex(rawHmac);
//        } catch (Exception e) {
//            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
//        }
//        return result;
//    }
//
//    public static String generateSignature(String httpMethod, String resource, HashMap<String, Object> params)
//    {
//        String baseString = "";
//        baseString += httpMethod.toUpperCase();
//        baseString += "&" + resource;
//
//        ArrayList<String> list = new ArrayList<String>();
//        Iterator<String> it = params.keySet().iterator();
//        while (it.hasNext()) {
//            list.add(it.next());
//        }
//
//        Collections.sort(list);
//        it = list.iterator();
//        while(it.hasNext()) {
//            String key = it.next();
//            baseString += "&" + key + "=" + params.get(key);
//        }
//
//        try {
//            String signature = signData(baseString, apiSecret);
//            return signature;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return "";
//        }
//    }
//
//    @Override
//    protected JSONArray doInBackground(String... url) {
//        HashMap<String, Object> params = new HashMap<String, Object>();
//        params.put("api_key", apiKey);
//        params.put("timestamp", System.currentTimeMillis() / 1000L);
//        params.put("username", username);
//
//        String signature = generateSignature("POST", resource, params);
//        params.put("signature", signature);
//
//
//
//
//
//        HttpResponse<JsonNode> response = Unirest.post(url)
//                .header("content-type", "x-www-form-urlencoded")
//                .queryString(params)
//                .asJson();
//        System.out.println(response.getBody());
//    }
}
