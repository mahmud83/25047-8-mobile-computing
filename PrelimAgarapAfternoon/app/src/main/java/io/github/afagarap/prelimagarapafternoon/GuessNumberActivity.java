package io.github.afagarap.prelimagarapafternoon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Locale;
import java.util.Random;

public class GuessNumberActivity extends AppCompatActivity {

    private EditText mTextGuessInput;
    private Button mTryButton;
    private Button mRevealButton;

    private static int guessNumber;
    private static int randomNumber;
    private static String textGuessNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_number);

        mTextGuessInput = (EditText) findViewById(R.id.textGuessInput);

        mTryButton = (Button) findViewById(R.id.btnTry);
        mRevealButton = (Button) findViewById(R.id.btnReveal);

        randomNumber = generateRandomNumber();

        mTryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int status = tryGuessNumber(randomNumber, mTextGuessInput.getText().toString());

                if (status == 1) {
                    Toast.makeText(getApplicationContext(), "Your guess was right.", Toast.LENGTH_SHORT).show();
                } else if (status == 0) {
                    Toast.makeText(getApplicationContext(), "Your guess was higher.", Toast.LENGTH_SHORT).show();
                } else if (status == -1) {
                    Toast.makeText(getApplicationContext(), "Your guess was lower.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRevealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), String.format(Locale.ENGLISH, "%d", randomNumber), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static int generateRandomNumber() {
        Random random = new Random();
        int random_number = random.nextInt(21 - 1) + 1;
        return random_number;
    }

    public static int tryGuessNumber(int random_number, String textGuessNumber) {
        int guess_number = Integer.parseInt(textGuessNumber);
        if (random_number == guess_number) {
            return 1;
        } else if (random_number < guess_number) {
            return 0;
        } else if (random_number > guess_number) {
            return -1;
        }
        return -1;
    }
}
