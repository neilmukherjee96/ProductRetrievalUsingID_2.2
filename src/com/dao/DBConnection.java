package com.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.model.Product;

public class DBConnection {
	private Connection connection;

	public DBConnection(String dbURL, String user, String pwd) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection(dbURL, user, pwd);
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeConnection() throws SQLException {
		if (this.connection != null)
			this.connection.close();
	}

	public static void seedDatabase() {
		String clearSeedsSQL = "drop database project";
		String createSchemaSQL = "create database project";
		String useSchemaSQL = "use project";
		String createTableSQL = "create table product(id integer auto_increment primary key, name varchar(255),price decimal(10,2),category varchar(255))";
		try {
			InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("config.properties");
			Properties props = new Properties();
			props.load(in);

			DBConnection conn = new DBConnection(props.getProperty("dbURL"), props.getProperty("user"),
					props.getProperty("pwd"));
			Statement statement = conn.getConnection().createStatement();
			try {
				statement.executeUpdate(clearSeedsSQL);
				System.out.println("Dropping existing database");
			} catch (SQLException e) {
				System.out.println("Does not exist.");
			}

			statement.executeUpdate(createSchemaSQL);
			System.out.println("Creating schema");
			statement.executeUpdate(useSchemaSQL);
			System.out.println("Selecting schema");
			statement.executeUpdate(createTableSQL);
			System.out.println("Create table");

			String product1SQL = "insert into product(name, price, category) values('Laptop',1000,'Electronics')";
			String product2SQL = "insert into product(name, price, category) values('ipad',500,'Electronics')";
			String product3SQL = "insert into product(name, price, category) values('barbell',100,'Sports')";
			String product4SQL = "insert into product(name, price, category) values('Joggers',10,'Wearables')";
			String product5SQL = "insert into product(name, price, category) values('Glasses',15,'Fashion')";
			String product6SQL = "insert into product(name, price, category) values('Notebook',2,'stationary')";
			String product7SQL = "insert into product(name, price, category) values('Charger',1000,'Accessory')";
			String product8SQL = "insert into product(name, price, category) values('Fridge',1000,'Appliances')";
			String product9SQL = "insert into product(name, price, category) values('Knives',13,'HomeGoods')";
			String product10SQL = "insert into product(name, price, category) values('Sofa',300,'Furniture')";

			statement.executeUpdate(product1SQL);
			statement.executeUpdate(product2SQL);
			statement.executeUpdate(product3SQL);
			statement.executeUpdate(product4SQL);
			statement.executeUpdate(product5SQL);
			statement.executeUpdate(product6SQL);
			statement.executeUpdate(product7SQL);
			statement.executeUpdate(product8SQL);
			statement.executeUpdate(product9SQL);
			statement.executeUpdate(product10SQL);
			System.out.println("Updating records....");

			statement.close();
			conn.closeConnection();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		seedDatabase();
	}
}
