import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;  
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class FlappyBirdFrame extends JFrame {

	private Dimension windowSize = new Dimension(700,500);
	private Font bitf;
	private GraphicsEnvironment ge;

	public FlappyBirdFrame(){
		JFrame window = new JFrame();

		MenuPanel mp = createPanel();
		window.setTitle("Flappy Bird");
		window.setSize(windowSize);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setIconImage(new ImageIcon("../img/game/bird.png").getImage());
		window.add(mp);
		window.setResizable(false);
		window.setVisible(true);
	}

	public MenuPanel createPanel(){
		
		Color btnOffColor = new Color(242, 86, 53);
		Color btnOnColor = new Color(77, 204, 112);
		MenuPanel mp = new MenuPanel(windowSize);
		mp.setLayout(null);
		try {
			//create the font to use. Specify the size!
			bitf = Font.createFont(Font.TRUETYPE_FONT, new File("../fonts/8bit.ttf")).deriveFont(12f);
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			//register the font
			ge.registerFont(bitf);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		JButton offline = new JButton("Offline mode");
		offline.setBounds((int)windowSize.getWidth()/2 - 100, 200, 200, 60);
		offline.setFont(bitf);
		offline.setFocusable(false);
		offline.setBackground(btnOffColor);
        offline.setForeground(Color.WHITE);
		offline.setCursor(new Cursor(Cursor.HAND_CURSOR));
		offline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog offlinegame = new JDialog();
                JLabel l = new JLabel("this is a dialog box"); 
  
				offlinegame.add(l); 
                offlinegame.setSize(100, 100); 
                offlinegame.setVisible(true); 
            }
        });
		
		JButton online = new JButton("Online mode");
		online.setBounds((int)windowSize.getWidth()/2 - 100, 300, 200, 60);
		online.setFont(bitf);
		online.setFocusable(false);
		online.setBackground(btnOnColor);
        online.setForeground(Color.WHITE);
        online.setCursor(new Cursor(Cursor.HAND_CURSOR));
        online.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Online Mode");
            }
        });

		Font bitfs = bitf.deriveFont(bitf.getSize() * 0.8F);
		
		JButton changeSkin = new JButton("Change Skin");
		changeSkin.setBounds(10, (int)windowSize.getHeight() - 80, 150, 40);
		changeSkin.setFont(bitfs);
		changeSkin.setFocusable(false);
		changeSkin.setBackground(Color.BLACK);
        changeSkin.setForeground(Color.WHITE);
        changeSkin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        changeSkin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "ChangeSkin");
            }
        });
		
		mp.add(offline);
		mp.add(online);
		mp.add(changeSkin);
		
		return mp;
	}
}
