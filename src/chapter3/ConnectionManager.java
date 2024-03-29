package chapter3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private Connection con;
	private static final String DRIVER_NAME = "org.h2.Driver";
	private static final String URL = "jdbc:h2:tcp://localhost/~/h2db/test";
	private static final String USER = "sa";
	private static final String PASS = "";
	static { // ドライバのロードは1回でいいので、static 初期化にしておく
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("ドライバーのロードに失敗しました", e);
		}
	}

	public Connection getConnection() { // 接続
		if (con == null) {
			try {
				con = DriverManager.getConnection(URL, USER, PASS);
			} catch (SQLException e) {
				throw new RuntimeException("データベースの接続に失敗しました", e);
			}
		}
		return con;
	}

	public void closeConnection() { // 切断
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("データベースの切断に失敗しました", e);
		}
	}
}
