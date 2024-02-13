package com.narayan.tiktaktok;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  TextView showtag, showmsg;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    showtag = findViewById(R.id.showtag);
    showmsg = findViewById(R.id.showmsg);
  }

  boolean gameActive = true;

  int gamestate[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
  int activeP = 0;
  int[][] winPostion = {
    {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
    {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
    {0, 4, 8}, {2, 4, 6}
  };

  public void click(View view) {
    ImageView img = (ImageView) view;
    int tagnum = Integer.parseInt(img.getTag().toString());
    try {
      Toast.makeText(getApplicationContext(), tagnum, Toast.LENGTH_LONG).show();
    } catch (Exception e) {
      Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }
    if (!gameActive) {
      Restartgame();
    }

    if (gamestate[tagnum] == 2) {
      if (activeP == 0) {
        gamestate[tagnum] = 0;
        activeP = 1;
        img.setImageResource(R.drawable.circle);
        showmsg.setText("x player turn");
        try {
          int pos = SelectAi(tagnum);
                    Toast.makeText(getApplicationContext(),pos,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
          Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
      } // else {

      // gamestate[tagnum] = 1;
      // activeP = 0;
      // img.setImageResource(R.drawable.cross);
      // showmsg.setText("o player turn");
      // }
    } else {
      Toast.makeText(MainActivity.this, "allread click", Toast.LENGTH_LONG).show();
    }

    for (int[] winPostion : winPostion) {
      if (gamestate[winPostion[0]] == gamestate[winPostion[1]]
          && gamestate[winPostion[1]] == gamestate[winPostion[2]]
          && gamestate[winPostion[0]] != 2) {

        if (gamestate[winPostion[0]] == 0) {
          gameActive = false;
          showmsg.setText("O is win");
          Restartgame();
        } else {
          gameActive = false;
          showmsg.setText("X is win");
          Restartgame();
        }
      }
    }
  }

  void Restartgame() {
    activeP = 0;
    gameActive = true;

    for (int i = 0; i < gamestate.length; i++) {
      gamestate[i] = 2;
    }

    ((ImageButton) findViewById(R.id.img1)).setImageResource(0);
    ((ImageButton) findViewById(R.id.img2)).setImageResource(0);
    ((ImageButton) findViewById(R.id.img3)).setImageResource(0);
    ((ImageButton) findViewById(R.id.img4)).setImageResource(0);
    ((ImageButton) findViewById(R.id.img5)).setImageResource(0);
    ((ImageButton) findViewById(R.id.img6)).setImageResource(0);
    ((ImageButton) findViewById(R.id.img7)).setImageResource(0);
    ((ImageButton) findViewById(R.id.img8)).setImageResource(0);
    ((ImageButton) findViewById(R.id.img9)).setImageResource(0);
  }

  int SelectAi(int tagnum) {

    int pos = 0;

    int[] PositionVal = {};
    PositionVal[pos] = tagnum;
    pos++;

    final int PVal = RandomPos();
        return PVal;

  }

  int RandomPos() {

    int min = 0;
    int max = 8;

    final int RandomVal = new Random().nextInt((max - min) + 1) + min;

    return RandomVal;
  }
}
