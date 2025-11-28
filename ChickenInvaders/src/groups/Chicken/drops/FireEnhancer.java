package groups.Chicken.drops;

import Manager.Assets;
import Panels.UserPanels.GamePanel;
import player.SpaceShip;

public class FireEnhancer extends Drops {
	int type;

	public FireEnhancer(double x, double y, double vx, double vy, int type) {
		super(x, y, vx, vy);
		this.type = type;
		width = 35;
		height = 35;
		switch (type) {
		case 1:
			image = Assets.RedGift;
			break;
		case 2:
			image = Assets.GreenGift;
			break;
		case 3:
			image = Assets.BlueGift;
			break;
		}

	}

	@Override
	public void intersect(SpaceShip spaceShip) {
		if (getDistance(spaceShip) <= this.radius + spaceShip.radius) {
			GamePanel.getCurrent().getController().wave.drops.remove(this);
			if (spaceShip.getFire_type() != type)
				spaceShip.setFire_type(type);
			else {
				spaceShip.setFire_stage(spaceShip.getFire_stage() + 1);
			}
			GamePanel.getCurrent().updateState();
		}
	}
}
