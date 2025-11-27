package Panels.options;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Manager.Assets;
import Manager.controller.Manager;
import Manager.generals.Background;
import Manager.generals.Panel;
import Manager.generals.Button.Acts;
import Manager.generals.Button.LabelButton;
import Panels.UserPanels.GamePanel;

@SuppressWarnings("serial")
public class Costumback extends Panel {
	static Costumback cosback = new Costumback();
	LabelPic l1 = new LabelPic(100, 100, 200, 100, Assets.movingback1);
	LabelPic l2 = new LabelPic(400, 100, 200, 100, Assets.movingback2);
	LabelPic l3 = new LabelPic(700, 100, 200, 100, Assets.movingback3);
	LabelPic l4 = new LabelPic(100, 400, 200, 100, Assets.movingback4);
	LabelPic l5 = new LabelPic(400, 400, 200, 100, Assets.movingback5);
	LabelPic l6 = new LabelPic(700, 400, 200, 100, Assets.movingback6);
	public LabelButton back = new LabelButton("Back", 140, 600, 130, 50, 47);
	SetLabel set1 = new SetLabel(100, 240, 200, 50, 23, l1);
	SetLabel set2 = new SetLabel(400, 240, 200, 50, 23, l2);
	SetLabel set3 = new SetLabel(700, 240, 200, 50, 23, l3);
	SetLabel set4 = new SetLabel(100, 540, 200, 50, 23, l4);
	SetLabel set5 = new SetLabel(400, 540, 200, 50, 23, l5);
	SetLabel set6 = new SetLabel(700, 540, 200, 50, 23, l6);

	private Costumback() {
		super(Background.backs.get("MenuBackground\\OptionBackground.jpg"));
	}

	private void init() {
		this.setLayout(null);
		Collections.addAll(drawables, l1, l2, l3, l4, l5, l6, back, set1, set2, set3, set4, set5, set6);
		for (Component c : drawables) {
			this.add(c);
		}
		back.addMouseListener(new Acts(back) {

			@Override
			public void mousePressed(MouseEvent arg0) {
				OptionPanel u = OptionPanel.getcurrent();
				u.backgrounds.setForeground(LabelButton.ExitColor);
				Manager.setPanel(u);
			}
		});
	}

	public static Costumback create() {
		cosback.init();
		return cosback;
	}

	public static Costumback getcurrent() {
		return cosback;
	}

	class LabelPic extends JLabel {
		Background back;

		public LabelPic(int x, int y, int width, int height, Background back) {
			this.back = back;
			this.setBounds(x, y, width, height);
			this.setIcon(new ImageIcon(back.back));
		}
	}

	class SetLabel extends LabelButton {
		Background back;

		public SetLabel(int i, int j, int k, int l, int m, LabelPic l1) {
			super("Set Background", i, j, k, l, m);
			this.back = l1.back;
			this.addMouseListener(new Acts(this) {

				@Override
				public void mousePressed(MouseEvent arg0) {
					GamePanel.getCurrent().setBack(back);
				}
			});
		}
	}
}
