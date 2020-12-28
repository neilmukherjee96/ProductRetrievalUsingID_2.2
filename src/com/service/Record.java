package com.service;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dao.DBConnection;
import com.model.Product;

public class Record {

	List<Product> recordsList = new ArrayList<>();

	public int getRecords() {
		String retrieveSQL = "select * from product";
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream("configr.properties");
			Properties props = new Properties();
			props.load(in);

			DBConnection conn = new DBConnection(props.getProperty("dbURL"), props.getProperty("user"),
					props.getProperty("pwd"));
			Statement statement = conn.getConnection().createStatement();

			System.out.println("Retrieving records....");
			ResultSet rs = statement.executeQuery(retrieveSQL);
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setPrice(rs.getDouble(3));
				p.setCategory(rs.getString(4));
				recordsList.add(p);
			}
			
			statement.close();
			conn.closeConnection();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recordsList.size();
	}

	
}
