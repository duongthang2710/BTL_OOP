package Manager.generals;

import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import SaveLoad.SaveData;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	protected Background back;
	protected boolean isContent = true;
	protected ArrayList<Component> drawables = new ArrayList<>();
	protected SaveData save;

	public Panel(Background back) {
		this.back = back;
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		back.drawFixed(g);
	}

}
