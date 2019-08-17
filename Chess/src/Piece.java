import javax.swing.ImageIcon;					//Imports
import javax.swing.JLabel;

//An abstract class for all the types of pieces
@SuppressWarnings("serial")
public abstract class Piece extends JLabel {
	
	public String color = "white";	//Color
	public ImageIcon icon;		//The piece image
	public String pieceType;		//and the piece's type
	
	public abstract String getColor();	//This gets the color
	public abstract String getType();	//This gets the type
}
