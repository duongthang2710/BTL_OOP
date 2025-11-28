package Manager.generals;

import static Manager.Dimentions.screenSize;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Timer;

import Manager.Assets;
import Manager.controller.GameAction;
import Manager.controller.GameController;
import Manager.controller.GameKey;
import Manager.generals.Button.LabelButton;
import player.Animatable;
import player.Player;

@SuppressWarnings("serial")
public class GeneralGame extends JPanel implements Animatable {

	public static GeneralGame game = new GeneralGame();
	public Player player;

	public Background back = Assets.movingback6;
	Timer run;
	LabelButton point = new LabelButton("Points 0", 0, 0, 200, 100, 20);
	LabelButton state = new LabelButton("", 0, screenSize.height - 40, 280, 40, 18);
	GameAction action;
	public GameController controller;
	public boolean paused = false;
	int x = 10;

	private GeneralGame() {
	}



	public static GeneralGame create(Player player) {

        game.setLayout(null);
        game.player = player;

        System.out.println("Hello");



		game.setLayout(null);
		game.player = player;
		game.controller = new GameController(game.player.stage, game.player.wave);
		game.updateState();
		GameAction.unvisiblePointer(true, (JPanel) game);
		game.add(game.point);
		game.add(game.state);
		game.action = new GameAction();
		game.run = new Timer(game.x, game.action);
		game.run.start();
		game.addKeyListener(new GameKey());
		game.player.first_time = System.currentTimeMillis() / 1000;
		return game;
	}

	public void updateState() {
		state.setText("          " + player.spaceShip.lives + "          " + player.spaceShip.bomb + "          "
				+ player.spaceShip.getFire_stage() + "          " + player.spaceShip.getCoins());
		point.setText("Points" + player.score);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		back.drawMoving(g);
		player.spaceShip.paint(g);
		game.controller.paint(g);
		drawHeatbar(g);
		g.setColor(LabelButton.ExitColor);
		g.drawRect(0, 70, 30, (int) game.player.spaceShip.Max_heat * 2);
		g.drawImage(Assets.bar, 0, screenSize.height - 40, 280, 40, null);
	}

	private void drawHeatbar(Graphics g) {
		Color startColor = Color.blue;
		Color endColor = Color.red;
		int startX = 0, startY = 70, endX = 30, endY = (int) player.spaceShip.heat * 2;
		GradientPaint gradient = new GradientPaint(startX, startY, startColor, endX, endY, endColor);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(gradient);
		g2d.fill(new Rectangle(startX, startY, endX, endY));
	}

	@Override
	public void move() {
		player.spaceShip.update();
		game.back.move();
		game.controller.move();
	}

	public static GeneralGame getCurrent() {
		return game;
	}

	@Override
	public void addNotify() {
		super.addNotify();
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setRequestFocusEnabled(true);
	}

}
