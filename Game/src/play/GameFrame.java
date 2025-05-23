package play;

import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.*;


public class GameFrame extends JFrame {   //  Game change to gameframe
	public static void Frame() {
		GameFrame gm = new GameFrame();
		SnakePanel gp=new SnakePanel();
		gm.setTitle("snakeladder");
		gm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gm.setResizable(false);
		gm.add(gp);
		gp.setPreferredSize(new Dimension(gp.panelwidth, gp.panelheight));

		gm.pack();
		gm.setVisible(true);
		gm.setLocationRelativeTo(null);
	//	gp.setPreferredSize(new Dimension(SnakePanel.panelheight,SnakePanel.panelwidth));
	//	gp.setPreferredSize(new Dimension(gp.panelheight,gp.panelwidth)); 
	//	gp.setPreferredSize(new Dimension(gp.panelwidth, gp.panelheight));

	} 

		
	}
	


