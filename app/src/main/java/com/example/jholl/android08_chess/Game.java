package com.example.jholl.android08_chess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import chess.Board;

public class Game extends AppCompatActivity {

    private GridView board;
    private Board backendBoard = new Board();
    //this method will be called first
    //384x640

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        board = findViewById(R.id.Board);

    }
}
