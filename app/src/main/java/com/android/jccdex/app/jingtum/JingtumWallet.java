package com.android.jccdex.app.jingtum;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.jccdex.app.base.JCallback;
import com.android.jccdex.app.util.JCCJson;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import org.json.JSONObject;

public class JingtumWallet implements IJingtum {
    public static final String SWTC_CHAIN = "swt";
    public static final String BIZAIN_CHAIN = "bwt";

    private static final String JINGTUM_JS = "file:///android_asset/jccdex_jingtum.html";
    private static BridgeWebView mWebview;
    private static JingtumWallet instance = new JingtumWallet();

    private JingtumWallet() {
    }

    public void init(Context context) {
        mWebview = new BridgeWebView(context);
        mWebview.loadUrl(JINGTUM_JS);
    }

    public static JingtumWallet getInstance() {
        return instance;
    }

    @Override
    public void createWallet(String chain, @NonNull final JCallback callback) {
        mWebview.callHandler("createJingtumWallet", chain, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson(data);
                callback.completion(jccJson);
            }
        });
    }

    @Override
    public void importSecret(String secret, String chain, @NonNull final JCallback callback) {
        JCCJson jccJson = new JCCJson();
        jccJson.put("secret", secret);
        jccJson.put("chain", chain);
        mWebview.callHandler("importJingtumSecret", jccJson.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson(data);
                callback.completion(jccJson);
            }
        });
    }

    @Override
    public void isValidAddress(String address, String chain, @NonNull final JCallback callback) {
        JCCJson jccJson = new JCCJson();
        jccJson.put("address", address);
        jccJson.put("chain", chain);
        mWebview.callHandler("isJingtumAddress", jccJson.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson();
                jccJson.put("isValid", data.equals("true"));
                callback.completion(jccJson);
            }
        });
    }

    @Override
    public void isValidSecret(String secret, String chain, @NonNull final JCallback callback) {
        JCCJson jccJson = new JCCJson();
        jccJson.put("secret", secret);
        jccJson.put("chain", chain);
        mWebview.callHandler("isJingtumSecret", jccJson.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson();
                jccJson.put("isValid", data.equals("true"));
                callback.completion(jccJson);
            }
        });
    }

    @Override
    public void sign(JSONObject transaction, String secret, String chain, @NonNull final JCallback callback) {
        JCCJson jccJson = new JCCJson();
        jccJson.put("transaction", transaction);
        jccJson.put("secret", secret);
        jccJson.put("chain", chain);
        mWebview.callHandler("jingtumSignTx", jccJson.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson();
                if (!data.equals("null")) {
                    jccJson.put("signature", data);
                }
                callback.completion(jccJson);
            }
        });
    }
}
