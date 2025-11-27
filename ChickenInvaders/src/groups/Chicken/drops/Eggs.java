package groups.Chicken.drops;

import Manager.Assets;
import Panels.UserPanels.GamePanel;
import player.SpaceShip;

public class Eggs extends Drops {
	public Eggs(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		image = Assets.Egg;
		width = 20;
		height = 26;
		this.radius = 10;
	}

	@Override
	public void intersect(SpaceShip rocket) {
		if (getDistance(rocket) <= this.radius + rocket.radius) {
			GamePanel.getCurrent().getController().wave.drops.remove(this);
			rocket.die();
		}
	}

}
