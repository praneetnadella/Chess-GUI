import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Cell extends JPanel {
	private Piece piece; 						//What piece in the cell
	private boolean open = true;				//Cell Status (Open?)
	private int row;
	private int col;
	
	//A cell on the board
	public Cell (int row, int col) {
		this.row = row;
		this.col = col;
	}
	//Check weather the cell is open or not
	public boolean isOpen() {
		return this.open;
	}
	//Set cell status
	public void setStatus(boolean status) {
		this.open = status;
	}
	//Get cell piece
	public Piece getPiece() {
		return this.piece;
	}
	//Set new cell piece
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	//Set cell position
	public void setPosition(int row, int col) {
		this.row = row;
		this.col = col;
	}
	//Get the row
	public int getRow() {
		return this.row;
	}
	//Get the column
	public int getCol() {
		return this.col;
	}
}
