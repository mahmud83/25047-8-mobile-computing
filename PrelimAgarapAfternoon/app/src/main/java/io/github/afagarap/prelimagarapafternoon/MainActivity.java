package io.github.afagarap.prelimagarapafternoon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mGuessNumberButton;
    private Button mTriangularButton;
    private Button mReverseRepeatedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGuessNumberButton = (Button) findViewById(R.id.btnGuessNumber);
        mTriangularButton = (Button) findViewById(R.id.btnTriangularNumber);
        mReverseRepeatedButton = (Button) findViewById(R.id.btnCountRepeated);

        mGuessNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGuessNumber();
            }
        });

        mTriangularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTriangularNumber();
            }
        });

        mReverseRepeatedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startReverseRepeated();
            }
        });
    }

    /**
     * Starts the Guessing Number activity
     */
    private void startGuessNumber() {
        Intent intent = new Intent(MainActivity.this, GuessNumberActivity.class);
        startActivity(intent);
    }

    /**
     * Starts the Triangular Number activity
     */
    private void startTriangularNumber() {
        Intent intent = new Intent(MainActivity.this, TriangularNumberActivity.class);
        startActivity(intent);
    }

    /**
     * Starts the ReverseRepeated activity
     */
    private void startReverseRepeated() {
        Intent intent = new Intent(MainActivity.this, ReverseRepeatedActivity.class);
        startActivity(intent);
    }
}
