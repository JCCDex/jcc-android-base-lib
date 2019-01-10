package com.android.jccdex.jwallet.util;

import org.json.JSONObject;

public class JCCJson {

    private JSONObject jsonObject;

    public JCCJson() {
        try {
            jsonObject = new JSONObject();
        } catch (Throwable e) {
            jsonObject = null;
        }
    }

    public JCCJson(String json) {
        if (json == null) {
            jsonObject = null;
        } else {
            try {
                jsonObject = new JSONObject(json);
            } catch (Throwable e) {
                jsonObject = null;
            }
        }
    }

    public void put(String name, Boolean value) {
        if(jsonObject != null) {
            try {
                jsonObject.put(name, value);
            } catch (Throwable e) {

            }
        }
    }

    public void put(String name, String value) {
        if (jsonObject != null) {
            try {
                jsonObject.put(name, value);
            } catch (Throwable e) {

            }
        }
    }

    public void put(String name, JSONObject value) {
        if (jsonObject != null) {
            try {
                jsonObject.put(name, value);
            } catch (Throwable e) {

            }
        }
    }

    public String getString(String name) {
        String value;
        if (jsonObject != null) {
            try {
                value = jsonObject.getString(name);
            } catch (Throwable e) {
                value = null;
            }
        } else {
            value = null;
        }
        return value;
    }

    public Boolean getBoolean(String name) {
        Boolean value;
        if (jsonObject != null) {
            try {
                value = jsonObject.getBoolean(name);
            } catch (Throwable e) {
                value = false;
            }
        } else {
            value = false;
        }
        return value;
    }

    public String toString() {
        String value;
        if (jsonObject == null) {
            value = "";
        } else {
            value = jsonObject.toString();
        }
        return value;
    }
}
