/*
MIT License

Copyright (c) 2017 Abien Fred Agarap

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package io.github.afagarap.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    // UI Widgets for Username, Password,
    // and Login
    private EditText mUsernameText;
    private EditText mPasswordText;
    private Button mLoginButton;

    // String to store
    // username and password
    private String username;
    private String password;

    // dummy username and password
    private String user = "user";
    private String pass = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameText = (EditText)findViewById(R.id.textUsername);
        mPasswordText = (EditText)findViewById(R.id.textPassword);
        mLoginButton = (Button)findViewById(R.id.buttonLogin);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = mUsernameText.getText().toString();
                password = mPasswordText.getText().toString();
                // check if username and password is correct
                if (username.equals(user) && password.equals(pass)) {
                    // create an intent for WelcomeActivity class
                    Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);

                    // store a string extra
                    intent.putExtra("USERNAME", username);

                    // start the WelcomeActivity class
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
