package SaveLoad.records;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Panels.hallOfFame.HallPanel;
import player.Player;

public class Recorder {
	private static File records = new File("records.txt");
	private static PrintWriter writer;
	private static ArrayList<String> allRecords = new ArrayList<>();
	static {
		try {
			records.createNewFile();
			writer = new PrintWriter(new FileWriter(records, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Recorder() {

	}

	public static void record(Player p) {
        String time = String.valueOf(p.time_in_game/60) + "m" + String.valueOf(p.time_in_game%60) + "s";

		String s = p.name + " " + p.getLevel() + " " + p.score + " " + time;
		allRecords.add(s);
		addToHall(s);
		writer.println(s);
		writer.flush();
	}

	private static void addToHall(String s) {
		HallPanel h = HallPanel.create();
		h.addLabel(s.split(" "));
	}

	public static void loadRecords() throws Exception {
		Scanner s = new Scanner(new File("records.txt"));
		while(s.hasNext()) {
            String str = s.nextLine();
			allRecords.add(str);
            addToHall(str);
            System.out.println(str);
		}

		s.close();
	}
}
