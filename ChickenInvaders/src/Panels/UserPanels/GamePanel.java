package Panels.UserPanels;

import static Manager.Dimentions.screenSize;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JPanel;
import javax.swing.Timer;

import Manager.Assets;
import Manager.controller.GameAction;
import Manager.controller.GameController;
import Manager.controller.GameKey;
import Manager.generals.Background;
import Manager.generals.Button.LabelButton;
import player.Animatable;
import player.Player;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Animatable {
	protected static GamePanel game = new GamePanel();
//	public ArrayList<Player> players = new ArrayList<>();
	public LinkedHashMap<String, Player> MAP_PLAYERS = new LinkedHashMap<>();

	public Player player;
	protected Background back = Assets.movingback7;
	protected Timer run;
	protected LabelButton point = new LabelButton("Points 0", 0, 0, 200, 100, 20);
	protected LabelButton state = new LabelButton("", 0, screenSize.height - 40, 280, 40, 18);
	protected GameAction action;
	protected GameController controller;
	protected boolean paused = false;
	protected int x = 10;

	protected GamePanel() {
	}

	public static GamePanel create(Player player) {
		game.setLayout(null);
		game.setPlayer(player);
		game.MAP_PLAYERS.put(game.player.name, game.player);
		game.setController(new GameController(game.getPlayer().stage, game.getPlayer().wave));
		game.updateState();
		GameAction.unvisiblePointer(true, (JPanel) game);
		game.add(game.getPoint());
		game.add(game.getState());
		game.setAction(new GameAction());
		game.setRun(new Timer(game.getX(), game.getAction()));
		game.getRun().start();
		game.addKeyListener(new GameKey());
		game.getPlayer().first_time = System.currentTimeMillis() / 1000;
		return game;
	}

	public void updateState() {
		getState().setText("          " + getPlayer().rocket.lives + "          " + getPlayer().rocket.bomb
				+ "          " + getPlayer().rocket.getFire_stage() + "          " + getPlayer().rocket.getCoins());
		getPoint().setText("Points" + getPlayer().score);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		getBack().drawMoving(g);
		ArrayList<Player> values = new ArrayList<Player>(MAP_PLAYERS.values());

		for (int t = values.size() - 1; t >= 0; t--) {
			values.get(t).rocket.paint(g);
		}
		game.getController().paint(g);
		drawHeatbar(g);
		g.setColor(LabelButton.ExitColor);
		g.drawRect(0, 70, 30, (int) game.getPlayer().rocket.Max_heat * 2);
		g.drawImage(Assets.bar, 0, screenSize.height - 40, 280, 40, null);
	}

	private void drawHeatbar(Graphics g) {
		Color startColor = Color.blue;
		Color endColor = Color.red;
		int startX = 0, startY = 70, endX = 30, endY = (int) getPlayer().rocket.heat * 2;
		GradientPaint gradient = new GradientPaint(startX, startY, startColor, endX, endY, endColor);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(gradient);
		g2d.fill(new Rectangle(startX, startY, endX, endY));
	}

	@Override
	public void move() {
		MAP_PLAYERS.put(game.player.name, game.player);
		ArrayList<Player> values = new ArrayList<Player>(MAP_PLAYERS.values());

		for (int t = values.size() - 1; t >= 0; t--) {
			values.get(t).rocket.update();
			values.get(t).rocket.collapse(game.controller.wave);
		}
//___________________________________________________________________>
		game.getBack().move();
		game.getController().move();
	}

	public static GamePanel getCurrent() {
		return game;
	}

	@Override
	public void addNotify() {
		super.addNotify();
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setRequestFocusEnabled(true);
	}

	public Timer getRun() {
		return run;
	}

	public void setRun(Timer run) {
		this.run = run;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public Background getBack() {
		return back;
	}

	public void setBack(Background back) {
		this.back = back;
	}

	public GameController getController() {
		return controller;
	}

	public void setController(GameController controller) {
		this.controller = controller;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public LabelButton getPoint() {
		return point;
	}

	public void setPoint(LabelButton point) {
		this.point = point;
	}

	public LabelButton getState() {
		return state;
	}

	public void setState(LabelButton state) {
		this.state = state;
	}

	public GameAction getAction() {
		return action;
	}

	public void setAction(GameAction action) {
		this.action = action;
	}

}
