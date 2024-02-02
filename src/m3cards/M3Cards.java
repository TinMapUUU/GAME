package m3cards;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class M3Cards extends JFrame {
	public static long startTimeMillis = 0;
	static int width = 1300;
	static int height = 870;
	static String PLAYER="";	
	
	public M3Cards() {
		setTitle("FLIP GAME");
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		ImageIcon icon = new ImageIcon("D:\\GAME\\src\\images\\iconbai.jpg");
		
		// Set a custom title and get player input
        PLAYER = (String) JOptionPane.showInputDialog(
                this,
                "Please enter the name of player",
                "Player Input",
                JOptionPane.PLAIN_MESSAGE,
                icon,
                null,
                "");		
		//KHỞI TẠO TIME SAU KHI PLAYER CUNG CẤP IN4
		startTimeMillis = System.currentTimeMillis();	
		
		setIconImage(icon.getImage());
		add(new Settings_3());
		
		setVisible(true);
	}
}
