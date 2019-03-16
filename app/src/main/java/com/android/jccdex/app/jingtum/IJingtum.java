package com.android.jccdex.app.jingtum;

import com.android.jccdex.app.base.JCallback;

import org.json.JSONObject;

public interface IJingtum {
    /**
     * @param chain    default with swt.
     * @param callback
     */
    void createWallet(String chain, JCallback callback);

    /**
     * @param secret
     * @param chain    default with swt.
     * @param callback
     */
    void importSecret(String secret, String chain, JCallback callback);

    /**
     * @param address
     * @param chain    default with swt.
     * @param callback
     */
    void isValidAddress(String address, String chain, JCallback callback);

    /**
     * @param secret
     * @param chain    default with swt.
     * @param callback
     */
    void isValidSecret(String secret, String chain, JCallback callback);

    /**
     * @param transaction JSONObject
     * @param secret
     * @param chain       default with swt.
     * @param callback
     */
    void sign(JSONObject transaction, String secret, String chain, JCallback callback);
}
