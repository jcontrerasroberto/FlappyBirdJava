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

public class SkinFrame extends JFrame{
	
	private JLabel thumb = new JLabel();
	private static String URLSkins="../img/skins";
	
	public SkinFrame(){
		
		HashMap<String, ImageIcon> skins = new HashMap<String, ImageIcon>();
		
		JFrame window = new JFrame("Choose a skin");
        
        JPanel requestarea = new JPanel();
        JLabel lblname = new JLabel("SKINS");
        JComboBox skin = new JComboBox();
        skin.setPreferredSize(new Dimension(250, 20));
        JLabel lblimagename = new JLabel("Imagen: ");
		lblimagename.setPreferredSize(new Dimension(200,25));
        requestarea.add(lblname);
        requestarea.add(skin);
        File[] directories = new File(URLSkins).listFiles(File::isDirectory);
        for (File f : directories) {
			try{
				ImageIcon temp = new ImageIcon(f.toString()+"/bird.png");
				skins.put(f.getName().toUpperCase(), temp);
				skin.addItem(f.getName().toUpperCase());
			}catch(Exception e){
			}
	    }
	    

		skin.setSelectedItem(0);

		skin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon imageIcon = new ImageIcon(skins.get(skin.getSelectedItem().toString()).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
				thumb.setIcon(imageIcon);
			}
		});
		
        JPanel imagearea = new JPanel();
		JButton save = new JButton("Choose");
	    save.setPreferredSize(new Dimension(280, 25));
	    save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				if(!skin.getSelectedItem().toString().equals("")){
					System.out.println(skin.getSelectedItem().toString());
					UserProperties up = new UserProperties();
					up.setSkin(skin.getSelectedItem().toString().toLowerCase());
					up.saveProp();
					window.dispose();
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
