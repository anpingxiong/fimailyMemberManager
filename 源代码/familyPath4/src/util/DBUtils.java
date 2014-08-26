package util;

import java.sql.*;

import javax.swing.JOptionPane;

public class DBUtils {
   private  static   String  url  =  null;
   private  static   String  username  =  null;
   private  static   String  password  =  null;
    static {
	     url    = ReadProperties.getValue("url");
	  
	     username  = ReadProperties.getValue("username");
	     password   = ReadProperties.getValue("password");
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
	    	
			System.out.println("Driver fails to load...");
    	}
	}
	
	public static Connection getConn(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch(SQLException e){
			System.out.println("link failed to database...");
	        JOptionPane.showMessageDialog(null, "数据库连接失败,请仔细检查配置文件是否填写正确");
	    	
		}
		return conn;
	}
	
	public static PreparedStatement getPstmt(Connection conn, String sql) {
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
		}catch(SQLException e){
			System.out.println("预处处理失败");
		}
		return ps;
	}
	
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn){
		if(rs != null){
			try{
				rs.close();
			}catch(SQLException e){
				System.out.println("关闭异常");
			}
		}
		if(ps != null){
			try{
				ps.close();
			}catch(SQLException e){
				System.out.println("关闭异常");
			}
		}
		if(conn != null){
			try{
				conn.close();
			}catch(SQLException e){
				System.out.println("关闭异常");
			}
		}
	}
}