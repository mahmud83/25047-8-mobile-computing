package io.github.afagarap.midtermy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG_NAME = RegistrationActivity.class.getSimpleName();

    private EditText mAccountNumberEditText;
    private EditText mAccountPasswordEditText;
    private EditText mAccountBalanceEditText;
    private EditText mAccountPinEditText;
    private Button mRegisterButton;

    private String mAccountNumber;
    private String mAccountPassword;
    private float mAccountBalance;
    private String mAccountPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAccountNumberEditText = (EditText) findViewById(R.id.accountNumberEditText);
        mAccountPasswordEditText = (EditText) findViewById(R.id.accountPasswordEditText);
        mAccountBalanceEditText = (EditText) findViewById(R.id.accountBalanceEditText);
        mAccountPinEditText = (EditText) findViewById(R.id.accountPinEditText);

        mRegisterButton = (Button) findViewById(R.id.registerButton);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAccountNumber = mAccountNumberEditText.getText().toString();
                mAccountPassword = mAccountPasswordEditText.getText().toString();
                mAccountBalance = Float.valueOf(mAccountBalanceEditText.getText().toString());
                mAccountPin = mAccountPinEditText.getText().toString();

                if (mAccountNumber.length() != 0 && mAccountPassword.length() != 0
                        && mAccountBalance != 0.0f && mAccountPin.length() != 0) {
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    intent.putExtra(Constants.ACCOUNT_NUMBER, mAccountNumber);
                    intent.putExtra(Constants.ACCOUNT_PASSWORD, mAccountPassword);
                    intent.putExtra(Constants.ACCOUNT_BALANCE, mAccountBalance);
                    intent.putExtra(Constants.ACCOUNT_PIN, mAccountPin);
                    startActivity(intent);
                }
            }
        });
    }
}
