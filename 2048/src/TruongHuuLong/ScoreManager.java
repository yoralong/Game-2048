package TruongHuuLong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class ScoreManager {

	// Current score
	private int currentScore;
	private int currentTopScore;
	private long time;
	private long startingTime;
	private long bestTime;
	private int[] board = new int[GameBoard.ROWS *GameBoard.COLS];
	
	//File
	private String filePath;
	private String temp ;
	private GameBoard gBoard;
	
	//Boolean
	private boolean newGame;
	
	public ScoreManager(GameBoard gBoard) {
		try {
			
			filePath = new File("").getAbsolutePath();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		temp = "TEMP.tmp";
		this.gBoard = gBoard;
	}
	
	public void reset() {
		File f = new File(filePath,temp);
		if(f.isFile()) {
			f.delete();
		}
		newGame = true;
		startingTime = 0;
		currentScore =  0;
		time = 0;
	}
	
	private void createFile() {
		FileWriter output = null;
		newGame = true;
		
		try {
			File f = new File(filePath , temp);
			output = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(output);
			writer.write(""+0);
			writer.newLine();
			writer.write(""+0);
			writer.newLine();
			writer.write(""+0);
			writer.newLine();
			writer.write(""+0);
			writer.newLine();
			
			for(int row=0;row<GameBoard.ROWS;row++) {
				for(int col = 0 ; col<GameBoard.COLS;col++) {
					if(row == GameBoard.ROWS -1 && col == GameBoard.COLS-1) {
						writer.write(""+0);
					}
					else {
						writer.write(0+ "-");
					}
				}
			}
			writer.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void saveGame() {
		FileWriter output = null;
		if(newGame) newGame = false;
		
		try {
			File f = new File(filePath,temp);
			output = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(output);
			writer.write(""+currentScore);
			writer.newLine();
			writer.write(""+currentTopScore);
			writer.newLine();
			writer.write(""+time);
			writer.newLine();
			writer.write(""+bestTime);
			writer.newLine();
			
			for(int row=0;row<GameBoard.ROWS;row++) {
				for(int col = 0 ; col<GameBoard.COLS;col++) {
					int location = row*GameBoard.COLS+col;
					Tile tile = gBoard.getBoard()[row][col];
					this.board[location] =tile!=null ? tile.getValue():0;
					
					if(row == GameBoard.ROWS -1 && col == GameBoard.COLS-1) {
						writer.write(""+board[location]);
					}
					else {
						writer.write(board[location]+"-");
					}
				}
			}
			writer.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadGame() {
		try {
			File f = new File(filePath,temp);
			if(!f.isFile()) {
				createFile();
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			currentScore = Integer.parseInt(reader.readLine());
			currentTopScore = Integer.parseInt(reader.readLine());
			time = Long.parseLong(reader.readLine());
			startingTime = time;
			bestTime = Long.parseLong(reader.readLine());
			
			String[] board = reader.readLine().split("-");
			for(int i=0;i<board.length;i++) {
				this.board[i] = Integer.parseInt(board[i]);
				
			}
			reader.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	public int getCurrentTopScore() {
		return currentTopScore;
	}

	public void setCurrentTopScore(int currentTopScore) {
		this.currentTopScore = currentTopScore;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time + startingTime;
	}

	public long getBestTime() {
		return bestTime;
	}

	public void setBestTime(long bestTime) {
		this.bestTime = bestTime;
	}

	public int[] getBoard() {
		return board;
	}

	public boolean NewGame() {
		return newGame;
	}

	
}
