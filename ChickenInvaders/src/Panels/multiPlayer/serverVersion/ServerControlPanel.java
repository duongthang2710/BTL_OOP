package Panels.multiPlayer.serverVersion;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JComboBox;

import Manager.controller.Manager;
import Manager.generals.Background;
import Manager.generals.Panel;
import Manager.generals.Button.Acts;
import Manager.generals.Button.LabelButton;
import Panels.UserPanels.UserSetting;
import player.Player;

@SuppressWarnings("serial")
public class ServerControlPanel extends Panel {
	private static ServerControlPanel panel = new ServerControlPanel();
	LabelButton plLabel = new LabelButton("Players In The Game -->", 80, 90, 600, 50, 30);
	LabelButton launch = new LabelButton("Launch The Game", 850, 400, 400, 50, 40);
	LabelButton exit = new LabelButton("Exit To Main Menu", 850, 550, 400, 50, 40);
	JComboBox<String> playerList = new JComboBox<String>();
	Player player;
	int port;
	int maxPlayers;
	int maxStage;
	ServerSocket serverSocket;
	Socket socket;
	ArrayList<Thread> clients = new ArrayList<>();

	private void init() {
		playerList.setBounds(500, 105, 170, 30);
		playerList.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		Collections.addAll(drawables, exit, launch, playerList, plLabel);
		for (Component c : drawables)
			this.add(c);
		launch.addMouseListener(new Acts(launch) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				launch.setForeground(LabelButton.ExitColor);
				Manager.setPanel(ServerGame.create(player));
				ServerGame.getCurrent().setPaused(false);

			}
		});
		exit.addMouseListener(new Acts(exit) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					panel.socket.close();
					panel.serverSocket.close();
				} catch (Exception e) {
				}
				exit.setForeground(LabelButton.ExitColor);
				Manager.setPanel(UserSetting.getcurrent());
			}
		});
	}

	private void acceptClient() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (int i = 0; i < maxPlayers; i++) {
						panel.socket = panel.serverSocket.accept();
						ManageClients client = new ManageClients(panel.socket);
						Thread run = new Thread(client);
						clients.add(run);
						run.setDaemon(true);
						panel.clients.add(run);
						run.start();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	private ServerControlPanel() {
		super(Background.backs.get("Backgrounds\\Background7.jpg"));
		init();
	}

	public static ServerControlPanel create(Player player, int port, int maxPlayers, int maxStage) {
		panel.player = player;
		panel.player = new Player(panel.player.name);
		panel.playerList.removeAllItems();
		panel.playerList.addItem(player.name);
		panel.port = port;
		panel.maxPlayers = maxPlayers;
		panel.maxStage = maxStage;
		try {
			panel.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel.acceptClient();
		return panel;
	}

	public static ServerControlPanel getcurrent() {
		return panel;
	}
}
