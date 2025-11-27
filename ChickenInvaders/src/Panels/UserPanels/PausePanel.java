package Panels.UserPanels;

import static Manager.Dimentions.screenSize;

import java.awt.event.MouseEvent;

import Manager.controller.GameAction;
import Manager.controller.Manager;
import Manager.generals.Background;
import Manager.generals.Panel;
import Manager.generals.Button.Acts;
import Manager.generals.Button.LabelButton;

@SuppressWarnings("serial")
public class PausePanel extends Panel {
	static PausePanel esc = new PausePanel();
	LabelButton paused = new LabelButton("Game Paused", screenSize.width / 2 - 170, 0, 400, 200, 55);
	LabelButton exit = new LabelButton("Exit to main menu", screenSize.width / 2 - 170, 220, 400, 100, 33);
	LabelButton resume = new LabelButton("Resume", screenSize.width / 2 - 170, 330, 300, 100, 33);
	
	private PausePanel() {
		super(Background.backs.get("MenuBackground\\ExitBackground.png"));
		paused.setForeground(LabelButton.Blue);
		this.add(paused);
		this.add(exit);
		this.add(resume);
		init();
	}

	private void init() {
		exit.addMouseListener(new Acts(exit) {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Manager.setPanel(UserSetting.getcurrent());
				UserSetting.getcurrent().remove(UserSetting.getcurrent().resume);
				UserSetting.getcurrent().remove(UserSetting.getcurrent().newgame);
				exit.setForeground(LabelButton.ExitColor);
			}
		});
		resume.addMouseListener(new Acts(resume) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				GamePanel.game.getPlayer().first_time = System.currentTimeMillis() / 1000;

				Manager.setPanel(GamePanel.getCurrent());
				GamePanel.game.setPaused(false);
				GamePanel.getCurrent().getRun().start();
				GamePanel.getCurrent().setPaused(false);
				GameAction.unvisiblePointer(true,GamePanel.getCurrent());
				resume.setForeground(LabelButton.ExitColor);
			}
		});
	}

	public static PausePanel create() {
		return esc;
	}

}
