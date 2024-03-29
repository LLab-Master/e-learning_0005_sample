package chapter3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SampleDAO {
	private Connection con;

	public SampleDAO(Connection con) { // コンストラクタ
		super();
		this.con = con;
	}

	public int insert(Sample sample) {
		int result = 0;
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement("insert into sample (id, name) values (?, ?)"); // SQL
			psmt.setInt(1, sample.getId());
			psmt.setString(2, sample.getName());
			result = psmt.executeUpdate(); // SQL 実行
		} catch (SQLException e) {
			throw new RuntimeException("INSERT 失敗", e);
		} finally {
			try { // PreparedStatement をcloseする
				if (psmt != null) { // closeしないと領域が解放されない
					psmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("PreparedStatement close失敗", e);
			}
		}
		return result;
	}

	public List<Sample> SelectAll() { // select * from sample
		List<Sample> sampleList = new ArrayList<Sample>();
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement("select * from sample");
			ResultSet rs = psmt.executeQuery(); // ? がないのでそのまま実行
			while (rs.next()) { // 結果を順に取り出して
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				Sample sample = new Sample(id, name); // 結果をDTOにコピーして
				sampleList.add(sample); // Listに入れて返す
			}
		} catch (SQLException e) {
			throw new RuntimeException("SELECT 失敗", e);
		} finally {
			// PreparedStatementのclose (insert と同じ。省略)
		}
		return sampleList;
	}
}
