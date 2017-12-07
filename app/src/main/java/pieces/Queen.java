package pieces;

import chess.Board;
import chess.Space;
/**
 * 
 * @author Rituraj Kumar
 *
 */
public class Queen extends Piece{

	public Queen(int x, int y, boolean isWhite) {
		super(x, y, isWhite);
	}

	@Override
	public boolean move(Space moveTo, Board game) {
		
		if(moveTo.getX() == this.x && moveTo.getY() == this.y){
			return false;
		}
		//rook copy		
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
						return true;
					}
				}
				else{
					this.y=moveTo.getY();
					moveTo.storePiece(this);
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
						return true;
					}
				}
				else{
					this.y=moveTo.getY();
					moveTo.storePiece(this);
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
						return true;
					}
				}
				else{
					this.x=moveTo.getX();
					moveTo.storePiece(this);
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
						return true;
					}
				}
				else{
					this.x=moveTo.getX();
					moveTo.storePiece(this);
					return true;
				}
			}
		}
		
		//bishop copy
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
		return "Queen";
	}
	public String toString(){
		if(isWhite){
			return "wQ";
		}
		return "bQ";
	}
}
