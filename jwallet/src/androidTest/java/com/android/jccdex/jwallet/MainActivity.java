package com.android.jccdex.jwallet;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    private JTWalletManager mJTWalletManager;

    private JTWalletManager instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJTWalletManager = JTWalletManager.getInstance(this);
        instance = JTWalletManager.getInstance(this);
    }

    public JTWalletManager getJTWalletManager() {
        return mJTWalletManager;
    }

    public JTWalletManager getInstance() {
        return instance;
    }
}
