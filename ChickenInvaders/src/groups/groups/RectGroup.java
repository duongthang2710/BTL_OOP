package groups.groups;

import java.util.ArrayList;

import groups.Chicken.GeneralChicken;
import groups.Chicken.Chicken;

public class RectGroup extends GeneralGroups {
	public RectGroup(int level) {
		this.level = level;
		chickens = new ArrayList<GeneralChicken>();
		initArr();
	}

	@Override
	protected void initArr() {
		for (int i = 1; i <= maxChickens; i++) {
			chickens.add(new Chicken(this.x, this.y, 0, 8,this.level));
			this.x += 75;
			if (i % 15 == 0) {
				this.y += 75;
				this.x = 90;
			}
		}
	}

	int r = 0;

	@Override
	public void move() {
		for (int i = chickens.size() - 1; i >= 0; i--) {
			if (chickens.get(0).getY() < 60) {
				chickens.get(i).move();
			} else {
				if (r < chickens.size()) {
					chickens.get(i).setV_x(1);
					chickens.get(i).setV_y(0);
					r++;
				}
				if (chickens.get(i).inEdgeX()) {
					chickens.get(i).move();
					for (int j = chickens.size() - 1; j >= 0; j--) {
						if(chickens.get(j).getY() == chickens.get(i).getY() && (i != j)) {
						chickens.get(j).setV_x(chickens.get(i).getV_x());
						}
					}
				}
				chickens.get(i).move();
			}
		}
	}
}
