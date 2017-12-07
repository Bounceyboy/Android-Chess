package pieces;

import chess.Board;
import chess.Space;

/**
 * @author Jason Holley Rituraj Kumar
 */
public class Rook extends Piece {
	boolean hasMovedYet = false;
	public Rook(int x, int y, boolean isWhite) {
		super(x, y, isWhite);
	}

	@Override
	public boolean move(Space moveTo,Board game) {
		
		if(moveTo.getX() == this.x && moveTo.getY() == this.y){
			return false;
		}
				
		if(moveTo.getX()!=this.x ^ moveTo.getY()!=this.y){
			//Col up
			
			if(moveTo.getY()<this.y){
				for(int i=this.y;i>moveTo.getY();i--){
					if(game.Spaces[this.x][i].isOccupied()){
						return false;
					}
				}
				if(moveTo.isOccupied()){
					if(moveTo.getPiece().isWhite!=this.isWhite){
						this.y=moveTo.getY();
						moveTo.storePiece(this);
						this.hasMovedYet=true;
						return true;
					}
				}
				else{
					this.y=moveTo.getY();
					moveTo.storePiece(this);
					this.hasMovedYet=true;
					return true;
				}
			}
			
			//Col down
			if(moveTo.getY()>this.y){
				for(int i=this.y;i<moveTo.getY();i++){
					if(game.Spaces[this.x][i].isOccupied()){
						return false;
					}
				}
				if(moveTo.isOccupied()){
					if(moveTo.getPiece().isWhite!=this.isWhite){
						this.y=moveTo.getY();
						moveTo.storePiece(this);
						this.hasMovedYet=true;
						return true;
					}
				}
				else{
					this.y=moveTo.getY();
					moveTo.storePiece(this);
					this.hasMovedYet=true;
					return true;
				}
			}
			
			//row left
			if(moveTo.getX()<this.x){
				for(int i=this.x;i>moveTo.getX();i--){
					if(game.Spaces[i][this.y].isOccupied()){
						return false;
					}
				}
				if(moveTo.isOccupied()){
					if(moveTo.getPiece().isWhite!=this.isWhite){
						this.x=moveTo.getX();
						moveTo.storePiece(this);
						this.hasMovedYet=true;
						return true;
					}
				}
				else{
					this.x=moveTo.getX();
					moveTo.storePiece(this);
					this.hasMovedYet=true;
					return true;
				}
			}
			//row right
			if(moveTo.getX()>this.x){
				for(int i=this.x;i<moveTo.getX();i++){
					if(game.Spaces[i][this.y].isOccupied()){
						return false;
					}
				}
				if(moveTo.isOccupied()){
					if(moveTo.getPiece().isWhite!=this.isWhite){
						this.x=moveTo.getX();
						moveTo.storePiece(this);
						this.hasMovedYet=true;
						return true;
					}
				}
				else{
					this.x=moveTo.getX();
					moveTo.storePiece(this);
					this.hasMovedYet=true;
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public String getname(){
		return "Rook";
	}
	@Override
	public boolean hasmoved(){
		return this.hasMovedYet;
	}
	@Override
	public void setmove(boolean m){
		this.hasMovedYet=m;
	}
	public String toString(){
		if(isWhite){
			return "wR";
		}
		return "bR";
	}
}
