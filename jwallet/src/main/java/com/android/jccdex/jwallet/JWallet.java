package com.android.jccdex.jwallet;

import com.github.lzyzsd.jsbridge.CallBackFunction;

/**
 * JWallet
 *
 * @author wanggang
 * Copyright © 2019 JCCDex. All rights reserved.
 */
public interface JWallet {

    /**
     * @param chain    default with swt.
     * @param callBack
     */
    void createWallet(String chain, CallBackFunction callBack);

    /**
     * @param secret
     * @param chain    default with swt.
     * @param callBack
     */
    void importSecret(String secret, String chain, CallBackFunction callBack);

    /**
     * @param address
     * @param chain    default with swt.
     * @param callBack
     */
    void isValidAddress(String address, String chain, CallBackFunction callBack);

    /**
     * @param secret
     * @param chain    default with swt.
     * @param callBack
     */
    void isValidSecret(String secret, String chain, CallBackFunction callBack);

    /**
     * @param transaction JsonString
     * @param secret
     * @param chain       default with swt.
     * @param callBack
     */
    void signTx(String transaction, String secret, String chain, CallBackFunction callBack);

}
