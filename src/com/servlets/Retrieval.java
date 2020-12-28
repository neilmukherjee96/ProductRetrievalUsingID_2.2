package com.servlets;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.DBConnection;
@WebServlet("/Retrieval")
public class Retrieval extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("product_id");
		//DB here
		String resultSQL = "Select id, name, category, price from product where id=?";
		try {
			InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("configr.properties");
			Properties props = new Properties();
			props.load(in);
			DBConnection conn = new DBConnection(props.getProperty("dbURL"), props.getProperty("user"), props.getProperty("pwd"));
			PreparedStatement preparedStatemnt = conn.getConnection().prepareStatement(resultSQL);
			preparedStatemnt.setInt(1, Integer.parseInt(productId));
			ResultSet rs = preparedStatemnt.executeQuery();
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<table border='1' width='50%'>");
			out.println("<tr>");
			out.println("<th>Model Id</th> <th>Product Name</th> <th>Price</th> <th>Category</th> </tr>");
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String category = rs.getString(3);
				double price = rs.getDouble(4);
				out.println("<tr>");
				out.println("<td>" + id + "</td> <td>" + name + "</td> <td>" + price + "</td> <td>" + category + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</body></html>");
			preparedStatemnt.close();
			conn.closeConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}