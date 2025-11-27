package Panels.multiPlayer.clientVersion;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import Manager.controller.GameAction;
import Manager.controller.GameController;
import Manager.controller.Manager;
import Panels.UserPanels.GamePanel;
import player.Player;

@SuppressWarnings("serial")
public class ClientGame extends GamePanel{
	private ClientGame() {
	}
	public static GamePanel getcurrent() {
		return game;
	}
	public static GamePanel create(Player player) {
		game.setLayout(null);
		game.player = player;
		game.MAP_PLAYERS.put(game.player.name, game.player);
		game.setController(new GameController(game.getPlayer().stage, game.getPlayer().wave));
		game.updateState();
		GameAction.unvisiblePointer(true, (JPanel) game);
		game.add(game.getPoint());
		game.add(game.getState());
		game.setAction(new GameAction());
		game.setRun(new Timer(game.getX(), game.getAction()));
		game.getRun().start();
		game.addKeyListener(new GameKeyListener());
		game.getPlayer().first_time = System.currentTimeMillis() / 1000;
		return game;
	}
	
	@Override
	public void move() {
		super.move();
//		ServerControlPanel.getcurrent().playerList.removeAllItems();
		ArrayList<String> keys = new ArrayList<>(MAP_PLAYERS.keySet());
		for(int i = keys.size()-1;i>=0;i--) {
//			ServerControlPanel.getcurrent().playerList.addItem(keys.get(i));
		}
	}
}
class GameKeyListener implements KeyListener {
	static GamePanel game = ClientGame.getCurrent();

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 32)
			game.getPlayer().rocket.shooting = true;
		if (e.getKeyCode() == 10)
			System.out.println("");
		if (e.getKeyCode() == 37)
			game.getPlayer().rocket.move_left = true;
		if (e.getKeyCode() == 38)
			game.getPlayer().rocket.move_up = true;
		if (e.getKeyCode() == 39)
			game.getPlayer().rocket.move_right = true;
		if (e.getKeyCode() == 40)
			game.getPlayer().rocket.move_down = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// System.out.println(e.getKeyCode());
		switch (e.getKeyCode()) {
		case 27:
			pauseTheGame();
			break;
		}
		if (e.getKeyCode() == 32)
			game.getPlayer().rocket.shooting = false;
		if (e.getKeyCode() == 10)
			System.out.println("");
		if (e.getKeyCode() == 37)
			game.getPlayer().rocket.move_left = false;
		if (e.getKeyCode() == 38)
			game.getPlayer().rocket.move_up = false;
		if (e.getKeyCode() == 39)
			game.getPlayer().rocket.move_right = false;
		if (e.getKeyCode() == 40)
			game.getPlayer().rocket.move_down = false;
	}

	private void pauseTheGame() {
		game.getPlayer().ending_time = System.currentTimeMillis() / 1000;
		game.getPlayer().time_in_game += (game.getPlayer().ending_time - game.getPlayer().first_time);
		game.getPlayer().first_time = System.currentTimeMillis() / 1000;
		GameAction.unvisiblePointer(false, (JPanel) game);
		game.getRun().stop();
		game.setPaused(true);
		Manager.setPanel(ClientControlPanel.getCurrent());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}


}