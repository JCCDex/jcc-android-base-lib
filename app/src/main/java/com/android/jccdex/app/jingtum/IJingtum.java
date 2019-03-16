package com.android.jccdex.app.jingtum;

import com.android.jccdex.app.base.JCallback;

import org.json.JSONObject;

public interface IJingtum {

    void createWallet(String chain, JCallback callback);

    void importSecret(String secret, String chain, JCallback callback);

    void isValidAddress(String address, String chain, JCallback callback);

    void isValidSecret(String secret, String chain, JCallback callback);

    void sign(JSONObject transaction, String secret, String chain, JCallback callback);
}
