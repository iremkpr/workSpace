package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DBUtils {
	public static Connection conn;
	public static Statement st;
	public static ResultSet rs;

	@Test
	public void getDataFromDB() throws SQLException {
		getConnection();
		List<Map<String, String>> l = storeData(
				"SELECT employeeNumber, lastName, firstName,email,jobTitle  FROM employees LIMIT 5;");

		for(Map<String, String> m:l) {
			System.out.println(m);
		}
		
 		closeConnection();

	}
	
	public static void getConnection() throws SQLException {

		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		conn = DriverManager.getConnection(ConfigsReader.getProperty("dbUrl"), ConfigsReader.getProperty("dbUserName"),
				ConfigsReader.getProperty("dbPassword"));
	}

	public static List<Map<String, String>> storeData(String query) throws SQLException {

		st = conn.createStatement();
		rs = st.executeQuery(query);
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		List<Map<String, String>> listOfMap = new ArrayList<>();
		Map<String, String> map;
		while (rs.next()) {
			map = new LinkedHashMap<>();
			for (int i = 1; i <= columnCount; i++) {
				map.put(rsMetaData.getColumnName(i), rs.getString(i));
			}
			listOfMap.add(map);
		}
		return listOfMap;
	}

	public static void closeConnection() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (st != null) {

			st.close();
		}
		if (conn != null) {

			conn.close();
		}
	}
}
