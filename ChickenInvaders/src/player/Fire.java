package player;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Fire implements Animatable, Serializable {
	transient BufferedImage image;
	private double x;
	private double y;
	private double v_x;
	private double v_y;
	private double velocity;
	private double damage;
	private double teta;
	public int radius = 5;
	private int width = 10;
	private int height = 55;
	private AffineTransform transform;

	public Fire(double x, double y, double velocity, double teta, BufferedImage image, double damage) {
		this.x = x;
		this.y = y;
		this.velocity = velocity;
		this.teta = teta;
		this.damage = damage;
		this.v_x = Math.cos(Math.toRadians(teta)) * this.velocity;
		this.v_y = -Math.sin(Math.toRadians(teta)) * this.velocity;
		this.image = image;
		this.transform = AffineTransform.getTranslateInstance(this.x - this.width, this.y - this.height);
		transform.setToTranslation(x - image.getWidth() / 2, y - image.getHeight() / 2);
		transform.rotate(Math.toRadians(teta - 90), this.image.getWidth() / 2, this.image.getHeight() / 2);

	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	@Override
	public void paint(Graphics g2) {
		Graphics2D g = (Graphics2D) g2;
		g.drawImage(this.image, this.transform, null);
	}

	@Override
	public void move() {
		this.x += this.v_x;
		this.y += this.v_y;
		this.transform = AffineTransform.getTranslateInstance(this.x - this.width, this.y - this.height);
		transform.setToTranslation(x - image.getWidth() / 2, y - image.getHeight() / 2);
		transform.rotate(Math.toRadians(90 - teta), this.image.getWidth() / 2, this.image.getHeight() / 2);
	}
}
