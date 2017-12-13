package com.example.jholl.android08_chess;

import java.io.Serializable;
/**
 * Created by Rituraj on 12/13/2017.
 */

public class DataBaseInfo implements Serializable{
    String title;
    String date;
    String moves;

    public DataBaseInfo(){}
    public DataBaseInfo(String title, String date, String moves){
        this.title=title;
        this.date=date;
        this.moves=moves;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoves() {
        return this.moves;
    }

    public void setMoves(String moves) {
        this.moves = moves;
    }
}
