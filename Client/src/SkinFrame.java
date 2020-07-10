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
 
import java.awt.Point;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.HashMap;
import java.io.File;
import javax.swing.JButton;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

/*
	Class SkinFrame

	Esta clase crea la interfaz donde el usuario puede escoger su skin


*/

public class SkinFrame extends JFrame{
	
	private JLabel thumb = new JLabel(); //Label donde se colocara la imagen de previsualización
	private static String URLSkins="../img/skins"; //Ruta donde se encuentran las skins
	
	/*Constructor
		

	*/
	public SkinFrame(){
		
		HashMap<String, ImageIcon> skins = new HashMap<String, ImageIcon>();
		
		JFrame window = new JFrame("Choose a skin"); //Creación del frame
        
        JPanel requestarea = new JPanel(); //Un JPanel donde se colocara un título y un combo box
        JLabel lblname = new JLabel("SKINS");
        JComboBox skin = new JComboBox();
        skin.setPreferredSize(new Dimension(250, 20));
        //JLabel lblimagename = new JLabel("Imagen: ");
		//lblimagename.setPreferredSize(new Dimension(200,25));
        requestarea.add(lblname);
        requestarea.add(skin);
        
        //Obtenemos los nombres de todas las skins disponibles
        File[] directories = new File(URLSkins).listFiles(File::isDirectory);
        for (File f : directories) {
			try{
				ImageIcon temp = new ImageIcon(f.toString()+"/bird.png");
				skins.put(f.getName().toUpperCase(), temp); //Por cada skin guardamos el nombre y la imagen en el hashmap
				skin.addItem(f.getName().toUpperCase()); //Añadimos el nombre al combo box
			}catch(Exception e){
				System.out.println("Error loading images of skins");
			}
	    }
	    

		skin.setSelectedItem(0);

		//Listener para el combo box. Cada que se seleccione una diferente skin cambiemos la imagen de previsualizacion
		skin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon imageIcon = new ImageIcon(skins.get(skin.getSelectedItem().toString()).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
				thumb.setIcon(imageIcon);
			}
		});
		
        JPanel imagearea = new JPanel();

		JButton save = new JButton("Choose"); //Boton para guardar selección
	    save.setPreferredSize(new Dimension(280, 25));
	    save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//Cuando se apriete el boton
				if(!skin.getSelectedItem().toString().equals("")){
					System.out.println(skin.getSelectedItem().toString());
					UserProperties up = new UserProperties();
					up.setSkin(skin.getSelectedItem().toString().toLowerCase()); //Cambiamos el user properties con la nueva skin
					up.saveProp(); //Guardamos el archivo modificado
					window.dispose(); //Cerramos la ventana
				}	
                
            }
        });
	    imagearea.add(thumb);
        imagearea.add(save);

		window.setLocation(new Point(100,100));
        window.setSize(300,500);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BoxLayout boxLayout = new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
    	window.setLayout(boxLayout);
        window.setResizable(false);
        window.add(requestarea, BorderLayout.NORTH);
        window.add(imagearea, BorderLayout.CENTER);
        window.setVisible(true);
	}
}
