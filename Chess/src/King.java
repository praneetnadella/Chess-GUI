import javax.swing.ImageIcon;
	
//The king
@SuppressWarnings("serial")
public class King extends Piece {
	//A king
	public King(String color) {
		if (color == "white") {
			this.color = "white";
			icon = new ImageIcon("images/white_king.png");
			setIcon(icon);
		}
		else {
			this.color = "black";
			icon = new ImageIcon("images/black_king.png");
			setIcon(icon);
		}
	}
	//Returns color
	@Override
	public String getColor()  {
		return this.color;
	}
	//Returns type
	@Override
	public String getType()  {
		return "king";
	}
}
