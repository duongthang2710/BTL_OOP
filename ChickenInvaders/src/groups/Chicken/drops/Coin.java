package groups.Chicken.drops;

import Manager.Assets;
import Panels.UserPanels.GamePanel;
import player.SpaceShip;

public class Coin extends Drops {
    private int frameIndex = 0;
    private int frameDelay = 5;
    private int frameCounter = 0;
    private int frameLength = 8;
	public Coin(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		image = Assets.Coin.get(0);
		width = 25;
		height = 25;
	}

	@Override
	public void intersect(SpaceShip spaceShip) {
		if (this.getDistance(spaceShip) <= this.radius + spaceShip.radius) {
			GamePanel.getCurrent().getController().wave.drops.remove(this);
			spaceShip.setCoins(spaceShip.getCoins() + 1);
		}
		for (int i = spaceShip.tirs.size() - 1; i >= 0; i--) {
			if (this.getDistance(spaceShip.tirs.get(i)) <= this.radius + spaceShip.tirs.get(i).radius) {
				GamePanel.getCurrent().getController().wave.drops.remove(this);
			}
		}
	}

    public void updateAnimation() {
        frameCounter++;
        if(frameCounter >= frameDelay) {
            frameCounter = 0;
            frameIndex = (frameIndex + 1) % frameLength;
            image = Assets.Coin.get(frameIndex);
        }
    }

}
