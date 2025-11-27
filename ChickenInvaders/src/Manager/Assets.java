package Manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Manager.generals.Background;
import Manager.generals.Sound;
import SaveLoad.records.Recorder;

public class Assets {
	public static Background chooserBack = new Background("MenuBackground\\UserChooserBackground.png");
	public static Background pauseBack = new Background("MenuBackground\\ExitBackground.png");
	public static Background userSettingBack = new Background("MenuBackground\\UserWelcomeBackground.jpg");
	public static Background optionBack = new Background("MenuBackground\\OptionBackground.jpg");
	public static Background hallBack = new Background("MenuBackground\\HallBackground.jpg");
	public static Background movingback1 = new Background("Backgrounds\\Background1.jpg");
	public static Background movingback2 = new Background("Backgrounds\\Background2.jpg");
	public static Background movingback3 = new Background("Backgrounds\\Background3.jpg");
	public static Background movingback4 = new Background("Backgrounds\\Background4.jpg");
	public static Background movingback5 = new Background("Backgrounds\\Background5.jpg");
	public static Background movingback6 = new Background("Backgrounds\\Background6.png");
	public static Background movingback7 = new Background("Backgrounds\\Background7.jpg");


	public static BufferedImage boss1 = loadImage("BossChicken\\Boss1.png");
	public static BufferedImage boss2 = loadImage("BossChicken\\Boss2.png");
	public static BufferedImage boss3 = loadImage("BossChicken\\Boss3.png");
	public static BufferedImage boss4 = loadImage("BossChicken\\Boss4.png");
	public static BufferedImage bar = loadImage("Item\\Bar.png");
	public static BufferedImage fire1 = loadImage("Fires\\fire1.png");
	public static BufferedImage fire2 = loadImage("Fires\\fire2.png");
	public static BufferedImage fire22 = loadImage("Fires\\fire22.png");

	public static BufferedImage fire3 = loadImage("Fires\\fire3.png");

    public static ArrayList<BufferedImage> ChickenYellow = new ArrayList<>();
    static {
        for (int i = 1; i <= 7; i++) {
            ChickenYellow.add(loadImage("Chicken\\ChickenYellow\\Chicken" + i + ".png"));
        }
        for (int i = 6; i > 1; i--) {
            ChickenYellow.add(loadImage("Chicken\\ChickenYellow\\Chicken" + i + ".png"));
        }
    }
    public static ArrayList<BufferedImage> ChickenGreen = new ArrayList<>();
    static {
        for (int i = 1; i <= 7; i++) {
            ChickenGreen.add(loadImage("Chicken\\ChickenGreen\\Chicken" + i + ".png"));
        }
        for (int i = 6; i > 1; i--) {
            ChickenGreen.add(loadImage("Chicken\\ChickenGreen\\Chicken" + i + ".png"));
        }
    }
    public static ArrayList<BufferedImage> ChickenBlue = new ArrayList<>();
    static {
        for (int i = 1; i <= 7; i++) {
            ChickenBlue.add(loadImage("Chicken\\ChickenBlue\\Chicken" + i + ".png"));
        }
        for (int i = 6; i > 1; i--) {
            ChickenBlue.add(loadImage("Chicken\\ChickenBlue\\Chicken" + i + ".png"));
        }
    }
    public static ArrayList<BufferedImage> ChickenRed = new ArrayList<>();
    static {
        for (int i = 1; i <= 7; i++) {
            ChickenRed.add(loadImage("Chicken\\ChickenRed\\Chicken" + i + ".png"));
        }
        for (int i = 6; i > 1; i--) {
            ChickenRed.add(loadImage("Chicken\\ChickenRed\\Chicken" + i + ".png"));
        }
    }
    public static BufferedImage Rocket = loadImage("Item\\Rocket.png");
	public static BufferedImage Egg = loadImage("Item\\Egg.png");
	public static ArrayList<BufferedImage> Coin = new ArrayList<>();

    static {
        for (int i = 1; i < 9; i++) {
            Coin.add(loadImage("Coin\\Coin" + i + ".png"));
        }
    }

	public static BufferedImage PowerUp = loadImage("Item\\PowerUp.png");
	public static BufferedImage SpaceShip1 = loadImage("SpaceShip\\SpaceShip1.png");
	public static BufferedImage SpaceShip2 = loadImage("SpaceShip\\SpaceShip2.png");

	public static BufferedImage shield = loadImage("Item\\shield1.png");
	public static BufferedImage RedGift = loadImage("Gifts\\RedGift.png");
	public static BufferedImage GreenGift = loadImage("Gifts\\GreenGift.webp");
	public static BufferedImage BlueGift = loadImage("Gifts\\BlueGift.webp");

    public static Sound sound = new Sound();
    static {
        sound.loadSound("shipdie", "Sounds/ShipDie.wav");
        sound.loadSound("shoot", "Sounds/Shoot.wav");
        sound.loadSound("receivepowerup", "Sounds/ReceivePowerUp.wav");
        sound.loadSound("chickensound", "Sounds/ChickenSound.wav");
        sound.loadSound("chickendie", "Sounds/ChickenDie.wav");
    }

    static {
        try {
            Recorder.loadRecords();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	public static BufferedImage loadImage(String name) {
        try (InputStream in = Assets.class.getResourceAsStream("/" + name)) {

            if (in == null) {
                System.err.println("❌ Không tìm thấy file: " + name);
                return null;
            }

            return ImageIO.read(in);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}

}
