package com.android.jccdex.jwallet;

import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.github.lzyzsd.jsbridge.CallBackFunction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(AndroidJUnit4.class)
public class JWalletManagerTest {

    JWalletManager mJWalletManager;
    CallBackFunction callBack;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createWallet() {
        Looper.prepare();
//        TestLooperManager testLooperManager = InstrumentationRegistry.getInstrumentation().acquireLooperManager(Looper.myLooper());
        mJWalletManager = JWalletManager.getInstance(InstrumentationRegistry.getTargetContext());
        // swt wallet
//        callBack = Mockito.mock(CallBackFunction.class);
        Log.v("WWW", "ccc");
        callBack = new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                Log.v("WWW", data);
            }
        };
        mJWalletManager.createWallet("", callBack);
//        Mockito.verify(callBack).onCallBack(Mockito.anyString());
//        testLooperManager.release();
        // bwt wallet
//        callBack = Mockito.mock(CallBackFunction.class);
//        mJWalletManager.createWallet(JWalletManager.BIZAIN_CHAIN, null);
//        Mockito.verify(callBack).onCallBack(Mockito.anyString());
    }

    @Test
    public void importSecret() {
        //swt secret
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.importSecret("snfXQMEVbbZng84CcfdKDASFRi4Hf", JWalletManager.SWTC_CHAIN, callBack);
        Mockito.verify(callBack).onCallBack("{secret: \"snfXQMEVbbZng84CcfdKDASFRi4Hf\", address: \"jpgWGpfHz8GxqUjz5nb6ej8eZJQtiF6KhH\"}");

        //bwt secret
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.importSecret("ssZs9t8D9MbwS8f3214m3rKtfV1YQ", JWalletManager.BIZAIN_CHAIN, callBack);
        Mockito.verify(callBack).onCallBack("{secret: \"ssZs9t8D9MbwS8f3214m3rKtfV1YQ\", address: \"bw9x3rKp8fgWAqZaNSDC2bpjdrS12347Hc\"}");

        //none secret
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.importSecret("", JWalletManager.BIZAIN_CHAIN, callBack);
        Mockito.verify(callBack).onCallBack("null");
    }

    @Test
    public void isValidAddress() {
        //swt address
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.isValidAddress("jpgWGpfHz8GxqUjz5nb6ej8eZJQtiF6KhH", "", callBack);
        Mockito.verify(callBack).onCallBack("true");

        //address unmatch with chain
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.isValidAddress("jpgWGpfHz8GxqUjz5nb6ej8eZJQtiF6KhH", JWalletManager.BIZAIN_CHAIN, callBack);
        Mockito.verify(callBack).onCallBack("false");

        //bwt address
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.isValidAddress("bw9x3rKp8fgWAqZaNSDC2bpjdrS12347Hc", JWalletManager.BIZAIN_CHAIN, callBack);
        Mockito.verify(callBack).onCallBack("true");

        //address unmatch with chain
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.isValidAddress("jpgWGpfHz8GxqUjz5nb6ej8eZJQtiF6KhH", JWalletManager.SWTC_CHAIN, callBack);
        Mockito.verify(callBack).onCallBack("false");

        //none address
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.isValidAddress("", JWalletManager.BIZAIN_CHAIN, callBack);
        Mockito.verify(callBack).onCallBack("false");
    }

    @Test
    public void isValidSecret() {
        //swt secret
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.isValidSecret("snfXQMEVbbZng84CcfdKDASFRi4Hf", "", callBack);
        Mockito.verify(callBack).onCallBack("true");

        //secret unmatch with chain
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.isValidSecret("snfXQMEVbbZng84CcfdKDASFRi4Hf", JWalletManager.BIZAIN_CHAIN, callBack);
        Mockito.verify(callBack).onCallBack("false");

        //bwt secret
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.isValidSecret("ssZs9t8D9MbwS8f3214m3rKtfV1YQ", JWalletManager.BIZAIN_CHAIN, callBack);
        Mockito.verify(callBack).onCallBack("true");

        //secret unmatch with chain
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.isValidSecret("ssZs9t8D9MbwS8f3214m3rKtfV1YQ", JWalletManager.SWTC_CHAIN, callBack);
        Mockito.verify(callBack).onCallBack("false");

        //none secret
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.isValidSecret("", JWalletManager.BIZAIN_CHAIN, callBack);
        Mockito.verify(callBack).onCallBack("false");

    }

    @Test
    public void signTx() {
        // sign successfully
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.signTx("{TransactionType:\"Payment\"}", "ssZs9t8D9MbwS8f3214m3rKtfV1YQ", JWalletManager.BIZAIN_CHAIN, callBack);
        Mockito.verify(callBack).onCallBack("false");
        // sign unsuccessfully
        callBack = Mockito.mock(CallBackFunction.class);
        mJWalletManager.signTx("", "ssZs9t8D9MbwS8f3214m3rKtfV1YQ", JWalletManager.BIZAIN_CHAIN, callBack);
        Mockito.verify(callBack).onCallBack("null");
    }
}