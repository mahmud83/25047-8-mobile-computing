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

        Intent intent = getIntent();
        mProcessedStringText = (TextView) findViewById(R.id.processed_string_text);

        String processedString = intent.getExtras().getString(Constants.PROCESSED_STRING);

        Map map = countLetters(processedString);

        displayCharacterCount(processedString, map);
    }

    public void displayCharacterCount(String processedString, Map map) {
        Set keys = countLetters(processedString).keySet();
        for (Iterator i = keys.iterator(); i.hasNext(); ) {
            Character character = (Character) i.next();
            Integer value = (Integer) map.get(character);
            mProcessedStringText.append(String.format(Locale.ENGLISH, "%s : %s\n", character, value));
        }
    }

    public Map<Character, Integer> countLetters(String text) {
        int len = text.length();
        Map <Character, Integer> numChars = new HashMap<>(Math.min(len, 26));
        for (int index = 0; index < len; index++) {
            char charAt = text.charAt(index);
            if (!numChars.containsKey(charAt)) {
                numChars.put(charAt, 1);
            } else {
                numChars.put(charAt, numChars.get(charAt) + 1);
            }
        }
        return numChars;
    }
}
