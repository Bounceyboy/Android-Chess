package com.example.jholl.android08_chess;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import chess.Board;
import chess.Space;
import pieces.Piece;

import static com.example.jholl.android08_chess.GameActivity.currentplayer;
import static com.example.jholl.android08_chess.SpaceAdapter.gridView;
import static com.example.jholl.android08_chess.SpaceAdapter.moves;


/**
 * Created by Jason Holley on 12/7/2017.
 */

public class SpaceAdapter extends BaseAdapter {

    public static String moves = new String();
    public static String simulateMoves = new String();
    private Context mContext;
    chess.Board board;
    public static boolean firstSpace = false;
    public static String move[] = new String[2];
    public static String chars = "abcdefgh";
    public static View gridView;

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
        return i;
    }

    @Override
    public View getView(int i, View view, final ViewGroup parent) {

        if(view == null){
            if (!GameActivity.backendBoard.getgame()){
                AlertDialog.Builder builder2 = new AlertDialog.Builder(view.getContext());
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
            }

            LayoutInflater inflater = LayoutInflater.from(mContext);

            gridView = inflater.inflate(R.layout.space, parent, false);

            ImageView spaceImage = gridView.findViewById(R.id.spaceBackground);

            //0-8 x 2
            Integer x, y;
            x = (i%8);
            y = (i/8);
            final Space thisSpace = board.Spaces[x][y];

            if ((y%2==1 && x%2==1) || (x%2==0 && y%2==0))
                spaceImage.setImageResource(R.drawable.whitesquare);
            else
                spaceImage.setImageResource(R.drawable.blacksquare);

            final ImageButton piece = gridView.findViewById(R.id.piece);

            if(thisSpace.isOccupied()){
                if(thisSpace.getPiece().getWhite()){
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

            piece.setTag(i);
            spaceImage.setTag(i);

            if(!thisSpace.isOccupied())
                piece.setVisibility(View.GONE);

            piece.setOnTouchListener(new MyTouchListener());
            spaceImage.setOnTouchListener(new MyTouchListener());


            return gridView;
        }
        else
            return view;


    }
    DialogInterface.OnClickListener askReplaySave = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //TODO save replay
                    GameActivity.backendBoard = new Board();
                    GameActivity.board.setAdapter(new SpaceAdapter(GameActivity.board.getContext(), GameActivity.backendBoard));
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    GameActivity.backendBoard = new Board();
                    GameActivity.board.setAdapter(new SpaceAdapter(GameActivity.board.getContext(), GameActivity.backendBoard));
                    break;
            }
        }
    };
}

final class MyTouchListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.performClick();
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            if(!SpaceAdapter.firstSpace) {
                SpaceAdapter.firstSpace = true;
                SpaceAdapter.move[0]="";
                SpaceAdapter.move[0] = SpaceAdapter.move[0] + (SpaceAdapter.chars.charAt((int)view.getTag() % 8));
                SpaceAdapter.move[0] +=(8-(int)view.getTag() / 8);
                view.setBackgroundResource(R.drawable.highlighted);
            }
            else{
                SpaceAdapter.firstSpace = false;
                SpaceAdapter.move[1]="";
                SpaceAdapter.move[1] = SpaceAdapter.move[1] + (SpaceAdapter.chars.charAt((int)view.getTag() % 8));
                SpaceAdapter.move[1]+=(8-(int)view.getTag() / 8);

                if (!GameActivity.backendBoard.move (SpaceAdapter.move, currentplayer)){
                    Toast.makeText(SpaceAdapter.gridView.getContext(), "Illegal move, try again.", Toast.LENGTH_SHORT).show();
                }
                else {
                    moves+=(SpaceAdapter.move[0]);
                    moves+=(SpaceAdapter.move[1]);
                    GameActivity.simulatecount+=4;
                    if (currentplayer.equals ("White")){
                        currentplayer="Black";
                    }
                    else {
                        currentplayer= "White";
                    }
                }
                //System.out.println(SpaceAdapter.move[0] + SpaceAdapter.move[1]);
                GameActivity.board.setAdapter(new SpaceAdapter(view.getContext(), GameActivity.backendBoard));

            }
            return true;
        } else {
            return false;
        }
    }
}
