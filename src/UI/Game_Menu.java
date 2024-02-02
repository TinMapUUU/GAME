package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import test.m3cards;
import test.mmmcards;

public class Game_Menu  {
	public String playerID;
	
	public Game_Menu(String playerID) throws IOException {
		this.playerID = playerID;
		showGameMenuUI();
	}
	
	public void showGameMenuUI() throws IOException {
		JFrame frame = new JFrame("Game Menu");
		frame.setSize(400, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		ImageIcon icon = new ImageIcon("D:\\GAME\\src\\images\\iconbai.jpg");
        frame.setIconImage(icon.getImage());
		
		Image background = ImageIO.read(new File("./src/background/back1k_1k.jpg"));
        frame.setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, 400, 450, this);
            }
        });
        
        JPanel menu = new JPanel();
        menu.setOpaque(false);
        menu.setLayout(new GridBagLayout());
        
        GridBagConstraints toa_do = new GridBagConstraints();
        toa_do.anchor = GridBagConstraints.CENTER;
        toa_do.insets = new Insets(5, 5, 5, 5);
        
        JLabel welcome = new JLabel("Welcome "+playerID+ " !!!" );
        welcome.setFont(new Font("Arial", Font.BOLD, 30));
        welcome.setForeground(Color.WHITE);
        toa_do.gridx = 0;
        toa_do.gridy = 0;
        menu.add(welcome);
        
        JButton game2 = new JButton("GAME 2");
        toa_do.gridx = 0;
        toa_do.gridy = 1;
        menu.add(game2, toa_do);
        
        JButton game3 = new JButton("GAME 3");
        toa_do.gridx = 1;
        toa_do.gridy = 1;
        menu.add(game3, toa_do);
        
        JButton exit = new JButton("EXIT GAME");
        toa_do.gridx = 0;
        toa_do.gridy = 4;
        menu.add(exit, toa_do);
              
        game2.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
		        mmmcards first = new mmmcards();
		        frame.dispose();
		    }
		});
        
        game3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				m3cards second = new m3cards();
				frame.dispose();
			}
		});
        
        exit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Window[] windows = JFrame.getWindows();
			    for (Window window : windows) {
			        window.dispose();
			    }
			    
			}
		});
        
        frame.add(menu);
        frame.setVisible(true);
	}
}
