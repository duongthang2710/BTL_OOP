package Manager.controller;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Panels.UserPanels.GamePanel;
import Panels.UserPanels.PausePanel;
import Panels.hallOfFame.HallPanel;
import SaveLoad.records.Recorder;
import player.Player;

public class GameAction implements MouseMotionListener, MouseListener, ActionListener {
	static GamePanel game = GamePanel.getCurrent();

	public GameAction() {
		game.addMouseMotionListener(this);
		game.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (!game.isPaused()) {
			if (SwingUtilities.isRightMouseButton(arg0)) {
				game.getPlayer().rocket.launchMissile();
				game.updateState();
			}
			if (SwingUtilities.isLeftMouseButton(arg0)) {
				game.getPlayer().rocket.shooting = true;
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		game.getPlayer().rocket.shooting = false;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		mouseMoved(arg0);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		if (!game.isPaused()) {
			game.getPlayer().rocket.move(arg0);
		}
	}

	public static void pauseTheGame() {
		game.getPlayer().ending_time = System.currentTimeMillis() / 1000;
		game.getPlayer().time_in_game += (game.getPlayer().ending_time - game.getPlayer().first_time);
		System.out.println(game.getPlayer().time_in_game);
		game.getPlayer().first_time = System.currentTimeMillis() / 1000;
		unvisiblePointer(false, (JPanel) game);
		game.getRun().stop();
		game.setPaused(true);
		Manager.setPanel(PausePanel.create());
	}

	public static void unvisiblePointer(boolean b, JPanel game) {
		if (b)
			game.setCursor(game.getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB),
					new Point(), null));
		else {
			game.setCursor(Cursor.getDefaultCursor());
		}
	}

	public static void gameOver() {
		pauseTheGame();
        Recorder.record(game.getPlayer());
		game.setPlayer(new Player(game.getPlayer().name));
		game.updateState();
//        System.out.println(game.getPlayer().name);
		Manager.setPanel(HallPanel.create());
	}

	long beginTime, timeTaken, timeLeft;
	static final int UPDATES_PER_SEC = 1000; // number of game update per second
	static final long UPDATE_PERIOD_NSEC = 1000000000L / UPDATES_PER_SEC; // nanoseconds

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!game.isPaused()) {
			beginTime = System.nanoTime();
			game.move();
			game.repaint();
//			System.out.println("player size : "+game.players.size());
			timeTaken = System.nanoTime() - beginTime;
		}
		timeLeft = (UPDATE_PERIOD_NSEC - timeTaken) / 1000000; // in milliseconds
		if (timeLeft < 10)
			timeLeft = 10;
		game.setX((int) timeLeft);
	}

}
