package groups.groups;

import groups.Chicken.RoundChicken;

public class UpperRound extends CircleSnipers {
	private int radius;

	public UpperRound(int level) {
		super(level);
	}

	@Override
	protected void initArr() {
		radius = 190;
		for (int i = 0; i < maxChickens; i++) {
			if (i < 10) {
				chickens.add(new RoundChicken(i * 360 / 10, radius, center, i, this.level));
			} else if (i < 25) {
				chickens.add(new RoundChicken(i * 360 / 15, radius + 100, center, i, this.level));
			} else {
				chickens.add(new RoundChicken(i * 360 / 25, radius + 200, center, i, this.level));
			}
		}
	}
}
