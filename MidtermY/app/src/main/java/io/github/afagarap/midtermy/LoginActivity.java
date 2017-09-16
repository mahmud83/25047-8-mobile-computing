package io.github.afagarap.midtermy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private String mAccountNumber, mAccountPassword, mAccountPin;
    private float mAccountBalance;

    private EditText mAccountNumberEditText, mAccountPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button registerButton = (Button) findViewById(R.id.registerButton);
        Button loginButton = (Button) findViewById(R.id.loginButton);

        mAccountNumberEditText = (EditText) findViewById(R.id.accountNumberEditText);
        mAccountPasswordEditText = (EditText) findViewById(R.id.accountPasswordEditText);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mAccountNumberEditText.getText() != null && mAccountPasswordEditText.getText() != null &&
                    mAccountNumberEditText.getText().toString().equals(mAccountNumber) &&
                        mAccountPasswordEditText.getText().toString().equals(mAccountPassword)) {

                    Intent intent = new Intent(LoginActivity.this, TransactionActivity.class);
                    intent.putExtra(Constants.ACCOUNT_NUMBER, mAccountNumber);
                    intent.putExtra(Constants.ACCOUNT_PASSWORD, mAccountPassword);
                    intent.putExtra(Constants.ACCOUNT_BALANCE, mAccountBalance);
                    intent.putExtra(Constants.ACCOUNT_PIN, mAccountPin);

                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent() == null) return;

        Bundle bundle = getIntent().getExtras();

        if (bundle == null) return;

        mAccountNumber = bundle.getString(Constants.ACCOUNT_NUMBER);
        mAccountPassword = bundle.getString(Constants.ACCOUNT_PASSWORD);
        mAccountBalance = bundle.getFloat(Constants.ACCOUNT_BALANCE);
        mAccountPin = bundle.getString(Constants.ACCOUNT_PIN);
    }
}
