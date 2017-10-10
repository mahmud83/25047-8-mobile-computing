package io.github.afagarap.agarapfinalexamy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class BalanceActivity extends AppCompatActivity {

    private TextView mAccountBalanceLabelTextView;
    private EditText mAccountPinEditText;

    private String mAccountPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        mAccountBalanceLabelTextView = (TextView) findViewById(R.id.mAccountBalanceLabelTextView);

        mAccountPinEditText = (EditText) findViewById(R.id.accountPinEditText);
        Button mCheckBalanceButton = (Button) findViewById(R.id.checkBalanceButton);

        mAccountBalanceLabelTextView.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        float mAccountBalance = intent.getExtras().getFloat(Constants.ACCOUNT_BALANCE);
        mAccountPin = intent.getExtras().getString(Constants.ACCOUNT_PIN);

        mAccountBalanceLabelTextView.setText(String.format(Locale.ENGLISH, "%s : %s",
                mAccountBalanceLabelTextView.getText(), mAccountBalance));

        mCheckBalanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAccountPinEditText.getText() != null && mAccountPinEditText.getText().toString().equals(mAccountPin)) {
                    mAccountBalanceLabelTextView.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(BalanceActivity.this, "Invalid Pin", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
