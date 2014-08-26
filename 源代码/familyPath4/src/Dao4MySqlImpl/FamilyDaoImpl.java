package Dao4MySqlImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pojo.Family;
import util.DBUtils;

public class FamilyDaoImpl {
	public void save(Family family, String password) {
		String sql = "insert  into t_family values(null,?,?,?)";
		String sql_2 = "insert into  t_mibao values(?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_2 = null;
		conn = DBUtils.getConn(); // 1连接
		pstmt = DBUtils.getPstmt(conn, sql); // 2创建运输工具
		pstmt_2 = DBUtils.getPstmt(conn, sql_2);
		try {
			pstmt.setString(1, family.getNum());
			pstmt.setString(2, family.getName());
			pstmt.setString(3, family.getPassword());
			pstmt_2.setString(1, family.getNum()); 
			pstmt_2.setString(2, password);

			pstmt.executeUpdate(); // 3 执行
			pstmt_2.executeUpdate(); // 3 执行

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, pstmt, conn); // 4关闭
		}
	}

	public void delete(int id) {
		String sql = "delete from t_family where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = DBUtils.getConn();
		pstmt = DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, pstmt, conn);
		}
	}

	// 查找fammily所有 传入家族编号

	public Family foundFamilyById(int id) {
		Family fa = null;
		String sql = "select *  from t_family where id= ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		conn = DBUtils.getConn();
		pstmt = DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt(1);
				String num = rs.getString(2);
				String familyname = rs.getString(3);
				String password = rs.getString(4);
				fa = new Family();
				fa.setId(id2);
				fa.setName(familyname);
				fa.setNum(num);
				fa.setPassword(password);
				fa.setMembers(null); // 拿到我们的这个家族的成员
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, pstmt, conn);
		}
		return fa;
	}

	public List<Family> foundAllFamily() {

		List<Family> fa = new ArrayList<Family>();
		String sql = "select *  from t_family  ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = DBUtils.getConn();
		pstmt = DBUtils.getPstmt(conn, sql);
		try {
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Family faa = new Family();
				int id = rs.getInt(1);
				String num = rs.getString(2);
				String familyname = rs.getString(3);
				String password = rs.getString(4);

				faa.setId(id);
				faa.setName(familyname);
				faa.setNum(num);
				faa.setPassword(password);
				faa.setMembers(null); // 拿到我们的这个家族的成员
				fa.add(faa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, pstmt, conn);
		}
		return fa;

	}

	public Family getFamilyByNum(String num) {
		Family family = null;
		String sql = "select  * from  t_family where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = DBUtils.getConn();
		pstmt = DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				family = new Family();
				family.setId(rs.getInt(1));
				family.setNum(rs.getString(2));
				family.setName(rs.getString(3));
				family.setPassword(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, pstmt, conn);
		}
		return family;
	}

}
