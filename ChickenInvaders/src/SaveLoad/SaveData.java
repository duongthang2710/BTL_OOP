package SaveLoad;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import player.Player;

public class SaveData {
	Player[] users = Main.MyMain.frame.chooser.userMap.values()
			.toArray(new Player[Main.MyMain.frame.chooser.userMap.values().size()]);
	public File gamedata = new File("Game Data.txt");
	public PrintWriter writer;
	Gson gson = new Gson();
	public String[] datas = new String[users.length];

	public SaveData() {
		try {
			gamedata.createNewFile();
			writer = new PrintWriter(gamedata);
			for (int i = 0; i < users.length; i++) {
				datas[i] = gson.toJson(users[i]);
				writer.write(datas[i]);
				writer.println();
				writer.flush();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
