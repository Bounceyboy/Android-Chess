package chess;



import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

/**
 * Contains the board.
 * 
 * @author Jason Holley
 *
 */
public class Board {
	public Space[][] Spaces = new Space[8][8];
	Piece wk,bk;
	private boolean stillplaying=true;
	private int draw=0;
	private boolean enpassed=false;
	/**
	 * Builds the board and places all pieces in their starting locations.
	 */
	public Board(){
		for (int x = 0; x<8; x++) {
			for (int y = 0; y<8; y++) {
				Spaces[x][y] = new Space(x,y);
			}
		}
		
		//add rooks
		Spaces[0][0].storePiece(new Rook(0, 0, false));
		Spaces[7][0].storePiece(new Rook(7, 0, false));
		Spaces[0][7].storePiece(new Rook(0, 7, true));
		Spaces[7][7].storePiece(new Rook(7, 7, true));
		
		//add Knights
		Spaces[1][0].storePiece(new Knight(1, 0, false));
		Spaces[6][0].storePiece(new Knight(6, 0, false));
		Spaces[1][7].storePiece(new Knight(1, 7, true));
		Spaces[6][7].storePiece(new Knight(6, 7, true));
		
		//add Bishops
		Spaces[2][0].storePiece(new Bishop(2, 0, false));
		Spaces[5][0].storePiece(new Bishop(5, 0, false));
		Spaces[2][7].storePiece(new Bishop(2, 7, true));
		Spaces[5][7].storePiece(new Bishop(5, 7, true));
		
		//add Queens
		Spaces[3][0].storePiece(new Queen(3, 0, false));
		Spaces[3][7].storePiece(new Queen(3, 7, true));
		
		//add Kings
		Spaces[4][0].storePiece(new King(4, 0, false));
		Spaces[4][7].storePiece(new King(4, 7, true));
		bk = Spaces[4][0].getPiece();
		wk = Spaces[4][7].getPiece();
		//add Pawns
		for(int a = 0; a<8; a++) {
			Spaces[a][1].storePiece(new Pawn(a, 1, false));
			Spaces[a][6].storePiece(new Pawn(a, 6, true));
		}
		
	}
	
	/**
	 * 
	 * Checks to see if game is still running.
	 */
	public boolean getgame(){
		return stillplaying;
	}
	/**
	 * Set value for when game ends
	 * @param boolean still		boolean value to set if game is being run.
	 */
	private void setgame(boolean still){
		stillplaying=still;
	}
	/**
	 * Moves the piece to a new location
	 * @param String arr[] 		Array of arguments 
	 */
	public boolean move(String arr[], String player){
		/**
		 * if the first value entered is larger than 2 than the player entered resign otherwise illegal move
		 */
		if(arr.length==1){
		if(arr[0].length()>2){
			if(arr[0].equals("resign")){
				setgame(false);
				System.out.println(player+ " wins");
				return true;
			}
			else if(draw==1){
				if(arr[0].equals("draw")){
					setgame(false);
					return true;
				}
			}
			else{
				System.out.println("Illegal move, try again");
				return false;
			}
		}
		
		if(arr[1].length()>2){
			System.out.println("Illegal move, try again");
			return false;
		}
		}
		if(arr.length>3){
			System.out.println("Illegal move, try again");
			return false;
		}
		if(draw==1){
			draw=0;
		}
		String promotion="";
		int pc[]=parseboard(arr[0]);
		if(pc[0]==-1||pc[1]==-1){
			System.out.println("Illegal move, try again");
			return false;
			
		}
		int local[]=parseboard(arr[1]);
		if(local[0]==-1||local[1]==-1){
			System.out.println("Illegal move, try again");
			return false;
		}
		/**
		 * if argument size is 3 draw or promotion
		 */
		if(arr.length==3){
			if(arr[2].equals("draw?")){
				
				draw=1;
			}
			else if(arr[2].length()==1 && (arr[2].equals("N")|| arr[2].equals("B")|| arr[2].equals("R")|| arr[2].equals("Q"))){
				promotion+=arr[2];
			}
		}
		
		if(Spaces[pc[0]][pc[1]].getPiece()!=null && ((Spaces[pc[0]][pc[1]].getPiece().getWhite() && player.equals("Black")) || (!Spaces[pc[0]][pc[1]].getPiece().getWhite() && player.equals("White")))){
			//castle check
			if(Math.abs(pc[0]-local[0])>1 && Spaces[pc[0]][pc[1]].getPiece().getname().equals("King")){
				boolean ret=castle(Spaces[pc[0]][pc[1]],Spaces[local[0]][local[1]]);
				if(ret){
					return ret;
				}
			}
			//en passant
			if(Spaces[pc[0]][pc[1]].getPiece().getname().equals("Pawn") && (Math.abs(pc[0]-local[0])==1 && Math.abs(pc[1]-local[1])==1)){
				boolean ret=enpass(Spaces[pc[0]][pc[1]],Spaces[local[0]][local[1]]);
				if(ret){
					return true;
				}
			}
			Piece temp = Spaces[pc[0]][pc[1]].emptySpace();
			boolean moved=temp.move(Spaces[local[0]][local[1]], this);
			if(temp.getname().equals("Pawn") && temp.moves()==2){
				enpassed=true;
			}
			else{
				enpassed=false;
			}
			if(moved==false){
				System.out.println("Illegal move, try again");
				Spaces[pc[0]][pc[1]].storePiece(temp);
				return false;
			}
			if(wk.check(this) ||bk.check(this)){
				if(checkmate(wk)){
					setgame(false);
					System.out.println("Black wins");
					return true;
				}
				if(checkmate(bk)){
					setgame(false);
					System.out.println("White wins");
					return true;
				}
				System.out.println("Check");
			}
			//prmotion check
			if(temp.getname().equals("Pawn")){
				promotion(temp, promotion);
			}
		}
		else{
			System.out.println("Illegal move, try again");
			return false;
		}
		return true;
	}
	
	private int[] parseboard(String xy){
		char letter =xy.charAt(0);
		int num=Character.getNumericValue(xy.charAt(1));
		int ret[]=new int[2];
		switch(letter){
		case 'a':ret[0]=0;break;
		case 'b':ret[0]=1;break;
		case 'c':ret[0]=2;break;
		case 'd':ret[0]=3;break;
		case 'e':ret[0]=4;break;
		case 'f':ret[0]=5;break;
		case 'g':ret[0]=6;break;
		case 'h':ret[0]=7;break;
		default:ret[0]=-1;break;
		}
		if((8-num>8) || (8-num)<0){
			ret[1]=-1;
		}
		else{
			ret[1]=8-num;
		}
		return ret;
	}
	/**
	 * 
	 * @param Piece k	King
	 * @return true if checkmate, false if not
	 */
	private boolean checkmate(Piece king){
		for(int j=0;j<8;j++){
			for(int i=0;i<8;i++){
				if(Spaces[i][j].isOccupied() && Spaces[i][j].getPiece().getWhite()==king.getWhite()){
					Piece moving=Spaces[i][j].emptySpace();
					int x=moving.getX();
					int y=moving.getY();
					boolean hasmoved=moving.hasmoved();
					int moves=moving.moves();
					for(int l=0;l<8;l++){
						for(int k=0;k<8;k++){
							if(Spaces[k][l].isOccupied() && 
									Spaces[k][l].getPiece().getWhite()!=moving.getWhite()){
								
								Piece temp=Spaces[k][l].getPiece();
								
								if(moving.move(Spaces[k][l], this)){
								if(!king.check(this)){
									Spaces[k][l].emptySpace();
									Spaces[k][l].storePiece(temp);
									Spaces[i][j].storePiece(moving);
									moving.setX(x);
									moving.setY(y);
									moving.setmove(hasmoved);
									moving.setmoves(moves);
									return false;
								}
								else{
									Spaces[k][l].emptySpace();
									Spaces[k][l].storePiece(temp);
									Spaces[i][j].storePiece(moving);
									moving.setX(x);
									moving.setY(y);
									moving.setmove(hasmoved);
									moving.setmoves(moves);
								}
								}
								else{
									Spaces[k][l].storePiece(temp);
									Spaces[i][j].storePiece(moving);
								}
							}
							else if(!Spaces[k][l].isOccupied()){
								if(moving.move(Spaces[k][l], this)){
								if(!king.check(this)){
									king.check(this);
									Spaces[k][l].emptySpace();
									Spaces[i][j].storePiece(moving);
									moving.setX(x);
									moving.setY(y);
									moving.setmove(hasmoved);
									moving.setmoves(moves);
									return false;
								}
								else{
									Spaces[k][l].emptySpace();
									Spaces[i][j].storePiece(moving);
									moving.setX(x);
									moving.setY(y);
									moving.setmove(hasmoved);
									moving.setmoves(moves);
								
								}
								}
								else{
									Spaces[i][j].storePiece(moving);
								}
							}
						}
					}
				}
			}
		}
		//check if any piece can block king
		//check if any piece can attack what's putting king in check
		System.out.println("Checkmate");
		return true;
	}
	/**
	 * 
	 * @param temp		Pawn piece
	 * @param promotion String containing type to promote to
	 * promotes pawn if it reaches edge of board
	 */
	private void promotion(Piece temp, String promotion){
		if(temp.getWhite() && temp.getY()==0){
			if(promotion.equals("N")){
				Spaces[temp.getX()][temp.getY()].emptySpace();
				Spaces[temp.getX()][temp.getY()].storePiece(new Knight(temp.getX(),temp.getY(),true));
				
			}
			else if(promotion.equals("R")){
				Spaces[temp.getX()][temp.getY()].emptySpace();
				Spaces[temp.getX()][temp.getY()].storePiece(new Rook(temp.getX(),temp.getY(),true));
				
			}
			else if(promotion.equals("B")){
				Spaces[temp.getX()][temp.getY()].emptySpace();
				Spaces[temp.getX()][temp.getY()].storePiece(new Bishop(temp.getX(),temp.getY(),true));
			}
			else{
				Spaces[temp.getX()][temp.getY()].emptySpace();
				Spaces[temp.getX()][temp.getY()].storePiece(new Queen(temp.getX(),temp.getY(),true));
			}
		}
		if(!temp.getWhite() && temp.getY()==7){
			if(promotion.equals("N")){
				Spaces[temp.getX()][temp.getY()].emptySpace();
				Spaces[temp.getX()][temp.getY()].storePiece(new Knight(temp.getX(),temp.getY(),false));
				
			}
			else if(promotion.equals("R")){
				Spaces[temp.getX()][temp.getY()].emptySpace();
				Spaces[temp.getX()][temp.getY()].storePiece(new Rook(temp.getX(),temp.getY(),false));
				
			}
			else if(promotion.equals("B")){
				Spaces[temp.getX()][temp.getY()].emptySpace();
				Spaces[temp.getX()][temp.getY()].storePiece(new Bishop(temp.getX(),temp.getY(),false));
			}
			else{
				Spaces[temp.getX()][temp.getY()].emptySpace();
				Spaces[temp.getX()][temp.getY()].storePiece(new Queen(temp.getX(),temp.getY(),false));
			}
		}
	}
	/**
	 * 
	 * @param k		Space where king is located
	 * @param move	Space where king is trying to move after castle
	 */
	private boolean castle(Space k, Space move){
		if(k.getY()==move.getY()){
			if(!k.getPiece().hasmoved()){
				int x=k.getX();
				
				if(move.getX()>k.getX()){
					if(Spaces[move.getX()+1][k.getY()].isOccupied() && !Spaces[move.getX()+1][k.getY()].getPiece().hasmoved() && Spaces[move.getX()+1][k.getY()].getPiece().getWhite()==k.getPiece().getWhite()){
						if(Spaces[move.getX()+1][k.getY()].getPiece().getname().equals("Rook")){
							for(int i=k.getX()+1;i<k.getX()+3;i++){
								if(!Spaces[i][k.getY()].isOccupied()){
									Piece king=Spaces[i-1][k.getY()].emptySpace();
									king.move(Spaces[i][k.getY()], this);
									king.setmove(true);
									if(king.check(this)){
										Spaces[i][k.getY()].emptySpace();
										Spaces[x][k.getY()].storePiece(king);
										king.setX(x);
										king.setmove(false);
										return false;
									}
								}
								if(Spaces[6][k.getY()].isOccupied() && Spaces[6][k.getY()].getPiece().getname().equals("King")){
										Piece rook=Spaces[move.getX()+1][k.getY()].emptySpace();
										Spaces[k.getX()+1][k.getY()].storePiece(rook);
										rook.setX(5);
										rook.setmove(true);
										return true;
								}
							}
							
						}
					}
				}
				else{
					if(Spaces[move.getX()-2][k.getY()].isOccupied() && !Spaces[move.getX()-2][k.getY()].getPiece().hasmoved() && Spaces[move.getX()-2][k.getY()].getPiece().getWhite()==k.getPiece().getWhite()){
							if(Spaces[move.getX()-2][k.getY()].getPiece().getname().equals("Rook")){
								for(int i=k.getX()-1;i>1;i--){
									if(!Spaces[i][k.getY()].isOccupied()){
										Piece king=Spaces[i+1][k.getY()].emptySpace();
										king.move(Spaces[i][k.getY()], this);
										king.setmove(true);
										if(king.check(this)){
											Spaces[i][k.getY()].emptySpace();
											Spaces[x][k.getY()].storePiece(king);
											king.setX(x);
											king.setmove(false);
											return false;
										}
									}
									if(Spaces[2][k.getY()].isOccupied() && Spaces[2][k.getY()].getPiece().getname().equals("King")){
										Piece rook=Spaces[move.getX()-2][k.getY()].emptySpace();
										Spaces[k.getX()-1][k.getY()].storePiece(rook);
										rook.setX(3);
										rook.setmove(true);
										return true;
								}
								}
								
						}
					}
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @param p	pawn for en passant
	 * @param m	location to move
	 * @return true if en passant, false if illegal
	 */
	private boolean enpass(Space p, Space m){
		if((p.getPiece().getWhite() && p.getY()==3) || (!p.getPiece().getWhite() && p.getY()==4)){
			if(Spaces[p.getX()+1][p.getY()].isOccupied() && 
					Spaces[p.getX()+1][p.getY()].getPiece().getname().equals("Pawn") &&
						Spaces[p.getX()+1][p.getY()].getPiece().getWhite()!=p.getPiece().getWhite()){
				if(Spaces[p.getX()+1][p.getY()].getPiece().moves()==2 && enpassed==true){
					Spaces[p.getX()+1][p.getY()].emptySpace();
					//where to move
					m.storePiece(p.getPiece());
					p.getPiece().setX(m.getX());
					p.getPiece().setY(m.getY());
					p.emptySpace();
					return true;
				}
			}
			else if(Spaces[p.getX()-1][p.getY()].isOccupied() &&
						Spaces[p.getX()-1][p.getY()].getPiece().getname().equals("Pawn") &&
							Spaces[p.getX()-1][p.getY()].getPiece().getWhite()!=p.getPiece().getWhite()){
					if(Spaces[p.getX()-1][p.getY()].getPiece().moves()==2 && enpassed==true){
						Spaces[p.getX()-1][p.getY()].emptySpace();
						//where to move
						m.storePiece(p.getPiece());
						p.getPiece().setX(m.getX());
						p.getPiece().setY(m.getY());
						p.emptySpace();
						return true;
					}
			}
		}
		return false;
	}
}
