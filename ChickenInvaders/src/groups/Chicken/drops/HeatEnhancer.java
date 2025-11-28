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
	public void intersect(SpaceShip spaceShip) {

		if (getDistance(spaceShip) <= this.radius + spaceShip.radius) {
			GamePanel.getCurrent().getController().wave.drops.remove(this);
			spaceShip.setFan_strength(spaceShip.getFan_strength() + 0.005);
		}
	}
}
