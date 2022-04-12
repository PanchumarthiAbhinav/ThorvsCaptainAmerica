package com.example.xogame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0: Thor, 1: Captain America, 2: none
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winningpos = {{0,1,2},{3,4,5},{6,7,8},{2,4,6},{0,4,8},{0,3,6},{1,4,7},{2,5,8}};
    Integer activeplayer = 0;
    Boolean gameactive = true;
    public void dropin(View view){
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridlayout);
        ImageView counter = (ImageView) view;
        Integer place = Integer.parseInt(counter.getTag().toString());
        if(gamestate[place] == 2 && gameactive) {
            gamestate[place] = activeplayer;
            if(activeplayer==0){
                counter.setImageResource(R.drawable.thorhammer);
                activeplayer = 1;
            } else{
                counter.setImageResource(R.drawable.captainamericashield);
                activeplayer = 0;
            }
            TextView winner = (TextView) findViewById(R.id.winnertext);
            for(int[] winningposition:winningpos){
                if(gamestate[winningposition[0]]==gamestate[winningposition[1]]&&gamestate[winningposition[1]]==gamestate[winningposition[2]]&&gamestate[winningposition[0]]!=2){
                    gameactive = false;
                    if(activeplayer==0){
                        String Winnerplayer = "Captain America has WON!!";
                        winner.setText(Winnerplayer);
                    } else{
                        String Winnerplayer = "Thor has WON!!";
                        winner.setText(Winnerplayer);
                    }
                }
            }
        }
        else{
             TextView winner = (TextView) findViewById(R.id.winnertext);
            Button playagain = (Button) findViewById(R.id.playagain);
            winner.setVisibility(View.VISIBLE);
            playagain.setVisibility(View.VISIBLE);
        }
    }
    public void playagain(View view){
        for(int i =0;i<gamestate.length;i++){
            gamestate[i] = 2;
        }
        activeplayer = 0;
        gameactive = true;
        androidx.gridlayout.widget.GridLayout mygridLayout = findViewById(R.id.gridlayout);

        for(int i=0; i<mygridLayout.getChildCount(); i++){
            ((ImageView) mygridLayout.getChildAt(i)).setImageDrawable(null);
        }
        TextView winner = (TextView) findViewById(R.id.winnertext);
        Button playagain = (Button) findViewById(R.id.playagain);
        winner.setVisibility(View.INVISIBLE);
        playagain.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}