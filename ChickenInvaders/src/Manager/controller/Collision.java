package Manager.controller;

import java.util.ArrayList;

import Panels.UserPanels.GamePanel;
import groups.Chicken.GeneralChicken;
import groups.Chicken.Boss;
import groups.Chicken.drops.Eggs;
import groups.Chicken.drops.Drops;
import groups.Chicken.drops.FireEnhancer;
import player.SpaceShip;
import Manager.Assets;

public class Collision {
//polygon
	public static void chickenDropCollision(SpaceShip spaceShip, ArrayList<Drops> drops) {
		for (int i = drops.size() - 1; i >= 0; i--) {
			if (drops.get(i) instanceof Eggs && spaceShip.has_shield == false) {
				drops.get(i).intersect(spaceShip);
			} else if (drops.get(i) instanceof groups.Chicken.drops.Coin) {
				drops.get(i).intersect(spaceShip);
			} else if (drops.get(i) instanceof groups.Chicken.drops.HeatEnhancer) {
				drops.get(i).intersect(spaceShip);
			} else if (drops.get(i) instanceof FireEnhancer) {
				drops.get(i).intersect(spaceShip);
			}
		}
	}

	public static void spaceShipChickenCollapse(SpaceShip spaceShip, ArrayList<GeneralChicken> chickens) {
		a: for (int i = chickens.size() - 1; i >= 0; i--) {
			if (chickens.get(i).intersects(spaceShip) && !spaceShip.has_shield) {
				spaceShip.die();
				chickens.get(i).die();
                Assets.sound.playSound("chickendie");
				chickens.remove(i);
				break a;
			}
			for (int j = spaceShip.tirs.size() - 1; j >= 0 && spaceShip.tirs.size() >= 0; j--) {
				if (chickens.get(i).intersects(spaceShip.tirs.get(j))) {
					if (chickens.get(i).joon <= 0) {
						chickens.get(i).die();
                        Assets.sound.playSound("chickendie");
						GamePanel.getCurrent().getPlayer().score+= chickens.get(i).level;
						GamePanel.getCurrent().updateState();
						chickens.remove(chickens.get(i));
					} else {
						chickens.get(i).joon -=spaceShip.tirs.get(j).getDamage();
					}
					spaceShip.tirs.remove(j);
					break a;
				}
			}

		}
	}

	public static void spaceShipBossCollapse(SpaceShip spaceShip, ArrayList<Boss> bosses) {
		a: for (int i = bosses.size() - 1; i >= 0 && bosses.size() > 0; i--) {
			if (bosses.get(i).intersect(spaceShip) && !spaceShip.has_shield) {
				spaceShip.die();
				if (bosses.get(i).joon > 20) {
					bosses.get(i).joon -= 20;
				} else {
					bosses.get(i).die();
                    Assets.sound.playSound("chickendie");
					bosses.remove(i);
				}
				break a;
			}
			for (int j = spaceShip.tirs.size() - 1; j >= 0 && spaceShip.tirs.size() >= 0; j--) {
				if (bosses.get(i).intersect(spaceShip.tirs.get(j))) {
					if (bosses.get(i).joon <= 0) {
						bosses.get(i).die();
						bosses.remove(bosses.get(i));
					} else {
						bosses.get(i).joon -= 6;
					}
					spaceShip.tirs.remove(j);
					break a;
				}
			}
		}
	}
}