package com.github.privacystreams.utils;

import android.graphics.Rect;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.github.privacystreams.audio.AudioData;
import com.github.privacystreams.image.ImageData;
import com.github.privacystreams.location.LatLon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A set of io-related utility functions.
 */

public class IOUtils {

    public static Object serialize(Object object) {
        if (object == null) return null;

        if (object instanceof Number) {
            return object;
        }
        else if (object instanceof String) {
            return object;
        }
        else if (object instanceof List) {
            List<Object> result = new ArrayList<>();
            for (Object obj : (List) object) {
                result.add(serialize(obj));
            }
            return result;
        }
        else if (object instanceof Map) {
            Map<Object, Object> result = new HashMap<>();
            for (Object key : ((Map) object).keySet()) {
                Object value = ((Map) object).get(key);
                result.put(serialize(key), serialize(value));
            }
            return result;
        }
        else if (object instanceof LatLon) {
            Map<String, Double> result = new HashMap<>();
            result.put("lat", ((LatLon) object).getLatitude());
            result.put("lon", ((LatLon) object).getLongitude());
            return result;
        }
        else if (object instanceof AccessibilityEvent) {
            Map<String, Object> result = new HashMap<>();
            AccessibilityEvent event = (AccessibilityEvent) object;
            result.put("event_type", AccessibilityEvent.eventTypeToString(event.getEventType()));
            result.put("event_time", event.getEventTime());
            result.put("package_name", event.getPackageName());
//            result.put("action", event.getAction());
//            result.put("class_name", event.getClassName());
//            result.put("text", event.getText());
//            result.put("content_description", event.getContentDescription());
//            result.put("item_count", event.getItemCount());
//            result.put("current_item_index", event.getCurrentItemIndex());
//            result.put("is_enabled", event.isEnabled());
//            result.put("is_password", event.isPassword());
//            result.put("is_checked", event.isChecked());
//            result.put("is_full_screen", event.isFullScreen());
//            result.put("is_scrollable", event.isScrollable());
//            result.put("before_text", event.getBeforeText());
//            result.put("from_index", event.getFromIndex());
//            result.put("to_index", event.getToIndex());
//            result.put("scroll_x", event.getScrollX());
//            result.put("scroll_y", event.getScrollY());
//            result.put("max_scroll_x", event.getMaxScrollX());
//            result.put("max_scroll_y", event.getMaxScrollY());
//            result.put("removed_count", event.getRemovedCount());
            AccessibilityNodeInfo sourceNode = event.getSource();
            result.put("source_node", serialize(sourceNode));
            return result;
        }
        else if (object instanceof AccessibilityNodeInfo) {
            Map<String, Object> result = new HashMap<>();
            AccessibilityNodeInfo nodeInfo = (AccessibilityNodeInfo) object;
//            result.put("package_name", nodeInfo.getPackageName());
            result.put("text", nodeInfo.getText());
            result.put("class_name", nodeInfo.getClassName());
            result.put("child_count", nodeInfo.getChildCount());
            result.put("content_description", nodeInfo.getContentDescription());
            result.put("is_enabled", nodeInfo.isEnabled());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                result.put("view_id", nodeInfo.getViewIdResourceName());
//                result.put("is_editable", nodeInfo.isEditable());
            }
//            result.put("is_scrollable", nodeInfo.isScrollable());
//            result.put("is_checked", nodeInfo.isChecked());
//            result.put("is_password", nodeInfo.isPassword());
//            result.put("is_checkable", nodeInfo.isCheckable());
//            result.put("is_clickable", nodeInfo.isClickable());
//            result.put("is_focused", nodeInfo.isFocused());
//            result.put("is_focusable", nodeInfo.isFocusable());
//            result.put("is_visible_to_user", nodeInfo.isVisibleToUser());
//            result.put("is_long_clickable", nodeInfo.isLongClickable());
//            result.put("is_selected", nodeInfo.isSelected());

            Rect boundsInScreen = new Rect();
            nodeInfo.getBoundsInScreen(boundsInScreen);
            result.put("bounds", serialize(boundsInScreen));
//            Rect boundsInParent = new Rect();
//            nodeInfo.getBoundsInParent(boundsInParent);
//            result.put("bounds_in_parent", serialize(boundsInParent));

            List<Object> childNodes = new ArrayList<>();
            for(int i = 0; i < nodeInfo.getChildCount(); i ++){
                AccessibilityNodeInfo childNode = nodeInfo.getChild(i);
                if(childNode != null){
                    childNodes.add(serialize(childNode));
                }
            }
            result.put("child_nodes", childNodes);

            return result;
        }
        else if (object instanceof Rect) {
            Rect rect = (Rect) object;
            return new int[]{rect.left, rect.top, rect.right, rect.bottom};
        }
        else if (object instanceof ImageData) {
            // pass
        }
        else if (object instanceof AudioData) {
            // pass
        }
        return object.toString();
    }
}
