import javax.swing.ImageIcon;
	
//The pawn piece
@SuppressWarnings("serial")
public class Pawn extends Piece {
	//A pawn
	public Pawn(String color) {
		if (color == "white") {
			this.color = "white";
			icon = new ImageIcon("images/white_pawn.png");
			setIcon(icon);
		}
		else {
			this.color = "black";
			icon = new ImageIcon("images/black_pawn.png");
			setIcon(icon);
		}
	}
	//Returns color
	@Override
	public String getColor()  {
		return this.color;
	}
	//Returns the piece type
	@Override
	public String getType()  {
		return "pawn";
	}
}
