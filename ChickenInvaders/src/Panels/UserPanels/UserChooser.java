package Panels.UserPanels;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.LinkedHashMap;

import javax.swing.JComboBox;

import Main.MyMain;
import Manager.controller.Manager;
import Manager.generals.Background;
import Manager.generals.Panel;
import Manager.generals.SavePlayer;
import Manager.generals.Button.Acts;
import Manager.generals.Button.LabelButton;
import Manager.generals.Button.TextField;
import SaveLoad.Load;
import SaveLoad.SaveData;
import player.Player;

@SuppressWarnings("serial")
public class UserChooser extends Panel implements SavePlayer {
	static UserChooser userPanel = new UserChooser();

	LabelButton quit = new LabelButton("Quit", 300, 600, 80, 60, 33);
	LabelButton enter = new LabelButton("Enter", 800, 600, 100, 60, 33);
	LabelButton add = new LabelButton("Add user", 700, 200, 120, 60, 23);
	LabelButton delete = new LabelButton("Delete the user", 700, 250, 180, 60, 23);
	TextField user_text = new TextField("", 500, 215, 170, 30, 15);
	JComboBox<String> user_combo = new JComboBox<String>();
	public LinkedHashMap<String, Player> userMap = new LinkedHashMap<String, Player>();

	private UserChooser() {
		super(Background.backs.get("MenuBackground\\UserChooserBackground.png"));
		init();
		requestFocus();
		addLoaded(new Load().GetUserData());
	}

	private void addLoaded(Player[] loaded) {
		for (Player player : loaded) {
			player.rocket.initLoaded();
			addPlayer(player);
		}
	}

	private void init() {
		Collections.addAll(drawables, quit, enter, user_text, user_combo, add, delete);
		for (Component c : drawables) {
			this.add(c);
		}
		user_combo.setBounds(500, 265, 170, 30);
		user_combo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

		add.addMouseListener(new Acts(add) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				addPlayer(new Player(user_text.getText()));
			}
		});
		delete.addMouseListener(new Acts(delete) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					user_combo.removeItemAt(user_combo.getSelectedIndex());
					userMap.remove(user_combo.getItemAt(user_combo.getSelectedIndex()));
					user_combo.repaint();
				} catch (Exception e) {
				}
			}
		});
		quit.addMouseListener(new Acts(quit) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				save();
				MyMain.frame.dispatchEvent((new WindowEvent(MyMain.frame, WindowEvent.WINDOW_CLOSING)));
			}
		});
		enter.addMouseListener(new Acts(enter) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (!userMap.isEmpty()) {
					String player_name = user_combo.getItemAt(user_combo.getSelectedIndex());
					UserSetting us = UserSetting.create(userMap.get(player_name));
					us.back.setForeground(LabelButton.ExitColor);
					Manager.setPanel(us);
				}
			}
		});
	}

	private void addPlayer(Player player) {
		if (!player.name.equals("")) {
			userMap.put(player.name, player);
			if (duplicate(player)) {
				user_combo.addItem(player.name);
				user_combo.repaint();
			}
		}
		user_combo.setSelectedIndex(user_combo.getItemCount() - 1);
	}

	private boolean duplicate(Player player) {
		for (int i = 0; i < user_combo.getItemCount(); i++) {
			if (user_combo.getItemAt(i).equals(player.name))
				return false;
		}
		return true;
	}

	public static UserChooser create() {
		return userPanel;
	}

	@Override
	public void save() {
		save = new SaveData();
	}
}
