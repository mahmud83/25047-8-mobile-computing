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
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {

    // ImageView to use for
    // demo of OnTouchListener
    private ImageView mImageView;

    // string to store the intent extra
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = getIntent();
        // get the intent extra passed by LoginActivity class
        username = intent.getExtras().getString("USERNAME");

        mImageView = (ImageView)findViewById(R.id.imageOctocat);
        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    // show "user : PRESSED DOWN" Toast text
                    // when ImageView has received ACTION_DOWN
                    // MotionEvent
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(WelcomeActivity.this,
                                String.format(Locale.ENGLISH, "%s : %s", username, "PRESSED DOWN"),
                                Toast.LENGTH_SHORT).show();

                    // show "user : PRESSED UP" Toast text
                    // when ImageView has received ACTION_UP
                    // MotionEvent
                    case MotionEvent.ACTION_UP:
                        Toast.makeText(WelcomeActivity.this, String.format("%s : %s", username, "PRESSED UP"),
                                Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
}
