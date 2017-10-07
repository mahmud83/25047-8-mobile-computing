/*
MIT License

Copyright (c) 2017 Abien Fred Agarap

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package io.github.afagarap.agarapfinalexamy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

public class TictactoeActivity extends AppCompatActivity {

    private static final String TAG = TictactoeActivity.class.getSimpleName();

    // number of board cells
    public static final int numCells = 9;

    // indicator of whose turn it is
    public boolean isTurn0 = true;

    // indicator of whether the game is over
    public boolean isOver = false;

    // ImageView objects for each board cell
    public ImageView cell0, cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8;

    // ImageView array to contain all board cells
    public ImageView[] imageCells = new ImageView[numCells];

    public int[][] matrix = new int[3][3];
    public int scoreJake;
    public int scoreFinn;

    public Button buttonRestart;
    public Button buttonPlayAgain;

    public TextView textScoreJake;
    public TextView textScoreFinn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);

        // automated rewriting using Python script
        // for index in range(9):
        //     print('cell{} = (ImageView)findViewById(R.id.imageCell{});'.format(index, index))
        cell0 = (ImageView)findViewById(R.id.imageCell0);
        cell1 = (ImageView)findViewById(R.id.imageCell1);
        cell2 = (ImageView)findViewById(R.id.imageCell2);
        cell3 = (ImageView)findViewById(R.id.imageCell3);
        cell4 = (ImageView)findViewById(R.id.imageCell4);
        cell5 = (ImageView)findViewById(R.id.imageCell5);
        cell6 = (ImageView)findViewById(R.id.imageCell6);
        cell7 = (ImageView)findViewById(R.id.imageCell7);
        cell8 = (ImageView)findViewById(R.id.imageCell8);

        // for index in range(9):
        //     print('imageCells[{}] = cell{};'.format(index, index))
        imageCells[0] = cell0;
        imageCells[1] = cell1;
        imageCells[2] = cell2;
        imageCells[3] = cell3;
        imageCells[4] = cell4;
        imageCells[5] = cell5;
        imageCells[6] = cell6;
        imageCells[7] = cell7;
        imageCells[8] = cell8;

        textScoreFinn = (TextView) findViewById(R.id.textScoreFinn);
        textScoreJake = (TextView) findViewById(R.id.textScoreJake);

        // give ImageView element an OnTouchListener
        for (int index = 0; index < numCells; index++) {
            imageCells[index].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (!isOver) commitMove(v);
                    return true;
                }
            });
        }

        buttonPlayAgain = (Button) findViewById(R.id.buttonPlayAgain);
        buttonPlayAgain.setVisibility(View.INVISIBLE);
        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonRestart.setVisibility(View.INVISIBLE);
                buttonPlayAgain.setVisibility(View.INVISIBLE);
                isTurn0 = true;
                isOver = false;
                scoreFinn = 0;
                scoreJake = 0;
                textScoreFinn.setText("Finn:");
                textScoreJake.setText("Jake:");

                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        matrix[row][col] = 0;
                    }
                }

                for (int index = 0; index < 9; index++) {
                    imageCells[index].setAlpha(0f);
                }
            }
        });

        buttonRestart = (Button) findViewById(R.id.buttonRestart);
        buttonRestart.setVisibility(View.INVISIBLE);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonRestart.setVisibility(View.INVISIBLE);
                isTurn0 = true;
                isOver = false;

                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        matrix[row][col] = 0;
                    }
                }

                for (int index = 0; index < 9; index++) {
                    imageCells[index].setAlpha(0f);
                }
            }
        });
    }

    /**
     * Handles tap events on board cells
     * @param view
     */
    public void commitMove (View view) {
        // if cell has a chip, do nothing
        // else make the chip visible
        if (view.getAlpha() == 1){
            return;
        } else {
            view.setAlpha(1);
        }

        int objectId = view.getId();

        // placeholder for image
        ImageView imageCell = (ImageView) view;

        // set image resource based on whose turn it is
        imageCell.setImageResource(isTurn0 ? R.drawable.jake : R.drawable.finn);

        switch (objectId) {
            case R.id.imageCell0: matrix[0][0] = (isTurn0) ? 1 : 2; break;
            case R.id.imageCell1: matrix[0][1] = (isTurn0) ? 1 : 2; break;
            case R.id.imageCell2: matrix[0][2] = (isTurn0) ? 1 : 2; break;
            case R.id.imageCell3: matrix[1][0] = (isTurn0) ? 1 : 2; break;
            case R.id.imageCell4: matrix[1][1] = (isTurn0) ? 1 : 2; break;
            case R.id.imageCell5: matrix[1][2] = (isTurn0) ? 1 : 2; break;
            case R.id.imageCell6: matrix[2][0] = (isTurn0) ? 1 : 2; break;
            case R.id.imageCell7: matrix[2][1] = (isTurn0) ? 1 : 2; break;
            default: matrix[2][2] = (isTurn0) ? 1 : 2; break;
        }

        if (isWin(isTurn0 ? 1 : 2)) {
            Toast.makeText(this, String.format(Locale.ENGLISH, "Winner: Player %s", isTurn0 ? "Jake" : "Finn"),
                    Toast.LENGTH_SHORT).show();

            if (isTurn0) {
                scoreJake += 1;
                textScoreJake.setText("Jake: " + scoreJake);
            } else {
                scoreFinn += 1;
                textScoreFinn.setText("Finn: " + scoreFinn);
            }

            isOver = true;
            buttonRestart.setVisibility(View.VISIBLE);
            buttonPlayAgain.setVisibility(View.VISIBLE);
        } else if (isBoardFull()) {
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
            isOver = true;
            buttonRestart.setVisibility(View.VISIBLE);
            buttonPlayAgain.setVisibility(View.VISIBLE);
        }

        // boolean value for setting whose turn it is
        isTurn0 = !isTurn0;

        // log whose turn it is
        Log.d(TAG, String.format(Locale.ENGLISH, "Turn 0 : %s", isTurn0));
    }

    public boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (matrix[row][col] == 0) return false;
            }
        }
        return true;
    }

    public boolean isWin(int move) {
        return ((matrix[0][0] == move && matrix[0][1] == move && matrix[0][2] == move) ||
                (matrix[1][0] == move && matrix[1][1] == move && matrix[1][2] == move) ||
                (matrix[2][0] == move && matrix[2][1] == move && matrix[2][2] == move) ||
                (matrix[0][0] == move && matrix[1][0] == move && matrix[2][0] == move) ||
                (matrix[0][1] == move && matrix[1][1] == move && matrix[2][1] == move) ||
                (matrix[0][2] == move && matrix[1][2] == move && matrix[2][2] == move) ||
                (matrix[0][0] == move && matrix[1][1] == move && matrix[2][2] == move) ||
                (matrix[0][2] == move && matrix[1][1] == move && matrix[2][0] == move));
    }

}
