package Manager.generals.Button;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import Panels.UserPanels.GamePanel;

import static Manager.Dimentions.screenSize;

@SuppressWarnings("serial")
public class WaveLabel extends JLabel {
	static Color mainColor = Color.LIGHT_GRAY;
	public static int x_center = screenSize.width / 2;
	public static int y_center = screenSize.height / 2;
	public int x;
	public int y = -700;
	public int width;
	public int height;

	public WaveLabel(String massage, int width, int height, int size) {
		setForeground(mainColor);
		this.setText(massage);
		this.width = width;
		this.height = height;
		setBounds(x_center - width / 2, y, width, height);
		setFont(new Font(Font.MONOSPACED, Font.BOLD, size));
		GamePanel.getCurrent().add(this);
	}

	public void move() {
		this.y += 3;
		this.setBounds(x_center - width / 2, y, width, height);
	}
}
