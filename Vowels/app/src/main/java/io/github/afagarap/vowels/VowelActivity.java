package io.github.afagarap.vowels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VowelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vowel);
    }

    /**
     * Counts the number of vowels by taking advantage of regex
     * that filters consonant letters.
     * The filtered text will contain only vowel letters.
     * @param text
     * @return
     */
    public static int countVowels(String text) {
        int numVowels = 0;
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
        String newText = "";
        newText = text.replaceAll("[aeiouAEIOU]", "");
        return newText;
    }
}
