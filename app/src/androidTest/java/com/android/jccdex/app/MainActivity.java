package com.android.jccdex.app;

import android.app.Activity;
import android.os.Bundle;

import com.android.jccdex.app.jingtum.JingtumWallet;

public class MainActivity extends Activity {
    private JingtumWallet mJingtumWallet;

    private JingtumWallet instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJingtumWallet = JingtumWallet.getInstance();
        instance = JingtumWallet.getInstance();
        instance.init(this);
    }

    public JingtumWallet getJTWalletManager() {
        return mJingtumWallet;
    }

    public JingtumWallet getInstance() {
        return instance;
    }
}
