package io.github.afagarap.prelimagarapafternoon;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReverseRepeatedActivity extends AppCompatActivity {

    private Button mExecuteButton;
    private EditText mInputText;

    private static String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_repeated);

        mExecuteButton = (Button) findViewById(R.id.btnReverse);
        mInputText = (EditText) findViewById(R.id.textInput);

        mExecuteButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View view) {
                // create an intent to start an activity displaying the result
                Intent intent = new Intent(ReverseRepeatedActivity.this, ResultActivity.class);

                // get text from widget
                text = mInputText.getText().toString();

                // validate if string is not empty
                if (!text.isEmpty()){
                    // use regex to extract alphabet characters only
                    text = text.replaceAll("[^A-Za-z]+", "");

                    // store the extracted text as intent extra
                    intent.putExtra("TEXT", text);
                } else {
                    Toast.makeText(getApplicationContext(), "Empty string!", Toast.LENGTH_SHORT).show();
                }

                // start the activity to display the result
                startActivity(intent);
            }
        });
    }
}
