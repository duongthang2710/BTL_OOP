package Panels.options;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Collections;

import Manager.controller.Manager;
import Manager.generals.Background;
import Manager.generals.Panel;
import Manager.generals.Button.Acts;
import Manager.generals.Button.LabelButton;
import Panels.UserPanels.UserSetting;
import player.Player;

@SuppressWarnings("serial")
public class OptionPanel extends Panel {
	private static OptionPanel optionPanel = new OptionPanel();
	Player player;

	LabelButton backgrounds = new LabelButton("Backgrounds", 220, 240, 400, 55, 47);

	public LabelButton back = new LabelButton("Back", 220, 360, 130, 50, 47);

	private OptionPanel() {
		super(Background.backs.get("MenuBackground\\OptionBackground.jpg"));
		init();
	}

	private void init() {
		Collections.addAll(drawables, back,  backgrounds);
		for (Component c : drawables) {
			this.add(c);
		}
		back.addMouseListener(new Acts(back) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				UserSetting u = UserSetting.getcurrent();
				u.remove(u.resume);
				u.remove(u.newgame);
				u.options.setForeground(LabelButton.ExitColor);
				Manager.setPanel(u);
			}
		});
		backgrounds.addMouseListener(new Acts(backgrounds) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				Costumback u = Costumback.create();
				backgrounds.setForeground(LabelButton.ExitColor);
				u.back.setForeground(LabelButton.ExitColor);

				Manager.setPanel(u);
			}
		});
	}

	public static OptionPanel create(Player player) {
		optionPanel.player = player;
		return optionPanel;
	}

	public static OptionPanel getcurrent() {
		return optionPanel;
	}
}
