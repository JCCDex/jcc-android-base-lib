package com.android.jccdex.jwallet;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.jccdex.jwallet.base.JCallback;
import com.android.jccdex.jwallet.util.JCCJson;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class JWalletManagerTest {

    private JTWalletManager manager;

    private final static String BIZAIN_SECRET = "ssySqG4BhxpngV2FjAe1SJYFD4dcm";
    private final static String BIZAIN_ADDRESS = "bMAy4Pu8CSf5apR44HbYyLFKeC9Dbau16Q";

    private final static String SWTC_SECRET = "sszWqvtbDzzMQEVWqGDSA5DbMYDBN";
    private final static String SWTC_ADDRESS = "jahbmVT3T9yf5D4Ykw8x6nRUtUfAAMzBRV";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        manager = mActivityRule.getActivity().getJTWalletManager();
    }

    @After
    public void tearDown() {
    }


    @Test
    public void testInstance() {
        Assert.assertEquals(manager, mActivityRule.getActivity().getInstance());
    }

    @Test
    public void testCreateSwtcWallet() {
        final CountDownLatch sigal = new CountDownLatch(1);
        manager.createWallet(JTWalletManager.SWTC_CHAIN, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                final String address = json.getString("address");
                String secret = json.getString("secret");
                manager.importSecret(secret, JTWalletManager.SWTC_CHAIN, new JCallback() {
                    @Override
                    public void completion(JCCJson json) {
                        Assert.assertEquals(address, json.getString("address"));
                        sigal.countDown();
                    }
                });
            }
        });
        try {
            sigal.await();
        } catch (InterruptedException e) {

        }
    }

    @Test
    public void testCreateBizainWallet() {
        final CountDownLatch sigal = new CountDownLatch(1);
        manager.createWallet(JTWalletManager.BIZAIN_CHAIN, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                final String address = json.getString("address");
                String secret = json.getString("secret");
                manager.importSecret(secret, JTWalletManager.BIZAIN_CHAIN, new JCallback() {
                    @Override
                    public void completion(JCCJson json) {
                        Assert.assertEquals(address, json.getString("address"));
                        sigal.countDown();
                    }
                });
            }
        });
        try {
            sigal.await();
        } catch (InterruptedException e) {

        }
    }

    @Test
    public void testCreateMoacWallet() {
        final CountDownLatch sigal = new CountDownLatch(1);
        manager.createWallet("moac", new JCallback() {
            @Override
            public void completion(JCCJson json) {
                final String address = json.getString("address");
                String secret = json.getString("secret");
                Assert.assertEquals(null, address);
                Assert.assertEquals(null, secret);
                sigal.countDown();
            }
        });
        try {
            sigal.await();
        } catch (InterruptedException e) {

        }
    }

    @Test
    public void testImportSwtcSecret() {
        final CountDownLatch sigal = new CountDownLatch(1);
        manager.importSecret(SWTC_SECRET, JTWalletManager.SWTC_CHAIN, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                String address = json.getString("address");
                String secret = json.getString("secret");
                Assert.assertEquals(SWTC_ADDRESS, address);
                Assert.assertEquals(SWTC_SECRET, secret);
                sigal.countDown();
            }
        });
        manager.importSecret("aaa", JTWalletManager.SWTC_CHAIN, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                String address = json.getString("address");
                String secret = json.getString("secret");
                Assert.assertEquals(null, address);
                Assert.assertEquals(null, secret);
                sigal.countDown();
            }
        });
        try {
            sigal.await();
        } catch (InterruptedException e) {

        }
    }

    @Test
    public void testImportBizainSecret() {
        final CountDownLatch sigal = new CountDownLatch(2);
        manager.importSecret(BIZAIN_SECRET, JTWalletManager.BIZAIN_CHAIN, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                String address = json.getString("address");
                String secret = json.getString("secret");
                Assert.assertEquals(BIZAIN_ADDRESS, address);
                Assert.assertEquals(BIZAIN_SECRET, secret);
                sigal.countDown();
            }
        });
        manager.importSecret("aaa", JTWalletManager.BIZAIN_CHAIN, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                String address = json.getString("address");
                String secret = json.getString("secret");
                Assert.assertEquals(null, address);
                Assert.assertEquals(null, secret);
                sigal.countDown();
            }
        });
        try {
            sigal.await();
        } catch (InterruptedException e) {

        }
    }

    @Test
    public void testIsValidSwtcAddress() {
        final CountDownLatch sigal = new CountDownLatch(2);
        manager.isValidAddress(SWTC_ADDRESS, JTWalletManager.SWTC_CHAIN, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                Boolean valid = json.getBoolean("isValid");
                Assert.assertEquals(true, valid);
                sigal.countDown();
            }
        });
        manager.isValidAddress("aaa", JTWalletManager.SWTC_CHAIN, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                Boolean valid = json.getBoolean("isValid");
                Assert.assertEquals(false, valid);
                sigal.countDown();
            }
        });
        try {
            sigal.await();
        } catch (InterruptedException e) {

        }
    }

    @Test
    public void testIsValidBizainAddress() {
        final CountDownLatch sigal = new CountDownLatch(2);
        manager.isValidAddress(BIZAIN_ADDRESS, JTWalletManager.BIZAIN_CHAIN, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                Boolean valid = json.getBoolean("isValid");
                Assert.assertEquals(true, valid);
                sigal.countDown();
            }
        });
        manager.isValidAddress("aaa", JTWalletManager.BIZAIN_CHAIN, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                Boolean valid = json.getBoolean("isValid");
                Assert.assertEquals(false, valid);
                sigal.countDown();
            }
        });
        try {
            sigal.await();
        } catch (InterruptedException e) {

        }
    }

    @Test
    public void testIsValidSwtcSecret() {
        final CountDownLatch sigal = new CountDownLatch(2);
        manager.isValidSecret(SWTC_SECRET, JTWalletManager.SWTC_CHAIN, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                Boolean valid = json.getBoolean("isValid");
                Assert.assertEquals(true, valid);
                sigal.countDown();
            }
        });
        manager.isValidSecret("aaaa", JTWalletManager.SWTC_CHAIN, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                Boolean valid = json.getBoolean("isValid");
                Assert.assertEquals(false, valid);
                sigal.countDown();
            }
        });
        try {
            sigal.await();
        } catch (InterruptedException e) {

        }
    }

    @Test
    public void testIsValidBizainSecret() {
        final CountDownLatch sigal = new CountDownLatch(2);
        manager.isValidSecret(BIZAIN_SECRET, JTWalletManager.BIZAIN_CHAIN, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                Boolean valid = json.getBoolean("isValid");
                Assert.assertEquals(true, valid);
                sigal.countDown();
            }
        });
        manager.isValidSecret("aaaa", JTWalletManager.BIZAIN_CHAIN, new JCallback() {
            @Override
            public void completion(JCCJson json) {
                Boolean valid = json.getBoolean("isValid");
                Assert.assertEquals(false, valid);
                sigal.countDown();
            }
        });
        try {
            sigal.await();
        } catch (InterruptedException e) {

        }
    }

    @Test
    public void testSignSwtcTransaction() {
        try {
            final CountDownLatch sigal = new CountDownLatch(2);
            JSONObject transaction = new JSONObject();
            transaction.put("Account", "jpgWGpfHz8GxqUjz5nb6ej8eZJQtiF6KhH");
            transaction.put("TransactionType", "Payment");
            transaction.put("Destination", "j4JJb3c17HuwRoKycjtrd9adpmbrneEE6w");
            transaction.put("Fee", 0.00001);
            transaction.put("Flags", 0);
            transaction.put("Amount", 1);
            transaction.put("Sequence", 1);
            String secret = "snfXQMEVbbZng84CcfdKDASFRi4Hf";
            final String sign = "120000220000000024000000016140000000000F424068400000000000000A732102C13075B18C87A032226CE383AEFD748D7BB719E02CD7F5A8C1F2C7562DE7C12A7446304402201C26C28C8DE3282D6B1ADE62CFFB64173976D33041DB853E7864B9463D189E4B0220265622645B6E56AEB9B42D4946AAAA7D86F37774F3956DEF3A81D4EB4EBA6B2181141270C5BE503A3A22B506457C0FEC97633B44F7DD8314E9A06519E65C7122C67380797BAE5B857E2822CF";
            manager.sign(transaction, secret, JTWalletManager.SWTC_CHAIN, new JCallback() {
                @Override
                public void completion(JCCJson json) {
                    Assert.assertEquals(sign, json.getString("signature"));
                    sigal.countDown();
                }
            });
            manager.sign(null, secret, JTWalletManager.SWTC_CHAIN, new JCallback() {
                @Override
                public void completion(JCCJson json) {
                    Assert.assertEquals(null, json.getString("signature"));
                    sigal.countDown();
                }
            });
            sigal.await();
        } catch (Throwable e) {

        }
    }

    @Test
    public void testSignBizainTransaction() {
        try {
            final CountDownLatch sigal = new CountDownLatch(2);
            JSONObject transaction = new JSONObject();
            transaction.put("Account", "bMAy4Pu8CSf5apR44HbYyLFKeC9Dbau16Q");
            transaction.put("TransactionType", "Payment");
            transaction.put("Destination", "bDGbTGBLCrSqW54YZrjQ5qQNQKSBX6GJUK");
            transaction.put("Fee", 0.00001);
            transaction.put("Flags", 0);
            transaction.put("Amount", 1);
            transaction.put("Sequence", 1);
            String secret = "ssySqG4BhxpngV2FjAe1SJYFD4dcm";
            final String sign = "120000220000000024000000016140000000000F424068400000000000000A73210305907425BF03CD414D089EB48FE0AB7898B74985F43B0A42EB06588DA6FFC58E74463044022067DAA47DAF9FEF458E5E64993183BDA4B603F9D0582466967E5CD38B5A46FBB1022006003164A4F5FB312E6A5336E69EDE6F18A1D484586470212C3A803474EC11C48114E5C8083009E1C466A7484CF57497009AB5A31AED831486782075FDFAAAB18F245142883C0B56BC23C18F";
            manager.sign(transaction, secret, JTWalletManager.BIZAIN_CHAIN, new JCallback() {
                @Override
                public void completion(JCCJson json) {
                    Assert.assertEquals(sign, json.getString("signature"));
                    sigal.countDown();
                }
            });

            manager.sign(null, secret, JTWalletManager.BIZAIN_CHAIN, new JCallback() {
                @Override
                public void completion(JCCJson json) {
                    Assert.assertEquals(null, json.getString("signature"));
                    sigal.countDown();
                }
            });
            sigal.await();
        } catch (Throwable e) {

        }
    }
}