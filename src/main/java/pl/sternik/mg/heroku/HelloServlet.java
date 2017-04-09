package pl.sternik.mg.heroku;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletOutputStream out = resp.getOutputStream();

		out.write("Hello Heroku".getBytes());
		System.out.println("---- hello -----");

//		HikariConfig config = new HikariConfig();
//		config.setJdbcUrl(System.getenv("JDBC_DATABASE_URL"));
//		final HikariDataSource dataSource = (config.getJdbcUrl() != null) ? new HikariDataSource(config)
//				: new HikariDataSource();
//
//		try (Connection connection = dataSource.getConnection()) {
//			Statement stmt = connection.createStatement();
//			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//			stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//			ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//
//			ArrayList<String> output = new ArrayList<String>();
//			while (rs.next()) {
//				output.add("Read from DB: " + rs.getTimestamp("tick"));
//				out.write(("\nRead from DB: " + rs.getTimestamp("tick")).getBytes());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		out.flush();
		out.close();
    }
	
	
}
