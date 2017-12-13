package com.example.jholl.android08_chess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import chess.Board;

public class GameActivity extends AppCompatActivity {

    public static Board backendBoard = new Board();
    public static String currentplayer = "Black";
    public static GridView board;

    //this method will be called first
    //384x640 screen (use dp for everything except text, text uses sp, ex: 12dp or 12sp)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        board = findViewById(R.id.Board);

        SpaceAdapter adapter = new SpaceAdapter(this, backendBoard);

        board.setAdapter(adapter);

        Button undo = this.findViewById(R.id.undoButton);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO undo code goes here
            }
        });

        final Button draw = this.findViewById(R.id.drawButton);
        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.setText(R.string.drawAccept);
                /*
                TODO draw code goes here (try to make it only work after the button's pressed the second time
                but if that's difficult, just change the text in the button to "draw" and remove the setText line above.
                */
            }
        });

        Button resign = this.findViewById(R.id.resignButton);
        resign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO resign code goes here
            }
        });

        Button replay = this.findViewById(R.id.replayButton);
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replayIntent = new Intent(GameActivity.this, ReplaysActivity.class);
                startActivity(replayIntent);
            }
        });

        Button ai = this.findViewById(R.id.aiButton);
        ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO ai code goes here
            }
        });


    }
}

