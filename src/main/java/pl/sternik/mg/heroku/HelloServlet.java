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

	HikariDataSource dataSource;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletOutputStream out = resp.getOutputStream();

		out.write("Hello Heroku".getBytes());
		System.out.println("---- hello -----");

		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(System.getenv("JDBC_DATABASE_URL"));
		if(config.getJdbcUrl() != null)
			dataSource = new HikariDataSource(config);
		else
			dataSource = new HikariDataSource();

		out.flush();
		out.close();
    }
	
	
}
