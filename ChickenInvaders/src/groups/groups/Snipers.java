package groups.groups;

import Panels.UserPanels.GamePanel;
import groups.Chicken.Chicken;
import player.SpaceShip;

public class Snipers extends GeneralGroups {
	SpaceShip rocket = GamePanel.getCurrent().getPlayer().rocket;
	long shoot_time;

	public Snipers(int level) {
		this.level = level;
		initArr();
	}

	@Override
	public void move() {
		if (System.currentTimeMillis() - shoot_time > 5000 && chickens.size() > 0) {
			Chicken s = (Chicken) RandomChicken();
			s.x_Destin = rocket.getX();
			s.y_Destin = rocket.getY();
			shoot_time = System.currentTimeMillis();
		}
		
		for (int i = 0; i < chickens.size(); i++) {
			if (chickens.get(i).getY() < 90 && chickens.get(i).getY() == -30) {
				chickens.get(i).move();
				chickens.get(i).x_Destin = chickens.get(i).getX();
				chickens.get(i).y_Destin = 90;
			} else if (reachedDestination((Chicken) chickens.get(i)) && chickens.get(i).getY() >= 0) {
				chickens.get(i).x_Destin = getRandomX();
				chickens.get(i).y_Destin = getRandomY();
			}
			chickens.get(i).moveDestin();
			reachedDestination((Chicken) chickens.get(i));
		}
	}

	private boolean reachedDestination(Chicken skull) {
		if ((int) skull.getX() == (int) skull.x_Destin) {
			return true;
		}
		return false;
	}

	@Override
	protected void initArr() {
		for (int i = 0; i < maxChickens; i++) {
			chickens.add(new Chicken(getRandomX(), -40, 0, 1, this.level));
		}
	}

}
