package groups.Chicken.drops;

import Manager.Assets;
import Panels.UserPanels.GamePanel;
import player.SpaceShip;

public class HeatEnhancer extends Drops {

	public HeatEnhancer(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		image = Assets.PowerUp;
		width = 50;
		height = 50;
	}

	@Override
	public void intersect(SpaceShip rocket) {

		if (getDistance(rocket) <= this.radius + rocket.radius) {
			GamePanel.getCurrent().getController().wave.drops.remove(this);
			rocket.setFan_strength(rocket.getFan_strength() + 0.005);
		}
	}
}
