package com.android.jccdex.jwallet;

import android.content.Context;
import android.text.TextUtils;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

/**
 * JWalletManager
 *
 * @author wanggang
 * Copyright © 2019 JCCDex. All rights reserved.
 */
public class JWalletManager implements JWallet {
    
    public static final String SWTC_CHAIN = "swt";
    public static final String BIZAIN_CHAIN = "bwt";

    private static final String JINGTUM_JS = "file:///android_asset/index.html";
    private static BridgeWebView mWebview;
    private static JWalletManager instance = new JWalletManager();

    private JWalletManager() {
    }

    public static JWalletManager getInstance(Context context) {
        mWebview = new BridgeWebView(context);
        mWebview.loadUrl(JINGTUM_JS);
        return instance;
    }

    @Override
    public void createWallet(String chain, CallBackFunction callBack) {
        mWebview.callHandler("createJingtumWallet", chain, callBack);
    }

    @Override
    public void importSecret(String secret, String chain, CallBackFunction callBack) {
        String data = toJsonString(secret, chain);
        mWebview.callHandler("importJingtumSecret", data, callBack);
    }

    @Override
    public void isValidAddress(String address, String chain, CallBackFunction callBack) {
        String data = toJsonString(address, chain);
        mWebview.callHandler("isJingtumAddress", data, callBack);
    }

    @Override
    public void isValidSecret(String secret, String chain, CallBackFunction callBack) {
        String data = toJsonString(secret, chain);
        mWebview.callHandler("isJingtumSecret", data, callBack);
    }


    @Override
    public void signTx(String transaction, String secret, String chain, CallBackFunction callBack) {
        String data = toSignJsonString(transaction, secret, chain);
        mWebview.callHandler("jingtumSignTx", data, callBack);
    }

    private String toJsonString(String address, String chain) {
        String jsonString;
        if (!TextUtils.isEmpty(chain)) {
            jsonString = "{\"key\":\"" + address + "\",\"chain\":\"" + chain + "\"}";
        } else {
            jsonString = "{\"key\":\"" + address + "\"}";
        }
        return jsonString;
    }

    private String toSignJsonString(String transaction, String secret, String chain) {
        String jsonString;
        if (!TextUtils.isEmpty(chain)) {
            jsonString = "{\"transaction\":" + transaction + ",\"secret\":\"" + secret + "\",\"chain\":\"" + chain + "\"}";
        } else {
            jsonString = "{\"transaction\":" + transaction + ",\"secret\":\"" + secret + "\"}";
        }
        return jsonString;
    }
}
