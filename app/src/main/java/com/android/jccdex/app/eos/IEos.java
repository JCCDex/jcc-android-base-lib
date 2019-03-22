package com.android.jccdex.app.eos;

import com.android.jccdex.app.base.JCallback;

import org.json.JSONObject;

public interface IEos {

    void importSecret(String secret, JCallback callback);

    void getBalance(String account, JCallback callback);

    void sendTransaction(JSONObject transaction, String secret, JCallback callback);
}
