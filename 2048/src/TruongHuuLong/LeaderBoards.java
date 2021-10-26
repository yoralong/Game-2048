package TruongHuuLong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LeaderBoards {

	private static LeaderBoards lBoard;
	private String filePath;
	private String hightScores;
	
	// All time leaderboards
	private ArrayList<Integer> topScores;
	private ArrayList<Integer> topTiles;
	private ArrayList<Long> topTimes;
	
	private LeaderBoards() {		
			try {
				filePath = new File("").getAbsolutePath();
				System.out.println(filePath);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			hightScores = "Scores";
			
			topScores = new ArrayList<Integer>();
			topTiles = new ArrayList<Integer>();
			topTimes = new ArrayList<Long>();		
		
	}
	
	public static LeaderBoards getInstance() {
		if(lBoard == null) {
			lBoard = new LeaderBoards();
		}
		return lBoard;
	}
	
	public void addScore(int score) {
		for(int i = 0; i< topScores.size();i++) {
			if(score >= topScores.get(i)) {
				topScores.add(i,score);
				topScores.remove(topScores.size()-1);
				return ;
			}
		}
	}
	public void addTile(int tileValue) {
		for(int i = 0; i< topTiles.size();i++) {
			if(tileValue >= topTiles.get(i)) {
				topTiles.add(i,tileValue);
				topTiles.remove(topTiles.size()-1);
				return ;
			}
		}
	}
	public void addTime(long millis) {
		for(int i = 0; i< topTimes.size();i++) {
			if(millis <= topTimes.get(i)) {
				topTimes.add(i,millis);
				topTimes.remove(topTimes.size()-1);
				return ;
			}
		}
	}
	
	public void loadScores() {
		try {
			File f = new File(filePath,hightScores);
			if(!f.isFile()) {
				createSaveData();
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			
			topScores.clear();
			topTiles.clear();
			topTimes.clear();
			
			String[] scores = reader.readLine().split("-");
			String[] tiles = reader.readLine().split("-");
			String[] times = reader.readLine().split("-");
			
			for(int i=0;i<scores.length;i++) {
				topScores.add(Integer.parseInt(scores[i]));
			}
			for(int i=0;i<tiles.length;i++) {
				topTiles.add(Integer.parseInt(tiles[i]));
			}
			for(int i=0;i<times.length;i++) {
				topTimes.add(Long.parseLong(times[i]));
			}
			reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveScores() {
		FileWriter output = null;
		
		try {
			File f = new File(filePath,hightScores);
			output = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(output);
			
			writer.write(topScores.get(0)+"-"+topScores.get(1)+"-"+topScores.get(2)+"-"+topScores.get(3)+"-"+topScores.get(4));
			writer.newLine();
			writer.write(topTiles.get(0)+"-"+topTiles.get(1)+"-"+topTiles.get(2)+"-"+topTiles.get(3)+"-"+topTiles.get(4));
			writer.newLine();
			writer.write(topTimes.get(0)+"-"+topTimes.get(1)+"-"+topTimes.get(2)+"-"+topTimes.get(3)+"-"+topTimes.get(4));
			writer.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createSaveData() {
		FileWriter output = null;
		
		try {
			File f = new File(filePath,hightScores);
			output = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(output);
			
			writer.write("0-0-0-0-0");
			writer.newLine();
			writer.write("0-0-0-0-0");			
			writer.newLine();
			writer.write(Integer.MAX_VALUE+ "-"+Integer.MAX_VALUE+ "-"+Integer.MAX_VALUE+ "-"+Integer.MAX_VALUE+ "-"+Integer.MAX_VALUE);
			writer.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getHighScore() {
		return topScores.get(0);
	}
	
	public long getFastestTime() {
		return topTimes.get(0);
	}
	
	public int getHightTile() {
		return topTiles.get(0);
	}

	public ArrayList<Integer> getTopScores() {
		return topScores;
	}

	public ArrayList<Integer> getTopTiles() {
		return topTiles;
	}

	public ArrayList<Long> getTopTimes() {
		return topTimes;
	}

	
}
