package client;

import java.awt.*;
import javax.swing.*;

/**
 * 
 * @author Aritra
 *
 */

//@SuppressWarnings("serial")
public class AboutFACEUP extends JFrame{
	//private static final long serialVersionUID = -5543648267213828692L;

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
		picLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		scrollPane.setViewportView(picLabel);
		picLabel.setAlignmentY(0.0f);
		picLabel.setMinimumSize(new Dimension(480, 300));		
		setBackground(SystemColor.desktop);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(500, 400));
		pack();
	}
}