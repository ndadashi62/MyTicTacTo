package com.example.mytictacto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    int activePlayer = 0;

    int[] cellState = {10, 10, 10, 10, 10, 10, 10, 10, 10};
    int[][] winningpositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 6}, {0, 4, 8}, {2, 5, 8},
            {1, 4, 7}, {2, 4, 6}};
    TextView txtPlayerO;
    TextView txtplayerX;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPlayerO=findViewById(R.id.txtPlayerO);
        txtplayerX=findViewById(R.id.txtPlayerX);

        
        
    }

    public void clickOnImage(View view) {
        ImageView clickedImageView = (ImageView) view;

        int tag = Integer.parseInt(clickedImageView.getTag().toString());
        cellState[tag] = activePlayer;

        clickedImageView.setTranslationY(-1500);

        if (activePlayer == 0) {
            clickedImageView.setImageResource(R.drawable.x);
            activePlayer = 1;
        } else  if(activePlayer==1)  {
            clickedImageView.setImageResource(R.drawable.o);
            activePlayer = 0;

        }
        clickedImageView.animate().translationYBy(1500).rotation(3600).setDuration(300);

        for (int[] winposition : winningpositions) {
            if (cellState[winposition[0]] == cellState[winposition[1]]
                    && cellState[winposition[2]] == cellState[winposition[0]]
                    && cellState[winposition[0]] != 10) {


                resetGame();
                resetCelState();
                updatescoreBoard();
            }
        }


    }
    public void updatescoreBoard () {

        if (activePlayer == 0) {
            int playerOwintime = Integer.parseInt(txtPlayerO.getText().toString());
            playerOwintime++;
            txtPlayerO.setText(playerOwintime+"");
            //"0"->0

        } else {
            int playerXwintime = Integer.parseInt(txtplayerX.getText().toString());
            playerXwintime++;
            txtPlayerO.setText(playerXwintime+"");


        }
    }
        public void resetGame () {
            ConstraintLayout container = findViewById(R.id.container);

            for (int i = 0; i < container.getChildCount(); i++) {
                if (container.getChildAt(i) instanceof ImageView) {
                    ImageView imageView = (ImageView) container.getChildAt(i);
                    if (imageView.getTag() != null) {
                        imageView.setImageDrawable(null);

                    }

                }
            }

        }

        public void resetCelState () {

            for (int i = 0; i < cellState.length; i++) {
                cellState[i] = 10;

            }
        }
    }



