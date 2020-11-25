
 
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


/*
	Class Bird

	Esta clase crea una ventana donde se mostraran todos los puntajes obtenidos en forma local


*/

public class ScoresTable extends JFrame{

	//Constructor. Se crea el JFrame y se añade un JPanel que obtenemos del método createPanel()
	public ScoresTable(){
		JFrame window = new JFrame("Scores");
		JPanel scoresPanel = createPanel();
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.add(scoresPanel);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

	/*
		public JPanel createPanel()

		Retorna un JPanel al que se le añade una tabla
	*/
	public JPanel createPanel(){
		
		JPanel temp = new JPanel();

		//Definición de la tabla
		DefaultTableModel modelo = new DefaultTableModel();
		JTable tabla = new JTable(modelo);
		modelo.addColumn("Nickname");
		modelo.addColumn("Score");
		
		ScoreList sl = new ScoreList();
		ArrayList<Score> scores = sl.getScores(); //Obtenemos los puntajes guardados

		for(Score s : scores){
			//Por cada puntaje añadimos una nueva columna en la tabla
			Object[] row = {s.getNickname(), s.getScore()};
			modelo.addRow(row);
		}
		
		tabla.setPreferredScrollableViewportSize(new Dimension(300, 100));
        JScrollPane scrollPane = new JScrollPane(tabla);
		temp.add(scrollPane, BorderLayout.CENTER); 
		return temp;
	}
}
