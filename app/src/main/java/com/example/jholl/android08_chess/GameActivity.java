package com.example.jholl.android08_chess;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import chess.Board;

public class GameActivity extends AppCompatActivity {
    public static int simulatecount=0;
    EditText title;
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

        final Button simulate = this.findViewById(R.id.simulate);
        simulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (simulatecount + 4 < SpaceAdapter.simulateMoves.length()) {
                    String[] playmove = {SpaceAdapter.simulateMoves.substring(simulatecount, simulatecount + 2),
                            SpaceAdapter.simulateMoves.substring(simulatecount + 2, simulatecount + 4)};
                    System.out.println(playmove[0] + " " + playmove[1]);
                    if (!GameActivity.backendBoard.move(playmove, currentplayer)) {
                        Toast.makeText(SpaceAdapter.gridView.getContext(), "Illegal move, try again.", Toast.LENGTH_SHORT).show();
                    } else {
                        SpaceAdapter.moves += (playmove[0]);
                        SpaceAdapter.moves += (playmove[1]);
                        simulatecount += 4;
                        if (currentplayer.equals("White")) {
                            currentplayer = "Black";
                        } else {
                            currentplayer = "White";
                        }
                    }
                    //System.out.println(SpaceAdapter.move[0] + SpaceAdapter.move[1]);
                    GameActivity.board.setAdapter(new SpaceAdapter(view.getContext(), GameActivity.backendBoard));
                }
            }
        });

        if(SpaceAdapter.simulateMoves.length()>0){
            simulate.setVisibility(View.VISIBLE);
        }
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
                    final AlertDialog.Builder input = new AlertDialog.Builder(board.getContext());
                    input.setMessage("Title of Game?");
                    title = new EditText(board.getContext());
                    input.setView(title);
                    input.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String name=title.getText().toString();
                            Date curr = Calendar.getInstance().getTime();
                            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
                            String date =format.format(curr);
                            DataBaseInfo newgame = new DataBaseInfo(name,date,SpaceAdapter.moves);
                            DataBase db = DataBase.getInstance(GameActivity.this);
                            db.add(newgame);

                        }
                    });

                    AlertDialog ask = input.create();
                    ask.show();
                    backendBoard = new Board();
                    currentplayer="Black";
                    board.setAdapter(new SpaceAdapter(board.getContext(), GameActivity.backendBoard));
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    backendBoard = new Board();
                    currentplayer="Black";
                    board.setAdapter(new SpaceAdapter(board.getContext(), GameActivity.backendBoard));
                    break;
            }
        }
    };
}


