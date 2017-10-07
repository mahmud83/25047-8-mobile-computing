package io.github.afagarap.agarapfinalexamy;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    // ImageView widget for storing logo
    private ImageView mImageView;

    // time-out for splash screen = 4 seconds
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        mImageView = (ImageView) findViewById(R.id.imageOctocat);

        mImageView.animate().alpha(1f).setDuration(SPLASH_TIME_OUT);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // create intent for LoginActivity class
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);

                // start LoginActivity class after splash screen
                startActivity(intent);

                // close activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
