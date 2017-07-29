package io.github.afagarap.prelimagarapafternoon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class TriangularNumberActivity extends AppCompatActivity {

    public Button mComputeButton;
    public Button mDisplayButton;
    public EditText mInputText;
    public TextView mResultText;

    private static int input;
    private static int triangularNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangular_number);

        mComputeButton = (Button) findViewById(R.id.btnCompute);
        mDisplayButton = (Button) findViewById(R.id.btnDisplayAll);
        mResultText = (TextView) findViewById(R.id.textResult);
        mInputText = (EditText) findViewById(R.id.textInputNumber);

        mComputeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                triangularNumber = getTriangularNumber(Integer.parseInt(mInputText.getText().toString()));
                mResultText.setText(String.format(Locale.ENGLISH, "%s : %d", "Result", triangularNumber));
            }
        });

        mDisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = displayTenTriangularNumbers();
                mResultText.setText(String.format(Locale.ENGLISH, "Result : %s", text));
            }
        });
    }

    public static int getTriangularNumber(int input) {
        int n = input * (input + 1) / 2;
        return n;
    }

    public static String displayTenTriangularNumbers() {
        String text = "";
        for (int i = 1; i < 11; i++) {
            text += "\t" + (i * (i + 1) / 2);
        }
        return text;
    }
}
