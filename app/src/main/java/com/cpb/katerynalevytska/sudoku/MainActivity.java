package com.cpb.katerynalevytska.sudoku;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button aboutBTN;
    Button exitBTN;
    Button newGameBTN;
    Button continueBTN;
    TextView title;
    TextView devAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView)findViewById(R.id.mainTitle);
        aboutBTN = (Button) findViewById(R.id.btnAbout);
        exitBTN = (Button) findViewById(R.id.btnExit);
        newGameBTN = (Button) findViewById(R.id.btnNewGame);
        continueBTN = (Button) findViewById(R.id.btnContinue);
        devAuth = (TextView) findViewById(R.id.devAuth);


        aboutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAbout();
            }
        });

        exitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        newGameBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        Typeface type = Typeface.createFromAsset(getAssets(), "my-font.ttf");
        title.setTypeface(type);
        aboutBTN.setTypeface(type);
        exitBTN.setTypeface(type);
        newGameBTN.setTypeface(type);
        continueBTN.setTypeface(type);
        devAuth.setTypeface(type);

    }

    private void startGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void showAbout() {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }
}
