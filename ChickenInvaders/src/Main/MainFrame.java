package Main;

import javax.swing.JFrame;

import Manager.Assets;
import Panels.UserPanels.UserChooser;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public Assets assets =new Assets();
	static MainFrame instance = new MainFrame();
	public UserChooser chooser = UserChooser.create();

	public MainFrame() {

	}

	public static MainFrame getInstance() {
        if(instance == null) {
            instance = new MainFrame();
        }
		return instance;

	}

	public void initialize() {
		 setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		setTitle("Chicken Invaders");
		setContentPane(chooser);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
