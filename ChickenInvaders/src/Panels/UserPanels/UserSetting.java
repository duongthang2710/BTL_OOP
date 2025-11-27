package Panels.UserPanels;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Collections;

import Manager.controller.Manager;
import Manager.generals.Background;
import Manager.generals.Panel;
import Manager.generals.Button.Acts;
import Manager.generals.Button.LabelButton;
import Panels.hallOfFame.HallPanel;
import Panels.multiPlayer.MultiPanel;
import Panels.options.OptionPanel;
import player.Player;

@SuppressWarnings("serial")
public class UserSetting extends Panel {
	static UserSetting user_setting = new UserSetting();
	Player player;
	public LabelButton back = new LabelButton("Back", 220, 600, 150, 50, 50);
	public LabelButton options = new LabelButton("Options", 220, 480, 250, 50, 50);
	public LabelButton hallOfFame = new LabelButton("Hall Of Fame", 220, 360, 400, 50, 50);
	public LabelButton newgame = new LabelButton("New Game", 650, 160, 400, 50, 35);
	public LabelButton resume = new LabelButton("Resume Game", 650, 80, 400, 50, 35);
	LabelButton singlePlayer = new LabelButton("Single Player", 220, 120, 450, 63, 50);
	LabelButton multiplePlayer = new LabelButton("Multiple Player", 220, 240, 450, 63, 50);

	private UserSetting() {
		super(Background.backs.get("MenuBackground\\UserWelcomeBackground.jpg"));
		init();
	}

	public static UserSetting create(Player player) {
		user_setting.player = player;
		return user_setting;
	}

	public static UserSetting getcurrent() {
		return user_setting;
	}

	public void init() {
		Collections.addAll(drawables, back, options, hallOfFame, singlePlayer, multiplePlayer);
		for (Component c : drawables) {
			this.add(c);
		}
		back.addMouseListener(new Acts(back) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				remove(resume);
				remove(newgame);
				UserChooser u = UserChooser.create();
				u.enter.setForeground(LabelButton.ExitColor);
				Manager.setPanel(u);
			}
		});

		options.addMouseListener(new Acts(options) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				OptionPanel u = OptionPanel.getcurrent();
				u.back.setForeground(LabelButton.ExitColor);
				Manager.setPanel(u);
			}
		});

		hallOfFame.addMouseListener(new Acts(hallOfFame) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				remove(resume);
				remove(newgame);
				HallPanel u = HallPanel.create();
				u.back.setForeground(LabelButton.ExitColor);
				Manager.setPanel(u);
			}
		});

		newgame.addMouseListener(new Acts(newgame) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				Main.MyMain.frame.chooser.userMap.replace(player.name, new Player(player.name));
				player = Main.MyMain.frame.chooser.userMap.get(player.name);
				Manager.setPanel(GamePanel.create(player));
				GamePanel.getCurrent().setPaused(false);
				newgame.setForeground(LabelButton.ExitColor);
			}
		});

		resume.addMouseListener(new Acts(resume) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				GamePanel game = GamePanel.create(player);
				Manager.setPanel(game);
				game.setPaused(false);
				resume.setForeground(LabelButton.ExitColor);
			}
		});
		singlePlayer.addMouseListener(new Acts(singlePlayer) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				Collections.addAll(drawables, newgame, resume);
				add(resume);
				add(newgame);
				repaint();
			}
		});
		multiplePlayer.addMouseListener(new Acts(multiplePlayer) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				multiplePlayer.setForeground(LabelButton.ExitColor);
				remove(resume);
				remove(newgame);
				MultiPanel m = MultiPanel.create(user_setting.player);
				Manager.setPanel(m);
			}
		});
	}
}
