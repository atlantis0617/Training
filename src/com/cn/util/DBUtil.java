package com.cn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class DBUtil {
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;
	private static Connection con = null;
	private static int i;
	private static String url = "jdbc:mysql://101.132.115.209:3306/msmtms";
//	private static String url = "jdbc:oracle:thin:@192.168.137.198:1521:orcl";
	private static String user = "root";
	private static String password = "5gMLudEJe!";
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getCon(){
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static ResultSet getQuery(String sql,Object[]obj){
		try {
			ps = getCon().prepareStatement(sql);
			if(obj!=null){
				for(int i=0;i<obj.length;i++){
					ps.setObject(i+1, obj[0]);
				}
			}
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static int getUpdate(String sql,Object[]obj){
		try {
			ps = getCon().prepareStatement(sql);
			if(obj!=null){
				for(int i=1;i<obj.length;i++){
					ps.setObject(i, obj[i-1]);
				}
			}
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public static void close(){
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				ps=null;
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs=null;
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				con=null;
			}
		}
	}
}
