package com.accp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;



public class BaseDao {
		static {
			String driver="com.mysql.jdbc.Driver";
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	private Connection con;
	private PreparedStatement ps;
	String url="jdbc:mysql://localhost:3306/student";
	public void getConn() {
		try {
			con=DriverManager.getConnection(url,"root","123456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int update(String sql,Object...objects) throws SQLException{
		getConn();
		
		ps = con.prepareStatement(sql);
		
		bindsObjects(objects);
		
		return ps.executeUpdate();
	} 
	private void bindsObjects(Object... objects) throws SQLException {
		for(int i = 0;i<objects.length;i++){
			ps.setObject(i+1, objects[i]);
		}
	}
	public ResultSet query(String sql,Object...objects) throws SQLException{
		getConn();
		
		ps = con.prepareStatement(sql);
		
		bindsObjects(objects);
		
		return ps.executeQuery();
	}
	
	public void closeAll(ResultSet rs) throws SQLException{
		if(rs != null){
			rs.close();
		}
		if(ps != null){
			ps.close();
		}
		if(con != null && !con.isClosed()){
			con.close();
		}
	}

}
