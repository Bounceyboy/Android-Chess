package com.example.jholl.android08_chess;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.*;

/**
 * Created by Jason Holley on 12/9/2017.
 */

public class ReplaysActivity extends AppCompatActivity {

    @Override
        protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.replayscreen);

        /*
        TODO need to add list of replays to listview
        this can't be done until replays are implemented, since I don't know
        for sure how you'll do that

        this can be done with this code:

        ListAdapter theAdapter = new ArrayAdapter<String>
        (this, android.R.layout.simple_list_item_1, ~List of strings~);

        ListView theListView = (ListView) findViewById(R.id.replayListView);
        theListView.setAdapter(theAdapter);
        */

        DataBase db = DataBase.getInstance(ReplaysActivity.this);
        List<DataBaseInfo> content  = db.getAll();

        ListAdapter theAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, content);

        ListView theListView = findViewById(R.id.replayListView);
        theListView.setAdapter(theAdapter);

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
            }
        });

        Button title = this.findViewById(R.id.titleSortButton);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO title sort code
            }
        });

        Button play = this.findViewById(R.id.playReplayButton);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO replay playback code
            }
        });


    }
}
