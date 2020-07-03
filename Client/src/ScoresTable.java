/*
 * PROYECTO FINAL DE PROGRAMACIÓN ORIENTADA A OBJETOS
 * FLAPPY BIRD (MULTIPLAYER Y SINGLEPLAYER)
 * 
 * 2CM3
 * 
 * INTEGRANTES:
 * 
 * CONTRERAS BARRITA JOSÉ ROBERTO
 * CONTRERAS MENDEZ BRANDON
 * FONSECA RAMOS ANGEL GABRIEL
 * TOLEDO ESPINOSA CRISTINA ALINE
 * 
 * */
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ScoresTable extends JFrame{
	public ScoresTable(){
		JFrame window = new JFrame("Scores");
		JPanel scoresPanel = createPanel();
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.add(scoresPanel);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

	public JPanel createPanel(){
		
		JPanel temp = new JPanel();
		DefaultTableModel modelo = new DefaultTableModel();
		JTable tabla = new JTable(modelo);
		modelo.addColumn("Nickname");
		modelo.addColumn("Score");
		
		ScoreList sl = new ScoreList();
		ArrayList<Score> scores = sl.getScores();

		for(Score s : scores){
			Object[] row = {s.getNickname(), s.getScore()};
			modelo.addRow(row);
		}
		
		tabla.setPreferredScrollableViewportSize(new Dimension(300, 100));
        JScrollPane scrollPane = new JScrollPane(tabla);
		temp.add(scrollPane, BorderLayout.CENTER); 
		return temp;
	}
}
