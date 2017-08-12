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
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    // ImageView widget for storing logo
    private ImageView mImageView;

    // time-out for splash screen = 4 seconds
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        mImageView = (ImageView) findViewById(R.id.imageGoogle);

        // fade-in animation for ImageView
        mImageView.animate().alpha(1f).setDuration(3000);

//        Mr. Manalili's approach for splash screen
//        Thread timeThread = new Thread(){
//            public void run() {
//                try{
//                    sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
//                    startActivity(intent);
//                }
//            }
//        };
//        timeThread.start();

        // use handler to schedule a runnable
        // delayed by the SPLASH_TIME_OUT (4 seconds)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // create intent for LoginActivity class
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);

                // start LoginActivity class after splash screen
                startActivity(intent);

                // close activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
