package io.github.afagarap.vowels;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class ConsonantActivity extends AppCompatActivity {

    private TextView mProcessedStringText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consonant);

        mProcessedStringText = (TextView) findViewById(R.id.processed_string_text);

        // get the intent that started this activity
        Intent intent = getIntent();

        // get the string extra passed using the intent
        String processedString = intent.getExtras().getString(Constants.PROCESSED_STRING);

        // get the count for each character in the string
        Map map = countLetters(processedString);

        // display the number of each character in the string
        displayCharacterCount(map);
    }

    /**
     * Procedure to display the count of each
     * character passed using the intent
     * @param map
     */
    public void displayCharacterCount(Map map) {
        Set keys = map.keySet();
        for (Iterator i = keys.iterator(); i.hasNext(); ) {
            // the character in the string serves as the key
            Character character = (Character) i.next();

            // the count of char serves as the value
            Integer value = (Integer) map.get(character);

            // append the char, int pair to the TextView widget
            mProcessedStringText.append(String.format(Locale.ENGLISH, "%s : %s\n", character, value));
        }
    }

    /**
     * Returns a map that contains the count of
     * each character found in the string
     * @param text
     * @return
     */
    public Map<Character, Integer> countLetters(String text) {
        // get the length of the string to process
        int len = text.length();

        // construct a hashmap to store the character and its count
        Map <Character, Integer> numChars = new HashMap<>(Math.min(len, 26));

        // loop through the string's characters
        for (int index = 0; index < len; index++) {
            // save the char at current iteration
            char charAt = text.charAt(index);

            // check if the hashmap already has the character
            // at current iteration inside it
            if (!numChars.containsKey(charAt)) {
                // if not, add the char and a count of it
                numChars.put(charAt, 1);
            } else {
                // if it already has the char, increment its count
                numChars.put(charAt, numChars.get(charAt) + 1);
            }
        }
        return numChars;
    }
}
