package chess;

//import pieces.Piece;
import java.util.Scanner;
/**
 * Main Class
 * 
 * @author Jason Holley, Rituraj Kumar
 * 
 */
public class Chess {

	
	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) {
		Board game = new Board();
		//System.out.println(game.Spaces[0][5].getPiece().toString());
		//random code for testing
		//Piece temp = game.Spaces[0][1].emptySpace();
		//temp.move(game.Spaces[1][1], game);
		Scanner scan= new Scanner(System.in);
		boolean legal=false;
		while(game.getgame()){
			/**
			 * Ask white to move
			 */
			printboard(game);
			while(legal==false){
				System.out.print("\nWhite's move: ");
				String overall=scan.nextLine();
				System.out.println();
				String arg[]= overall.split("\\s+");
				legal=game.move(arg, "Black");
			}
			legal=false;
			if(game.getgame()==false){
				break;
			}
			printboard(game);
			while(legal==false){
				System.out.print("\nBlack's move: ");
				String overall=scan.nextLine();
				System.out.println();
				String arg[]= overall.split("\\s+");
				legal=game.move(arg, "White");
			}
			legal=false;
		}
		scan.close();
		

	}
	
	/**
	 *prints the current board
	 * @param Board game 	The Board object.
	 * 
	 */
	public static void printboard(Board game){
		for(int j=0;j<game.Spaces[0].length;j++){
			for(int i=0;i<game.Spaces.length;i++){
				if(game.Spaces[i][j].isOccupied()){
				if(i!=0){
				System.out.print(" "+game.Spaces[i][j].getPiece().toString());
				}
				else{
				System.out.print(game.Spaces[i][j].getPiece().toString());
				}
				}
				else if((i%2==0 && j%2==0)||(i%2!=0 && j%2!=0)){
					if(i==0){
						System.out.print("  ");
					}
					else{
					System.out.print("   ");
					}
				}
				else{
					if(i==0){
						System.out.print("##");
					}
					else{
					System.out.print(" ##");
					}
				}
			}
			System.out.println(" "+(8-j));
		}
		System.out.println(" a  b  c  d  e  f  g  h");
	}
}
