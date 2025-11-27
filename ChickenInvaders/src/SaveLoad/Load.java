package SaveLoad;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.google.gson.Gson;

import player.Player;


public class Load {
	File loaded = new File("Game Data.txt");
	String[] Jsons = new String[(int) getLineCount(loaded)];
	Player[] Player_saves = new Player[Jsons.length];
	//UserData[] saves = new UserData[Jsons.length];

	public Load() {
		loadData();
	}

	private void loadData() {
		Gson g = new Gson();
		for (int i = 0; i < Jsons.length; i++) {
			try {
				Jsons[i] = Files.readAllLines(Paths.get("Game Data.txt")).get(i);
				Player_saves[i] = g.fromJson(Jsons[i], Player.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// String line32 = Files.readAllLines(Paths.get("file.txt")).get(32)
		}
		//System.out.println(saves[1].name);
	}

	public static long getLineCount(File file) {

		try (Stream<String> lines = Files.lines(file.toPath())) {
			return lines.count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public  Player[] GetUserData() {
		return this.Player_saves;
	}
}
