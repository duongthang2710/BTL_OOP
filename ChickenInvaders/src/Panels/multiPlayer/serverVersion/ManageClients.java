package Panels.multiPlayer.serverVersion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.Timer;

import player.Player;

public class ManageClients implements Runnable {
	Socket socket;
	ObjectInputStream din;
	ObjectOutputStream dout;
	Player PlayerRead;
	Timer sendObj;

	public ManageClients(Socket socket) {
		this.socket = socket;
		try {
			din = new ObjectInputStream(socket.getInputStream());
			dout = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public Player getInPlayer() {
//		return currentPlayer;
//	}

	@Override
	public void run() {

		sendObj = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ArrayList<Player> values = new ArrayList<Player>(ServerGame.getcurrent().MAP_PLAYERS.values());
				try {
					dout.writeObject(values);
					dout.reset();
					dout.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		sendObj.start();
		while (true) {
			try {
				PlayerRead = (Player) din.readObject();
				PlayerRead.spaceShip.initLoaded();
                System.out.println(PlayerRead.spaceShip.imagePath);
				if (PlayerRead.spaceShip.shooting) {
					ServerGame.getcurrent().player.spaceShip.constantShelik(PlayerRead.spaceShip.getX(),
							PlayerRead.spaceShip.getY(), PlayerRead.spaceShip.getFire_type(),
							PlayerRead.spaceShip.getFire_stage());
				}
				ServerGame.getcurrent().MAP_PLAYERS.put(PlayerRead.name, PlayerRead);
			} catch (Exception e) {
				sendObj.stop();
				break;
			}
		}
	}

}
