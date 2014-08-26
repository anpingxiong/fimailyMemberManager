package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import anping.AnfunctionImpl;

import pojo.Member;

import util.DBUtils;

import Dao4MySqlImpl.MemberDaoImpl;

class monitor_Button_delOk implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		
			int jid = MainFrame.getFamily().getId();
			MemberDaoImpl md = new MemberDaoImpl();
			String str = Delete_Frame.TextField_delName.getText();
		                
			Member mb = new Member();
			mb = md.getMemberByName(str, jid);
			if(mb != null){
				if(JOptionPane.showConfirmDialog(null, "删除时会将此人的妻子和子孙一并删除！","确认框", JOptionPane.YES_NO_OPTION) == 0){				
					
					MainFrame.Button_rollBack.setEnabled(true);
					   AnfunctionImpl.rememberDelete(str, jid) ;
	                              ////删除了孩子				
					  md.deleteMemberByName(mb.getName(), jid);
                               ////删除那些女人
					    md.deleteWomen();
					    AnfunctionImpl.fleshMainFrame();
					  JOptionPane.showMessageDialog(null, "数据一删除,如想恢复,请点击撤销删除");
				}
			}else {
				JOptionPane.showMessageDialog(null, "当前家族中没有此名字！");
			}
        MainFrame.mf.createTree();
	}
	}
	
 
