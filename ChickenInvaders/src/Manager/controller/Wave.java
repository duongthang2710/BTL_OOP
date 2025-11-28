package Manager.controller;

import static Manager.Dimentions.screenSize;

import java.awt.Graphics;
import java.util.ArrayList;

import Manager.generals.Button.WaveLabel;
import groups.groups.GeneralGroups;
import groups.Chicken.GeneralChicken;
import groups.Chicken.Boss;
import groups.Chicken.drops.Eggs;
import groups.Chicken.drops.Coin;
import groups.Chicken.drops.Drops;
import player.Animatable;

public class Wave implements Animatable {
	public ArrayList<Drops> drops = new ArrayList<Drops>();
	public ArrayList<GeneralGroups> groups = new ArrayList<GeneralGroups>();
	public Boss boss;
	int wave_Num;
	int stage;
	long Wave_Massage_Time;
	WaveLabel label;
	public int state = 1;
    public static long time_end_wave;


	public String startMassage(int stage, int wave, String massage) {
		return "Stage " + stage + " Wave " + wave + "\r" + massage;
	}

	public Wave(int wave_Num, int stage, ArrayList<GeneralGroups> groups) {
		this.wave_Num = wave_Num;
		this.stage = stage;
		this.groups.addAll(groups);
		label = new WaveLabel(startMassage(stage, wave_Num, ""), 300, 300, 30);
	}

	public Wave(int wave_Num, int stage, Boss boss) {
		this.wave_Num = wave_Num;
		this.stage = stage;
		this.boss = boss;
		label = new WaveLabel(startMassage(stage, wave_Num, ""), 300, 300, 30);
	}

	private void showMassage() {
		if (System.currentTimeMillis() - Wave_Massage_Time >= 1 && this.state == 1) {
			label.move();
			Wave_Massage_Time = System.currentTimeMillis();
		}
		if (label.y > screenSize.height) {
			this.state = 2;
		}
	}

	private boolean waveEnded() {
		for (GeneralGroups g : groups) {
			if (g.chickens.size() != 0 || drops.size() != 0)
				return false;
		}
		if (boss != null && (boss.bosses.size() > 0 || drops.size() != 0)) {
			return false;
		}
        time_end_wave = System.currentTimeMillis();
		return true;
	}

	@Override
	public void paint(Graphics g) {
		paintDrops(g);
		for (GeneralGroups group : groups) {
			group.paint(g);
		}
		if (boss != null)
			boss.paint(g);

	}

	@Override
	public void move() {
		switch (this.state) {
		case 1:
			showMassage();
			break;
		case 2:
			if (this.wave_Num < 5) {
				for (GeneralGroups g : groups) {
					g.move();
				}
				dropEggs();
			} else if (this.wave_Num == 5) {
				boss.move();
			}
			if (waveEnded()) {
				this.state = 3;
			}
			moveDrops();
			break;
		case 3:
			break;

		}
	}

	long drop_time = 0;

	public void dropEggs() {
		if (System.currentTimeMillis() - drop_time > 1000) {
			for (int j = groups.size() - 1; j >= 0 && groups.size() != 0; j--) {
				for (int i = groups.get(j).chickens.size() - 1; i >= 0 && groups.get(j).chickens.size() > 0; i--) {
					GeneralChicken s = groups.get(j).chickens.get(i);
					if (s.chance() <= s.getTir_chance() && s.getY() > 0) {
						drops.add(new Eggs(s.getX(), s.getY(), 0, s.getTir_velocity()));
					}
				}
			}
			drop_time = System.currentTimeMillis();
		}
	}

	private void paintDrops(Graphics g) {
		for (int i = drops.size() - 1; i >= 0 && drops.size() > 0; i--) {
			try {
				drops.get(i).paint(g);
			} catch (Exception e) {
				System.out.println("drop paint error");
			}
		}
	}

	private void moveDrops() {
		for (int i = drops.size() - 1; i >= 0 && drops != null; i--) {
            Drops drop = drops.get(i);
			drop.move();

            if (drop instanceof Coin){
                ((Coin) drop).updateAnimation();
            }

			if (drops.get(i).getY() > screenSize.getHeight() || drops.get(i).getY() < 0 || drops.get(i).getX() < 0
					|| drops.get(i).getX() > screenSize.getWidth()) {
				drops.remove(i);
			}
		}
	}
}
