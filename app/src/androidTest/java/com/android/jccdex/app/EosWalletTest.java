package com.android.jccdex.app;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.jccdex.app.base.JCallback;
import com.android.jccdex.app.eos.EosWallet;
import com.android.jccdex.app.util.JCCJson;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class EosWalletTest {

    private EosWallet manager;

    private final static String Account = "tpopensource";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        manager = mActivityRule.getActivity().getmEosWallet();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testImportSecret() {
        final CountDownLatch sigal = new CountDownLatch(1);

        manager.importSecret("aaaaa", new JCallback() {
            @Override
            public void completion(JCCJson json) {
                Assert.assertNull(json.getString("address"));
                Assert.assertNull(json.getString("secret"));
                sigal.countDown();
            }
        });

        try {
            sigal.await();
        } catch (InterruptedException e) {

        }
    }

    @Test
    public void testGetBalance() {
        final CountDownLatch sigal = new CountDownLatch(1);
        manager.getBalance(Account, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                String balance = json.getString("balance");
                Assert.assertNotNull(balance);
                sigal.countDown();
            }
        });

        try {
            sigal.await();
        } catch (InterruptedException e) {

        }
    }

    @Test
    public void testSendSignedTransaction() {
        try {
            final CountDownLatch sigal = new CountDownLatch(1);
            JSONObject transaction = new JSONObject();
            transaction.put("contract", "eosiotptoken");
            transaction.put("from", Account);
            transaction.put("value", "1.0000 TPT");
            transaction.put("to", "komantianafd");
            transaction.put("memo", "just test");
            manager.sendTransaction(transaction, "", new JCallback() {
                @Override
                public void completion(JCCJson json) {
                    String receipt = json.getString("hash");
                    String errorMessage = json.getString("errorMessage");
                    Assert.assertNotNull(errorMessage);
                    Assert.assertNull(receipt);
                    sigal.countDown();
                }
            });
            sigal.await();
        } catch (Throwable e) {

        }
    }
}