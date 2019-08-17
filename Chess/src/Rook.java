import javax.swing.ImageIcon;

//A Rook Piece
@SuppressWarnings("serial")
public class Rook extends Piece {
	//A rook
	public Rook(String color) {
		if (color == "white") {
			this.color = "white";
			icon = new ImageIcon("images/white_rook.png");	//
			setIcon(icon);									
		}
		else {
			this.color = "black";
			icon = new ImageIcon("images/black_rook.png");
			setIcon(icon);
		}
	}
	//Returns the color
	@Override
	public String getColor()  {
		return this.color;
	}
	//Returns the type of piece
	@Override
	public String getType()  {
		return "rook";
	}
}
