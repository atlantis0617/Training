package com.cn.database.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


import com.cn.util.DBUtil;

public class ProcedureTest {
		public static void main(String[] args) {
			//创建带出参存储过程代码：
			/**
			 * --带出参存储过程
			 *	CREATE OR REPLACE PROCEDURE stu_proc(v_name OUT VARCHAR2) AS
			 *	BEGIN 
  			 *	SELECT o.sname INTO v_name FROM student o where o.id = 2;
			 *	END;
			 * */
//				Connection conn = null;
//		        CallableStatement statement = null;
//		        String sql = "{call po_proc(?)}";
//		        
//		        try {
//		            conn = DBUtil.getCon();
//		            statement = conn.prepareCall(sql);
//		            statement.registerOutParameter(1, Types.VARCHAR);
//		            statement.executeUpdate();
//		            //输出：lisi
//		            String pcode = statement.getString(1);
//		            System.out.println(pcode);
//		        } catch (SQLException e) {
//		            e.printStackTrace();
//		        }finally{
//		            DBUtil.close();
//		        }
		        
		      //创建带出入参存储过程代码
		      /**
		       * CREATE OR REPLACE PROCEDURE stu_proc(v_id IN NUMBER, v_name OUT VARCHAR2) AS
			   * BEGIN 
  			   *  SELECT o.sname INTO v_name FROM student o where o.id = v_id;
			   * END;
		       * 
		       * */
		        Connection conn = null;
		        CallableStatement statement = null;
		        String sql = "{call send_mail(?,?,?)}";
		        try {
		            conn = DBUtil.getCon();
		            statement = conn.prepareCall(sql);
		            statement.setString(1, "973549268@qq.com");
		            statement.setString(2, "test");
		            statement.setString(3, "This is a ORACLE PROCEDURE Test!!");
		            statement.execute();
//		            statement.registerOutParameter(2, Types.VARCHAR);
//		            statement.executeUpdate();
		            //输出：zhangsan
//		            String pcode = statement.getString(2);
//		            System.out.println(pcode);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }finally{
		        	DBUtil.close();
		        }
			
			//返回结果集的存储过程
			/**
			 * --创建存储过程
			 *CREATE OR REPLACE PROCEDURE PO_TEST(O_CUR OUT SYS_REFCURSOR) IS
			 *BEGIN
       		 *OPEN O_CUR  FOR
       		 *SELECT * FROM PRODUCTION_ORDER O WHERE 1=1;
			 *END;                             
			 * 
			 * */
//		    Connection conn = null;
//	        CallableStatement statement = null;
//	        String sql = "{call PO_TEST(?)}";
//	        ResultSet rs = null;
//	        try {
//	            conn = DBUtil.getCon();
//	            statement = conn.prepareCall(sql);
//	            statement.registerOutParameter(1, OracleTypes.CURSOR);
//	            statement.execute();
//	            rs = (ResultSet) statement.getObject(1);
//	            while (rs.next()) {
//	                System.out.println("\t" + rs.getString(1) + "\t"
//	                        + rs.getString(2) + "\t"+ rs.getString(3) + "\t"+ rs.getString(4) + "\t");
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }finally{
//	            DBUtil.close();
//	        }
		}
}
