package com.jackass.tictactoe;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends Activity {
    private static Integer player = 0;
    private Integer[] visited = new Integer[9];
    int gameOn=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(gameOn==0)
        setContentView(R.layout.home_layout);

    }

       public void launchGame(View view)
       {
           gameOn=1;
           resetBoard();
       }
    private void resetBoard() {
        for ( Integer i = 0 ; i < 9 ; i++ ) {
            visited[i] = -1 ;
        }
        player=0;
        setContentView(R.layout.activity_main);
    }

    private void openWinnerDialog (Integer winner) {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setCanceledOnTouchOutside(false);
        ImageView img2=(ImageView)dialog.findViewById(R.id.image);

        TextView textView = (TextView)dialog.findViewById(R.id.winnerText);
        Button home = (Button) dialog.findViewById(R.id.homeButton);
        Button newGame = (Button) dialog.findViewById(R.id.newGameButton);
        winner++;
        if(winner==0)
        {
            textView.setText("MATCH DRAWN");

        }else
        {
            textView.setText("PLAYER " + winner.toString() + " WINS !!");
            if(winner==1)
            {
                img2.setImageResource(R.drawable.x1);
            }
            else
                img2.setImageResource(R.drawable.circle1);
        }
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.home_layout);
                dialog.hide();
            }
        });
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
                dialog.hide();
            }
        });
        dialog.show();
    }

    public void markCell (View view) {
        Integer id = view.getId();
        if(visited[id % 10] == -1) {
            ImageView img = (ImageView)findViewById(id);
            if (player == 0) {
                img.setImageResource(R.drawable.cross);
            } else {
                img.setImageResource(R.drawable.circle);
            }
            visited[id % 10] = player;
            player = (player+1)&1;
            Integer winner = checkWinner();
            if (winner != -1) {
                openWinnerDialog(winner);
            }
            else
            {   int i;
                for(i=0;i<9;i++)
                {
                    if(visited[i]==-1)
                        break;
                }
                if(i==9)
                {
                    openWinnerDialog(winner);
                }
            }

        }
    }

    private int checkWinner() {
        if (visited[0] == visited[1] && visited[1] == visited[2] && visited[0] != -1) {
            return visited[0];
        }
        else if (visited[3] == visited[4] && visited[4] == visited[5] && visited[3] != -1) {
            return visited[3];
        }
        else if (visited[6] == visited[7] && visited[7] == visited[8] && visited[7] != -1) {
            return visited[7];
        }
        else if (visited[0] == visited[3] && visited[3] == visited[6] && visited[0] != -1) {
            return visited[0];
        }
        else if (visited[1] == visited[4] && visited[4] == visited[7] && visited[1] != -1) {
            return visited[1];
        }
        else if (visited[2] == visited[5] && visited[5] == visited[8] && visited[2] != -1) {
            return visited[2];
        }
        else if (visited[0] == visited[4] && visited[4] == visited[8] && visited[0] != -1) {
            return visited[0];
        }
        else if (visited[2] == visited[4] && visited[4] == visited[6] && visited[2] != -1) {
            return visited[2];
        }
        else {
            return -1;
        }
    }
}
