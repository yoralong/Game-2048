package Gui;



import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//import TruongHuuLong.DrawUtils;
import TruongHuuLong.Game;

public class MainMenuPanel extends GuiPanel {

		private int buttonWidth = 220;
		private int buttonHeight = 60;
		private int spacing = 90;
		
		
		
		public MainMenuPanel() {
			super();
			GuiButton playButton = new GuiButton(20,160,buttonWidth,buttonHeight);
			GuiButton scoresButton = new GuiButton(20 ,playButton.getY()+spacing,buttonWidth,buttonHeight);
			GuiButton quitButton = new GuiButton(20,scoresButton.getY()+spacing,buttonWidth,buttonHeight);
			
			playButton.setText("Play");
			scoresButton.setText("Scores");
			quitButton.setText("Quit");
		
			
			playButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					GuiScreen.getInstance().setCurrentPanel("Play");		
				}
			});
			
			scoresButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					GuiScreen.getInstance().setCurrentPanel("LeaderBoards");	
				}		
			});
			
			quitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			
			add(playButton);
			add(scoresButton);
			add(quitButton);
			
		}
		
		 @Override
		public void render(Graphics2D g) {
			 //
		Toolkit t=Toolkit.getDefaultToolkit();  
		Image i=t.getImage("image/screen1.png"); 
		g.drawImage(i,0,0,Game.WIDTH,Game.HEIGHT,null);
		    //
		
		 i=t.getImage("image/giphy.gif"); 
		g.drawImage(i,590,180,200,200,null);
		//
		
		super.render(g);
		
		
		
		
		
		
       
        
		}
}
