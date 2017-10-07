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

public class WithdrawalActivity extends AppCompatActivity {

    private Button mWithdrawButton;
    private EditText mAmountEditText;
    private EditText mAccountPinWithdrawalEditText;
    private TextView mNewBalanceTextView;

    private float mAccountBalance;
    private float mAmountToWithdraw;
    private String mAccountPin;
    private String mAccountPinInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);

        // retrieve intent extras passed from
        // transaction activity
        Intent intent = getIntent();
        mAccountBalance = intent.getExtras().getFloat(Constants.ACCOUNT_BALANCE);
        mAccountPin = intent.getExtras().getString(Constants.ACCOUNT_PIN);

        mWithdrawButton = (Button) findViewById(R.id.withdrawButton);
        mAmountEditText = (EditText) findViewById(R.id.amountEditText);
        mAccountPinWithdrawalEditText = (EditText) findViewById(R.id.accountPinWithdrawalEditText);
        mNewBalanceTextView = (TextView) findViewById(R.id.newBalanceTextView);

        mWithdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    mAccountPinInput = mAccountPinWithdrawalEditText.getText().toString();

                    if (mAccountPinInput.length() != 0 && mAccountPinInput.equals(mAccountPin)) {
                        mAmountToWithdraw = Float.valueOf(mAmountEditText.getText().toString());
                        if (mAccountBalance >= mAmountToWithdraw) {
                            mAccountBalance -= mAmountToWithdraw;
                            mNewBalanceTextView.setText(String.format(Locale.ENGLISH, "%s: %f",
                                    mNewBalanceTextView.getText().toString(), mAccountBalance));
                        } else if (mAccountBalance < mAmountToWithdraw && mAccountBalance >= 0) {
                            Toast.makeText(WithdrawalActivity.this, "Insufficient funds!", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(WithdrawalActivity.this, "Incorrect PIN!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(WithdrawalActivity.this, "Incorrect PIN!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
