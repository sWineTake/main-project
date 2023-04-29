package com.playground.song.database;

public class MySqlConnection {
	// DataBase - MySql 정보
	private static final String ID = "user1";
	private static final String PWD = "12341234";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	private static final String IP = "localhost";
	private static final String PORT = "3306";
	private static final String DB_NAME = "study_db";

	private static final String URL = "jdbc:mysql://" + IP + ":" + PORT + "/"+ DB_NAME
			+"?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";


	public void dbTest() {
		/*try {
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL, ID, PWD);
			System.out.println(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}
