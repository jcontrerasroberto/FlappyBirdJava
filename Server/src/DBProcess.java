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

import java.sql.*;

 /*
 * CLASE DBProcess
 * 
 * Esta clase controla la conexion con la base de datos así como la inserción de datos
 * 
 * */

public class DBProcess {

	private Connection con = null;
	
	
	//Constructor de la clase. Solamente ejecuta el método connect()
	public DBProcess(){
		try {
			connect();
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	/*
	 * public void connect() 
	 * 
	 * Crea una conexión en la base de datos FlappyBird en localhost con el usuario root y la contraseña root
	 * 
	 * */
	public void connect() throws SQLException{
		String jdbc = "jdbc:mysql://localhost:3306/FlappyBird";
		con = DriverManager.getConnection(jdbc,"root", "root");
	}

	/*
	 * public boolean insertGame(String nickname, int score, int players)
	 * 
	 * Con ayuda de un PreparedStatement para evitar inyecciones SQL, este método inserta un nuevo registro en la base de datos
	 * Ingresa el número de jugadores, el score del ganador y el nickname del ganador. Estos valores son los parametros
	 * 	que recibe.
	 * 
	 * */
	public boolean insertGame(String nickname, int score, int players) throws SQLException{
		String query = "INSERT INTO OnlineGame(numofPlayers, scoreWinner, nicknameWinner) VALUES(?, ?, ?)";
		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.setInt(1, players);
		preparedStatement.setInt(2, score);
		preparedStatement.setString(3, nickname);
		return preparedStatement.execute();
	}

	/*
	 * public void close()
	 * 
	 * Método que cierra la conexión con la base de datos. Es importante no dejar conexiones abiertas.
	 * 
	 * */
	public void close() throws SQLException{
		if(con != null) con.close();
	}
}
