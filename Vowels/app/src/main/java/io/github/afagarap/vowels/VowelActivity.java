package io.github.afagarap.vowels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VowelActivity extends AppCompatActivity {

    // UI Widgets
    private Button mProcessStringButton;
    private EditText mStringInput;
    private TextView mVowelCountText;
    private TextView mNewStringText;

    // Variables to hold values
    private String text;
    private String newText;
    private int vowelCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vowel);

        mProcessStringButton = (Button) findViewById(R.id.count_remove_vowels_btn);
        mStringInput = (EditText) findViewById(R.id.string_input);
        mVowelCountText = (TextView) findViewById(R.id.vowel_count_text);
        mNewStringText = (TextView) findViewById(R.id.new_string_text);

        mProcessStringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get text from user input
                text = mStringInput.getText().toString();

                // get the vowel count on the text
                vowelCount = countVowels(text);

                // get the text with no vowels
                newText = removeVowels(text);

                // output the number of vowels in the text
                mVowelCountText.setText(getResources().getString(R.string.vowel_count) + vowelCount);

                // output the text with not vowels
                mNewStringText.setText(getResources().getString(R.string.string_with_no_vowel) + newText);

                // notify user that the process is done
                Toast.makeText(getApplicationContext(), "Done processing!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Counts the number of vowels by taking advantage of regex
     * that filters consonant letters.
     * The filtered text will contain only vowel letters.
     * @param text
     * @return
     */
    public static int countVowels(String text) {
        int numVowels;
        numVowels = text.replaceAll("[^aeiouAEIOU]", "").length();
        return numVowels;
    }

    /**
     * Uses a regex that removes the vowel letters of a
     * string by blanking them out.
     * @param text
     * @return
     */
    public static String removeVowels(String text) {
        String newText;
        newText = text.replaceAll("[aeiouAEIOU]", "");
        return newText;
    }
}
