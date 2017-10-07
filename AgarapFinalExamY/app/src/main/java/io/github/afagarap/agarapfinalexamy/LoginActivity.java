package io.github.afagarap.agarapfinalexamy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private String mFirstName;
    private String mLastName;
    private String mUsername;
    private String mPassword;

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button registerButton = (Button) findViewById(R.id.buttonRegister);
        Button loginButton = (Button) findViewById(R.id.buttonLogin);

        mUsernameEditText = (EditText) findViewById(R.id.textUsername);
        mPasswordEditText = (EditText) findViewById(R.id.textPassword);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mUsernameEditText.getText() != null && mPasswordEditText.getText() != null &&
                        mUsernameEditText.getText().toString().equals(mUsername) &&
                        mPasswordEditText.getText().toString().equals(mPassword)) {

                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    intent.putExtra(Constants.FIRSTNAME, mFirstName);
                    intent.putExtra(Constants.LASTNAME, mLastName);
                    intent.putExtra(Constants.USERNAME, mUsername);
                    intent.putExtra(Constants.PASSWORD, mPassword);
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

        mFirstName = bundle.getString(Constants.FIRSTNAME);
        mLastName = bundle.getString(Constants.LASTNAME);
        mUsername = bundle.getString(Constants.USERNAME);
        mPassword = bundle.getString(Constants.PASSWORD);
    }
}
