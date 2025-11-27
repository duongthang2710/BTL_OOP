package groups.groups;

import static Manager.Dimentions.screenSize;

import java.awt.Point;
import java.util.Random;

import groups.Chicken.RoundChicken;

public class CircleSnipers extends GeneralGroups {
	private int radius = 60;
	protected Point destin = new Point(screenSize.width / 2, screenSize.height / 2);
	protected Point center = new Point(screenSize.width / 2, -430);

	public CircleSnipers(int level) {
		this.level = level;
		initArr();
	}

	@Override
	protected void initArr() {
		byte i = 0;
		while (i < maxChickens) {
			chickens.add(new RoundChicken(35 * i, radius + (chickens.size() * 8), center, 0, this.level));
			i++;
		}
	}

	@Override
	public void move() {
		if (reachedDestin()) {
			destin = centerRandomDestin();
		} else {
			moveToDestin();
		}
		for (byte j = 0; j < chickens.size(); j++) {
			RoundChicken s = (RoundChicken) chickens.get(j);
			s.orbit(-0.4);
		}
	}

	protected void moveToDestin() {
		int dx = destin.x - center.x;
		int dy = destin.y - center.y;
		if (dx != 0)
			center.x += dx / Math.abs(dx);
		if (dy != 0)
			center.y += dy / Math.abs(dy);

	}

	protected boolean reachedDestin() {
		if (center.x == destin.x && center.y == destin.y) {
			return true;
		}
		return false;
	}

	protected Point centerRandomDestin() {
		Random r = new Random();
		int x = (r.nextInt(screenSize.width - 400) + 400);
		int y = (r.nextInt(screenSize.height - 600) + 250);
		return new Point(x, y);
	}

}
