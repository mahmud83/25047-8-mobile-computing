package io.github.afagarap.agarapfinalexamy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MenuActivity extends AppCompatActivity {

    private Button mAnimationButton;
    private Button mAtmButton;
    private Button mTriangularButton;
    private Button mTictactoeButton;

    private TextView mTextViewGreeting;

    private String mFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAnimationButton = (Button) findViewById(R.id.buttonAnimation);
        mAtmButton = (Button) findViewById(R.id.buttonATM);
        mTriangularButton = (Button) findViewById(R.id.buttonTriangular);
        mTictactoeButton = (Button) findViewById(R.id.buttonTictactoe);

        mTextViewGreeting = (TextView) findViewById(R.id.textViewGreetings);

        Intent intent = getIntent();
        mFirstName = intent.getExtras().getString(Constants.FIRSTNAME);
        mTextViewGreeting.setText(String.format(Locale.ENGLISH, "%s %s",
                mTextViewGreeting.getText(), mFirstName));

        mAnimationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, AnimationActivity.class);
                startActivity(intent);
            }
        });

        mAtmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, AtmLoginActivity.class);
                startActivity(intent);
            }
        });

        mTriangularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, TriangularActivity.class);
                startActivity(intent);
            }
        });

        mTictactoeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, TictactoeActivity.class);
                startActivity(intent);
            }
        });
    }
}
