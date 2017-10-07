package io.github.afagarap.agarapfinalexamy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    private String mFirstName;
    private String mLastName;
    private String mUsername;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mFirstNameEditText = (EditText) findViewById(R.id.registrationFirstNameEditText);
        mLastNameEditText = (EditText) findViewById(R.id.registrationLastNameEditText);
        mUsernameEditText = (EditText) findViewById(R.id.registrationUsernameEditText);
        mPasswordEditText = (EditText) findViewById(R.id.registrationPasswordEditText);

        Button mRegisterButton = (Button) findViewById(R.id.registrationRegisterButton);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirstName = mFirstNameEditText.getText().toString();
                mLastName = mLastNameEditText.getText().toString();
                mUsername = mUsernameEditText.getText().toString();
                mPassword = mPasswordEditText.getText().toString();

                if (mFirstName.length() != 0 && mLastName.length() != 0 &&
                        mUsername.length() != 0 && mPassword.length() != 0) {
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    intent.putExtra(Constants.FIRSTNAME, mFirstName);
                    intent.putExtra(Constants.LASTNAME, mLastName);
                    intent.putExtra(Constants.USERNAME, mUsername);
                    intent.putExtra(Constants.PASSWORD, mPassword);
                    startActivity(intent);
                }
            }
        });
    }
}