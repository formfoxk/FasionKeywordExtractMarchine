package DATA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WordDB {
	private String db_name;

	public WordDB(String db_name) {
		this.db_name = db_name;
	}

	public void insert(ArrayList<Word> word) {
		Connection conn = null;
		java.sql.Statement stmt = null;
		try {
			conn = Conn.getConnection();
			stmt = conn.createStatement();
			for (Word w : word) {
				String sql = "insert into " + db_name + " values('"
						+ w.getWord() + "', '" + w.getCount() + "')";
				stmt.executeUpdate(sql);
			}
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conn.close(stmt, conn);
		}
	}

	public void allDelete() {
		Connection conn = null;
		java.sql.Statement stmt = null;
		try {
			conn = Conn.getConnection();
			stmt = conn.createStatement();
			String sql = "DELETE FROM " + db_name;
			stmt.executeUpdate(sql);
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			Conn.close(stmt, conn);
		}
	}

	public ArrayList<Word> getWordList() {
		ArrayList<Word> word_list = new ArrayList<Word>();

		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Conn.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from " + db_name;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				word_list.add(new Word(rs.getString("word"), rs.getInt("count")));
			}
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			Conn.close(rs, stmt, conn);
		}

		return word_list;
	}
}