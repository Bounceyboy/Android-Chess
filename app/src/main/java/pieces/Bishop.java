package pieces;

import chess.Board;
import chess.Space;

/**
 * @author Jason Holley Rituraj Kumar
 *
 */
public class Bishop extends Piece {

	public Bishop(int x, int y, boolean isWhite) {
		super(x, y, isWhite);
	}

	@Override
	public boolean move(Space moveTo, Board game) {
		if(moveTo.getX() == this.x && moveTo.getY() == this.y){
			return false;
		}
		if (moveTo.getX()!=this.x && moveTo.getY()!=this.y){
			//diag left up (-x,-y)
			if(moveTo.getY()<this.y && moveTo.getX()<this.x){
				for(int i=this.x,j=this.y;i!=moveTo.getX() && j!=moveTo.getY();i--,j--){
					
					if(game.Spaces[i][j].isOccupied()){
						return false;
					}
				}
				if(moveTo.isOccupied()){
					if(moveTo.getPiece().isWhite!=this.isWhite){
						this.y=moveTo.getY();
						this.x=moveTo.getX();
						moveTo.storePiece(this);
						return true;
					}
				}
				else{
					this.y=moveTo.getY();
					this.x=moveTo.getX();
					moveTo.storePiece(this);
					return true;
				}
			}
			//diag right up (+x,-y)
			if(moveTo.getY()<this.y && moveTo.getX()>this.x){
				for(int i=this.x,j=this.y;i!=moveTo.getX() && j!=moveTo.getY();i++,j--){
					
					if(game.Spaces[i][j].isOccupied()){
						return false;
					}
				}
				if(moveTo.isOccupied()){
					if(moveTo.getPiece().isWhite!=this.isWhite){
						this.y=moveTo.getY();
						this.x=moveTo.getX();
						moveTo.storePiece(this);
						return true;
					}
				}
				else{
					this.y=moveTo.getY();
					this.x=moveTo.getX();
					moveTo.storePiece(this);
					return true;
				}
			}
			//diag left down (-x,+y)
			if(moveTo.getY()>this.y && moveTo.getX()<this.x){
				for(int i=this.x,j=this.y;i!=moveTo.getX() && j!=moveTo.getY();i--,j++){
					
					if(game.Spaces[i][j].isOccupied()){
						return false;
					}
				}
				if(moveTo.isOccupied()){
					if(moveTo.getPiece().isWhite!=this.isWhite){
						this.y=moveTo.getY();
						this.x=moveTo.getX();
						moveTo.storePiece(this);
						return true;
					}
				}
				else{
					this.y=moveTo.getY();
					this.x=moveTo.getX();
					moveTo.storePiece(this);
					return true;
				}
			}
			//diag right down (+x,+y)
			if(moveTo.getY()>this.y && moveTo.getX()>this.x){
				for(int i=this.x,j=this.y;i!=moveTo.getX() && j!=moveTo.getY();i++,j++){
					
					if(game.Spaces[i][j].isOccupied()){
						return false;
					}
				}
				if(moveTo.isOccupied()){
					if(moveTo.getPiece().isWhite!=this.isWhite){
						this.y=moveTo.getY();
						this.x=moveTo.getX();
						moveTo.storePiece(this);
						return true;
					}
				}
				else{
					this.y=moveTo.getY();
					this.x=moveTo.getX();
					moveTo.storePiece(this);
					return true;
				}
			}
		}
		
		return false;
	}
	@Override
	public String getname(){
		return "Bishop";
	}
	public String toString(){
		if(isWhite){
			return "wB";
		}
		return "bB";
	}

}
