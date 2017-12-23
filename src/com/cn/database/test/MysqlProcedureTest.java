package com.cn.database.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class MysqlProcedureTest {
	
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
	public static final String URL = "jdbc:mysql://192.168.10.10:3306/msmtmss"; 
	public static final String USERNAME = "root"; 
	public static final String PASSWORD = "root"; 
	
	public static void main(String[] args) {
		/* 
		 * 对应的存储过程 
		 *DROP PROCEDURE IF EXISTS DataTest; 
		 *delimiter;
		 *CREATE PROCEDURE DataTest(OUT param1 INT) 
		 *BEGIN 
		 *   SELECT COUNT(0) INTO param1 FROM base_order; 
		 *END 
		 *delimiter; 
		 */ 
		try {
			Class.forName(DRIVER_CLASS);
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
			String sql = "{CALL DataTest(?)}"; //调用存储过程 
			CallableStatement cstm = connection.prepareCall(sql); //实例化对象cstm
			cstm.registerOutParameter(1, Types.INTEGER); // 设置返回值类型 即返回值 
			cstm.execute(); // 执行存储过程 
			System.out.println(cstm.getInt(1)); 
			cstm.close(); 
			connection.close(); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
}
