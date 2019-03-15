package com.android.jccdex.app;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    private JTWalletManager mJTWalletManager;

    private JTWalletManager instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJTWalletManager = JTWalletManager.getInstance();
        instance = JTWalletManager.getInstance();
        instance.init(this);
    }

    public JTWalletManager getJTWalletManager() {
        return mJTWalletManager;
    }

    public JTWalletManager getInstance() {
        return instance;
    }
}
