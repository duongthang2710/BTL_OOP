package Manager.controller;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import Panels.UserPanels.GamePanel;
import groups.groups.CircleSnipers;
import groups.groups.GeneralGroups;
import groups.groups.RectGroup;
import groups.groups.SimpleRound;
import groups.groups.Snipers;
import groups.groups.UpperRound;
import groups.Chicken.Boss;
import player.Animatable;
import player.SpaceShip;

public class GameController implements Animatable {

	public Wave wave;

	public GameController(int stage, int wave_num) {
		if (wave_num == 5) {
			this.wave = new Wave(wave_num, stage, new Boss(stage));
		//	System.out.println();
		} else {
			this.wave = new Wave(wave_num, stage, makeGroups(stage, wave_num));
		}
	}

	public String congratulations() {
		return "System cleared!! Congratulations";
	}

	public String gameOver() {
		return "Game Over";
	}

	@Override
	public void paint(Graphics g) {
		wave.paint(g);
	}

	@Override
	public void move() {
		wave.move();
		if (this.wave.state == 3) {
			GamePanel.getCurrent().getPlayer().nextWave();
			wave = makeNextWave();
		}
	}

	private Wave makeNextWave() {

		if (wave.wave_Num == 5 && wave.stage < 4) {
			wave.wave_Num = 1;
			wave.stage++;
		} else {
			wave.wave_Num++;
		}
		if (wave.wave_Num < 5)
			return new Wave(wave.wave_Num, wave.stage, makeGroups(wave.stage, wave.wave_Num));
		else {
			return new Wave(wave.wave_Num, wave.stage, new Boss(wave.stage));
		}
	}

	private ArrayList<GeneralGroups> makeGroups(int stage, int wave_num) {
		switch (wave_num) {
		case 1:
			return new ArrayList<GeneralGroups>(Arrays.asList(new RectGroup(stage)));
		case 2:
			return new ArrayList<GeneralGroups>(Arrays.asList(new SimpleRound(stage)));
		case 3:
			return new ArrayList<GeneralGroups>(Arrays.asList(new Snipers(stage), new UpperRound(stage)));
		case 4:
			return new ArrayList<GeneralGroups>(Arrays.asList(new CircleSnipers(stage), new Snipers(stage)));
		case 5:
			return null;
		}
		return null;

	}
}