package com.example.jholl.android08_chess;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import chess.Board;
import chess.Space;
import pieces.Piece;


/**
 * Created by Jason Holley on 12/7/2017.
 */

public class SpaceAdapter extends BaseAdapter {


    private Context mContext;
    chess.Board board;

    public SpaceAdapter (Context context, Board board){
        this.mContext = context;
        this.board = board;
    }

    @Override
    public int getCount() {
        return 64;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);

            View gridView = inflater.inflate(R.layout.space, parent, false);

            ImageView spaceImage = gridView.findViewById(R.id.spaceBackground);

            //0-8 x 2
            Integer x, y;
            x = (i%8);
            y = (i/8);
            Space thisSpace = board.Spaces[x][y];

            if ((y%2==1 && x%2==1) || (x%2==0 && y%2==0))
                spaceImage.setImageResource(R.drawable.whitesquare);
            else
                spaceImage.setImageResource(R.drawable.blacksquare);

            ImageButton piece = gridView.findViewById(R.id.piece);

            if(thisSpace.isOccupied()){
                if(thisSpace.getPiece().getWhite() == true){
                    if(thisSpace.getPiece().getname().equalsIgnoreCase("Bishop"))
                        piece.setImageResource(R.drawable.whitebishop);
                    if(thisSpace.getPiece().getname().equalsIgnoreCase("King"))
                        piece.setImageResource(R.drawable.whiteking);
                    if(thisSpace.getPiece().getname().equalsIgnoreCase("queen"))
                        piece.setImageResource(R.drawable.whitequeen);
                    if(thisSpace.getPiece().getname().equalsIgnoreCase("knight"))
                        piece.setImageResource(R.drawable.whiteknight);
                    if(thisSpace.getPiece().getname().equalsIgnoreCase("rook"))
                        piece.setImageResource(R.drawable.whiterook);
                    if(thisSpace.getPiece().getname().equalsIgnoreCase("pawn"))
                        piece.setImageResource(R.drawable.whitepawn);
                }
                else{
                    if(thisSpace.getPiece().getname().equalsIgnoreCase("Bishop"))
                        piece.setImageResource(R.drawable.blackbishop);
                    if(thisSpace.getPiece().getname().equalsIgnoreCase("King"))
                        piece.setImageResource(R.drawable.blackking);
                    if(thisSpace.getPiece().getname().equalsIgnoreCase("queen"))
                        piece.setImageResource(R.drawable.blackqueen);
                    if(thisSpace.getPiece().getname().equalsIgnoreCase("knight"))
                        piece.setImageResource(R.drawable.blackknight);
                    if(thisSpace.getPiece().getname().equalsIgnoreCase("rook"))
                        piece.setImageResource(R.drawable.blackrook);
                    if(thisSpace.getPiece().getname().equalsIgnoreCase("pawn"))
                        piece.setImageResource(R.drawable.blackpawn);
                }

                if ((y%2==1 && x%2==1) || (x%2==0 && y%2==0))
                    piece.setBackgroundResource(R.drawable.whitesquare);
                else
                    piece.setBackgroundResource(R.drawable.blacksquare);

            }

            if(!thisSpace.isOccupied())
                piece.setVisibility(View.GONE);

            //TODO implement piece imagebuttons listener
            //I believe this can be done pretty easily since this code goes through every space

            return gridView;
        }
        else
            return view;


    }
}
