package player;

import static Manager.Dimentions.screenSize;

import java.io.Serializable;

import Panels.UserPanels.GamePanel;
import Panels.UserPanels.UserChooser;

@SuppressWarnings("serial")
public class Player implements Serializable {
	public String name;
	public SpaceShip rocket;
	public int score;
	public int stage = 1;
	public int wave = 1;
	public long time_in_game;
	public long first_time;
	public long ending_time;

	public Player(String name) {
		this.name = name;
		this.rocket = new SpaceShip(screenSize.width / 2, screenSize.height / 2, "SpaceShip\\SpaceShip2.png");
	}

	public void nextWave() {
		this.score += 3 * rocket.getCoins();
		rocket.setCoins(0);
		GamePanel.getCurrent().updateState();
		if (this.wave < 5) {
			this.wave++;
		} else if (this.wave == 5 && this.stage < 4) {
			this.stage++;
			this.wave = 1;
		}
		UserChooser.create().save();
	}

	public int getLevel() {
		return this.stage;
	}

	public String[] getData() {
		String[] s = { name, score + "", stage + "", wave + "" };
		return s;
	}
}
