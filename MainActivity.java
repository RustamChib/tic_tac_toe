package com.example.rusta.gameconnect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int player = 0;
    // 0 - zero, 1 - krestik
    int[] game_status = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winning_position = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;



    public void imgClick(View view){
        ImageView counter = (ImageView) view;
        int tapped_counter = Integer.parseInt(counter.getTag().toString());
        if (game_status[tapped_counter] == 2 && gameActive) {
            game_status[tapped_counter] = player;
            if (player == 0) {
                player = 1;
                counter.setImageResource(R.drawable.krestik);
                counter.animate().alpha(1).setDuration(500);
            } else {
                player = 0;
                counter.setImageResource(R.drawable.zero);
                counter.animate().alpha(1).setDuration(500);
            }
            for (int[] winningPosition : winning_position) {
                if (game_status[winningPosition[0]] == game_status[winningPosition[1]] &&
                        game_status[winningPosition[1]] == game_status[winningPosition[2]] && game_status[winningPosition[0]] != 2) {
                    //Someone has won!
                    gameActive = false;
                    String winner = "";
                    if (player == 1)
                        winner = "Крестик";
                    else
                        winner = "Нолик";
                    Button btn_restart = findViewById(R.id.btn_restart);
                    TextView txt_winner = findViewById(R.id.txtWinner);
                    txt_winner.setText(winner + " ВЫИГРАЛ");

                    btn_restart.setVisibility(View.VISIBLE);
                    txt_winner.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view){
        Button btn_restart = findViewById(R.id.btn_restart);
        TextView txt_winner = findViewById(R.id.txtWinner);
        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout);

        btn_restart.setVisibility(View.INVISIBLE);
        txt_winner.setVisibility(View.INVISIBLE);

        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i = 0; i < game_status.length; i++) {
            game_status[i] = 2;
        }
        player = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
