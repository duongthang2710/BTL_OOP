package Panels.hallOfFame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import Manager.controller.Manager;
import Manager.generals.Background;
import Manager.generals.Panel;
import Manager.generals.Button.Acts;
import Manager.generals.Button.LabelButton;
import Panels.UserPanels.UserSetting;
import player.Player;

@SuppressWarnings("serial")
public class HallPanel extends Panel {
	private static HallPanel hall = new HallPanel();
	public LabelButton back = new LabelButton("Back", 50, 650, 130, 50, 47);
	public LabelButton hallOfFame = new LabelButton("Hall Of Fame !", 480, 10, 600, 70, 55);
	LabelButton player_name = new LabelButton("Name", 250, 100, 190, 50, 40);
	LabelButton level = new LabelButton("Level", 600, 100, 190, 50, 40);
	LabelButton point = new LabelButton("Point", 900, 100, 190, 50, 40);
	LabelButton time = new LabelButton("Time", 1200, 100, 190, 50, 40);
    static int currentY = 200;
	ArrayList<Player> players = new ArrayList<>();

	private HallPanel() {
		super(Background.backs.get("MenuBackground\\HallBackground.jpg"));
		init();
	}

	public static HallPanel create() {
        return hall;
	}

	private void init() {
		Collections.addAll(drawables, back, hallOfFame, player_name, level, point, time);
		for (Component c : drawables) {
			this.add(c);
		}
		back.addMouseListener(new Acts(back) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				UserSetting u = UserSetting.getcurrent();
				u.remove(u.resume);
				u.remove(u.newgame);
				u.hallOfFame.setForeground(LabelButton.ExitColor);
				Manager.setPanel(u);
			}
		});

	}
	public void addLabel(String[] s) {

        LabelButton p_name = new LabelButton(s[0], 250, currentY, 190, 50, 30);
        LabelButton p_level = new LabelButton(s[1], 638, currentY, 190, 50, 30);
        LabelButton p_point = new LabelButton(s[2], 938, currentY, 190, 50, 30);
        LabelButton p_time = new LabelButton(s[3], 1205, currentY, 190, 50, 30);

        currentY += 60;
        p_name.setForeground(Color.WHITE);
        p_level.setForeground(Color.WHITE);
        p_point.setForeground(Color.WHITE);
        p_time.setForeground(Color.WHITE);


        Collections.addAll(drawables, p_name, p_level, p_point, p_time);
        this.add(p_name);
        this.add(p_level);
        this.add(p_point);
        this.add(p_time);

        this.repaint();
        this.revalidate();
	}
}
