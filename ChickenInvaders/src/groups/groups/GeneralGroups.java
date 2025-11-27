package groups.groups;

import static Manager.Dimentions.screenSize;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import groups.Chicken.GeneralChicken;
import player.Animatable;

public abstract class GeneralGroups implements Animatable {
	public ArrayList<GeneralChicken> chickens = new ArrayList<GeneralChicken>();
	protected final int maxChickens = 50;
	protected int level;
	protected int x = 90;
	protected int y = -1000;

	public void paint(Graphics g) {
		for (int i = chickens.size() - 1; i >= 0; i--) {
			chickens.get(i).paint(g);
		}
	}

	protected GeneralChicken RandomChicken() {
		Random r = new Random();
		try {
			return chickens.get(r.nextInt(chickens.size() - 1));
		} catch (Exception e) {
		}
		return chickens.get(0);
	}

	protected int getRandomX() {
		Random r = new Random();
		return 30 + r.nextInt((int) screenSize.getWidth() - 60);
	}

	protected int getRandomY() {
		Random r = new Random();
		return 90 + r.nextInt((int) screenSize.getHeight() - 300);
	}

	protected abstract void initArr();

}