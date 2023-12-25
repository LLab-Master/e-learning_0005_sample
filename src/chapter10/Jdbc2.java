package chapter10;

import java.sql.Connection;
import java.util.List;

public class Jdbc2 {

	public static void main(String[] args) {
		// Open connection
		ConnectionManager cm = new ConnectionManager();
		Connection con = cm.getConnection();
		// DAO
		SampleDAO sampleDao = new SampleDAO(con);
		// Insert
		Sample sample2 = new Sample(100, "Momotaro");
		sampleDao.insert(sample2);
		// Select All
		List<Sample> sampleList = sampleDao.SelectAll();
		for (Sample sample : sampleList) {
			System.out.println("ID = " + sample.getId());
			System.out.println("Name = " + sample.getName());
			System.out.println();
		}
		// Close connection
		cm.closeConnection();
	}
}
