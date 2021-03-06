package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;



import TruongHuuLong.AudioHandler;
import TruongHuuLong.DrawUtils;
import TruongHuuLong.Game;
import TruongHuuLong.GameBoard;
import TruongHuuLong.ScoreManager;

public class PlayPanel extends GuiPanel {
	
	private GameBoard board;
	private BufferedImage info;
	private ScoreManager scores;
	private Font scoreFont;
	private String timeF;
	private String bestTimeF;
	
	// Game Over
	private GuiButton tryAgain;
	private GuiButton mainMenu;
	private GuiButton screenShot;
	//nut reset
	private GuiButton reset;
	private GuiButton back;
	//
	private int smallButtonWidth = 160;
	private int spacing =20;
	private int largeButtonWidth = smallButtonWidth *2 + spacing;
	private int buttonHeight=50;
	private boolean added;
	private int alpha;
	//private Font gameOverFont;
	private boolean screenshot;
	public static int timecount = 0;
	public static int countscore = 1;
	
	//audio and gif
	private AudioHandler audio;
	private  String[] listGif= {"image/tenor.gif","image/animecute1.gif","image/animecute2.gif","image/animecute3.gif","image/animecute6.gif"}; // list gif
	private  String[] listaudio= {"cb3.wav","sugoii.mp3","comboburst1.wav","comboburst0.wav","comboburst7.wav"};
	private  String temp,temp1;
	
	public PlayPanel() {
		scoreFont = Game.main.deriveFont(24f);
		//gameOverFont = Game.main.deriveFont(70f);
		board = new GameBoard(Game.WIDTH/2 - GameBoard.BOARD_WIDTH /2 , Game.HEIGHT - GameBoard.BOARD_HEIGHT - 20);
		scores = board.getScores();
		info = new BufferedImage(Game.WIDTH,200,BufferedImage.TYPE_INT_RGB);
		
		mainMenu = new GuiButton(Game.WIDTH / 2 - largeButtonWidth / 2,450,largeButtonWidth,buttonHeight);
		tryAgain = new GuiButton(mainMenu.getX(),mainMenu.getY() - spacing - buttonHeight ,smallButtonWidth,buttonHeight);
		screenShot= new GuiButton(tryAgain.getX() + tryAgain.getWidth()+spacing,tryAgain.getY(),smallButtonWidth, buttonHeight);
		
		//tao nut reset va back
		reset = new GuiButton(530,25,60,30);
		reset.setText("Reset");
		back = new  GuiButton(260,25,60,30);
		back.setText("Back");
		
		add(reset);
		add(back);
		//
		
		tryAgain.setText("Try Again");
		screenShot.setText("Screenshot");
		mainMenu.setText("Back to Main Menu");
		
		
		tryAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.getScores().reset();	
				board.reset();
				alpha = 0;
				
				//
				add(reset);
				add(back);
				
				
				remove(tryAgain);
				remove(screenShot);
				remove(mainMenu);
				
				
				
				
				added = false;
			}
		});
		
		screenShot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screenshot = true;
			
			}
		});
		
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiScreen.getInstance().setCurrentPanel("Menu");
			}
		});
		
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.getScores().reset();	
				board.reset();
				alpha = 0;
				
				
				added = false;
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiScreen.getInstance().setCurrentPanel("Menu");
			}
		});
		
	} 
	
	private void drawGui(Graphics2D g) {
		//Format the times
		timeF = DrawUtils.formatTime(scores.getTime());
		bestTimeF = DrawUtils.formatTime(scores.getBestTime());
		
		// Draw it
		Graphics2D g2d = (Graphics2D)info.getGraphics();
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, info.getWidth(), info.getHeight());
		//
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(30,10 , 185, 30);
		g2d.setColor( new Color(0xCD853F));
		g2d.draw3DRect(25, 5, 185, 30, true);
		
		g2d.setColor(Color.black);
		g2d.setFont(scoreFont);
		g2d.drawString("Score:" + scores.getCurrentScore(), 30,30);
		
		//
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(630,10 , 220, 30);
		g2d.setColor( new Color(0xCD853F));
		g2d.draw3DRect(625,5,220, 30, true);
		
		g2d.drawString("Best:" + scores.getCurrentTopScore(),
							Game.WIDTH- DrawUtils.getMessageWitdth("Best:"+scores.getCurrentTopScore(), scoreFont, g2d) - 20,30);
		//
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(630,50 , 220, 30);
		g2d.setColor( new Color(0xCD853F));
		g2d.draw3DRect(625,45,220, 30, true);
		
		g2d.drawString("Fastest: "+bestTimeF , Game.WIDTH - DrawUtils.getMessageWitdth("Fastest:"+bestTimeF, scoreFont, g2d)-20,70);
		
		//
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(30	,50 , 185, 30);
		g2d.setColor( new Color(0xCD853F));
		g2d.draw3DRect(25,45,185, 30, true);
		
		g2d.setColor(Color.black);
		g2d.drawString("Time: "+ timeF,30,70);
		g2d.dispose();
		g.drawImage(info, 0, 0, null);
		
		//add nut reset
		
		//
		
		Toolkit a=Toolkit.getDefaultToolkit();  
		Image b=a.getImage("image/16.gif"); 
        g.drawImage(b,20,90,150,150,null);
        
        b=a.getImage("image/64.gif");
        g.drawImage(b,670,90,160,150,null);
        
        b= a.getImage("image/2048.gif");
        g.drawImage(b,360,0 , 150,150,null);
        
        b= a.getImage("image/256.gif");
        g.drawImage(b,20,390, 150,150,null);
        
        b= a.getImage("image/512.gif");
        g.drawImage(b,670,390, 160,150,null);
		
		//them .gif and Audio
		
		
		if(scores.getCurrentScore() % 300 >=0 && scores.getCurrentScore()  % 300 <= 100 && scores.getCurrentScore() >=300) {	
			
			Toolkit t=Toolkit.getDefaultToolkit();  
			Image i=t.getImage(temp); 
	        g.drawImage(i,10,240,180,150,null);
	        g.drawImage(i,660,240,180,150,null);
	                  
		}
		else
		{
			Toolkit t=Toolkit.getDefaultToolkit();  
	        Image i=t.getImage("white.png"); 
	        g.drawImage(i,10,340,180,150,null);
	        g.drawImage(i,660,340,180,150,null);
		}
		
		if(scores.getCurrentScore() == 0)
			countscore = 1;
		if(scores.getCurrentScore() > countscore*300)
		{
				//random gif
				Random random = new Random();
				int value = random.nextInt(5);
				temp = listGif[value].toString().trim();
				//random audio
				
				temp1 = listaudio[value].toString().trim();
				
				audio = AudioHandler.getInstance();
				audio.load(temp1.trim(),"GS");
				audio.play("GS", 0);
	            countscore++;
	       
	            
				
		}
		
	}
	
	public void drawGameOver(Graphics2D g) {
		
		
		g.setColor(new Color(222,222,222,alpha));
		g.fillRect(0, 0, Game.WIDTH,Game.HEIGHT);
		
		//
		Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("image/gameover.gif"); 
        g.drawImage(i,250,10 , 350,350,null);
        
        
		//
		//g.setColor(Color.red);
		//g.setFont(gameOverFont);
		//g.drawString("Game Over !!", Game.WIDTH /2 - DrawUtils.getMessageWitdth("Game Over !!", gameOverFont, g)/2 , 250 );
		
	}
	
	@Override
	public void update() {
		board.update();
		if(board.isDead()) {
			alpha++;
			if(alpha >170) alpha = 170;
		}
	}
	
	@Override
	public void render(Graphics2D g) {
	
	drawGui(g);
	board.render(g);
	if(screenshot) {
		BufferedImage bi = new BufferedImage(Game.WIDTH,Game.HEIGHT,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) bi.getGraphics();
		g2d.setColor(Color.white);
		g2d.fillRect(0,0,Game.WIDTH,Game.HEIGHT);
		drawGui(g2d);
		board.render(g2d);
		try {
			
			ImageIO.write(bi, "gif", new File(System.getProperty("user.home")+"\\Desktop","screenshot"+System.nanoTime() + ".gif"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		screenshot = false;
		}
	if(board.isDead()) {
		if(!added) {
			remove(reset);
	        remove(back);
			added = true;
			add(mainMenu);
			add(screenShot);
			add(tryAgain);
			
		}		
		drawGameOver(g);
	}
	super.render(g);
}
		
}
