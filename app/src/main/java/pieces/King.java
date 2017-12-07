package pieces;

import chess.Board;
import chess.Space;

/**
 * There's no castling checking in this yet because it needs information from the whole board.
 * This needs to be handled separately.
 * 
 * @author Jason Holley Rituraj Kumar
 *
 */
public class King extends Piece {
	public boolean hasMoved = false;

	public King(int x, int y, boolean isWhite) {
		super(x, y, isWhite);

	}

	@Override
	public boolean move(Space moveTo, Board game) {
		if(moveTo.getX()==this.x && moveTo.getY()==this.y){
			return false;
		}
		
		if(Math.abs(moveTo.getX() - this.x) > 1 || Math.abs(moveTo.getY() - this.y) > 1){
			return false;
		}
		if(moveTo.isOccupied()){
			if(moveTo.getPiece().isWhite==this.isWhite){
				return false;
			}
		}
		this.x=moveTo.getX();
		this.y=moveTo.getY();
		this.hasMoved = true;
		moveTo.storePiece(this);
		
		return true;
	}
	/**
	 * @param Board game	The current game being played.
	 * Views if king is in check 
	 */
	public boolean check(Board game){
		
		//x right
		for(int i=this.x;i<8;i++){
			if(game.Spaces[i][this.y].isOccupied() && game.Spaces[i][this.y].getPiece()!=this){
				if(game.Spaces[i][this.y].getPiece().isWhite!=this.isWhite){
					if(i==this.x+1 && game.Spaces[i][this.y].getPiece().getname().equals("King")){
						return true;
					}
					if(game.Spaces[i][this.y].getPiece().getname().equals("Queen") ||
							game.Spaces[i][this.y].getPiece().getname().equals("Rook")){
						return true;
					}
					else{
						break;
					}
				}
				else{
					break;
				}
			}
		}
		//x left
		for(int i=this.x;i>=0;i--){
			if(game.Spaces[i][this.y].isOccupied()&& game.Spaces[i][this.y].getPiece()!=this){
				if(game.Spaces[i][this.y].getPiece().isWhite!=this.isWhite){
					if(i==this.x-1 && game.Spaces[i][this.y].getPiece().getname().equals("King")){
						return true;
					}
					if(game.Spaces[i][this.y].getPiece().getname().equals("Queen") ||
							game.Spaces[i][this.y].getPiece().getname().equals("Rook")){
						return true;
					}
					else{
						break;
					}
				}
				else{
					break;
				}
			}
		}
		//y up
		for(int i=this.y;i>=0;i--){
			if(game.Spaces[this.x][i].isOccupied()&& game.Spaces[this.x][i].getPiece()!=this){
				if(game.Spaces[this.x][i].getPiece().isWhite!=this.isWhite){
					if(i==this.y-1 && game.Spaces[this.x][i].getPiece().getname().equals("King")){
						return true;
					}
					if(game.Spaces[this.x][i].getPiece().getname().equals("Queen") ||
							game.Spaces[this.x][i].getPiece().getname().equals("Rook")){
						return true;
					}
					else{
						break;
					}
				}
				else{
					break;
				}
			}
		}
		//y down
		for(int i=this.y;i<8;i++){
			if(game.Spaces[this.x][i].isOccupied()&& game.Spaces[this.x][i].getPiece()!=this){
				if(game.Spaces[this.x][i].getPiece().isWhite!=this.isWhite){
					if(i==this.y+1 && game.Spaces[this.x][i].getPiece().getname().equals("King")){
						return true;
					}
					if(game.Spaces[this.x][i].getPiece().getname().equals("Queen") ||
							game.Spaces[this.x][i].getPiece().getname().equals("Rook")){
						return true;
					}
					else{
						break;
					}
				}
				else{
					break;
				}
			}
		}
		//diag left up (-x,-y)
		for(int i=this.x,j=this.y;i>=0 && j>=0;i--,j--){
			if(game.Spaces[i][j].isOccupied() && game.Spaces[i][j].getPiece()!=this){
				if(game.Spaces[i][j].getPiece().isWhite!=this.isWhite){
					if((i==this.x-1 && j==this.y-1) && game.Spaces[i][j].getPiece().getname().equals("King")){
						return true;
					}
					if(game.Spaces[i][j].getPiece().getname().equals("Queen") ||
							game.Spaces[i][j].getPiece().getname().equals("Bishop")){
						return true;
					}
					else{
						break;
					}
				}
				else{
					break;
				}
			}
		}
		//diag right up (+x,-y)
		for(int i=this.x,j=this.y;i<8 && j>=0;i++,j--){
			if(game.Spaces[i][j].isOccupied() && game.Spaces[i][j].getPiece()!=this){
				if(game.Spaces[i][j].getPiece().isWhite!=this.isWhite){
					if((i==this.x+1 && j==this.y-1) && game.Spaces[i][j].getPiece().getname().equals("King")){
						return true;
					}
					if(game.Spaces[i][j].getPiece().getname().equals("Queen") ||
							game.Spaces[i][j].getPiece().getname().equals("Bishop")){
						return true;
					}
					else{
						break;
					}
				}
				else{
					break;
				}
			}
		}
		//diag left down (-x,+y)
		for(int i=this.x,j=this.y;i>=0 && j<8;i--,j++){
			if(game.Spaces[i][j].isOccupied() && game.Spaces[i][j].getPiece()!=this){
				if(game.Spaces[i][j].getPiece().isWhite!=this.isWhite){
					if((i==this.x-1 && j==this.y+1) && game.Spaces[i][j].getPiece().getname().equals("King")){
						return true;
					}
					if(game.Spaces[i][j].getPiece().getname().equals("Queen") ||
							game.Spaces[i][j].getPiece().getname().equals("Bishop")){
						return true;
					}
					else{
						break;
					}
				}
				else{
					break;
				}
			}
		}
		//diag right down (+x,+y)
		for(int i=this.x,j=this.y;i<8 && j<8;i++,j++){
			if(game.Spaces[i][j].isOccupied() && game.Spaces[i][j].getPiece()!=this){
				if(game.Spaces[i][j].getPiece().isWhite!=this.isWhite){
					if((i==this.x+1 && j==this.y+1) && game.Spaces[i][j].getPiece().getname().equals("King")){
						return true;
					}
					if(game.Spaces[i][j].getPiece().getname().equals("Queen") ||
							game.Spaces[i][j].getPiece().getname().equals("Bishop")){
						return true;
					}
					else{
						break;
					}
				}
				else{
					break;
				}
			}
		}
		//black pawn check
		if(this.isWhite){
			if(this.y!=0){
				if(this.x==0){
					if(game.Spaces[this.x+1][this.y-1].isOccupied()&&game.Spaces[this.x+1][this.y-1].getPiece().toString().equals("bp")){
						return true;
					}
				}
				else if(this.x==7){
					if(game.Spaces[this.x-1][this.y-1].isOccupied() && game.Spaces[this.x-1][this.y-1].getPiece().toString().equals("bp")){
						return true;
					}
				}
				else{
					if((game.Spaces[this.x+1][this.y-1].isOccupied() && game.Spaces[this.x+1][this.y-1].getPiece().toString().equals("bp")) ||
							(game.Spaces[this.x-1][this.y-1].isOccupied() && game.Spaces[this.x-1][this.y-1].getPiece().toString().equals("bp"))){
						return true;
					}
				}
			}
			
		}
		//white pawn check
		else{
			if(this.y!=7){
				if(this.x==0){
					if(game.Spaces[this.x+1][this.y+1].isOccupied()&&game.Spaces[this.x+1][this.y+1].getPiece().toString().equals("wp")){
						return true;
					}
				}
				else if(this.x==7){
					if(game.Spaces[this.x-1][this.y+1].isOccupied()&&game.Spaces[this.x-1][this.y+1].getPiece().toString().equals("wp")){
						return true;
					}
				}
				else{
					if((game.Spaces[this.x+1][this.y+1].isOccupied()&&game.Spaces[this.x+1][this.y+1].getPiece().toString().equals("wp")) ||
							(game.Spaces[this.x-1][this.y+1].isOccupied()&&game.Spaces[this.x-1][this.y+1].getPiece().toString().equals("wp"))){
						return true;
					}
				}
			}
			
		}
		//Knights
		if(this.y-2>=0){
			if(this.x-1>=0){
				if(game.Spaces[this.x-1][this.y-2].isOccupied() 
						&& game.Spaces[this.x-1][this.y-2].getPiece().isWhite!=this.isWhite 
							&&game.Spaces[this.x-1][this.y-2].getPiece().getname().equals("Knight")){
					return true;
				}
			}
			if(this.x+1<8){
				if(game.Spaces[this.x+1][this.y-2].isOccupied() 
						&& game.Spaces[this.x+1][this.y-2].getPiece().isWhite!=this.isWhite 
							&&game.Spaces[this.x+1][this.y-2].getPiece().getname().equals("Knight")){
					return true;
				}
			}
		}
		if(this.y+2<8){
			if(this.x-1>=0){
				if(game.Spaces[this.x-1][this.y+2].isOccupied() 
						&& game.Spaces[this.x-1][this.y+2].getPiece().isWhite!=this.isWhite 
							&&game.Spaces[this.x-1][this.y+2].getPiece().getname().equals("Knight")){
					return true;
				}
			}
			if(this.x+1<8){
				if(game.Spaces[this.x+1][this.y+2].isOccupied() 
						&& game.Spaces[this.x+1][this.y+2].getPiece().isWhite!=this.isWhite 
							&&game.Spaces[this.x+1][this.y+2].getPiece().getname().equals("Knight")){
					return true;
				}
			}
		}
		if(this.x-2>=0){
			if(this.y-1>=0){
				if(game.Spaces[this.x-2][this.y-1].isOccupied() 
						&& game.Spaces[this.x-2][this.y-1].getPiece().isWhite!=this.isWhite 
							&&game.Spaces[this.x-2][this.y-1].getPiece().getname().equals("Knight")){
					return true;
				}
			}
			if(this.y+1<8){
				if(game.Spaces[this.x-2][this.y+1].isOccupied() 
						&& game.Spaces[this.x-2][this.y+1].getPiece().isWhite!=this.isWhite 
							&&game.Spaces[this.x-2][this.y+1].getPiece().getname().equals("Knight")){
					return true;
				}
			}
		}
		if(this.x+2<8){
			if(this.y-1>=0){
				if(game.Spaces[this.x+2][this.y-1].isOccupied() 
						&& game.Spaces[this.x+2][this.y-1].getPiece().isWhite!=this.isWhite 
							&&game.Spaces[this.x+2][this.y-1].getPiece().getname().equals("Knight")){
					return true;
				}
			}
			if(this.y+1<8){
				if(game.Spaces[this.x+2][this.y+1].isOccupied() 
						&& game.Spaces[this.x+2][this.y+1].getPiece().isWhite!=this.isWhite 
							&&game.Spaces[this.x+2][this.y+1].getPiece().getname().equals("Knight")){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public String getname(){
		return "King";
	}
	@Override
	public boolean hasmoved(){
		return this.hasMoved;
	}
	@Override
	public void setmove(boolean a){
		this.hasMoved=a;
	}
	public String toString(){
		if(isWhite){
			return "wK";
		}
		return "bK";
	}
}
