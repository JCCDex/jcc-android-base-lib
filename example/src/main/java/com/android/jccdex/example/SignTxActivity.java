package com.android.jccdex.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.jccdex.jwallet.JTWalletManager;
import com.android.jccdex.jwallet.base.JCallback;
import com.android.jccdex.jwallet.util.JCCJson;
import com.google.gson.Gson;

import org.json.JSONObject;

public class SignTxActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mAccEditText;
    private EditText mAmoEditText;
    private EditText mDesEditText;
    private EditText mSecEditText;
    private EditText mChaEditText;
    private Button mSignButton;
    private TextView mResTextView;
    private JTWalletManager mJWalletManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_tx);
        mDesEditText = findViewById(R.id.EDes);
        mAccEditText = findViewById(R.id.EAcc);
        mAccEditText.setText(MainActivity.mAddEditText.getText().toString());
        mAmoEditText = findViewById(R.id.EAmo);
        mSecEditText = findViewById(R.id.ESec);
        mSecEditText.setText(MainActivity.mEscEditText.getText().toString());
        mChaEditText = findViewById(R.id.ECha);
        mResTextView = findViewById(R.id.Res);
        mSignButton = findViewById(R.id.SignTx);
        mSignButton.setOnClickListener(this);
        mJWalletManager = JTWalletManager.getInstance(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSignButton) {

            mJWalletManager.sign(getTransaction(), mSecEditText.getText().toString(), mChaEditText.getText().toString(), new JCallback() {
                @Override
                public void completion(JCCJson jccJson) {
                    mResTextView.setText(jccJson.getString("signature"));
                }
            });
        }
    }

    private JSONObject getTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAccount(mAccEditText.getText().toString());
        transaction.setAmount(Integer.parseInt(mAmoEditText.getText().toString()));
        transaction.setDestination(mDesEditText.getText().toString());
        transaction.setFee(0.00001);
        transaction.setFlags(0);
        transaction.setSequence(1);
        transaction.setTransactionType("Payment");
        try {
            return new JSONObject(new Gson().toJson(transaction));
        } catch (Throwable e) {
            return null;
        }
    }
}
