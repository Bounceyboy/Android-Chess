package chess;

import pieces.Piece;

/**
 * These serve a double purpose of making sure moves don't go out of bounds and also helping read piece collisions
 * 
 * @author Jason Holley
 *
 */
public class Space {
	private int x;
	private int y;
	Piece piece;
	
	/**
	 * A space on a chessboard, contains the coordinate of the space and the piece it contains, or null if no piece.
	 * @param x			The x coordinate.
	 * @param y			The y coordinate.
	 */
	public Space(int x, int y) {
		this.setX(x);
		this.setY(y);
		piece = null;
	}
	
	/**
	 * @return		True if occupied, false if unoccupied.
	 */
	public boolean isOccupied() {
		if(this.piece == null)
			return false;
		return true;
	}
	
	/**
	 * @param space		The space you're getting the piece from.
	 * @return			The piece in that space.
	 */
	public Piece getPiece() {
		return this.piece;
	}
	
	/**
	 * @return			The piece that was removed (for the purposes of the move() functions).
	 */
	public Piece emptySpace() {
		if(this.piece == null)
			return null;
		Piece contained = this.piece;
		this.piece = null;
		return contained;
	}
	
	public void storePiece(Piece piece) {
		this.piece = piece;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
