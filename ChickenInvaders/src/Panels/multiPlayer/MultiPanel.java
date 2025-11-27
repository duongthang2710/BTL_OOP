package Panels.multiPlayer;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Collections;

import Manager.controller.Manager;
import Manager.generals.Background;
import Manager.generals.Panel;
import Manager.generals.Button.Acts;
import Manager.generals.Button.LabelButton;
import Manager.generals.Button.TextField;
import Panels.UserPanels.UserSetting;
import Panels.multiPlayer.clientVersion.ClientControlPanel;
import Panels.multiPlayer.serverVersion.ServerControlPanel;
import player.Player;

@SuppressWarnings("serial")
public class MultiPanel extends Panel {
	static MultiPanel multi = new MultiPanel();
	Player player;
	LabelButton server = new LabelButton("Be A Host", 100, 100, 230, 50, 45);
	LabelButton client = new LabelButton("Choose A Host", 100, 250, 330, 50, 45);
	LabelButton back = new LabelButton("Back", 100, 400, 130, 50, 45);
	LabelButton port = new LabelButton("Port", 1100, 100, 130, 50, 35);
	LabelButton ip = new LabelButton("IP", 1100, 200, 50, 50, 35);
	LabelButton numOfPlayers = new LabelButton("Max Players", 1100, 200, 230, 50, 35);
	LabelButton numOfStages = new LabelButton("Final Stage", 1100, 300, 230, 50, 35);
	LabelButton invite = new LabelButton("Send Invitation", 950, 500, 300, 50, 40);
	LabelButton senReq = new LabelButton("Send Request", 950, 500, 300, 50, 40);

	TextField portIn = new TextField("9990", 950, 110, 70, 40, 20);
	TextField ipIn = new TextField("localhost", 950, 210, 100, 40, 20);
	TextField playerIn = new TextField("4", 950, 210, 70, 40, 20);
	TextField stageIn = new TextField("2", 950, 310, 70, 40, 20);

	private MultiPanel() {
		super(Background.backs.get("MenuBackground\\UserWelcomeBackground.jpg"));
		init();
	}

	public static MultiPanel create(Player player) {
		multi.player = player;
		return multi;
	}

	private void init() {
		Collections.addAll(drawables, back, server, client);
		for (Component c : drawables) {
			this.add(c);
		}
		back.addMouseListener(new Acts(back) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				remove(UserSetting.getcurrent().resume);
				remove(UserSetting.getcurrent().newgame);
				remove(senReq);
				remove(port);
				remove(ip);
				remove(ipIn);
				remove(numOfPlayers);
				remove(numOfStages);
				remove(portIn);
				remove(playerIn);
				remove(stageIn);
				remove(invite);
				repaint();
				back.setForeground(LabelButton.ExitColor);
				Manager.setPanel(UserSetting.getcurrent());
			}
		});

		server.addMouseListener(new Acts(server) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				Collections.addAll(drawables, port, numOfPlayers, numOfStages, portIn, playerIn, stageIn, invite);
				for (Component c : drawables)
					add(c);
				remove(ip);
				remove(ipIn);
				remove(senReq);
				repaint();
			}
		});
		client.addMouseListener(new Acts(client) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				Collections.addAll(drawables, ip, ipIn, senReq, port, portIn);
				for (Component c : drawables)
					add(c);
				remove(numOfPlayers);
				remove(numOfStages);
				remove(playerIn);
				remove(stageIn);
				remove(invite);
				repaint();
			}
		});

		invite.addMouseListener(new Acts(invite) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				remove(port);
				remove(ip);
				remove(ipIn);
				remove(numOfPlayers);
				remove(numOfStages);
				remove(portIn);
				remove(playerIn);
				remove(stageIn);
				remove(invite);
				invite.setForeground(LabelButton.ExitColor);
				Manager.setPanel(ServerControlPanel.create(multi.player, Integer.parseInt(portIn.getText()),
						Integer.parseInt(playerIn.getText()), Integer.parseInt(stageIn.getText())));
			}
		});
		senReq.addMouseListener(new Acts(senReq) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				remove(port);
				remove(portIn);
				remove(ip);
				remove(ipIn);
				senReq.setForeground(LabelButton.ExitColor);
				try {
					Manager.setPanel(ClientControlPanel.create(multi.ipIn.getText(), Integer.parseInt(portIn.getText()),
							multi.player));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}
}
