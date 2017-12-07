package pieces;

import chess.Board;
import chess.Space;

/**
 * @author Jason Holley, Rituraj Kumar
 *
 */
public class Pawn extends Piece {
	private int moves = 0;
	public Pawn(int x, int y, boolean isWhite) {
		super(x, y, isWhite);
	}
	
	@Override
	public boolean move(Space moveTo, Board game) {
		
		if(moveTo.getX()==this.x && moveTo.getY()==this.y){
			return false;
		}
		//white moves
		if(isWhite){
			if(moveTo.isOccupied()){//diagonal attack
				if(moveTo.getX()==this.x-1 || moveTo.getX()==this.x+1){
					
					if(moveTo.getY()==this.y-1){
						this.x=moveTo.getX();
						this.y=moveTo.getY();
						moveTo.storePiece(this);
						this.moves++;
						return true;
					}
				}
			}
			if(moveTo.getX()==this.x){
				//2 space move
				if(moveTo.getY()==(this.y-2)){
					if(moves==0){
						if(game.Spaces[this.x][this.y-1].isOccupied() || game.Spaces[this.x][this.y-2].isOccupied()){
							return false;
						}
						else{
							this.x=moveTo.getX();
							this.y=moveTo.getY();
							moveTo.storePiece(this);
							this.moves=this.moves+2;
							return true;
						}
					}
					else{
						return false;
					}
				}
				
				else if(moveTo.getY()==(this.y-1)){
					if(game.Spaces[this.x][this.y-1].isOccupied()){
						return false;
					}
					else{
						this.x=moveTo.getX();
						this.y=moveTo.getY();
						moveTo.storePiece(this);
						this.moves++;
						return true;
					}
				}
			}
			
		}
		//black moves
		else{
			if(moveTo.isOccupied()){//diagonal attack
				if(moveTo.getX()==this.x-1 || moveTo.getX()==this.x+1){
					if(moveTo.getY()==this.y+1){
						this.x=moveTo.getX();
						this.y=moveTo.getY();
						moveTo.storePiece(this);
						this.moves++;
						return true;
					}
				}
			}
			if(moveTo.getX()==this.x){
				//2 space move
				if(moveTo.getY()==(this.y+2)){
					if(moves==0){
						if(game.Spaces[this.x][this.y+1].isOccupied() || game.Spaces[this.x][this.y+2].isOccupied()){
							return false;
						}
						else{
							this.x=moveTo.getX();
							this.y=moveTo.getY();
							moveTo.storePiece(this);
							this.moves=this.moves+2;
							return true;
						}
					}
					else{
						return false;
					}
				}
				
				else if(moveTo.getY()==(this.y+1)){
					if(game.Spaces[this.x][this.y+1].isOccupied()){
						return false;
					}
					else{
						this.x=moveTo.getX();
						this.y=moveTo.getY();
						moveTo.storePiece(this);
						this.moves++;
						return true;
					}
				}
			}
		}
		return false;
	}
	@Override
	public String getname(){
		return "Pawn";
	}
	@Override
	public int moves(){
		return this.moves;
	}
	@Override
	public void setmoves(int x){
		this.moves=x;
	}
	public String toString(){
		if(isWhite){
			return "wp";
		}
		return "bp";
	}
}
