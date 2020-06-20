import java.awt.Dimension;
import javax.swing.JFrame;

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
		window.setResizable(false);
		window.add(gamePanel);
		window.setVisible(true);
	}
}
