package io.github.afagarap.agarapfinalexamy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button mAnimationButton = (Button) findViewById(R.id.buttonAnimation);
        Button mAtmButton = (Button) findViewById(R.id.buttonATM);
        Button mTriangularButton = (Button) findViewById(R.id.buttonTriangular);
        Button mTictactoeButton = (Button) findViewById(R.id.buttonTictactoe);
        Button mLogoutButton = (Button) findViewById(R.id.buttonLogout);

        TextView mTextViewGreeting = (TextView) findViewById(R.id.textViewGreetings);

        final Bundle bundle = getIntent().getExtras();
        String mFirstName = bundle.getString(Constants.FIRSTNAME);
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
                intent.putExtras(bundle);
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

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(MenuActivity.this, "No back button!", Toast.LENGTH_SHORT).show();
    }
}
