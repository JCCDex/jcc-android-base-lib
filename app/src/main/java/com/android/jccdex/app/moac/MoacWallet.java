package com.android.jccdex.app.moac;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.jccdex.app.base.JCallback;
import com.android.jccdex.app.util.JCCJson;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import org.json.JSONObject;

public class MoacWallet implements IMoac {

    private static final String MOAC_JS = "file:///android_asset/jccdex_moac.html";
    private static BridgeWebView mWebview;
    private static MoacWallet instance = new MoacWallet();

    public void init(Context context) {
        mWebview = new BridgeWebView(context);
        mWebview.loadUrl(MOAC_JS);
    }

    public static MoacWallet getInstance() {
        return instance;
    }

    public void initChian3Provider(String node) {
        mWebview.callHandler("initChain3", node, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {

            }
        });
    }

    @Override
    public void createWallet(@NonNull final JCallback callback) {
        mWebview.callHandler("createMoacWallet", null, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson(data);
                callback.completion(jccJson);
            }
        });
    }

    @Override
    public void isValidAddress(String address, @NonNull final JCallback callback) {
        mWebview.callHandler("isValidMoacAddress", address, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson();
                jccJson.put("isValid", data.equals("true"));
                callback.completion(jccJson);
            }
        });
    }

    @Override
    public void isValidSecret(String secret, @NonNull final JCallback callback) {
        mWebview.callHandler("isValidMoacSecret", secret, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson();
                jccJson.put("isValid", data.equals("true"));
                callback.completion(jccJson);
            }
        });
    }

    @Override
    public void importSecret(String secret, @NonNull final JCallback callback) {
        mWebview.callHandler("importMoacSecret", secret, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson(data);
                callback.completion(jccJson);
            }
        });
    }

    @Override
    public void toIban(String address, @NonNull final JCallback callback) {
        mWebview.callHandler("toMoacIban", address, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson(data);
                callback.completion(jccJson);
            }
        });
    }

    @Override
    public void fromIban(String iban, @NonNull final JCallback callback) {
        mWebview.callHandler("fromMoacIban", iban, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson(data);
                callback.completion(jccJson);
            }
        });
    }

    @Override
    public void sign(JSONObject transaction, String secret, @NonNull final JCallback callback) {
        JCCJson jccJson = new JCCJson();
        jccJson.put("transaction", transaction);
        jccJson.put("secret", secret);
        mWebview.callHandler("signMoacTransaction", jccJson.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson json = new JCCJson(data);
                callback.completion(json);
            }
        });
    }

    @Override
    public void sendSignedTransaction(String signedTransaction, @NonNull final JCallback callback) {
        mWebview.callHandler("sendMoacSignedTransaction", signedTransaction, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson json = new JCCJson(data);
                callback.completion(json);
            }
        });
    }

    @Override
    public void gasPrice(@NonNull final JCallback callback) {
        mWebview.callHandler("moacGasPrice", null, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson json = new JCCJson(data);
                callback.completion(json);
            }
        });
    }

    @Override
    public void getBalance(String address, @NonNull final JCallback callback) {
        mWebview.callHandler("moacBalance", address, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson json = new JCCJson(data);
                callback.completion(json);
            }
        });
    }
}
