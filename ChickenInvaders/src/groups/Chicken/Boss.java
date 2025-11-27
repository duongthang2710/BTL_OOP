package groups.Chicken;

import static Manager.Dimentions.screenSize;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import Manager.Assets;
import Panels.UserPanels.GamePanel;
import groups.Chicken.drops.Eggs;
import groups.Chicken.drops.Drops;
import player.Fire;
import player.SpaceShip;

public class Boss {
	private static final int DEFAULT_JOON = 700;
    private static final int distanceheight = -200;
	public ArrayList<Boss> bosses = new ArrayList<Boss>();
	private double x = screenSize.getWidth() / 2;
	private double y = screenSize.height / 2 + distanceheight;
	@SuppressWarnings("unused")
	private double v_x;
	private double v_y;
	private BufferedImage image;
	private int width = 500;
	private int height = 550;
	public int joon;
	private int radius;
	private int level;
	private long bone_time;
    private double baseSpeed = 2.0;


	public Boss(int level) {
		this.level = level;
		makeGhool();
		bosses.add(this);
	}

	private void makeGhool() {
		switch (level) {
		case 1:
			this.radius = 150;
			this.image = Assets.boss1;
			this.width = 372;
			this.height = 267;
            this.baseSpeed = 2.0;
			break;

		case 2:
			this.radius = 150;
			this.image = Assets.boss2;
            this.width = 370;
            this.height = 250;
            this.baseSpeed = 2.5;
			break;
		case 3:
			this.radius = 170;
			this.image = Assets.boss3;
            this.width = 400;
            this.height = 327;
            this.baseSpeed = 3.0;
			break;
		case 4:
			this.radius = 200;
			this.image = Assets.boss4;
            this.width = 450;
            this.height = 410;
            this.baseSpeed = 3.5;
			break;
		}
		this.joon = this.level * DEFAULT_JOON;
		this.v_y = 4;
	}

	public void changeJoon(int t) {
		joon -= t;
	}

	private void addEggs() {
        ArrayList<Drops> drops = GamePanel.getCurrent().getController().wave.drops;

        int totalDirections = 16;
        int bonesPerShot = 5;
        double Speed = this.baseSpeed;

        for (int i = 0; i < bonesPerShot; i++) {
            int dirIndex = (int)(Math.random() * totalDirections);
            double angle = 2 * Math.PI * dirIndex / totalDirections;

            double vx = Math.cos(angle) * Speed;
            double vy = Math.sin(angle) * Speed;

            vx += (Math.random() - 0.5) * 0.5;
            vy += (Math.random() - 0.5) * 0.5;

            drops.add(new Eggs(x, y, vx, vy));
        }
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public void move() {
		for (Boss s : bosses) {
			if (System.currentTimeMillis() - bone_time > 500) {
				s.addEggs();
				bone_time = System.currentTimeMillis();
			}
            if (s.y < screenSize.height / 2 + distanceheight) {
                s.y += s.v_y;
            }
        }
	}

	public void paint(Graphics g) {
		g.setColor(new Color(243, 5, 5));
		for (int i = bosses.size() - 1; i >= 0 && bosses.size() > 0; i--) {
			Boss s = bosses.get(i);
            int barWidth = 800; // chiều rộng tối đa mong muốn
            int barHeight = 10;

            int barX = (screenSize.width - barWidth) / 2;  // 💥 căn giữa màn hình
            int barY = 20;

            int currentWidth = (int)((double)s.joon / (s.level * DEFAULT_JOON) * barWidth);

            g.setColor(Color.BLACK);
            g.drawRoundRect(barX, barY, barWidth, barHeight, 15, 15);

            g.setColor(new Color(152, 8, 8));
            g.fillRoundRect(barX, barY, currentWidth, barHeight, 15, 15);


            g.drawImage(s.image, (int) (s.x - s.getWidth() / 2), (int) (s.y - s.getHeight() / 2), s.width, s.height,
					null);
		}
	}

	protected double chance() {
		return new Random().nextDouble();
	}

	public boolean intersect(Object obj) {
		if (obj instanceof Fire) {
			Fire o = (Fire) obj;
			int deltaX = (int) Math.sqrt(((o.getX() - this.getX()) * (o.getX() - this.getX())
					+ (o.getY() - this.getY()) * (o.getY() - this.getY())));
			if (deltaX <= o.radius + this.radius)
				return true;
		}
		if (obj instanceof SpaceShip) {
			SpaceShip o = (SpaceShip) obj;
			int deltaX = (int) Math.sqrt(((o.getX() - this.getX()) * (o.getX() - this.getX())
					+ (o.getY() - this.getY()) * (o.getY() - this.getY())));
			if (deltaX <= o.radius + this.radius)
				return true;
		}
		return false;
	}

	public void die() {

	}
}
