package io.github.afagarap.midtermy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        showSplashScreen();
        startLoginActivity(2000);
    }

    private void showSplashScreen() {
        ImageView iconImage = (ImageView) findViewById(R.id.iconImageView);
        iconImage.animate()
                .rotationBy(1080)
                .setDuration(2000)
                .start();
    }

    private void startLoginActivity(final int delay) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(delay);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };

        thread.start();
    }
}
