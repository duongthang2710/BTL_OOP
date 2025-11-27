package groups.Chicken;

import Manager.Assets;

import java.awt.Graphics;
import java.util.Random;

public class Chicken extends GeneralChicken {

	public Chicken(float x, float y, float v_x, float v_y, int level) {
		super(level);
		this.setX(x);
		this.setY(y);
		this.setV_x(v_x);
		this.setV_y(v_y);
	}

	@Override
	public void paint(Graphics g) {
        g.drawImage(images[currentFrame], (int)(x-width/2), (int)(y-height/2), width, height, null);
        Random random = new Random();
        if (random.nextInt(100000) < 2) {
            Assets.sound.playSound("chickensound");
        }
	}

	@Override
	public void orbit(double v) {
	}

}
