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

package mobilecomputinglab.tictactoeafternoon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // number of board cells
    public static final int numCells = 9;

    // indicator of whose turn it is
    public boolean isTurn0 = true;

    // ImageView objects for each board cell
    public ImageView cell0, cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8;

    // ImageView array to contain all board cells
    public ImageView[] imageCells = new ImageView[numCells];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        // give ImageView element an OnTouchListener
        for (int index = 0; index < numCells; index++) {
            imageCells[index].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    commitMove(v);
                    return true;
                }
            });
        }
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

        // placeholder for image
        ImageView imageCell = (ImageView) view;

        // set image resource based on whose turn it is
        imageCell.setImageResource(isTurn0 ? R.drawable.red : R.drawable.yellow);

        // boolean value for setting whose turn it is
        isTurn0 = !isTurn0;
    }

}
