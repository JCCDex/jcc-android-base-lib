package com.android.jccdex.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.jccdex.jwallet.JWalletManager;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private Button mCreateButton;
    private Button mImportButton;
    private Button mIsAddressButton;
    private Button mIsSecretButton;
    private Button mSignButton;
    public static EditText mAddEditText;
    public static EditText mEscEditText;
    private EditText mChaEditText;
    private TextView mOutputTextView;
    private JWalletManager mJWalletManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJWalletManager = JWalletManager.getInstance(this);

        mCreateButton = findViewById(R.id.createWallet);
        mCreateButton.setOnClickListener(this);
        mImportButton = findViewById(R.id.importWallet);
        mImportButton.setOnClickListener(this);
        mIsAddressButton = findViewById(R.id.isAddress);
        mIsAddressButton.setOnClickListener(this);
        mIsSecretButton = findViewById(R.id.isSecret);
        mIsSecretButton.setOnClickListener(this);
        mSignButton = findViewById(R.id.sign);
        mSignButton.setOnClickListener(this);

        mAddEditText = findViewById(R.id.EAddress);
        mEscEditText = findViewById(R.id.ESecret);
        mChaEditText = findViewById(R.id.EChain);
        mOutputTextView = findViewById(R.id.output);
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateButton) {
            CallBackFunction callBackFunction = new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
                    mOutputTextView.setText(data);
                    JSONObject json = null;
                    try {
                        json = new JSONObject(data);
                        mEscEditText.setText(json.getString("secret"));
                        mAddEditText.setText(json.getString("address"));
                    } catch (JSONException e) {
                        Log.v(TAG, e.getMessage());
                    }
                }
            };
            mJWalletManager.createWallet(mChaEditText.getText().toString(), callBackFunction);
        } else if (v == mImportButton) {
            CallBackFunction callBackFunction = new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
                    Log.v(TAG, data);
                    mOutputTextView.setText(data);
                }
            };
            mJWalletManager.importSecret(mEscEditText.getText().toString(), mChaEditText.getText().toString(), callBackFunction);
        } else if (v == mIsAddressButton) {
            CallBackFunction callBackFunction = new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
                    Log.v(TAG, data);
                    mOutputTextView.setText(data);
                }
            };
            mJWalletManager.isValidAddress(mAddEditText.getText().toString(), mChaEditText.getText().toString(), callBackFunction);
        } else if (v == mIsSecretButton) {
            CallBackFunction callBackFunction = new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
                    Log.v(TAG, data);
                    mOutputTextView.setText(data);
                }
            };
            mJWalletManager.isValidSecret(mEscEditText.getText().toString(), mChaEditText.getText().toString(), callBackFunction);
        } else if (v == mSignButton) {
            Intent intent = new Intent(this, SignTxActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
        }
    }
}
