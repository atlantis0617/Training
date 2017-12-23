package com.cn.database.test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.cn.util.DBUtil;


public class JdbcTest {
	public static void main(String[] args) throws SQLException {
//		String sql = "select * from users";
		String sql = "select b.id as bid,b.RELATED_NUMBER as br from BASE_ORDER b";
		Object[]obj=null;
		ResultSet rs = DBUtil.getQuery(sql, obj);
		ResultSetMetaData md =  rs.getMetaData();
		System.out.println(md.getColumnCount());
		try {
			while(rs.next()){
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
