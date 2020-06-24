import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class FlappyBirdFrame extends JFrame {

	private Dimension windowSize = new Dimension(350,720);

	public FlappyBirdFrame(){
		JFrame window = new JFrame();
		
		GamePanel gamePanel = new GamePanel(windowSize);
		gamePanel.addMouseListener(gamePanel);
		gamePanel.setFocusable(true);
		
		window.setTitle("Flappy Bird");
		window.setSize(windowSize);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setIconImage(new ImageIcon("../img/bird.png").getImage());
		window.add(gamePanel);
		window.setVisible(true);
	}
}
