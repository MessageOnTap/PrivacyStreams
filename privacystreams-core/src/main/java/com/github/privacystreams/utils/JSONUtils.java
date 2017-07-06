package com.github.privacystreams.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mars on 06/07/2017.
 */

public class JSONUtils {
    public static Map<String, String> getJsonLeafNodes(String payload){

        Map<String, String> result = new HashMap<>();
        String[] splitPayload = payload.split("\"");
        for(int i=1;i<splitPayload.length-1;i++){
            if(splitPayload[i].equals(":")) {
                if (isLeafNode(splitPayload[i - 1]) && isLeafNode(splitPayload[i + 1])) {
                    result.put(splitPayload[i - 1], splitPayload[i + 1]);
                }
            }
        }
        return result;
    }

    private static boolean isLeafNode(String str){
        if(str.equals("") || str.charAt(0) == ',' || str.charAt(0) == '[' || str.charAt(0) == '{'
                || str.charAt(0) == ']' || str.charAt(0) == '}' || str.equals("null"))
            return false;
        return true;
    }
}
