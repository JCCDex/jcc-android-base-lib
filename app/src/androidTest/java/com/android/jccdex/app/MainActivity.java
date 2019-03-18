package com.android.jccdex.app;

import android.app.Activity;
import android.os.Bundle;

import com.android.jccdex.app.ethereum.EthereumWallet;
import com.android.jccdex.app.jingtum.JingtumWallet;
import com.android.jccdex.app.moac.MoacWallet;

public class MainActivity extends Activity {
    private JingtumWallet mJingtumWallet;
    private EthereumWallet mEthereumWallet;
    private MoacWallet mMoacWallet;

    private JingtumWallet instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJingtumWallet = JingtumWallet.getInstance();
        mEthereumWallet = EthereumWallet.getInstance();
        mMoacWallet = MoacWallet.getInstance();
        instance = JingtumWallet.getInstance();
        instance.init(this);
        mEthereumWallet.init(this);
        mEthereumWallet.initWeb3Provider("https://eth626892d.jccdex.cn");
        mMoacWallet.init(this);
        mMoacWallet.initChian3Provider("https://moac1ma17f1.jccdex.cn");
    }

    public JingtumWallet getJTWalletManager() {
        return mJingtumWallet;
    }

    public JingtumWallet getInstance() {
        return instance;
    }

    public EthereumWallet getmEthereumWallet() {
        return mEthereumWallet;
    }

    public MoacWallet getmMoacWallet() {
        return mMoacWallet;
    }
}
