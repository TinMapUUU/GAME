package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
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
import javax.swing.JTextField;

public class ID_Player extends JPanel {
    static int width = 450;
    static int height = 300;

    public static void main(String[] args) throws IOException {
    	new ID_Player();
    }

	public JTextField name_player;

    public ID_Player() throws IOException {
        JFrame frame = new JFrame("ID Player ");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        ImageIcon icon = new ImageIcon("D:\\GAME\\src\\images\\iconbai.jpg");
        frame.setIconImage(icon.getImage());

        // Set background image for JFrame
        Image background = ImageIO.read(new File("./src/images/background02.jpg"));
        frame.setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, width, height, this);
            }
        });

        JPanel menu = new JPanel();
        menu.setOpaque(false);
        menu.setLayout(new GridBagLayout());

        GridBagConstraints toa_do = new GridBagConstraints();
        toa_do.anchor = GridBagConstraints.CENTER;
        toa_do.insets = new Insets(10, 10, 10, 10);

        JLabel login = new JLabel("LOGIN ");
        login.setFont(new Font("Arial", Font.BOLD, 25));
        login.setForeground(Color.WHITE);
        toa_do.gridx = 0;
        toa_do.gridy = 0;
        toa_do.gridwidth = 2;
        menu.add(login, toa_do);

        JLabel name = new JLabel("ID :");
        name.setFont(new Font("Arial", Font.BOLD, 18));
        name.setForeground(Color.WHITE);
        toa_do.gridx = 0;
        toa_do.gridy = 1;
        toa_do.gridwidth = 1;
        menu.add(name, toa_do);

        name_player = new JTextField(15);
        toa_do.gridx = 1;
        toa_do.gridy = 1;
        toa_do.gridwidth = 1;
        menu.add(name_player, toa_do);

        JButton okButton = new JButton("OK");
        toa_do.gridx = 0;
        toa_do.gridy = 2;
        toa_do.gridwidth = 2;
        menu.add(okButton, toa_do);
        
        okButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Game_Menu gameMenu = new Game_Menu(name_player.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();			
			}
		});

        frame.add(menu);
        frame.setVisible(true);
    }   
}
