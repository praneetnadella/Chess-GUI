import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JFrame implements MouseListener {
	
	private static Board board = new Board();
	private static ChessClock clock = new ChessClock();
	public static Cell previousCell = null;											//What cell was clicked before
	public static Cell sourceCell = null;											//What is clicked?
	public static String pieceColor = "";
	public static String whoseMove = "";
	public static boolean active = false;											//Was a piece clicked before
	public static boolean capture = false;											//Determine whether to take to not
	
	//Game object
	public Game() {
	  //Initializes the game 
		setLayout(new GridBagLayout());												//The JFrame uses a GridBagLayout
		//Sets Board
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		add(board, c);
		//Sets Clock
		c.insets = new Insets(0,50,0,0);  //top padding
		c.gridx = 9;
		c.gridy = 0;
		add(clock, c);
		board.initializeChessBoard();
		whoseMove = "white";														//Sets whose move
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) board.cell[i][j].addMouseListener(this);
		}		
		
	}
	Color selected;
	//Mouse Clicked/Allows pieces to move when the user clicks the mouse.
	@Override
	public void mouseClicked(MouseEvent e) {
		sourceCell = (Cell) e.getSource();											//Get the cell which was clicked
		
		if(!sourceCell.isOpen()) pieceColor = sourceCell.getPiece().getColor();		//If not open, get piece color
		
		if(!active && !sourceCell.isOpen()) {										//If the user choice is the user's first choice
			active = true;
			previousCell = sourceCell;
			selected = previousCell.getBackground();
			previousCell.setBackground(new Color(175, 0, 0));
			if (previousCell.getPiece().getColor() != whoseMove) clear();
		}
		else if (active && previousCell.getPiece().getColor() == whoseMove) {
			String pieceType = previousCell.getPiece().getType();
			String pieceColor = previousCell.getPiece().getColor();
			//-----------------------------Pawn Movement-----------------------------
			if (pieceType.equalsIgnoreCase("pawn")) {
				int move = 1; int preCell = 6;
				if (pieceColor.equalsIgnoreCase("black")) { move = -1; preCell = 1; }	//This is what allows for the double move as the first move for a pawn
				//Move
				if(previousCell.getRow() == preCell && previousCell.getRow() == sourceCell.getRow() + move + move && previousCell.getCol() == sourceCell.getCol()) {
					movePiece();
				}
				else if(previousCell.getRow() == sourceCell.getRow() +  move && previousCell.getCol() == sourceCell.getCol()) {
					movePiece();
				}
				//Kill
				else if(!sourceCell.isOpen() && previousCell.getRow() == sourceCell.getRow() + move && Math.abs(previousCell.getCol() - sourceCell.getCol()) == 1) {
					capture = true;
					movePiece();
				}
				else{clear();}				
			}
			//-----------------------------Knight Movement-----------------------------
			else if(pieceType.equalsIgnoreCase("knight")) { 

				//Move/Kill
				if((previousCell.getRow() == sourceCell.getRow() + 1 && previousCell.getCol() == sourceCell.getCol() + 2)
						|| (previousCell.getRow() == sourceCell.getRow() + 1 && previousCell.getCol() == sourceCell.getCol() - 2)
						|| (previousCell.getRow() == sourceCell.getRow() - 1 && previousCell.getCol() == sourceCell.getCol() + 2)
						|| (previousCell.getRow() == sourceCell.getRow() - 1 && previousCell.getCol() == sourceCell.getCol() - 2)
						|| (previousCell.getRow() == sourceCell.getRow() + 2 && previousCell.getCol() == sourceCell.getCol() + 1)
						|| (previousCell.getRow() == sourceCell.getRow() - 2 && previousCell.getCol() == sourceCell.getCol() + 1)
						|| (previousCell.getRow() == sourceCell.getRow() + 2 && previousCell.getCol() == sourceCell.getCol() - 1)
						|| (previousCell.getRow() == sourceCell.getRow() - 2 && previousCell.getCol() == sourceCell.getCol() - 1)
				) {
					capture = true;
					movePiece();
				}
				else{clear();}
			}
			//-----------------------------Rook Movement-----------------------------
			else if(pieceType.equalsIgnoreCase("rook")) {

				if(previousCell.getRow() == sourceCell.getRow() || previousCell.getCol() == sourceCell.getCol()) {
					capture = true;
					movePiece();
				}
				else{clear();}
			}
			//-----------------------------Bishop Movement-----------------------------
			else if(pieceType.equalsIgnoreCase("bishop")) {

				//Move/Kill
				int rowOffset = previousCell.getRow() - sourceCell.getRow();
				int colOffset = previousCell.getCol() - sourceCell.getCol();
				if(Math.abs(rowOffset) == Math.abs(colOffset)) {
					capture = true;
					movePiece();
				}
				else{clear();}
			}
			//-----------------------------Queen Movement-----------------------------
			else if(pieceType.equalsIgnoreCase("queen")) {

				//Move/Kill
				int rowOffset = previousCell.getRow() - sourceCell.getRow();
				int colOffset = previousCell.getCol() - sourceCell.getCol();
				if(Math.abs(rowOffset) == Math.abs(colOffset) 
						|| previousCell.getRow() == sourceCell.getRow()
						|| previousCell.getCol() == sourceCell.getCol()
				) {
					capture = true;
					movePiece();
				}
				else{clear();}
			}
			//-----------------------------King Movement-----------------------------
			else if(pieceType.equalsIgnoreCase("king")) {

				//Move/Kill
				int rowOffset = previousCell.getRow() - sourceCell.getRow();
				int colOffset = previousCell.getCol() - sourceCell.getCol();
				if(Math.abs(rowOffset) == 1 ||  Math.abs(colOffset) == 1)  {
					capture = true;
					movePiece();
				}
				//White Castle
				else if(pieceColor == "white" && previousCell.getRow() == 7 && previousCell.getCol() == 4) {
					//King Side
					if (sourceCell.getRow() == 7 && sourceCell.getCol() == 6 && 
							board.cell[7][5].isOpen() && board.cell[7][6].isOpen() && board.cell[7][7].getPiece().getType() == "rook" && board.cell[7][7].getPiece().getColor() == "white") {
						movePiece();
						sourceCell = board.cell[7][5];
						previousCell = board.cell[7][7];
						whoseMove = "white";
						ChessClock.isWhite = true;
						selected = board.cell[7][5].getBackground();
						movePiece();
					}
					//Queen Side
					else if (sourceCell.getRow() == 7 && sourceCell.getCol() == 2 && 
							board.cell[7][1].isOpen() && board.cell[7][2].isOpen() && board.cell[7][3].isOpen() && board.cell[7][0].getPiece().getType() == "rook" && board.cell[7][0].getPiece().getColor() == "white") {
						movePiece();
						sourceCell = board.cell[7][3];
						previousCell = board.cell[7][0];
						whoseMove = "white";
						ChessClock.isWhite = true;
						movePiece();
					}
				}
				//Black Castle
				else if(pieceColor == "black" && previousCell.getRow() == 0 && previousCell.getCol() == 4) {
					//King Side
					if (sourceCell.getRow() == 0 && sourceCell.getCol() == 6 && 
							board.cell[0][5].isOpen() && board.cell[0][6].isOpen() && board.cell[0][7].getPiece().getType() == "rook" && board.cell[0][7].getPiece().getColor() == "black") {
						movePiece();
						sourceCell = board.cell[0][5];
						previousCell = board.cell[0][7];
						whoseMove = "black";
						ChessClock.isWhite = false;
						selected = board.cell[0][5].getBackground();
						movePiece();
					}
					//Queen Side
					else if (sourceCell.getRow() == 0 && sourceCell.getCol() == 2 && 
							board.cell[0][1].isOpen() && board.cell[0][2].isOpen() && board.cell[0][3].isOpen() && board.cell[0][0].getPiece().getType() == "rook" && board.cell[0][0].getPiece().getColor() == "black") {
						movePiece();
						sourceCell = board.cell[0][3];
						previousCell = board.cell[0][0];
						whoseMove = "black";
						ChessClock.isWhite = false;
						movePiece();
					}
				}
			}
			else{clear();}
		}
		
	}

	//Function moves the pieces
	public void movePiece() {
		System.out.println("Piece was moved");
		//Switches the chess clock
		if (ChessClock.started == false) {ChessClock.started = true; ChessClock.counter.start(); }
		if (ChessClock.isWhite) ChessClock.isWhite = false;
		else ChessClock.isWhite = true;
		if(capture && !sourceCell.isOpen() && !previousCell.getPiece().getColor().equalsIgnoreCase(pieceColor)) {
			sourceCell.remove(sourceCell.getPiece());									//Remove Piece
			sourceCell.setPiece(null);													//Set moved piece null
			capture = false;
		}
		previousCell.setBackground(selected);
		sourceCell.add(previousCell.getPiece());										//Adds piece
		sourceCell.setPiece(previousCell.getPiece());									//Sets piece
		sourceCell.revalidate();
		board.repaint();																//Refreshes the board
		//Lines 137 to 143 set the variables for the affected cells
		active = false;		
		previousCell.setStatus(true);												
		previousCell.setPiece(null);
		sourceCell.setStatus(false);
		previousCell = null;
		if (whoseMove == "white") whoseMove = "black";
		else whoseMove = "white";
	}
	
	public void clear() { 																//Clear variables for next move 
		System.out.println("Piece was not moved");
		previousCell.setBackground(selected);
		active = false;
		capture = false;
		previousCell = null;
		sourceCell = null;
	}

	public static void main(String[] args) {	
		JFrame frame = new Game();
		frame.setSize(800, 600);														//Size of the GUI Frame
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);					//When user exits
		frame.setVisible(true);
		frame.setResizable(isDefaultLookAndFeelDecorated());
	}
	
	//To make sure user input is a priority
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}

