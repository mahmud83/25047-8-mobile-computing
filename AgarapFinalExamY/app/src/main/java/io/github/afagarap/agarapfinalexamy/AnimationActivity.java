package io.github.afagarap.agarapfinalexamy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by darth 08/05/2017
 */
public class AnimationActivity extends AppCompatActivity {

    /**
     * Fade out imageFirefox, fade in imageChrome
     * Uses ternary operator for setting the image alpha
     * @param view
     */
    public void animateImage(View view) {
        ImageView imageFirefox = (ImageView) findViewById(R.id.imageFirefox);
        ImageView imageChrome = (ImageView) findViewById(R.id.imageChrome);
        imageFirefox.animate()
                .rotationBy(3600)
                .alpha(imageFirefox.getAlpha() == 0 ? 1 : 0)
                .setDuration(2000);
        imageChrome.animate()
                .rotationBy(-3600)
                .alpha(imageChrome.getAlpha() == 1 ? 0 : 1)
                .setDuration(2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        ImageView imageFirefox = (ImageView) findViewById(R.id.imageChrome);
        imageFirefox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateImage(view);
            }
        });
    }
}