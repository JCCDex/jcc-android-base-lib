package com.android.jccdex.app;

import android.app.Activity;
import android.os.Bundle;

import com.android.jccdex.app.ethereum.EthereumWallet;
import com.android.jccdex.app.jingtum.JingtumWallet;

public class MainActivity extends Activity {
    private JingtumWallet mJingtumWallet;
    private EthereumWallet mEthereumWallet;

    private JingtumWallet instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJingtumWallet = JingtumWallet.getInstance();
        mEthereumWallet = EthereumWallet.getInstance();
        instance = JingtumWallet.getInstance();
        instance.init(this);
        mEthereumWallet.init(this);
        mEthereumWallet.initWeb3Provider("https://eth626892d.jccdex.cn");
    }

    public JingtumWallet getJTWalletManager() {
        return mJingtumWallet;
    }

    public JingtumWallet getInstance() {
        return instance;
    }

    public EthereumWallet getmEthereumWallet() {
        return  mEthereumWallet;
    }
}
