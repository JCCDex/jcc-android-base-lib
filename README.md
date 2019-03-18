<!-- markdownlint-disable MD024 -->

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
    implementation 'com.github.JCCDex:jcc-android-base-lib:0.1.8'
}
```

## API of JingtumWallet

Interface for interacting with the node sdk of jingtum & jingtum alliance chains. Now supports [SWTC](https://state.jingtum.com/#!/) & [BIZAIN](https://bizain.net/) chain.

Create JingtumWallet with Context in your activity.

```java
private JingtumWallet mJingtumWallet;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mJingtumWallet = JingtumWallet.getInstance();
    mJingtumWallet.init(this);
}
```

### createWallet

```java
String chain = JingtumWallet.SWTC_CHAIN;
// String chain = JingtumWallet.BIZAIN_CHAIN;

mJingtumWallet.createWallet(chain, new JCallback() {
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

String chain = JingtumWallet.SWTC_CHAIN;
// String chain = JingtumWallet.BIZAIN_CHAIN;

mJingtumWallet.importSecret(secret, chain, new JCallback() {
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

String chain = JingtumWallet.SWTC_CHAIN;
// String chain = JingtumWallet.BIZAIN_CHAIN;

mJingtumWallet.isValidAddress(address, chain, new JCallback() {
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

String chain = JingtumWallet.SWTC_CHAIN;
// String chain = JingtumWallet.BIZAIN_CHAIN;

mJingtumWallet.isValidSecret(secret, chain, new JCallback() {
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

String chain = JingtumWallet.SWTC_CHAIN;
// String chain = JingtumWallet.BIZAIN_CHAIN;

mJingtumWallet.sign(transaction, secret, chain, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String signature = jccJson.getString("signature");
        // the signature is not null if sign successfully
    }
});
```

For more structure of transaction data, see [jcc_exchange](https://github.com/JCCDex/jcc_exchange/blob/master/src/tx.js).

## API of EthereumWallet

Interface for interacting with the node sdk of web3. Create EthereumWallet with Context in your activity.

```java
private EthereumWallet mEthereumWallet;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mEthereumWallet = EthereumWallet.getInstance();
    mEthereumWallet.init(this);
    String ethereumNode = "";
    mEthereumWallet.initWeb3Provider(ethereumNode);
}
```

### createWallet

```java
mEthereumWallet.createWallet(new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String secret = jccJson.getString("secret");
        String address = jccJson.getString("address");
        String words = jccJson.getString("words");
        // the secret、address and words is not null if create wallet successfully
    }
});
```

### isValidAddress

```java
String address = "";

mEthereumWallet.isValidAddress(address, new JCallback() {
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

mEthereumWallet.isValidSecret(secret, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        Boolean isValid = jccJson.getBoolean("isValid");
        // the isValid is true if the secret is valid
    }
});
```

### importSecret

```java
String secret = "";

mEthereumWallet.importSecret(secret, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String secret = jccJson.getString("secret");
        String address = jccJson.getString("address");
        // the secret and address is not null if import secret successfully
    }
});
```

### importWords

```java
String words = "";

mEthereumWallet.importWords(words, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String secret = jccJson.getString("secret");
        String address = jccJson.getString("address");
        // the secret and address is not null if import words successfully
    }
});
```

### toIban

```java
String address = "";

mEthereumWallet.toIban(address, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String iban = json.getString("iban");
        // the iban is not null if the address is valid
    }
});
```

### fromIban

```java
String iban = "";

mEthereumWallet.fromIban(iban, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String address = json.getString("address");
        // the address is not null if the iban is valid
    }
});
```

### sign

Only support eth except erc20 for now.

```java
JSONObject transaction = new JSONObject();

transaction.put("from", "");
transaction.put("value", "");
transaction.put("to", "");
transaction.put("gas", "");
transaction.put("gasPrice", "");

String secret = "";

mEthereumWallet.sign(transaction, secret, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String rawTransaction = json.getString("rawTransaction");
        // the rawTransaction is not null if sign successfully
    }
});
```

### sendSignedTransaction

```java
String rawTransaction = "";

mEthereumWallet.sendSignedTransaction(rawTransaction, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String hash = json.getString("hash");
        // the hash is not null if the send successfully.
    }
});
```

### gasPrice

```java

mEthereumWallet.gasPrice(new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String gas = json.getString("gasPrice");
        // the gas is not null if request gas price successfully.
    }
});
```

### getBalance

```java
String address = "";

mEthereumWallet.getBalance(address, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String balance = json.getString("balance");
        // the balance is not null if request balance successfully.
    }
});
```

## API of MoacWallet

Interface for interacting with the node sdk of chain3. Create MoacWallet with Context in your activity.

```java
private MoacWallet mMoacWallet;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mMoacWallet = MoacWallet.getInstance();
    mMoacWallet.init(this);
    String moacNode = "";
    mMoacWallet.initChain3Provider(moacNode);
}
```

### createWallet

```java
mMoacWallet.createWallet(new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String secret = jccJson.getString("secret");
        String address = jccJson.getString("address");
        String words = jccJson.getString("words");
        // the secret、address and words is not null if create wallet successfully
    }
});
```

### isValidAddress

```java
String address = "";

mMoacWallet.isValidAddress(address, new JCallback() {
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

mMoacWallet.isValidSecret(secret, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        Boolean isValid = jccJson.getBoolean("isValid");
        // the isValid is true if the secret is valid
    }
});
```

### importSecret

```java
String secret = "";

mMoacWallet.importSecret(secret, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String secret = jccJson.getString("secret");
        String address = jccJson.getString("address");
        // the secret and address is not null if import secret successfully
    }
});
```

### importWords

```java
String words = "";

mMoacWallet.importWords(words, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String secret = jccJson.getString("secret");
        String address = jccJson.getString("address");
        // the secret and address is not null if import words successfully
    }
});
```

### toIban

```java
String address = "";

mMoacWallet.toIban(address, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String iban = json.getString("iban");
        // the iban is not null if the address is valid
    }
});
```

### fromIban

```java
String iban = "";

mMoacWallet.fromIban(iban, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String address = json.getString("address");
        // the address is not null if the iban is valid
    }
});
```

### sign

Only support moac except erc20 for now.

```java
JSONObject transaction = new JSONObject();

transaction.put("from", "");
transaction.put("value", "");
transaction.put("to", "");
transaction.put("gas", "");
transaction.put("gasPrice", "");

String secret = "";

mMoacWallet.sign(transaction, secret, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String rawTransaction = json.getString("rawTransaction");
        // the rawTransaction is not null if sign successfully
    }
});
```

### sendSignedTransaction

```java
String rawTransaction = "";

mMoacWallet.sendSignedTransaction(rawTransaction, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String hash = json.getString("hash");
        // the hash is not null if the send successfully.
    }
});
```

### gasPrice

```java

mMoacWallet.gasPrice(new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String gas = json.getString("gasPrice");
        // the gas is not null if request gas price successfully.
    }
});
```

### getBalance

```java
String address = "";

mMoacWallet.getBalance(address, new JCallback() {
    @Override
    public void completion(JCCJson jccJson) {
        String balance = json.getString("balance");
        // the balance is not null if request balance successfully.
    }
});
```