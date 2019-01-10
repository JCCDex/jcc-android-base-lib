package com.android.jccdex.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.jccdex.jwallet.JTWalletManager;
import com.android.jccdex.jwallet.base.JCallback;
import com.android.jccdex.jwallet.util.JCCJson;
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
    private JTWalletManager mJTWalletManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJTWalletManager = JTWalletManager.getInstance(this);
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
            mJTWalletManager.createWallet(mChaEditText.getText().toString(), new JCallback() {
                @Override
                public void completion(JCCJson jccJson) {
                    mEscEditText.setText(jccJson.getString("secret"));
                    mAddEditText.setText(jccJson.getString("address"));
                }
            });
        } else if (v == mImportButton) {

        } else if (v == mIsAddressButton) {
            mJTWalletManager.isValidAddress(mAddEditText.getText().toString(), mChaEditText.getText().toString(), new JCallback() {
                @Override
                public void completion(JCCJson jccJson) {
                    mOutputTextView.setText(jccJson.getBoolean("isValid") + "");
                }
            });

        } else if (v == mIsSecretButton) {
            mJTWalletManager.isValidSecret(mEscEditText.getText().toString(), mChaEditText.getText().toString(), new JCallback() {
                @Override
                public void completion(JCCJson jccJson) {
                    mOutputTextView.setText(jccJson.getBoolean("isValid") + "");
                }
            });

        } else if (v == mSignButton) {
            Intent intent = new Intent(this, SignTxActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
        }
    }
}
