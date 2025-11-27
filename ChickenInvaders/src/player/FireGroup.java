package player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Manager.Assets;

public class FireGroup {
	ArrayList<Fire> fires = new ArrayList<Fire>();
	private int type;
	private int state;
	private int heat_to_release;
	private int delay;
	private double x;
	private double y;
	private double velocity;
	private double damage;
	private BufferedImage image;
	private BufferedImage image2;

	public FireGroup(double x, double y, int type, int state) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.state = state;
		makeFire();
	}

	private void makeFire() {
		switch (this.type) {
		case 1:
			this.velocity = 6.2;
			this.image = Assets.fire1;
			this.damage = 1.1;
			this.heat_to_release = 5;
			this.delay = 200;
			switch (this.state) {
			case 1:
				fires.add(new Fire(this.x, this.y, this.velocity, 90, this.image, this.damage));
				break;
			case 2:
				fires.add(new Fire(this.x - 10, this.y, this.velocity, 90, this.image, this.damage));
				fires.add(new Fire(this.x + 10, this.y, this.velocity, 90, this.image, this.damage));

				break;
			case 3:
				fires.add(new Fire(this.x, this.y, this.velocity, 90, this.image, this.damage));
				fires.add(new Fire(this.x + 5, this.y, this.velocity, 80, this.image, this.damage));
				fires.add(new Fire(this.x - 5, this.y, this.velocity, 100, this.image, this.damage));
				break;
			case 4:
				fires.add(new Fire(this.x - 10, this.y, this.velocity, 90, this.image, this.damage));
				fires.add(new Fire(this.x + 10, this.y, this.velocity, 90, this.image, this.damage));
				fires.add(new Fire(this.x + 15, this.y, this.velocity, 80, this.image, this.damage));
				fires.add(new Fire(this.x - 15, this.y, this.velocity, 100, this.image, this.damage));
				break;

			case 5:
				fires.add(new Fire(this.x, this.y, this.velocity, 90, this.image, this.damage));
				fires.add(new Fire(this.x + 5, this.y, this.velocity, 84, this.image, this.damage));
				fires.add(new Fire(this.x - 5, this.y, this.velocity, 96, this.image, this.damage));
				fires.add(new Fire(this.x + 10, this.y, this.velocity, 78, this.image, this.damage));
				fires.add(new Fire(this.x - 10, this.y, this.velocity, 102, this.image, this.damage));
				break;
			case 6:
				fires.add(new Fire(this.x, this.y, this.velocity, 90, this.image, this.damage));
				fires.add(new Fire(this.x + 5, this.y, this.velocity, 84, this.image, this.damage));
				fires.add(new Fire(this.x - 5, this.y, this.velocity, 96, this.image, this.damage));
				fires.add(new Fire(this.x - 8, this.y, this.velocity, 84, this.image, this.damage));
				fires.add(new Fire(this.x + 8, this.y, this.velocity, 96, this.image, this.damage));
				fires.add(new Fire(this.x + 15, this.y, this.velocity, 78, this.image, this.damage));
				fires.add(new Fire(this.x - 15, this.y, this.velocity, 102, this.image, this.damage));
				break;
			case 7:
				fires.add(new Fire(this.x - 7, this.y, this.velocity, 90, this.image, this.damage));
				fires.add(new Fire(this.x + 7, this.y, this.velocity, 90, this.image, this.damage));
				fires.add(new Fire(this.x + 5, this.y, this.velocity, 84, this.image, this.damage));
				fires.add(new Fire(this.x - 5, this.y, this.velocity, 96, this.image, this.damage));
				fires.add(new Fire(this.x + 7, this.y, this.velocity, 78, this.image, this.damage));
				fires.add(new Fire(this.x + 17, this.y, this.velocity, 77, this.image, this.damage));
				fires.add(new Fire(this.x - 7, this.y, this.velocity, 102, this.image, this.damage));
				fires.add(new Fire(this.x - 17, this.y, this.velocity, 103, this.image, this.damage));
				break;
			case 8:
				fires.add(new Fire(this.x - 7, this.y, this.velocity, 90, this.image, this.damage));
				fires.add(new Fire(this.x + 7, this.y, this.velocity, 90, this.image, this.damage));
				fires.add(new Fire(this.x + 5, this.y, this.velocity, 84, this.image, this.damage));
				fires.add(new Fire(this.x - 5, this.y, this.velocity, 96, this.image, this.damage));
				fires.add(new Fire(this.x + 7, this.y, this.velocity, 78, this.image, this.damage));
				fires.add(new Fire(this.x + 17, this.y, this.velocity, 77, this.image, this.damage));
				fires.add(new Fire(this.x - 7, this.y, this.velocity, 102, this.image, this.damage));
				fires.add(new Fire(this.x - 17, this.y, this.velocity, 103, this.image, this.damage));
				fires.add(new Fire(this.x - 8, this.y, this.velocity, 84, this.image, this.damage));
				fires.add(new Fire(this.x + 8, this.y, this.velocity, 96, this.image, this.damage));
				break;
			default:
				fires.add(new Fire(this.x - 7, this.y, this.velocity, 90, this.image,
						this.damage + (this.state - 8) / 10));
				fires.add(new Fire(this.x + 7, this.y, this.velocity, 90, this.image,
						this.damage + (this.state - 8) / 10));
				fires.add(new Fire(this.x + 5, this.y, this.velocity, 84, this.image,
						this.damage + (this.state - 8) / 10));
				fires.add(new Fire(this.x - 5, this.y, this.velocity, 96, this.image,
						this.damage + (this.state - 8) / 10));
				fires.add(new Fire(this.x + 7, this.y, this.velocity, 78, this.image,
						this.damage + (this.state - 8) / 10));
				fires.add(new Fire(this.x + 17, this.y, this.velocity, 77, this.image,
						this.damage + (this.state - 8) / 10));
				fires.add(new Fire(this.x - 7, this.y, this.velocity, 102, this.image,
						this.damage + (this.state - 8) / 10));
				fires.add(new Fire(this.x - 17, this.y, this.velocity, 103, this.image,
						this.damage + (this.state - 8) / 10));
				fires.add(new Fire(this.x - 8, this.y, this.velocity, 84, this.image,
						this.damage + (this.state - 8) / 10));
				fires.add(new Fire(this.x + 8, this.y, this.velocity, 96, this.image,
						this.damage + (this.state - 8) / 10));
				fires.add(new Fire(this.x, this.y, this.velocity, 58, this.image, this.damage + (this.state - 8) / 10));
				fires.add(
						new Fire(this.x, this.y, this.velocity, 122, this.image, this.damage + (this.state - 8) / 10));
				break;
			}
			break;
		case 2:
			this.velocity = 4;
			this.image = Assets.fire2;
			this.damage = 1.8;
			this.heat_to_release = 7;
			this.delay = 400;
			switch (this.state) {
			case 1:
				fires.add(new Fire(this.x, this.y, this.velocity, 90, this.image, this.damage));
				break;
			case 2:
				fires.add(new Fire(this.x - 10, this.y, this.velocity, 90, this.image, this.damage));
				fires.add(new Fire(this.x + 10, this.y, this.velocity, 90, this.image, this.damage));
				break;
			case 3:
				fires.add(new Fire(this.x - 10, this.y, this.velocity, 92, this.image, this.damage));
				fires.add(new Fire(this.x + 10, this.y, this.velocity, 88, this.image, this.damage));
				fires.add(new Fire(this.x, this.y - 5, this.velocity + 0.5, 90, this.image, this.damage + 0.25));
				break;
			case 4:
				fires.add(new Fire(this.x - 10, this.y, this.velocity + 0.4, 93, this.image, this.damage + 0.4));
				fires.add(new Fire(this.x + 10, this.y, this.velocity + 0.4, 87, this.image, this.damage + 0.4));
				fires.add(new Fire(this.x, this.y - 5, this.velocity, 90, this.image, this.damage));
				break;
			case 5:
				fires.add(new Fire(this.x - 4, this.y, this.velocity + 0.5, 91, this.image, this.damage + 0.9));
				fires.add(new Fire(this.x + 4, this.y, this.velocity + 0.5, 89, this.image, this.damage + 0.9));
				fires.add(new Fire(this.x - 8, this.y - 8, this.velocity, 94, this.image, this.damage));
				fires.add(new Fire(this.x + 8, this.y - 8, this.velocity, 86, this.image, this.damage));
				break;
			case 6:
				this.image2 = Assets.fire22;
				fires.add(new Fire(this.x - 19, this.y, this.velocity, 94, this.image, this.damage + 0.4));
				fires.add(new Fire(this.x + 19, this.y, this.velocity, 86, this.image, this.damage + 0.4));
				fires.add(new Fire(this.x, this.y - 5, this.velocity, 90, this.image2, this.damage + 4));
				break;
			default:
				this.image2 = Assets.fire22;
				fires.add(new Fire(this.x - 19, this.y, this.velocity, 94, this.image,
						this.damage + 0.4 + (this.state - 8) / 10));
				fires.add(new Fire(this.x + 19, this.y, this.velocity, 86, this.image,
						this.damage + 0.4 + (this.state - 8) / 10));
				fires.add(new Fire(this.x - 19, this.y, this.velocity, 98, this.image,
						this.damage + 0.4 + (this.state - 8) / 10));
				fires.add(new Fire(this.x + 19, this.y, this.velocity, 82, this.image,
						this.damage + 0.4 + (this.state - 8) / 10));
				fires.add(new Fire(this.x, this.y - 5, this.velocity, 90, this.image2,
						this.damage + 4 + (this.state - 8) / 10));
				break;
			}
			break;
		case 3:
			this.velocity =11;
			this.image = Assets.fire3;
			this.damage = 0.9;
			this.heat_to_release = 6;
			this.delay = 200;
			switch (this.state) {
			case 1:
				fires.add(new Fire(this.x, this.y, this.velocity, 90, this.image, this.damage));
				break;
			case 2:
				fires.add(new Fire(this.x - 15, this.y, this.velocity, 89, this.image, this.damage));
				fires.add(new Fire(this.x + 15, this.y, this.velocity, 91, this.image, this.damage));
				break;
			case 3:
				fires.add(new Fire(this.x - 7, this.y + 10, this.velocity, 92, this.image, this.damage));
				fires.add(new Fire(this.x + 7, this.y + 10, this.velocity, 88, this.image, this.damage));
				fires.add(new Fire(this.x, this.y, this.velocity, 90, this.image, this.damage));
				break;
			case 4:
				fires.add(new Fire(this.x - 10, this.y, this.velocity, 89.4, this.image, this.damage));
				fires.add(new Fire(this.x + 10, this.y, this.velocity, 90.6, this.image, this.damage));
				fires.add(new Fire(this.x + 15, this.y + 10, this.velocity, 88, this.image, this.damage));
				fires.add(new Fire(this.x - 15, this.y + 10, this.velocity, 92, this.image, this.damage));
				break;
			case 5:
				fires.add(new Fire(this.x, this.y, this.velocity + 0.1, 90, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x - 10, this.y, this.velocity, 89.4, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x + 10, this.y, this.velocity, 90.6, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x + 15, this.y + 10, this.velocity, 88, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x - 15, this.y + 10, this.velocity, 92, this.image, this.damage + 0.2));
				break;
			case 6:
				fires.add(new Fire(this.x, this.y, this.velocity, 90, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x - 4, this.y + 1, this.velocity, 92, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x + 4, this.y + 1, this.velocity, 88, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x - 8, this.y + 2, this.velocity, 94, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x + 8, this.y + 2, this.velocity, 86, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x - 12, this.y + 3, this.velocity, 96, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x + 12, this.y + 3, this.velocity, 84, this.image, this.damage + 0.2));
				break;
			case 7:
				fires.add(new Fire(this.x - 8, this.y - 5, this.velocity, 89.4, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x + 8, this.y - 5, this.velocity, 90.6, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x - 13, this.y, this.velocity, 92, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x + 13, this.y, this.velocity, 88, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x - 17, this.y, this.velocity, 92, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x + 17, this.y, this.velocity, 88, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x - 21, this.y, this.velocity, 89, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x + 21, this.y, this.velocity, 91, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x - 25, this.y, this.velocity, 89, this.image, this.damage + 0.2));
				fires.add(new Fire(this.x + 25, this.y, this.velocity, 91, this.image, this.damage + 0.2));
				break;
			default:
				fires.add(new Fire(this.x - 8, this.y - 5, this.velocity, 89.4, this.image,
						this.damage + 0.2 + (this.state - 8) / 10));
				fires.add(new Fire(this.x + 8, this.y - 5, this.velocity, 90.6, this.image,
						this.damage + 0.2 + (this.state - 8) / 10));
				fires.add(new Fire(this.x - 13, this.y, this.velocity, 92, this.image,
						this.damage + 0.2 + (this.state - 8) / 10));
				fires.add(new Fire(this.x + 13, this.y, this.velocity, 88, this.image,
						this.damage + 0.2 + (this.state - 8) / 10));
				fires.add(new Fire(this.x - 17, this.y, this.velocity, 92, this.image,
						this.damage + 0.2 + (this.state - 8) / 10));
				fires.add(new Fire(this.x + 17, this.y, this.velocity, 88, this.image,
						this.damage + 0.2 + (this.state - 8) / 10));
				fires.add(new Fire(this.x - 21, this.y, this.velocity, 89, this.image,
						this.damage + 0.2 + (this.state - 8) / 10));
				fires.add(new Fire(this.x + 21, this.y, this.velocity, 91, this.image,
						this.damage + 0.2 + (this.state - 8) / 10));
				fires.add(new Fire(this.x - 25, this.y, this.velocity, 89, this.image,
						this.damage + 0.2 + (this.state - 8) / 10));
				fires.add(new Fire(this.x + 25, this.y, this.velocity, 91, this.image,
						this.damage + 0.2 + (this.state - 8) / 10));
				break;
			}
			break;
		}
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getHeat_to_release() {
		return heat_to_release;
	}

	public void setHeat_to_release(int heat_to_release) {
		this.heat_to_release = heat_to_release;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDelay() {
		return delay;
	}

	public void setShooting_delay(int shooting_delay) {
		this.delay = shooting_delay;
	}

}
