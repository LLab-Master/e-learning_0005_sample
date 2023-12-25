package chapter9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbTest1 {

	public static void main(String[] args) {
//		try {
//			Class.forName("org.h2.Driver");
//		} catch (ClassNotFoundException e) {
//			throw new IllegalStateException("ドライバのロードに失敗しました");
//		}
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/h2db/test", "sa", "");
			// Update
			PreparedStatement psmt = con.prepareStatement("update sample set name = ? where name = 'taro' ");
			psmt.setString(1, "Taro");
			int st = psmt.executeUpdate();
			if (st == 0) {
				System.out.println("変更できませんでした");
			}
			// idが 4 未満を select
			PreparedStatement psmt2 = con.prepareStatement("select * from sample where id < ?");
			psmt2.setInt(1, 4);
			ResultSet rs = psmt2.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("id") + " " + rs.getString("name"));
				// rs.getInt(1), rs.getString(2) のようにカラム番号でも可
			}
		} catch (SQLException e) { // 接続失敗時
			e.printStackTrace();
		} finally { // 最後にデータベースを切断
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) { // 切断失敗時
				e.printStackTrace();
			}
		}
	}

}
