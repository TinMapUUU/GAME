package m3cards;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import UI.Game_Menu;

public class Settings_3 extends JPanel implements ActionListener, MouseListener {
	
	Image background; 
	Timer timer;
	boolean flip = false;
	boolean check = false;
	int [] selected = new int[3];
	int count = 0;
	int index;
	Card3 [] card;
	int cardSize = 24;
	String [] lol= {"jinx", "kaisa", "lux","missfortune", 
					"rakan", "seraphine", "xayah", "zyra"};  

	long complTime = 0;	
	int dem = 0;
	
	private JLabel timerLabel; // label hiện thời gian

	public Settings_3() {
		card = new Card3[cardSize];
		
		List<String> shuffled_list = Arrays.asList(lol);		
		Collections.shuffle(shuffled_list);


		for (int i=0; i<cardSize; i++) {			
			card[i] = new Card3(lol[i%8], i);			
			card[i].setX(52 + i *120);
			if (i>=10 && i<20) {
				card[i].setX(52+(i-10)*120);
				card[i].setY(225);
			}
			if (i>=20) {
				card[i].setX(52+(i-20)*120);
				card[i].setY(2*225);
			}
		}
		
		addMouseListener(this);
		timer = new Timer(10, this);
        timer.start();

		try {
			background = ImageIO.read(new File("./src/images/background02.jpg"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
  // TẠO VÀ CẤU HÌNH JLABEL THỜI GIAN  	
	timerLabel = new JLabel();	
	timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
	timerLabel.setForeground(Color.WHITE);
    timerLabel.setHorizontalAlignment(SwingConstants.RIGHT); //import THƯ VIỆN
    timerLabel.setBounds(800, 20, 200, 30);
    add(timerLabel);
    
    // THIẾT LẬP THỜI GIAN START 00:00
    M3Cards.startTimeMillis = System.currentTimeMillis();
	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, M3Cards.width, M3Cards.height, null);

		for (int i=0; i<cardSize; i++) {
			g.drawImage(card[i].getPicture(), card[i].getX(), card[i].getY(), 
						card[i].getWidth(), card[i].getHeight(), null);

		}		
		Font kiten = new Font("Forte", Font.BOLD, 40);
		g.setFont(kiten);
		g.setColor(Color.MAGENTA);
		g.drawString("DMinh_MDuy_Hung", 100, 750);
		Font playerFont = new Font("Cambria", Font.BOLD, 24);
	    g.setFont(playerFont);
	    g.setColor(Color.WHITE);
	    g.drawString("Player: "+ M3Cards.PLAYER, 950, 750);    
	}	

	private String formatTime(long timeMillis) {
        long elapsedSeconds = (int) timeMillis / 1000;
        long minutes = elapsedSeconds / 60;
        long seconds = elapsedSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
		
	  // PHƯƠNG HƯỚNG ( SAU KHI ĐC LẬT)
	int direction = 1;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (flip) {
            flip = card[index].flip();
            if (card[index].getWidth() <= 0) {
                card[index].setPicture(card[index].getLol());
            }
        }

        if (check) {
            if (!card[selected[0]].getLol().equals(card[selected[1]].getLol()) ||
            	!card[selected[1]].getLol().equals(card[selected[2]].getLol() )) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                card[selected[0]].setPicture("BG_cards");
                card[selected[1]].setPicture("BG_cards");
                card[selected[2]].setPicture("BG_cards");
                dem = 0;
            }
            
            check = false;
        }
        ///////////////////////////////
        if (count == 3 && !flip) {
            if (card[selected[0]].getLol().equals(card[selected[1]].getLol()) && 
            	card[selected[1]].getLol().equals(card[selected[2]].getLol()) ) {
                dem = dem +1;
            } else {
            	try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                card[selected[0]].setPicture("BG_cards");
                card[selected[1]].setPicture("BG_cards"); 
                card[selected[2]].setPicture("BG_cards"); 
            }
            count = 0;
                      
        }
///////////////////////////
        repaint();

        if (count == 3 && !flip) {
            check = true;
            count = 0;
        }

        if (dem == 8) {
            showCompletionDialog();
        }
        updateTimer();
        
    }

	private void updateTimer() {
		try {
		long currentTimeMillis = System.currentTimeMillis();
		long elapsedSeconds = (currentTimeMillis - M3Cards.startTimeMillis) / 1000;
		complTime = currentTimeMillis - M3Cards.startTimeMillis; // Cập nhật complTime
		long minutes = elapsedSeconds / 60;
		long seconds = elapsedSeconds % 60; 
		String timeString = String.format("%02d:%02d", minutes, seconds);
		timerLabel.setText(timeString);
		 } catch (Exception e) {
			 System.err.println("Error updating timer: " + e.getMessage());
		    }
	}
	
	private void showCompletionDialog() {
	    String message = "Player: " + M3Cards.PLAYER + "!\n"
	            + "Finish Time: " + formatTime(complTime);
	   
	   // set name of message_JOptionPane = result   
	    int result = JOptionPane.showConfirmDialog(this, message, "Finish Game", JOptionPane.OK_OPTION); 
	    
		   // result choice OK of message
		   if (result == JOptionPane.OK_OPTION) {
			   // display Notification : Yes / No
		        // set name of "Continue ? "_JOptionPane = continueResult 
		        int continueResult = JOptionPane.showConfirmDialog(this, "Continue ?", "Finish Game", JOptionPane.YES_NO_OPTION);
		          
		          // Select Yes
		        if (continueResult == JOptionPane.YES_OPTION) {
		            // Select Yes in CONTINUERESULT_JOptionPane
		            try {
						Game_Menu gameMenu = new Game_Menu(M3Cards.PLAYER);
						gameMenu.showGameMenuUI();
						dem =0;
						
						// Close Settings
		                Window settingsWindow = SwingUtilities.getWindowAncestor(this);
		                settingsWindow.dispose();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
		        } else {
		            // Select No in CONTINUERESULT_JOptionPane
		        	System.exit(0);
		        }
		    } else {
		        // select No in RESULT_JOptionPane
		    	System.exit(0);        
		    }   
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		 flip = true;
	        direction = 1;

	        for (Card3 the : card) {
	            if (the.collision(e.getX(), e.getY())) {
	                selected[count] = the.getIndex();

	                if (count == 0 || (count == 1 && selected[0] != selected[1])
	                        	   || (count == 2 && (selected[0] != selected[2] 
	                        	   || selected[1] != selected[2]) )) {
	                    index = the.getIndex();
	                    count++;
	                }
	            }
	        }
	    }
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}

