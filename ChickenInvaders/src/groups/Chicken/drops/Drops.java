package groups.Chicken.drops;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import player.Animatable;
import player.Fire;
import player.SpaceShip;

public abstract class Drops implements Animatable {
	protected double x;
	protected double y;
	protected double vx;
	protected double vy;
	protected BufferedImage image;
	protected int height;
	protected int width;
	public int radius = 20;

	public Drops(double x, double y, double vx, double vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void paint(Graphics g2) {
		g2.drawImage(image, (int) x - width / 2, (int) y - width / 2, width, height, null);
	}

	public void move() {
		x += vx;
		y += vy;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getDistance(Object o) {
		if (o instanceof SpaceShip) {
			return (int) Math.sqrt((this.getX() - ((SpaceShip) o).getX()) * (this.getX() - ((SpaceShip) o).getX())
					+ (this.getY() - ((SpaceShip) o).getY()) * (this.getY() - ((SpaceShip) o).getY()));
		}
		if (o instanceof Fire) {
			return (int) Math.sqrt((this.getX() - ((Fire) o).getX()) * (this.getX() - ((Fire) o).getX())
					+ (this.getY() - ((Fire) o).getY()) * (this.getY() - ((Fire) o).getY()));
		}
		return 0;

	}

	public abstract void intersect(SpaceShip rocket);

}
