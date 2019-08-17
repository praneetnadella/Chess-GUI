import javax.swing.ImageIcon;
	
//The Queen
@SuppressWarnings("serial")
public class Queen extends Piece {
	//A queen
	public Queen(String color) {
		if (color == "white") {
			this.color = "white";
			icon = new ImageIcon("images/white_queen.png");
			setIcon(icon);
		}
		else {
			this.color = "black";
			icon = new ImageIcon("images/black_queen.png");
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
		return "queen";
	}
}
