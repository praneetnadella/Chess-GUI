import javax.swing.ImageIcon;

//The horse/knight piece
@SuppressWarnings("serial")
public class Knight extends Piece{
	//A knight
	public Knight(String color) {
		if (color == "white") {
			this.color = "white";
			icon = new ImageIcon("images/white_knight.png");
			setIcon(icon);
		}
		else {
			this.color = "black";
			icon = new ImageIcon("images/black_knight.png");
			setIcon(icon);
		}
	}
	//Returns the color
	@Override
	public String getColor() {
		return this.color;
	}
	//Returns the type
	@Override
	public String getType() {
		return "knight";
	}
}
