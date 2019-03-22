package com.android.jccdex.app.eos;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.jccdex.app.base.JCallback;
import com.android.jccdex.app.util.JCCJson;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import org.json.JSONObject;

public class EosWallet implements IEos {

    private static final String EOS_JS = "file:///android_asset/jccdex_eos.html";
    private static BridgeWebView mWebview;
    private static EosWallet instance = new EosWallet();

    public void init(Context context) {
        mWebview = new BridgeWebView(context);
        mWebview.loadUrl(EOS_JS);
    }

    public static EosWallet getInstance() {
        return instance;
    }

    public void initEosProvider(String eosChainId, String eosHttpEndpoint) {

        JCCJson json = new JCCJson();
        json.put("eosHttpEndpoint", eosHttpEndpoint);
        json.put("eosChainId", eosChainId);
        mWebview.callHandler("initEos", json.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {

            }
        });
    }

    @Override
    public void importSecret(String secret, @NonNull final JCallback callback) {
        mWebview.callHandler("importEosSecret", secret, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson(data);
                callback.completion(jccJson);
            }
        });
    }

    @Override
    public void getBalance(String account, @NonNull final JCallback callback) {
        mWebview.callHandler("eosBalance", account, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson json = new JCCJson(data);
                callback.completion(json);
            }
        });
    }

    @Override
    public void sendTransaction(JSONObject transaction, String secret, @NonNull final JCallback callback) {
        JCCJson json = new JCCJson();
        json.put("transaction", transaction);
        json.put("secret", secret);
        mWebview.callHandler("sendEosTransaction", json.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson json = new JCCJson(data);
                callback.completion(json);
            }
        });
    }
}
