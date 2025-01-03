package steps;

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

import org.junit.Assert;
import org.testng.annotations.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;

public class dbTest extends CommonMethods {
	public static Connection conn;
	public static Statement st;
	public static ResultSet rs;
	public static ResultSetMetaData rsMetaData;

	@Given("I am connected to the database")
	public void i_am_connected_to_the_database() throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://147.182.216.34:3306/classicmodels", "user1", "Neotech@123");
		st = conn.createStatement();
		rs = st.executeQuery("SELECT * FROM customers order by creditLimit desc limit 3;");

	}

	@When("I get the top three customers by credit limit")
	public void i_get_the_top_three_customers_by_credit_limit() throws SQLException {
		rsMetaData = rs.getMetaData();

	}

	@Then("I print their names and credit limits")
	public void i_print_their_names_and_credit_limits() throws SQLException {
		Map<String, String> map;
		List<Map<String, String>> listOfMaps = new ArrayList<>();
		int colCount = rsMetaData.getColumnCount();

		while (rs.next()) {
			map = new LinkedHashMap<>();
			for (int i = 1; i <= colCount; i++) {
				map.put(rsMetaData.getColumnName(i), rs.getString(i));
			}
			listOfMaps.add(map);
		}
		for(Map<String,String> m:listOfMaps) {
			System.out.println(" Name = "+m.get("customerName")+" | creditLimit =  "+m.get("creditLimit")+" | ");
			
		}
 	}

	@Then("I close the database connection")
	public void i_close_the_database_connection() throws SQLException {
		rs.close();
		st.close();
		conn.close();
	}
	
	@Test
	public void testingDB() throws SQLException {
		Connection conn=DriverManager.getConnection("jdbc:mysql://147.182.216.34:3306/classicmodels","user1","Neotech@123");
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM customers where state='NY';");
		
		ResultSetMetaData rsMetaData=rs.getMetaData();
		int col=rsMetaData.getColumnCount();
		List<Map<String,String>> listOfMaps=new ArrayList<>();
		int rowCount=0;
		while(rs.next()) {
		    rowCount++;
			Map<String,String> m=new LinkedHashMap<>();
			for(int i=1;i<col;i++) {
				String key=rsMetaData.getColumnName(i);
				String value=rs.getString(i);
				m.put(key, value);
			}
			listOfMaps.add(m);
		}
		for(Map<String,String> m: listOfMaps) {
			System.out.println(m);
		}
		System.out.println(rowCount);
		Assert.assertEquals(6, rowCount);
	}
	//Display all employees whose last name ends with ‘n’
	@Test
	public static void db2024() throws SQLException {
		Connection conn=DriverManager.getConnection("jdbc:mysql://147.182.216.34:3306/classicmodels","user1","Neotech@123");
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM employees where lastName like '%n';");
		
		ResultSetMetaData rsMetaData=rs.getMetaData();
		
		Map<String,String> map;
		List<Map<String,String>> listOfMaps=new ArrayList<>();
		while(rs.next()) {
 			map=new LinkedHashMap<>();
 			for(int i=1;i<rsMetaData.getColumnCount();i++) {
 				map.put(rsMetaData.getColumnName(i), rs.getString(i));
 			}
 			listOfMaps.add(map);
		}
		
		for(Map<String,String>m:listOfMaps) {
			System.out.println(m);
		}
		
	}
	//jdbc:mysql://147.182.216.34:3306/classicmodels
	//user1
	//Neotech@123
	
	@Test
	public static void notUSA() throws SQLException {
		 Connection conn=DriverManager.getConnection("jdbc:mysql://147.182.216.34:3306/classicmodels","user1","Neotech@123");
		 Statement st=conn.createStatement();
		 ResultSet rs=st.executeQuery("SELECT * FROM offices where country!='USA';");
		 ResultSetMetaData rsMetaData=rs.getMetaData();
		 Map<String,String> map;
		 List<Map<String,String>> listOfMaps=new ArrayList<>();
		 
		 while(rs.next()) {
			 map = new LinkedHashMap<>();
			 for(int i=1;i<rsMetaData.getColumnCount();i++) {
				 map.put(rsMetaData.getColumnName(i), rs.getString(i));
			 }
			 listOfMaps.add(map);
		 }
		 for(Map<String,String> m:listOfMaps) {
			 System.out.println(m.get("country"));
		 }
	}
	/*        Connect to classicmodels database
        Execute a query to get all information of customer with id 124
        Get the resultset metadata
        Print the number of columns
        Get all the column names and store them in an arraylist
        Print the Arraylist
*/
	//jdbc:mysql://147.182.216.34:3306/classicmodels
	//user1
	//Neotech@123
	@Test
	public static void DataMi() throws SQLException {
		Connection conn=DriverManager.getConnection("jdbc:mysql://147.182.216.34:3306/classicmodels","user1","Neotech@123");
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM customers where customerNumber='124';");
		ResultSetMetaData rsMetaData=rs.getMetaData();
		
		Map<String,String> map;
		List<Map<String,String>> listOfMaps= new ArrayList<>();
		
		while(rs.next()) {
			map=new LinkedHashMap<>();
			for(int i=1;i<rsMetaData.getColumnCount();i++) {
				map.put(rsMetaData.getColumnName(i) ,rs.getString(i));
			}
			listOfMaps.add(map);
		}
		
		for(Map<String,String> m:listOfMaps) {
			String customerNumber=m.get("customerNumber");
			Assert.assertEquals(customerNumber, "124");
			System.out.println(m);
		}
	}
	
	
	
}
