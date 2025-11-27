package Manager.generals;

import static Manager.Dimentions.screenSize;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Background {
	private static final float scrollFactor = 1f;
	public BufferedImage back;
	public BufferedImage flippedBack;
	private int x;
	private int y1 = 0;
	private int y2 = y1 - screenSize.height;
	public String path;
	public static Map<String,Background> backs =new LinkedHashMap<String, Background>();

	public Background(String path) {
		try {
			this.path = path;
            InputStream in = Background.class.getResourceAsStream("/" + path);

            if (in == null) {
                System.err.println("❌ Không tìm thấy background: " + path);
                return;
            }

            this.back = ImageIO.read(in);
			this.flippedBack = flipVertical(back);
			backs.put(path,this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawFixed(Graphics g) {
		g.drawImage(back, 0, 0, screenSize.width, screenSize.height, null);
	}

	public void drawMoving(Graphics g) {
		g.drawImage(back, x, y1, screenSize.width, screenSize.height, null);
		g.drawImage(flippedBack, x, y2, screenSize.width, screenSize.height, null);
	}

	public void move() {
		y1 += scrollFactor;
		y2 += scrollFactor;
		if (tah(y1)) {
			y1 = y2 - screenSize.height;
			y1 += scrollFactor;
		} else if (tah(y2)) {
			y2 = y1 - screenSize.height;
			y2 += scrollFactor;
		}
	}

	private boolean tah(int y) {
		return y > screenSize.height;
	}

	private BufferedImage flipVertical(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				flipped.setRGB(x, (height - 1) - y, img.getRGB(x, y));
			}
		}
		return flipped;
	}
	
}
