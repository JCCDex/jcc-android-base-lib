# jcc-base-lib-android

An interface for interacting with the blockchain wallet operation for android.

[![](https://jitpack.io/v/JCCDex/jcc-android-base-lib.svg)](https://jitpack.io/#JCCDex/jcc-android-base-lib)

## Usage

## Installation

Step 1. Add it in your root build.gradle at the end of repositories:
```groovy
repositories {
    ...
    maven { url 'https://jitpack.io' }
}
```
Step 2. Add the dependency
```groovy
dependencies {
    implementation 'com.github.JCCDex:jcc-android-base-lib:0.0.1'
}
```

## API of JWalletManager

Interface for interacting with the node sdk of jingtum & jingtum alliance chains. Now supports [SWTC](https://state.jingtum.com/#!/) & [BIZAIN](https://bizain.net/) chain.

Create JWalletManager with Context in your activity.
```java
private JWalletManager mJWalletManager;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJWalletManager = JWalletManager.getInstance(this);
        ...
    }
```

### createWallet
param

  chain - default with swt.

  callBack - `data` is null when create wallet unseccusfully.
```java
com.github.lzyzsd.jsbridge.CallBackFunction callBackFunction = new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
                    ...
                }
            };
// mJWalletManager.mJWalletManager(JWalletManager.BIZAIN_CHAIN, callBackFunction);
mJWalletManager.mJWalletManager(JWalletManager.SWTC_CHAIN, callBackFunction);
```

### importSecret
param

  secret - 

  chain - default with swt.

  callBack - `data` is null when import wallet unseccusfully.
```java
com.github.lzyzsd.jsbridge.CallBackFunction callBackFunction = new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
                    ...
                }
            };
// mJWalletManager.importSecret("s...", JWalletManager.BIZAIN_CHAIN, callBackFunction);
mJWalletManager.importSecret("s...", JWalletManager.SWTC_CHAIN, callBackFunction);
```
### isValidAddress
param

  address - 

  chain - default with swt.

  callBack - `data` is true when the address is valid,otherwise is false.
```java
com.github.lzyzsd.jsbridge.CallBackFunction callBackFunction = new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
                    ...
                }
            };
// mJWalletManager.isValidAddress("b...", JWalletManager.BIZAIN_CHAIN, callBackFunction);
mJWalletManager.isValidAddress("j...", JWalletManager.SWTC_CHAIN, callBackFunction);
```

### isValidSecret
param

  secret - 

  chain - default with swt.

  callBack - `data` is true when the secret is valid,otherwise is false.
```java
com.github.lzyzsd.jsbridge.CallBackFunction callBackFunction = new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
                    ...
                }
            };
// mJWalletManager.isValidSecret("s...", JWalletManager.BIZAIN_CHAIN, callBackFunction);
mJWalletManager.isValidSecret("s...", JWalletManager.SWTC_CHAIN, callBackFunction);
```

### signTx
param

  transaction - JsonString

  secret - 

  chain - default with swt.

  callBack - `data` is true when secret is valid,otherwise is false.
```java
com.github.lzyzsd.jsbridge.CallBackFunction callBackFunction = new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
                    ...
                }
            };
// mJWalletManager.signTx("{xxx}", "s...", JWalletManager.BIZAIN_CHAIN, callBackFunction);
mJWalletManager.signTx("{xxx}", "s...", JWalletManager.SWTC_CHAIN, callBackFunction);
```

