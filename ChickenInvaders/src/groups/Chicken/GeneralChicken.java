package groups.Chicken;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import Manager.Assets;
import Manager.Dimentions;
import Panels.UserPanels.GamePanel;
import groups.Chicken.drops.Coin;
import groups.Chicken.drops.FireEnhancer;
import groups.Chicken.drops.HeatEnhancer;
import player.Fire;
import player.SpaceShip;

public abstract class GeneralChicken {
	public float x_Destin;
	public float y_Destin;
	protected float x;
	protected float y;
	protected float v_x;
	protected float v_y;
	protected float tir_velocity;
	protected double tir_chance;
	protected double coin_chance;
	protected double heat_chance;
	protected double FireEnhancer_chance;
	public int joon;
	public int level;
	protected int width = 80;
	protected int height = 80;
	protected int radius = 40;
	public byte DefaultVelicity = 1;

    protected BufferedImage[] images = new BufferedImage[12];   // nhiều frame
    protected int currentFrame = 0;     // frame hiện tại
    protected int frameCount = 12;       // số frame
    protected int animationSpeed = 6;   // tốc độ đổi frame
    protected int animationCounter = 0; // đếm tick




    public GeneralChicken(int level) {
		this.level = level;
		makeChicken(level);
	}

    public void updateAnimation() {

        animationCounter++;
        if (animationCounter >= animationSpeed) {
            animationCounter = 0;
            currentFrame = (currentFrame + 1) % frameCount;
        }
    }


    protected void makeChicken(int level) {
		switch (level) {
		case 1:
			this.joon = 2;
			this.tir_chance = 0.05;
			this.tir_velocity = 2;
			for (int i = 0; i < frameCount; i++){
                images[i] = Assets.ChickenYellow.get(i);
            }
			break;
		case 2:
			this.joon = 3;
			this.tir_chance = 0.05;
			this.tir_velocity = 4;
            for (int i = 0; i < frameCount; i++){
                images[i] = Assets.ChickenGreen.get(i);
            }
			break;
		case 3:
			this.joon = 5;
			this.tir_chance = 0.1;
			this.tir_velocity = 8;
            for (int i = 0; i < frameCount; i++){
                images[i] = Assets.ChickenBlue.get(i);
            }
			break;
		case 4:
			this.joon = 8;
			this.tir_chance = 0.2;
			this.tir_velocity = 8;
            for (int i = 0; i < frameCount; i++){
                images[i] = Assets.ChickenRed.get(i);
            }
			break;
		}
		this.coin_chance = 0.06;
		this.heat_chance = 0.03;
		this.FireEnhancer_chance = 0.03;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getV_x() {
		return v_x;
	}

	public void setV_x(float v_x) {
		this.v_x = v_x;
	}

	public float getV_y() {
		return v_y;
	}

	public void setV_y(float v_y) {
		this.v_y = v_y;
	}

	public float getTir_velocity() {
		return tir_velocity;
	}

	public void move() {
		this.x += this.v_x;
		this.y += this.v_y;
        updateAnimation();
	}

	public double getTir_chance() {
		return tir_chance;
	}

	public boolean inEdgeX() {
		if ((this.x + this.getWidth() / 2 >= Dimentions.screenSize.getWidth() && this.getV_x() > 0)
				|| (this.x - this.getWidth() / 2 <= 0 && this.getV_x() < 0)) {
			this.setV_x(-this.getV_x());
			return true;
		}
		return false;
	}

	public boolean inEdgeY() {
		if ((this.y + this.getHeight() / 2 >= Dimentions.screenSize.getHeight() && this.getV_y() > 0)
				|| (this.x - this.getWidth() / 2 <= 0 && this.getV_y() < 0)) {
			this.setV_y(-this.getV_y());
			return true;
		}
		return false;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public double chance() {
		Random r = new Random();
		return r.nextDouble();
	};

	public void moveDestin() {
		float deltaX = x_Destin - this.getX();
		float deltaY = y_Destin - this.getY();
		float teta = (float) Math.atan2(deltaY, deltaX);
		this.v_x = (float) Math.cos(teta) * this.DefaultVelicity;
		this.v_y = (float) Math.sin(teta) * this.DefaultVelicity;
		move();
	}

	public boolean die() {
		if (this.chance() <= this.FireEnhancer_chance) {
			GamePanel.getCurrent().getController().wave.drops
					.add(new FireEnhancer(this.getX(), this.getY(), 0, 1.3, (int) (chance() * 3) + 1));
			return true;
		}
		if (chance() <= this.coin_chance) {
			GamePanel.getCurrent().getController().wave.drops.add(new Coin(this.getX(), this.getY(), 0, 1.3));
			return true;
		}
		if (chance() <= this.heat_chance) {
			GamePanel.getCurrent().getController().wave.drops.add(new HeatEnhancer(this.getX(), this.getY(), 0, 1.3));
			return true;
		}
		return false;
	}

	public boolean intersects(SpaceShip rocket) {
		int deltaX = (int) Math.sqrt((this.getX() - rocket.getX()) * (this.getX() - rocket.getX())
				+ (this.getY() - rocket.getY()) * (this.getY() - rocket.getY()));
		if (this.radius + rocket.radius >= deltaX)
			return true;
		return false;
	}

	public boolean intersects(Fire tir) {
		int deltaX = (int) Math.sqrt((this.getX() - tir.getX()) * (this.getX() - tir.getX())
				+ (this.getY() - tir.getY()) * (this.getY() - tir.getY()));
		if (this.radius + tir.radius >= deltaX)
			return true;
		return false;
	}



	public abstract void paint(Graphics g);

	public abstract void orbit(double v);
}
