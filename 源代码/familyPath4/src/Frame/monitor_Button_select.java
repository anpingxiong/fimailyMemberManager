package Frame;

// 实现查询栏中的按钮功能

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import pojo.Member;

import Dao4MySqlImpl.FamilyDaoImpl;
import Dao4MySqlImpl.MemberDaoImpl;

import util.DBUtils;

class monitor_Button_select implements ActionListener {

	private Member getMember_2(int id) {
		Member member = new Member();
		String sql = "select  * from t_member where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = (Connection) DBUtils.getConn();
		pstmt = (PreparedStatement) DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setId(rs.getInt(1));
				member.setName(rs.getString(2));
				member.setSex(rs.getString(3));
				member.setBirthday(rs.getDate(4));
				member.setFamily(new FamilyDaoImpl().foundFamilyById(rs
						.getInt(5)));
				member.setFather(null);
				member.setMother(null);
				member.setPeiou(null);
				member.setAge(rs.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, pstmt, conn);
		}
		return member;
	}

	public   void  doSelected(){

		String str = Select_Frame.TextField_selectNameOrBirthday.getText();
		char ch[] = str.toCharArray();
		boolean flag = true; 			// 当是按姓名查找时则是true，按出生日期查找时则是false
		int count = 0;
		
			for(int i = 0; i < ch.length; i++){
				if('-' == ch[i]){
					String s[] = str.split("-");
					if(s.length < 3){
						JOptionPane.showMessageDialog(null,"输入的出生日期格式不正确！");				
						break;
					}else if(s.length == 3){
						flag = false;
						break;
					}				
				}else {
					count ++;
				}
			}
		if(count == ch.length){	
			if(flag){
				int jid = MainFrame.getFamily().getId();
				MemberDaoImpl md = new MemberDaoImpl();
				Member mb = new Member();
				mb = md.getMemberByName(str, jid);
				if(mb != null){
					MainFrame.tableBody = new String[1][7];
					MainFrame.tableBody[0][0] = mb.getAge() + "";
					MainFrame.tableBody[0][1] = mb.getName() + "";
					MainFrame.tableBody[0][2] = mb.getSex() + "";
					MainFrame.tableBody[0][3] = mb.getBirthday() + "";
					MainFrame.tableBody[0][4] = mb.getFather().getName() == null ? "" : mb.getFather().getName();
					MainFrame.tableBody[0][5] = mb.getMother().getName() == null ? "" : mb.getMother().getName();
					MainFrame.tableBody[0][6] = mb.getPeiou().getName() == null? "未婚" : mb.getPeiou().getName();
					MainFrame.mf.createTableBody();
				}else {
					JOptionPane.showMessageDialog(null, "当前家族中没有此名字");
			 
				}
			}
		}else if(!flag){
			int id = MainFrame.getFamily().getId();
			String sql = "select * from t_member where birthday = " + "'" + str + "'" + "and jid = " + id;
			Connection conn = DBUtils.getConn();
			PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
			ResultSet rs = null;
			try {
					rs = pstmt.executeQuery();
				
					List<Member> members = new ArrayList<Member>();
					rs.first();
					Member member = new Member();
					member.setId(rs.getInt(1));
					member.setName(rs.getString(2));
					member.setSex(rs.getString(3));
					member.setBirthday(rs.getDate(4));
					member.setFamily(new FamilyDaoImpl().foundFamilyById(rs
							.getInt(5)));
					member.setFather(getMember_2(rs.getInt(6)));
					member.setMother(getMember_2(rs.getInt(7)));
					member.setPeiou(getMember_2(rs.getInt(8)));
					member.setAge(rs.getInt(9));
					members.add(member);
					while(rs.next()){
						Member member2 = new Member();
						member2.setId(rs.getInt(1));
						member2.setName(rs.getString(2));
						member2.setSex(rs.getString(3));
						member2.setBirthday(rs.getDate(4));
						member2.setFamily(new FamilyDaoImpl().foundFamilyById(rs
							.getInt(5)));
						member2.setFather(getMember_2(rs.getInt(6)));
						member2.setMother(getMember_2(rs.getInt(7)));
						member2.setPeiou(getMember_2(rs.getInt(8)));
						member2.setAge(rs.getInt(9));
						members.add(member2);
					}
					MainFrame.tableBody = new String[members.size()][7];
					for(int i = 0; i < members.size(); i++ ){
						MainFrame.tableBody[i][0] = members.get(i).getAge() + "";
						MainFrame.tableBody[i][1] = members.get(i).getName() + "";
						MainFrame.tableBody[i][2] = members.get(i).getSex() + "";
						MainFrame.tableBody[i][3] = members.get(i).getBirthday() + "";
						MainFrame.tableBody[i][4] =  members.get(i).getFather().getName() == null ? "" :  members.get(i).getFather().getName();
						MainFrame.tableBody[i][5] =  members.get(i).getMother().getName() == null ? "" :  members.get(i).getMother().getName();
						MainFrame.tableBody[i][6] =  members.get(i).getPeiou().getName() == null? "未婚" :  members.get(i).getPeiou().getName();
			}				
					MainFrame.mf.createTableBody();
				
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,"家族中没有此日期出生的人！");
			}finally{
				DBUtils.close(rs, pstmt, conn);
			}
		}

	}
	public void actionPerformed(ActionEvent e) {
		doSelected();		
	}
}
