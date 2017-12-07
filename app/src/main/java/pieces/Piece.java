package pieces;

import chess.Board;
import chess.Space;

/**
 * @author Jason Holley
 * 
 */
public class Piece {
	protected int x,y;
	protected boolean isWhite;	
	
	Piece (int x, int y, boolean isWhite){
		this.x = x;
		this.y = y;
		this.isWhite = isWhite;
	}
	
	//no need for out of bounds checking anymore because this takes a space as an argument and not a coordinate
	/**
	 * Make sure that you remove the piece from the space you're moving from before using this move method.
	 * <p>Also be sure to not be moving the piece to the same space because there is no check for that.
	 * 
	 * @param moveTo	The space to attempt moving the piece to.
	 * @return			False if illegal move, true if legal move (for the piece. This will overwrite a piece existing in the moveTo spot, if that piece is the other players piece).
	 */
	public boolean move(Space moveTo, Board game) {
		return false;
	};
	public String getname(){
		return "name";
	};
	public boolean check(Board game){
		return false;
	};
	public int getX(){
		return this.x;
	};
	public void setX(int x){
		this.x=x;
	};
	public void setY(int y){
		this.y=y;
	};
	public int getY(){
		return this.y;
	};
	public boolean getWhite(){
		return this.isWhite;
	};
	public boolean hasmoved(){
		return true;
	};
	public int moves(){
		return 0;
	};
	public void setmoves(int x){
		
	};
	public void setmove(boolean m){
		
	};
	
}
