package player;

import static Manager.Dimentions.screenSize;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import Manager.Assets;
import Manager.controller.Collision;
import Manager.controller.GameAction;
import Manager.controller.Wave;
import Panels.UserPanels.GamePanel;
import Panels.UserPanels.UserChooser;
import groups.groups.GeneralGroups;

@SuppressWarnings("serial")
public class SpaceShip implements Animatable, Serializable {
	private static transient BufferedImage shieldImage = Assets.shield;
	public ArrayList<Fire> tirs = new ArrayList<Fire>();
	public transient ArrayList<player.Missile> missile = new ArrayList<player.Missile>();
	public transient BufferedImage image;
	public String imagePath;

	private long die_time;
	private long shelik_time = 0;
	private long move_time = 0;
	private long shield_time = 0;
	private int coins = 0;
	private int width;
	private int height;
	private int fire_delay;
	public int bomb = 5;
	public int lives = 5;
	public int radius = 50;
	private int fire_type = 1;
	private int fire_stage = 1 ;

	private double x = screenSize.width / 2;
	private double y = screenSize.height / 2;
	private double fan_strength = 0.14;
	public double heat = 0;
	public double Max_heat = 100;

	public boolean shooting = false;
	public boolean move_up = false;
	public boolean move_down = false;
	public boolean move_right = false;
	public boolean move_left = false;
	public boolean has_shield = false;
	private boolean is_dead = false;
	private boolean dagh = false;



	public SpaceShip(int x, int y, String i) {
		this.x = x;
		this.y = y;
		imagePath = i;
		image = Assets.SpaceShip2;
		this.width = 110;
		this.height = 110;
	}

	public int getX() {
		return (int) x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return (int) y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public double getFan_strength() {
		return fan_strength;
	}

	public void setFan_strength(double fan_strength) {
		this.fan_strength = fan_strength;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
		GamePanel.getCurrent().updateState();
		UserChooser.create().save();
	}

	public int getFire_type() {
		return fire_type;
	}

	public void setFire_type(int fire_type) {
		this.fire_type = fire_type;
	}

	public int getFire_stage() {
		return fire_stage;
	}

	public void setFire_stage(int fire_stage) {
		this.fire_stage = fire_stage;
	}

	public void collapse(Wave wave) {
		// Wave wave = GamePanel.getCurrent().getController().wave;
		for (GeneralGroups g : wave.groups) {
			Collision.spaceShipChickenCollapse(this, g.chickens);
		}
		Collision.chickenDropCollision(this, wave.drops);
		if (wave.boss != null)
			Collision.spaceShipBossCollapse(this, wave.boss.bosses);
	}

	public void update() {
//		this.collapse();
		this.spaceShipPowerUp();
		this.move();
		this.constantShelik(x, y, fire_type, fire_stage);
		for (int i = missile.size() - 1; i >= 0; i--) {
			missile.get(i).move();
			if (missile.get(i).getY() < 0) {
				missile.remove(i);
			}
		}
		this.isAlive();
	}

	@Override
	public void paint(Graphics g) {
		for (Fire tir : tirs) {
			tir.paint(g);
		}
		for (player.Missile missiles : missile) {
			missiles.paint(g);
		}
		if (this.has_shield)
			this.drawShield(g);
		g.drawImage(this.image, (int) this.x - this.getWidth() / 2, (int) this.y - this.getHeight() / 2,
				this.getWidth(), this.getHeight(), null);
	}

	int rotationAngle = 1;

	private void drawShield(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform af = AffineTransform.getTranslateInstance(this.x - this.width, this.y - this.height);
		af.rotate(Math.toRadians(rotationAngle), shieldImage.getWidth() / 2, shieldImage.getHeight() / 2);
		g2.drawImage(shieldImage, af, null);
		rotationAngle += 2;
	}

	public void launchMissile() {
		if (bomb > 0) {
			missile.add(new Missile((int) this.x, (int) this.y));
			bomb--;
		}
	}

	public void constantShelik(double x, double y, int fire_type, int fire_stage) {
		if (System.currentTimeMillis() - shelik_time > fire_delay && shooting && dagh == false && System.currentTimeMillis() - Wave.time_end_wave > 5000) {

            Assets.sound.playSound("shoot");

            FireGroup f = new FireGroup(x, y, fire_type, fire_stage);
			tirs.addAll(f.fires);
			this.heat += f.getHeat_to_release();
			fire_delay = f.getDelay();
			shelik_time = System.currentTimeMillis();
		}

	}

	private void spaceShipPowerUp() {
		if (this.lives > 0 && System.currentTimeMillis() - die_time > 6500) {
			if (heat > 0) {
				heat -= this.fan_strength;
			}
			if (heat <= 0) {
				dagh = false;
			}
			if (heat >= Max_heat) {
				dagh = true;
			}
		} else {
			this.heat = 1;
			dagh = true;
		}
	}

	public void initLoaded() {
//        System.out.println(imagePath);
		this.image = Assets.loadImage(imagePath);
		// this.tirs = new ArrayList<Fire>();
		if(tirs.size()>0)
		switch (fire_type) {
		case 1:
			for (Fire f : tirs) {
				f.image = Assets.fire1;
			}
			break;
		case 2:
			for (Fire f : tirs) {
				f.image = Assets.fire2;
			}
			break;
		case 3:
			for (Fire f : tirs) {
				f.image = Assets.fire3;
			}
			break;
		}

		this.missile = new ArrayList<Missile>();
	}

	@Override
	public void move() {
		if (this.is_dead == false && System.currentTimeMillis() - move_time > 0.001) {
			if (move_up == true && this.y - this.radius >= 0) {
				this.y -= 4;
			}
			if (move_down == true && this.y + this.radius <= screenSize.getHeight()) {
				this.y += 4;
			}
			if (move_right == true && this.x + this.radius <= screenSize.getWidth()) {
				this.x += 4;
			}
			if (move_left == true && this.x - this.radius >= 0) {
				this.x -= 4;

			}
			move_time = System.currentTimeMillis();
		}
		for (int i = tirs.size() - 1; i >= 0; i--) {
			tirs.get(i).move();
			if (tirs.get(i).getY() < 0) {
				tirs.remove(tirs.get(i));
			}
		}
	}

	public void move(MouseEvent arg0) {
		if (this.is_dead == false) {
			if (arg0.getX() + this.radius <= screenSize.getWidth() && arg0.getX() - this.radius >= 0) {
				this.setX(arg0.getX());
			}
			if (this.radius + arg0.getY() <= screenSize.getHeight() && arg0.getY() - this.radius >= 0) {
				this.setY(arg0.getY());
			}
			GamePanel.getCurrent().repaint();
			GamePanel.getCurrent().validate();
		}
	}

	public void die() {
        Assets.sound.playSound("shipdie");
		if (System.currentTimeMillis() - die_time > 5000) {
			this.is_dead = true;
			this.dagh = true;
			this.heat = 1;
			this.x = -100;
			this.y = -100;
			this.lives--;
			this.coins = 0;
			GamePanel.getCurrent().updateState();
			die_time = System.currentTimeMillis();
		}
	}

	private boolean isAlive() {
		if (this.is_dead == true && System.currentTimeMillis() - die_time > 5000 && this.lives > 0) {
			this.is_dead = false;
			this.x = 130;
			this.y = screenSize.height - 130;
			has_shield = true;
			shield_time = System.currentTimeMillis();
		} else if (this.lives == 0) {
			GameAction.gameOver();
		}
		if (has_shield == true && System.currentTimeMillis() - shield_time > 5000) {
			has_shield = false;
		}
		return !this.is_dead;
	}

//	public String getState() {
//		return ;
//	}
}
