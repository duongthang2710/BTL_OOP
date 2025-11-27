package Manager.generals.Button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public abstract class Acts implements MouseListener {
	LabelButton button;
	JLabel label;

	public Acts(LabelButton button) {
		this.button = button;
	}

	public Acts() {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (button != null)
			button.setForeground(LabelButton.enterColor);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (button != null)
			button.setForeground(LabelButton.ExitColor);
	}

	@Override
	public abstract void mousePressed(MouseEvent arg0);

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
