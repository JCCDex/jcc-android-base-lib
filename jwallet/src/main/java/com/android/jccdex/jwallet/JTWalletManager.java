package com.android.jccdex.jwallet;

import android.content.Context;

import com.android.jccdex.jwallet.base.JCallback;
import com.android.jccdex.jwallet.util.JCCJson;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import org.json.JSONObject;

/**
 * JWalletManager
 *
 * @author wanggang
 * Copyright © 2019 JCCDex. All rights reserved.
 */
public class JTWalletManager implements JWallet {
    
    public static final String SWTC_CHAIN = "swt";
    public static final String BIZAIN_CHAIN = "bwt";

    private static final String JINGTUM_JS = "file:///android_asset/index.html";
    private static BridgeWebView mWebview;
    private static JTWalletManager instance = new JTWalletManager();

    private JTWalletManager() {
    }

    public static JTWalletManager getInstance(Context context) {
        mWebview = new BridgeWebView(context);
        mWebview.loadUrl(JINGTUM_JS);
        return instance;
    }

    @Override
    public void createWallet(String chain, final JCallback callback) {
        mWebview.callHandler("createJingtumWallet", chain, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson(data);
                if (callback != null) {
                    callback.completion(jccJson);
                }
            }
        });
    }

    @Override
    public void importSecret(String secret, String chain, final JCallback callback) {
        JCCJson jccJson = new JCCJson();
        jccJson.put("secret", secret);
        jccJson.put("chain", chain);
        mWebview.callHandler("importJingtumSecret", jccJson.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson(data);
                if (callback != null) {
                    callback.completion(jccJson);
                }
            }
        });
    }

    @Override
    public void isValidAddress(String address, String chain, final JCallback callback) {
        JCCJson jccJson = new JCCJson();
        jccJson.put("address", address);
        jccJson.put("chain", chain);
        mWebview.callHandler("isJingtumAddress", jccJson.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson();
                jccJson.put("isValid", data == "true");
                if (callback != null) {
                    callback.completion(jccJson);
                }
            }
        });
    }

    @Override
    public void isValidSecret(String secret, String chain, final JCallback callback) {
        JCCJson jccJson = new JCCJson();
        jccJson.put("secret", secret);
        jccJson.put("chain", chain);
        mWebview.callHandler("isJingtumSecret", jccJson.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson jccJson = new JCCJson();
                jccJson.put("isValid", data == "true");
                if (callback != null) {
                    callback.completion(jccJson);
                }
            }
        });
    }

    @Override
    public void sign(JSONObject transaction, String secret, String chain, final JCallback callback) {
        JCCJson jccJson = new JCCJson();
        jccJson.put("transaction", transaction);
        jccJson.put("secret", secret);
        jccJson.put("chain", chain);
        mWebview.callHandler("jingtumSignTx", jccJson.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson  jccJson = new JCCJson();
                if (data != "null") {
                    jccJson.put("signature", data);
                }
                if (callback != null) {
                    callback.completion(jccJson);
                }
            }
        });
    }
}
