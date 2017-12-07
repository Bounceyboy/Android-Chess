package pieces;

import chess.Board;
import chess.Space;

/**
 * @author Jason Holley Rituraj Kumar
 *
 */
public class Knight extends Piece {

	public Knight(int x, int y, boolean isWhite) {
		super(x, y, isWhite);
	}

	@Override
	public boolean move(Space moveTo, Board game) {
		
		if(Math.abs(moveTo.getY() - this.y) == 2 && Math.abs(moveTo.getX() - this.x) == 1) {
			if(moveTo.isOccupied()){
				if(moveTo.getPiece().isWhite==this.isWhite){
					return false;
				}
			}
			this.x=moveTo.getX();
			this.y=moveTo.getY();
			moveTo.storePiece(this);
			return true;
		}

		if(Math.abs(moveTo.getY() - this.y) == 1 && Math.abs(moveTo.getX() - this.x) == 2) {
			if(moveTo.isOccupied()){
				if(moveTo.getPiece().isWhite==this.isWhite){
					return false;
				}
			}
			this.x=moveTo.getX();
			this.y=moveTo.getY();
			moveTo.storePiece(this);
			return true;
		}
		
		return false;
	}
	@Override
	public String getname(){
		return "Knight";
	}
	public String toString(){
		if(isWhite){
			return "wN";
		}
		return "bN";
	}
}
