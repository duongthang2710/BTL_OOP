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
	public static void skullDropCollision(SpaceShip rocket, ArrayList<Drops> drops) {
		for (int i = drops.size() - 1; i >= 0; i--) {
			if (drops.get(i) instanceof Eggs && rocket.has_shield == false) {
				drops.get(i).intersect(rocket);
			} else if (drops.get(i) instanceof groups.Chicken.drops.Coin) {
				drops.get(i).intersect(rocket);
			} else if (drops.get(i) instanceof groups.Chicken.drops.HeatEnhancer) {
				drops.get(i).intersect(rocket);
			} else if (drops.get(i) instanceof FireEnhancer) {
				drops.get(i).intersect(rocket);
			}
		}
	}

	public static void rocketSkullCollapse(SpaceShip rocket, ArrayList<GeneralChicken> skulls) {
		a: for (int i = skulls.size() - 1; i >= 0; i--) {
			if (skulls.get(i).intersects(rocket) && !rocket.has_shield) {
				rocket.die();
				skulls.get(i).die();
                Assets.sound.playSound("chickendie");
				skulls.remove(i);
				break a;
			}
			for (int j = rocket.tirs.size() - 1; j >= 0 && rocket.tirs.size() >= 0; j--) {
				if (skulls.get(i).intersects(rocket.tirs.get(j))) {
					if (skulls.get(i).joon <= 0) {
						skulls.get(i).die();
                        Assets.sound.playSound("chickendie");
						GamePanel.getCurrent().getPlayer().score+=skulls.get(i).level;
						GamePanel.getCurrent().updateState();
						skulls.remove(skulls.get(i));
					} else {
						skulls.get(i).joon -=rocket.tirs.get(j).getDamage();
					}
					rocket.tirs.remove(j);
					break a;
				}
			}

		}
	}

	public static void rocketGhoolCollapse(SpaceShip rocket, ArrayList<Boss> ghool) {
		a: for (int i = ghool.size() - 1; i >= 0 && ghool.size() > 0; i--) {
			if (ghool.get(i).intersect(rocket) && !rocket.has_shield) {
				rocket.die();
				if (ghool.get(i).joon > 20) {
					ghool.get(i).joon -= 20;
				} else {
					ghool.get(i).die();
                    Assets.sound.playSound("chickendie");
					ghool.remove(i);
				}
				break a;
			}
			for (int j = rocket.tirs.size() - 1; j >= 0 && rocket.tirs.size() >= 0; j--) {
				if (ghool.get(i).intersect(rocket.tirs.get(j))) {
					if (ghool.get(i).joon <= 0) {
						ghool.get(i).die();
						ghool.remove(ghool.get(i));
					} else {
						ghool.get(i).joon -= 6;
					}
					rocket.tirs.remove(j);
					break a;
				}
			}
		}
	}
}