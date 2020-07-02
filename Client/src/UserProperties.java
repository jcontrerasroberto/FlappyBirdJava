import java.util.Properties;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;

public class UserProperties {
	
	private Properties userprop = new Properties();
	private InputStream read;

	public UserProperties(){
		
		try {
			read = new FileInputStream("../.config/user.properties");
			userprop.load(read);
		} catch (Exception e) {
			System.out.println("Error opening file");
		}
		
	}

	public String getSkin(){
		return userprop.getProperty("user.skin");
	}

	public void setSkin(String newSkin){
		userprop.setProperty("user.skin", newSkin);
	}

	public String getMaxScore(){
		return userprop.getProperty("user.maxscore");
	}

	public void setMaxScore(String score){
		userprop.setProperty("user.maxscore", score);
	}

	public String getMaxNick(){
		return userprop.getProperty("user.maxnick");
	}

	public void setMaxNick(String nick){
		userprop.setProperty("user.maxnick", nick);
	}

	public void saveProp(){
		try{
			userprop.store(new FileOutputStream("../.config/user.properties"), null);
		}catch(Exception e){
			System.out.println("Error saving the properties");
		}		
	}
}
