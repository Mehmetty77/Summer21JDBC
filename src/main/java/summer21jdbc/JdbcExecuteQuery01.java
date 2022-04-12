package summer21jdbc;

//1.Step having phone , importing sql package.
import java.sql.*;

public class JdbcExecuteQuery01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// Registering gsm operator.Registering our driver.

		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 3.Step : Call your friend means establish connection with the database.

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain", "mehmetty",
				"01061453");

		// 4.Step: Create some sentences to tell to your friend means "Create Statement"
		Statement st = con.createStatement();

		// 5.Step:Start to talk with your friend means "Create SQL Query and execute it"
		String q1 = "SELECT * FROM employees";
		ResultSet r1 = st.executeQuery(q1);

		// 6.Step: Do some actions according to the result
		while (r1.next()) {
			System.out.println(r1.getInt(1) + " - " + r1.getString(2));
		}

		String q2 = "SELECT * FROM employees WHERE employee_id<13";

		ResultSet r2 = st.executeQuery(q2);

		while (r2.next()) {
			System.out.println(r2.getString("employee_first_name"));
		}

		String q3 = "SELECT employee_id, employee_first_name " + "FROM employees " + "ORDER BY employee_id DESC "
				+ "OFFSET 2 ROW " + "FETCH NEXT 1 ROW ONLY";
		ResultSet r3 = st.executeQuery(q3);
		while (r3.next()) {
			System.out.println(r3.getInt("employee_id") + " --> " + r3.getString("employee_first_name"));
		}

		/*
		 * //4.Example: Select the company whose id is the second lowest String q4 =
		 * "SELECT company_name FROM my_companies ORDER BY company_id ASC OFFSET 1 ROW FETCH NEXT 1 ROW ONLY"
		 * ; ResultSet r4 = st.executeQuery(q3); while(r4.next()) {
		 * System.out.println(r4.getString("company_name")); }
		 * 
		 * 
		 * 
		 */
		// 7.Step: End the call means "Close the DB Connection"
		con.close();
		st.close();
		r1.close();
		r2.close();
	}
}