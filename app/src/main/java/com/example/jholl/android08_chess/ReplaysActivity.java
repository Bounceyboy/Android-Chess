package com.example.jholl.android08_chess;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.*;

import chess.Board;
import chess.Space;

/**
 * Created by Jason Holley on 12/9/2017.
 */

public class ReplaysActivity extends AppCompatActivity {
    int selected=-1;

    @Override
        protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.replayscreen);

        /*

        this can't be done until replays are implemented, since I don't know
        for sure how you'll do that

        this can be done with this code:

        ListAdapter theAdapter = new ArrayAdapter<String>
        (this, android.R.layout.simple_list_item_1, ~List of strings~);

        ListView theListView = (ListView) findViewById(R.id.replayListView);
        theListView.setAdapter(theAdapter);
        */

        DataBase db = DataBase.getInstance(ReplaysActivity.this);
        final List<DataBaseInfo> dbout  = db.getAll();
        final List<String> content = new ArrayList<>();
        for(int i=0;i<dbout.size();i++){
            content.add(dbout.get(i).getTitle()+"\n"+dbout.get(i).getDate());
        }
        final ListAdapter theAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, content);

        final ListView theListView = findViewById(R.id.replayListView);
        theListView.setAdapter(theAdapter);
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                  selected=i;

            }
        });

        Button back = this.findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replayIntent = new Intent(ReplaysActivity.this, GameActivity.class);
                startActivity(replayIntent);
            }
        });

        Button date = this.findViewById(R.id.dateSortButton);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO datesort code
                
                for(int i=(content.size()-1);i>=0;i--){
                    for(int j=1;j<=i;j++){
                        String [] lineone = content.get((j-1)).split("\\n");
                        String [] linetwo = content.get(j).split("\\n");
                       int first=Integer.parseInt(lineone[1]);
                       int second=Integer.parseInt(linetwo[1]);
                       if(first>second){
                           String temp = content.get((j-1));
                           content.set((j-1),content.get(j));
                           content.set(j,temp);

                       }
                    }
                }
                theListView.invalidateViews();
                theListView.refreshDrawableState();
            }
        });

        Button title = this.findViewById(R.id.titleSortButton);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO title sort code
                Collections.sort(content, String.CASE_INSENSITIVE_ORDER);
                theListView.invalidateViews();
                theListView.refreshDrawableState();
            }
        });

        Button play = this.findViewById(R.id.playReplayButton);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected!=-1) {


                    //reset board
                    GameActivity.backendBoard = new Board();
                    GameActivity.currentplayer = "Black";
                    GameActivity.board.setAdapter(new SpaceAdapter(GameActivity.board.getContext(), GameActivity.backendBoard));

                    SpaceAdapter.simulateMoves=dbout.get(selected).getMoves();

                    Intent replayIntent = new Intent(ReplaysActivity.this, GameActivity.class);
                    startActivity(replayIntent);
                }
            }
        });


    }
}
