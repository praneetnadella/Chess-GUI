import javax.swing.ImageIcon;

//The bishop
@SuppressWarnings("serial")
public class Bishop extends Piece {
	//A standard bishop (Defaults to white)
	public Bishop() {
		icon = new ImageIcon("images/white_bishop.png");
		setIcon(icon);
	}
	//A bishop with color
	public Bishop(String color) {
		if (color == "white") {
			this.color = "white";
			icon = new ImageIcon("images/white_bishop.png");
			setIcon(icon);
		}
		else {
			this.color = "black";
			icon = new ImageIcon("images/black_bishop.png");
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
		return "bishop";
	}
}
