# jcc-android-base-lib

An interface for interacting with the blockchain wallet operation for android.

[![JitPack](https://jitpack.io/v/JCCDex/jcc-android-base-lib.svg)](https://jitpack.io/#JCCDex/jcc-android-base-lib)
[![Build Status](https://travis-ci.com/JCCDex/jcc-android-base-lib.svg?branch=master)](https://travis-ci.com/JCCDex/jcc-android-base-lib)
[![codecov](https://codecov.io/gh/JCCDex/jcc-android-base-lib/branch/master/graph/badge.svg)](https://codecov.io/gh/JCCDex/jcc-android-base-lib)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

## Usage

## Installation

Step 1. Add it in your root build.gradle at the end of repositories:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

Step 2. Add the dependency

```groovy
dependencies {
    implementation 'com.github.JCCDex:jcc-android-base-lib:0.1.3'
}
```

## API of JTWalletManager

Interface for interacting with the node sdk of jingtum & jingtum alliance chains. Now supports [SWTC](https://state.jingtum.com/#!/) & [BIZAIN](https://bizain.net/) chain.

Create JTWalletManager with Context in your activity.

```java
private JTWalletManager mJTWalletManager;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mJTWalletManager = JTWalletManager.getInstance();
    mJTWalletManager.init(this);
}
```

### createWallet

```java
String chain = JTWalletManager.SWTC_CHAIN;
// String chain = JTWalletManager.BIZAIN_CHAIN;

mJTWalletManager.createWallet(chain, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String secret = jccJson.getString("secret");
        String address = jccJson.getString("address");
        // the secret and address is not null if create wallet successfully
    }
});
```

### importSecret

```java
String secret = "";

String chain = JTWalletManager.SWTC_CHAIN;
// String chain = JTWalletManager.BIZAIN_CHAIN;

mJTWalletManager.importSecret(secret, chain, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String secret = jccJson.getString("secret");
        String address = jccJson.getString("address");
        // the secret and address is not null if import secret successfully
    }
});
```

### isValidAddress

```java
String address = "";

String chain = JTWalletManager.SWTC_CHAIN;
// String chain = JTWalletManager.BIZAIN_CHAIN;

mJTWalletManager.isValidAddress(address, chain, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        Boolean isValid = jccJson.getBoolean("isValid");
        // the isValid is true if the address is valid
    }
});
```

### isValidSecret

```java
String secret = "";

String chain = JTWalletManager.SWTC_CHAIN;
// String chain = JTWalletManager.BIZAIN_CHAIN;

mJTWalletManager.isValidSecret(secret, chain, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        Boolean isValid = jccJson.getBoolean("isValid");
        // the isValid is true if the secret is valid
    }
});
```

### sign

```java
JSONObject transaction = null;

String secret = "";

String chain = JTWalletManager.SWTC_CHAIN;
// String chain = JTWalletManager.BIZAIN_CHAIN;

mJTWalletManager.sign(transaction, secret, chain, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String signature = jccJson.getString("signature");
        // the signature is not null if sign successfully
    }
});
```

For more structure of transaction data, see [jcc_exchange](https://github.com/JCCDex/jcc_exchange/blob/master/src/tx.js).