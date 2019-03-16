package com.android.jccdex.app.ethereum;

import android.content.Context;

import com.android.jccdex.app.base.JCallback;
import com.android.jccdex.app.util.JCCJson;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import org.json.JSONObject;

public class EthereumWallet implements IEthereum {

    private static final String ETHEREUM_JS = "file:///android_asset/jccdex_web3.html";
    private static BridgeWebView mWebview;
    private static EthereumWallet instance = new EthereumWallet();

    public void init(Context context) {
        mWebview = new BridgeWebView(context);
        mWebview.loadUrl(ETHEREUM_JS);
    }

    public static EthereumWallet getInstance() {
        return instance;
    }

    public void initWeb3Provider(String node) {
        mWebview.callHandler("initEthereum", node, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {

            }
        });
    }

    @Override
    public void createWallet(final JCallback callback) {
        mWebview.callHandler("createEtherumWallet", null, new CallBackFunction() {
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
    public void isValidAddress(String address, final JCallback callback) {
        mWebview.callHandler("isValidEthereumAddress", address, new CallBackFunction() {
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
    public void isValidSecret(String secret, final JCallback callback) {
        mWebview.callHandler("isValidEthereumSecret", secret, new CallBackFunction() {
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
    public void importSecret(String secret, final JCallback callback) {
        mWebview.callHandler("importEthereumSecret", secret, new CallBackFunction() {
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
    public void importWords(String words, final JCallback callback) {
        mWebview.callHandler("importEthereumWords", words, new CallBackFunction() {
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
    public void toIban(String address, final JCallback callback) {
        mWebview.callHandler("toEtherumIban", address, new CallBackFunction() {
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
    public void fromIban(String iban, final JCallback callback) {
        mWebview.callHandler("fromEthereumIban", iban, new CallBackFunction() {
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
    public void sign(JSONObject transaction, String secret, final JCallback callback) {
        JCCJson jccJson = new JCCJson();
        jccJson.put("transaction", transaction);
        jccJson.put("secret", secret);
        mWebview.callHandler("signEthereumTransaction", jccJson.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson json = new JCCJson(data);
                if (callback != null) {
                    callback.completion(json);
                }
            }
        });
    }

    @Override
    public void sendSignedTransaction(String signedTransaction, final JCallback callback) {
        mWebview.callHandler("sendEthereumSignedTransaction", signedTransaction, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson json = new JCCJson(data);
                if (callback != null) {
                    callback.completion(json);
                }
            }
        });
    }

    @Override
    public void gasPrice(final JCallback callback) {
        mWebview.callHandler("ethereumGasPrice", null, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson json = new JCCJson(data);
                if (callback != null) {
                    callback.completion(json);
                }
            }
        });
    }

    @Override
    public void getBalance(String address, final JCallback callback) {
        mWebview.callHandler("ethereumBalance", address, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JCCJson json = new JCCJson(data);
                if (callback != null) {
                    callback.completion(json);
                }
            }
        });
    }
}
