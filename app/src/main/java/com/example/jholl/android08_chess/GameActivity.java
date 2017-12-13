package com.example.jholl.android08_chess;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Your opponent has offered a draw, do you accept?").setPositiveButton("Yes", drawClickListener)
                        .setNegativeButton("No",drawClickListener);
                builder.show();
            }
        });

        Button resign = this.findViewById(R.id.resignButton);
        resign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you sure you want to resign?").setPositiveButton("Yes", resignClickListener)
                        .setNegativeButton("No",resignClickListener);
                builder.show();
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
    DialogInterface.OnClickListener drawClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    String draws[] = new String[1];
                    draws[0] = "draw";
                    backendBoard.move(draws, currentplayer);
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(board.getContext());
                    builder2.setMessage("Draw! Would you like to save replay?").setPositiveButton("Yes", askReplaySave)
                            .setNegativeButton("No",askReplaySave);
                    builder2.show();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        }
    };

    DialogInterface.OnClickListener resignClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    String resign[] = new String[1];
                    resign[0] = "resign";
                    backendBoard.move(resign, currentplayer);
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(board.getContext());
                        if(currentplayer.equalsIgnoreCase("White")) {
                            builder2.setMessage("White wins! Would you like to save replay?").setPositiveButton("Yes", askReplaySave)
                                    .setNegativeButton("No", askReplaySave);
                            builder2.show();
                        }
                        else{
                            builder2.setMessage("Black wins! Would you like to save replay?").setPositiveButton("Yes", askReplaySave)
                                    .setNegativeButton("No", askReplaySave);
                            builder2.show();
                    }
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        }
    };
    DialogInterface.OnClickListener askReplaySave = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //TODO save replay
                    backendBoard = new Board();
                    board.setAdapter(new SpaceAdapter(board.getContext(), GameActivity.backendBoard));
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    backendBoard = new Board();
                    board.setAdapter(new SpaceAdapter(board.getContext(), GameActivity.backendBoard));
                    break;
            }
        }
    };
}


