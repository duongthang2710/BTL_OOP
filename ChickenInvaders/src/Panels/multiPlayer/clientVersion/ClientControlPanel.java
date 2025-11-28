package Panels.multiPlayer.clientVersion;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTable;
import javax.swing.Timer;

import Manager.controller.Manager;
import Manager.generals.Background;
import Manager.generals.Panel;
import Manager.generals.Button.Acts;
import Manager.generals.Button.LabelButton;
import Panels.UserPanels.UserSetting;
import player.Player;

@SuppressWarnings("serial")
public class ClientControlPanel extends Panel {
	private static ClientControlPanel panel = new ClientControlPanel();
	Player player;
	LabelButton launch = new LabelButton("Launch The Game", 850, 400, 400, 50, 40);
	LabelButton exit = new LabelButton("Exit To Main Menu", 850, 550, 400, 50, 40);
	PlayerTable table;
	Socket socket;
	ObjectInputStream din;
	ObjectOutputStream dout;
	String IP;
	int port;

	private ClientControlPanel() {
		super(Background.backs.get("Backgrounds\\Background7.jpg"));
		init();
	}

	private void init() {
		Collections.addAll(drawables, launch, exit);
		for (Component c : drawables)
			this.add(c);
		launch.addMouseListener(new Acts(launch) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				launch.setForeground(LabelButton.ExitColor);
				Manager.setPanel(ClientGame.create(player));
				ClientGame.getCurrent().setPaused(false);

			}
		});
		exit.addMouseListener(new Acts(exit) {
			public void mousePressed(MouseEvent arg0) {
				exit.setForeground(LabelButton.ExitColor);
				Manager.setPanel(UserSetting.getcurrent());
			}
		});
	}

	public static ClientControlPanel create(String IP, int port, Player player) throws IOException {
		panel.player = player;
		panel.player = new Player(player.name);
		panel.socket = new Socket(IP, port);
		Timer sendObj = new Timer(10, new ActionListener() {
			{
				panel.dout = new ObjectOutputStream(panel.socket.getOutputStream());
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					panel.dout.writeObject(panel.player);
					panel.dout.reset();
					panel.dout.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		sendObj.start();
		Thread receive = new Thread(new Runnable() {

			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				try {
					panel.din = new ObjectInputStream(panel.socket.getInputStream());

					while (true) {
						ArrayList<Player> pls = (ArrayList<Player>) panel.din.readObject();
						for (Player p : pls) {
							if (!p.name.equals(panel.player.name)) {
								p.spaceShip.initLoaded();
								if (p.spaceShip.shooting) {
									panel.player.spaceShip.constantShelik(p.spaceShip.getX(), p.spaceShip.getY(),
											p.spaceShip.getFire_type(), p.spaceShip.getFire_stage());
								}
								ClientGame.getcurrent().MAP_PLAYERS.put(p.name, p);
							}
							panel.table = new PlayerTable(
									new ArrayList<>(ClientGame.getcurrent().MAP_PLAYERS.values()));
							panel.add(panel.table.jt);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		receive.setPriority(Thread.MAX_PRIORITY);
		receive.start();
		return panel;
	}

	public static ClientControlPanel getCurrent() {
		return panel;
	}
}

class PlayerTable {
	static String column[] = { "NAME", "SCORE", "STAGE", "LEVEL" };
	String data[][];
	String[] g = { "amir", "20", "1", "1" };
	JTable jt;

	PlayerTable(ArrayList<Player> players) {
		data = new String[players.size()][4];
		for (int t = players.size() - 1; t >= 0; t--) {
			data[t] = players.get(t).getData();
		}
		jt = new JTable(data, column);
		jt.setBounds(50, 70, 500, 100 * (players.size() + 1));
		jt.setBackground(LabelButton.ExitColor);
		// sp = new JScrollPane(jt);
	}
}
