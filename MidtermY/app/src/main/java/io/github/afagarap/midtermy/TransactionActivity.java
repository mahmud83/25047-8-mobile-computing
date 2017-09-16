package io.github.afagarap.midtermy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TransactionActivity extends AppCompatActivity {

    private Button mCheckBalanceButton;
    private Button mWithdrawButton;

    private float mAccountBalance;
    private String mAccountPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        // receiving intent
        Intent intent = getIntent();
        // get account balance through intent extras
        mAccountBalance = intent.getExtras().getFloat(Constants.ACCOUNT_BALANCE);
        // get account pin through intent extras
        mAccountPin = intent.getExtras().getString(Constants.ACCOUNT_PIN);

        mCheckBalanceButton = (Button) findViewById(R.id.checkBalanceButton);
        mWithdrawButton = (Button) findViewById(R.id.withdrawButton);

        mCheckBalanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransactionActivity.this, BalanceActivity.class);
                intent.putExtra(Constants.ACCOUNT_PIN, mAccountPin);
                intent.putExtra(Constants.ACCOUNT_BALANCE, mAccountBalance);
                startActivity(intent);
            }
        });

        mWithdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransactionActivity.this, WithdrawalActivity.class);
                intent.putExtra(Constants.ACCOUNT_BALANCE, mAccountBalance);
                intent.putExtra(Constants.ACCOUNT_PIN, mAccountPin);
                startActivity(intent);
            }
        });
    }
}
