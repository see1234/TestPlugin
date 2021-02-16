package me.test.plugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

	private String host;
	private String dbname;
	private String user;
	private String password;
	private Boolean sqlite;
	public static String name;

	private Connection connection;

	public DatabaseManager(Main main) {
		
		host = "localhost";
		dbname = "test123";
		user = "rootf";
		password = "zx123zx12";
		name = "gemor1221";
		sqlite = false;
		openConnection();
		loadTables();
	}
	/**
	 * 
	 */
	private void openConnection() {
		if (!isConnected()) {
			try {
			    if(sqlite == false) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + dbname + "?useUnicode=true&characterEncoding=utf-8&autoReconnect=true", user, password);
			    }
			    else {
			    	Class.forName("org.sqlite.JDBC");
			    	connection = DriverManager.getConnection("jdbc:sqlite:" + Main.getInstance().getDataFolder() + File.separator + "database.db");
			    }
			} catch (SQLException ex) {
			} catch (ClassNotFoundException ex) {
			}
		}
	}

	private void loadTables() {
		if (isConnected()) {
			update("CREATE TABLE IF NOT EXISTS " + name + "(Date VARCHAR(256), name VARCHAR(16), NameOcelot VARCHAR(2560), PRIMARY KEY (Date))");
		}
	}

	public void closeConnection() {
		if (isConnected()) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException ex) {
			}
		}
	}

	public void update(String query) {
		if (isConnected()) {
			try {
				connection.prepareStatement(query).executeUpdate();
			} catch (SQLException ex) {
			}
		}
	}

	public ResultSet getResult(String query) {
		if (isConnected()) {
			try {
				return connection.prepareStatement(query).executeQuery();
			} catch (SQLException ex) {
			}
		}
		return null;
	}

	public String getHost() {
		return host;
	}

	public String getDBName() {
		return dbname;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public Connection getConnection() {
		return connection;
	}

	public boolean isConnected() {
		return connection != null;
	}
}
