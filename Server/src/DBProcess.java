import java.sql.*;

public class DBProcess {

	private Connection con = null;

	public DBProcess(){
		try {
			connect();
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	public void connect() throws SQLException{
		String jdbc = "jdbc:mysql://localhost:3306/FlappyBird";
		con = DriverManager.getConnection(jdbc,"root", "root");
	}

	public boolean insertGame(String nickname, int score, int players) throws SQLException{
		String query = "INSERT INTO OnlineGame(numofPlayers, scoreWinner, nicknameWinner) VALUES(?, ?, ?)";
		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.setInt(1, players);
		preparedStatement.setInt(2, score);
		preparedStatement.setString(3, nickname);
		return preparedStatement.execute();
	}

	public void close() throws SQLException{
		if(con != null) con.close();
	}
}
