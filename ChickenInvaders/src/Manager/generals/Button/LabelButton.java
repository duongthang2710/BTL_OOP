package Manager.generals.Button;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public  class LabelButton extends JLabel {
	public static Color enterColor = new Color(136, 14, 79);
	public static Color ExitColor = new Color(41, 182, 246);
	public static Color Blue = new Color(34, 0, 255);
	public static Color mainColor;
	public LabelButton(String name, int x, int y, int width, int length, int size) {
		this.setText(name);
		mainColor =ExitColor;
		setForeground(mainColor);
		setBounds(x, y, width, length);
		setFont(new Font(Font.SANS_SERIF, Font.BOLD, size)); 
	}
}
