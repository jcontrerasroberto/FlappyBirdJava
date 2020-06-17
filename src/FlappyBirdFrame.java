import java.awt.Dimension;
import javax.swing.JFrame;

public class FlappyBirdFrame extends JFrame {

	private Dimension windowSize = new Dimension(350,700);

	public FlappyBirdFrame(){
		JFrame window = new JFrame();
		window.setTitle("Flappy Bird");
		window.setSize(windowSize);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setResizable(false);
		GamePanel gamePanel = new GamePanel(windowSize);
		gamePanel.addKeyListener(gamePanel);
		gamePanel.setFocusable(true);
		window.add(gamePanel);
		window.setVisible(true);
	}
}
