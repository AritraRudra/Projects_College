package server;

import java.awt.*;
import javax.swing.*;

/**
 * 
 * @author Aritra
 *
 */

public class AboutFACEUP extends JFrame{
	//private static final long serialVersionUID = 4377203027038191379L;

	public AboutFACEUP() {
		setSize(new Dimension(960, 650));
		setTitle("About FACEUP");
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().setBackground(new Color(255, 255, 255));
				
		/*try {
			BufferedImage myPicture = ImageIO.read(new File("arctic15.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		getContentPane().setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(1000, 700));
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);
		getContentPane().add(scrollPane);
		JLabel picLabel = new JLabel(new ImageIcon(getClass().getResource("res/about.jpg")));
		///JLabel picLabel = new JLabel(new ImageIcon("D:\\4th_year\\Project\\about.jpg"));
		picLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		scrollPane.setViewportView(picLabel);
		picLabel.setAlignmentY(0.0f);
		picLabel.setMinimumSize(new Dimension(480, 300));
		
		//For access from JAR
		/*BufferedImage wPic = ImageIO.read(this.getClass().getResource("snow.png")); 
		JLabel wIcon = new JLabel(new ImageIcon(wPic));*/
		
		/*JLabel cpri8_1 = new JLabel("\u00A9 Aritra Rudra");		
		cpri8_1.setIconTextGap(5);
		cpri8_1.setForeground(new Color(0, 255, 0));
		cpri8_1.setBounds(50, 270, 100, 15);
		cpri8_1.setFont(new Font("Times New Roman", Font.BOLD, 34));
		getContentPane().add(cpri8_1);
		JLabel cpri8_2 = new JLabel("\u00A9 Ankita Mandal");		
		cpri8_2.setBounds(50, 290, 100, 15);
		cpri8_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		getContentPane().add(cpri8_2);
		JLabel cpri8_3 = new JLabel("\u00A9 Atanu Ghosh");
		cpri8_3.setBounds(50, 310, 100, 15);
		cpri8_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		getContentPane().add(cpri8_3);
		JLabel cpri8_4 = new JLabel("\u00A9 Somnath Das");		
		cpri8_4.setBounds(50, 330, 100, 15);
		cpri8_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		getContentPane().add(cpri8_4);*/
		
		//JLabel lblNewLabel = new JLabel(image);
		//lblNewLabel.setBounds(89, 51, 46, 14);
		//getContentPane().add(lblNewLabel);
		
		/*ImagePanel imgPanel=new ImagePanel();
		try {
			imgPanel.loadImage("E:\\Projects\\Online_Video_Chat\\temp_test\\aboutGUI\\About_FACEUP\\bin\\arctic15.jpg");
		} catch (IOException e1) {			
			e1.printStackTrace();
		}
		imgPanel.scaleImage();
		getContentPane().add(imgPanel);*/

		
		setBackground(SystemColor.desktop);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(500, 400));
		pack();
	}
}