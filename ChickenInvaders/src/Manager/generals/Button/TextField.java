package Manager.generals.Button;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TextField extends JTextField{
	public TextField(String text,int x,int y , int width,int length,int size) {
		this.setText(text);
		this.setBounds(x, y, width, length);
		this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, size));
		this.setBorder(BorderFactory.createMatteBorder(2,2,2,2,LabelButton.ExitColor));
	}
}
