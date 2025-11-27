package player;

import static Manager.Dimentions.screenSize;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import Manager.Assets;

@SuppressWarnings("serial")
public class Missile implements Animatable, Serializable {
	private static final int x_Destination = screenSize.width / 2;
	private static final int y_Destination = screenSize.height / 2;
	private static final float default_Velocity = 3f;
	float x;
	private float y;
	float Vx;
	float Vy;
	float teta;
	float m;
	private transient BufferedImage image;

	public Missile(float x, float y) {
		this.image = Assets.Rocket;
		this.x = x;
		this.setY(y);
		float deltaX = x_Destination - x;
		float deltaY = y_Destination - y;
		m = deltaY / deltaX;
		teta = (float) Math.atan2(deltaY, deltaX);
		Vx = (float) Math.cos(teta) * default_Velocity;
		Vy = (float) Math.sin(teta) * default_Velocity;
	}

	@Override
	public void paint(Graphics g2) {
		if (!this.reached())
			g2.drawImage(image, (int) x, (int) getY(), null);
	}

	@Override
	public void move() {
		if (!this.reached()) {
			x += Vx;
			setY(getY() + Vy);
		}
	}

	public boolean reached() {
		int deltaX = (int) Math.abs(x_Destination - x);
		int deltaY = (int) Math.abs(y_Destination - getY());
		if (deltaX < 7 && deltaY < 7) {
			return true;
		}
		return false;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
