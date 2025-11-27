package Manager.controller;

import javax.swing.JPanel;

import Main.MainFrame;
import Manager.generals.Panel;

public class Manager {
	public static MainFrame frame = MainFrame.getInstance();

	public Manager() {
	}

	public static void setPanel(Panel panel) {
		frame.remove(frame.getContentPane());
		frame.setContentPane(panel);
		frame.revalidate();
	}

	public static void setPanel(JPanel p) {
		frame.remove(frame.getContentPane());
		frame.setContentPane(p);
		frame.revalidate();
	}

}
