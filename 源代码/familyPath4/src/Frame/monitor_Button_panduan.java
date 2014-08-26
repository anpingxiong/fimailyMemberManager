package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import pojo.Member;
import util.DBUtils;
import Dao4MySqlImpl.MemberDaoImpl;

class monitor_Button_panduan implements ActionListener{

	DefaultMutableTreeNode node_1;
	DefaultMutableTreeNode node_2;
	MemberDaoImpl md = new MemberDaoImpl();
	int jid = MainFrame.getFamily().getId();
	
	public void actionPerformed(ActionEvent e) {		
		String name_one = new String(Select_Frame.TextField_panduan_1.getText());
		String name_two = new String(Select_Frame.TextField_panduan_2.getText());		
		Member one = new Member();
		Member two = new Member();
		one = md.getMemberByName(name_one, jid);
		two = md.getMemberByName(name_two, jid);
		if(one != null){
			if(two != null){
				
				if(one.getName().equals(two.getName())){
					JOptionPane.showMessageDialog(null,"名称相同，无法判断");
					return ;
				}
				
					// 在树中找到该节点
					node_1 = findNode(Tree_display.dmtRoot,name_one);    
					node_2 = findNode(Tree_display.dmtRoot,name_two);//end 
					
					//如果是女性则找她丈夫
					String s = null;				
					s = findWife(one);
					if(s != null){
						node_1 = findNode(Tree_display.dmtRoot,s);
					}
					s = null;
					s = findWife(two);
					if(s != null){
						node_2 = findNode(Tree_display.dmtRoot,s);
					} //end 
					
					//相同代系的比较start
					if(one.getAge() == two.getAge()){
						//往上找父亲，直到找到共同的父亲为止
						DefaultMutableTreeNode node1_parent = (DefaultMutableTreeNode)node_1.getParent();
						DefaultMutableTreeNode node2_parent = (DefaultMutableTreeNode)node_2.getParent();
						int count = 1;
						while(!((String)node1_parent.getUserObject()).equals((String)node2_parent.getUserObject())){
							node1_parent = (DefaultMutableTreeNode)node_1.getParent();
							node2_parent = (DefaultMutableTreeNode)node_2.getParent();
							count ++;
							if(count > 3)break;
						}//end
						if(count > 3){
							JOptionPane.showMessageDialog(null, "两人同代有共同的祖先，以族兄族弟族姐族妹称呼");
							return;
						}						
						judgeEqualsAge(one, two, node_1, node_2, count);		
					}else if(one.getAge() != two.getAge()){
						int i = one.getAge() - two.getAge();
						if(i < -2 || i > 2){
							JOptionPane.showMessageDialog(null, "两人代系差异超过三代，没有合适的称呼");
							return ;
						}
						judgeNoEqualsAge(one,two,node_1,node_2,i);
					}							
			}else {
				JOptionPane.showMessageDialog(null, name_two + "没有此人！");
			}
		}else {
			JOptionPane.showMessageDialog(null, name_one + "没有此人！");
		}
	}
	
	public DefaultMutableTreeNode findNode(DefaultMutableTreeNode node, String name){   // 遍历树寻找结点
		DefaultMutableTreeNode temp_node = node;
		try {
			new String((String)temp_node.getUserObject()).equals(name);
		}
			 catch (Exception e) {
					JOptionPane.showMessageDialog(null, "请先查看全部成员！"); 
					}
		
		if(new String((String)temp_node.getUserObject()).equals(name)){
				return temp_node;
			}else {
				return findNode(temp_node.getNextNode(),name);
			  }
	
	}
		  
	
	
	
	// 判断是否为某人的妻子，如果是则返回这个女性的丈夫名字，否则就返回null
	public String findWife(Member temp){
		String str = null;
		if(temp.getSex().equals("女")){
			String sql = "select * from t_member where jid = " + MainFrame.getFamily().getId() 
					+ " and wid = " + temp.getId();
			Connection conn = DBUtils.getConn();
			PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
			ResultSet rs = null;
			try {
				rs = pstmt.executeQuery();
				while(rs.next()){
					str = rs.getString(2);
				}
			} catch (SQLException e) {
				
			}finally {
				DBUtils.close(rs, pstmt, conn);
			}
		}
		return str;
	}
	
	//相同代系的比较
	private void judgeEqualsAge(Member one, Member two, DefaultMutableTreeNode node_1, DefaultMutableTreeNode node_2,  int count){
		if(count == 1){   //找一次找到共同的父亲
			if(one.getSex().equals("男")){
				if(two.getSex().equals("女")){
					if(((String)node_1.getUserObject()).equals((String)node_2.getUserObject())){
						Select_Frame.TextField_guanxi.setText("夫妻关系");
					}else{
						Member temp_mb = new Member();
						temp_mb = md.getMemberByName((String)node_2.getUserObject(), jid);
						if(temp_mb.getSex().equals("男")){
							// 再次判断年龄
							
							Select_Frame.TextField_guanxi.setText("两人关系为：弟妹/嫂子");
						}else if(temp_mb.getSex().equals("女")){
							// 再次判断年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：兄妹/姐弟");
						}
					}
				}else if(two.getSex().equals("男")){
					// 比较年龄
					Select_Frame.TextField_guanxi.setText("两人关系为：亲兄弟");
				}
			}else if(one.getSex().equals("女")){
				Member temp_mb = new Member();
				temp_mb = md.getMemberByName((String)node_1.getUserObject(), jid);
				if(two.getSex().equals("男")){
					if(((String)node_1.getUserObject()).equals((String)node_2.getUserObject())){
						Select_Frame.TextField_guanxi.setText("两人关系为：夫妻");
					}else{						
						if(temp_mb.getSex().equals("男")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：弟妹/嫂子");
						}else if(temp_mb.getSex().equals("女")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：兄妹/姐弟");
						}
					}
				}else if(two.getSex().equals("女")){
					Member temp2_mb = new Member();
					temp2_mb = md.getMemberByName((String)node_2.getUserObject(), jid);
					if(temp_mb.getSex().equals("男")){
						if(temp2_mb.getSex().equals("男")){
							Select_Frame.TextField_guanxi.setText("两人关系为：妯娌");
						}else if(temp2_mb.getSex().equals("女")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：弟妹/嫂子");
						}
					}else if(temp_mb.getSex().equals("女")){
						if(temp2_mb.getSex().equals("男")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：弟妹/嫂子");
						}else if(temp2_mb.getSex().equals("女")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：姐妹");
						}
					}
				}
			}
		}else if(count == 2){     //找2次找到共同的父亲
			if(one.getSex().equals("男")){
				if(two.getSex().equals("女")){
					if(((String)node_1.getUserObject()).equals((String)node_2.getUserObject())){
						Select_Frame.TextField_guanxi.setText("两人关系为：夫妻");
					}else{
						Member temp_mb = new Member();
						temp_mb = md.getMemberByName((String)node_2.getUserObject(), jid);
						if(temp_mb.getSex().equals("男")){
							// 再次判断年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：弟妹/嫂子");
						}else if(temp_mb.getSex().equals("女")){
							// 再次判断年龄
							System.out.println("两人关系为：堂兄妹/姐弟");
						}
					}
				}else if(two.getSex().equals("男")){
					// 比较年龄
					Select_Frame.TextField_guanxi.setText("两人关系为：堂兄弟");
				}
			}else if(one.getSex().equals("女")){
				Member temp_mb = new Member();
				temp_mb = md.getMemberByName((String)node_1.getUserObject(), jid);
				if(two.getSex().equals("男")){
					if(((String)node_1.getUserObject()).equals((String)node_2.getUserObject())){
						Select_Frame.TextField_guanxi.setText("两人关系为：夫妻");
					}else{						
						if(temp_mb.getSex().equals("男")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：弟妹/嫂子");
						}else if(temp_mb.getSex().equals("女")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：堂兄妹/姐弟");
						}
					}
				}else if(two.getSex().equals("女")){
					Member temp2_mb = new Member();
					temp2_mb = md.getMemberByName((String)node_2.getUserObject(), jid);
					if(temp_mb.getSex().equals("男")){
						if(temp2_mb.getSex().equals("男")){
							Select_Frame.TextField_guanxi.setText("两人关系为：妯娌");
						}else if(temp2_mb.getSex().equals("女")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：弟妹/嫂子");
						}
					}else if(temp_mb.getSex().equals("女")){
						if(temp2_mb.getSex().equals("男")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：弟妹/嫂子");
						}else if(temp2_mb.getSex().equals("女")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：堂姐妹");
						}
					}
				}
			}
		}else if(count == 3){     //找3次找到共同的父亲
			if(one.getSex().equals("男")){
				if(two.getSex().equals("女")){
					if(((String)node_1.getUserObject()).equals((String)node_2.getUserObject())){
						Select_Frame.TextField_guanxi.setText("两人关系为：夫妻");
					}else{
						Member temp_mb = new Member();
						temp_mb = md.getMemberByName((String)node_2.getUserObject(), jid);
						if(temp_mb.getSex().equals("男")){
							// 再次判断年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：弟妹/嫂子");
						}else if(temp_mb.getSex().equals("女")){
							// 再次判断年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：表兄妹/姐弟");
						}
					}
				}else if(two.getSex().equals("男")){
					// 比较年龄
					Select_Frame.TextField_guanxi.setText("两人关系为：表兄弟");
				}
			}else if(one.getSex().equals("女")){
				Member temp_mb = new Member();
				temp_mb = md.getMemberByName((String)node_1.getUserObject(), jid);
				if(two.getSex().equals("男")){
					if(((String)node_1.getUserObject()).equals((String)node_2.getUserObject())){
						Select_Frame.TextField_guanxi.setText("夫妻关系");
					}else{						
						if(temp_mb.getSex().equals("男")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：弟妹/嫂子");
						}else if(temp_mb.getSex().equals("女")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：表兄妹/姐弟");
						}
					}
				}else if(two.getSex().equals("女")){
					Member temp2_mb = new Member();
					temp2_mb = md.getMemberByName((String)node_2.getUserObject(), jid);
					if(temp_mb.getSex().equals("男")){
						if(temp2_mb.getSex().equals("男")){
							Select_Frame.TextField_guanxi.setText("两人关系为：妯娌");
						}else if(temp2_mb.getSex().equals("女")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：弟妹/嫂子");
						}
					}else if(temp_mb.getSex().equals("女")){
						if(temp2_mb.getSex().equals("男")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：弟妹/嫂子");
						}else if(temp2_mb.getSex().equals("女")){
							// 比较年龄
							Select_Frame.TextField_guanxi.setText("两人关系为：表姐妹");
						}
					}
				}
			}
		}
	}
	
	// 不同代系的比较
	private void judgeNoEqualsAge(Member one, Member two, DefaultMutableTreeNode node_1, DefaultMutableTreeNode node_2,  int count){
		//相差一代的比较
		if((count == -1) || (count == 1)){ 
			if(one.getSex().equals("男")){
				if(two.getSex().equals("男")){					
					// 父子/叔侄关系的判断
					if(count == -1){
						DefaultMutableTreeNode node2_parent = (DefaultMutableTreeNode) node_2.getParent();
						if(((String)node_1.getUserObject()).equals((String)node2_parent.getUserObject())){
							Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的父亲");
							return ;
						}else {
							DefaultMutableTreeNode node2_parent_parent = (DefaultMutableTreeNode) node_2.getParent().getParent();
							DefaultMutableTreeNode node1_parent = (DefaultMutableTreeNode) node_1.getParent();
							if(((String)node2_parent_parent.getUserObject()).equals((String)node1_parent.getUserObject())){
								Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的直系叔叔");
								return ;
							}else {
								Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的旁系叔叔");
								return ;
							}
						}
					}else if(count == 1){
						DefaultMutableTreeNode node1_parent = (DefaultMutableTreeNode) node_1.getParent();
						if(((String)node_2.getUserObject()).equals((String)node1_parent.getUserObject())){
							Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName()  + "的父亲");
							return ;
						}else {
							DefaultMutableTreeNode node1_parent_parent = (DefaultMutableTreeNode) node_1.getParent().getParent();
							DefaultMutableTreeNode node2_parent = (DefaultMutableTreeNode) node_2.getParent();
							if(((String)node1_parent_parent.getUserObject()).equals((String)node2_parent.getUserObject())){
								Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName()  + "的直系叔叔");
								return ;
							}else {
								Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的旁系叔叔");
								return ;
							}
						}
					}
				}else if(two.getSex().equals("女")){
					if(count == -1){
						DefaultMutableTreeNode node2_father = (DefaultMutableTreeNode) node_2.getParent();
						if(((String)node_1.getUserObject()).equals((String)node2_father.getUserObject())){
							if(two.getName().equals(((String)node_2.getUserObject()))){
								Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的父亲");
								return ;
							}else {
								Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的公公");
								return ;
							}
						}else {
							DefaultMutableTreeNode node1_father = (DefaultMutableTreeNode) node_1.getParent();
							DefaultMutableTreeNode node2_father_father = (DefaultMutableTreeNode) node_2.getParent().getParent();
							if(((String)node1_father.getUserObject()).equals(((String)node2_father_father.getUserObject()))){
								if(two.getName().equals(((String)node_2.getUserObject()))){
									Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的直系叔叔");
								}else {
									Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的直系小叔");
									return ;
								}
							}else{
								if(two.getName().equals(((String)node_2.getUserObject()))){
									Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的旁系叔叔");
								}else {
									Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的旁系小叔");
									return ;
								}
							}
						}
					}else if(count == 1){
						if(((String)node_1.getUserObject()).equals(((String)node_2.getUserObject()))){
							Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的母亲");
							return ;
						}else if(two.getName().equals(((String)node_2.getUserObject()))){
							DefaultMutableTreeNode node1_father_father = (DefaultMutableTreeNode) node_1.getParent().getParent();
							DefaultMutableTreeNode node2_father = (DefaultMutableTreeNode) node_2.getParent();
							if(((String)node1_father_father.getUserObject()).equals(((String)node2_father.getUserObject()))){
								Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的直系姑姑");
								return ;
							}else{
								Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的旁系姑姑");
								return ;
							}
						}else {
							// 拿父亲年龄作比较，确定是伯母还是婶婶
							Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的伯母/婶婶");
						}
					}
				}
			}else if(one.getSex().equals("女")){
				if(two.getSex().equals("男")){
					if(count == -1){
						if(one.getName().equals(((String)node_1.getUserObject()))){ // 真正的女人
							DefaultMutableTreeNode node1_father = (DefaultMutableTreeNode) node_1.getParent();
							DefaultMutableTreeNode node2_father_father = (DefaultMutableTreeNode) node_2.getParent().getParent();
							if(((String)node1_father.getUserObject()).equals(((String)node2_father_father.getUserObject()))){
								Select_Frame.TextField_guanxi.setText(one.getName()+ "是" + two.getName() + "的直系姑姑");
								return ; 
							}else{
								Select_Frame.TextField_guanxi.setText(one.getName()+ "是" + two.getName() + "的旁系姑姑");
								return ; 
							}
						}else {
							DefaultMutableTreeNode node2_father = (DefaultMutableTreeNode) node_2.getParent();
							if(((String)node_1.getUserObject()).equals(((String)node2_father.getUserObject()))){
								Select_Frame.TextField_guanxi.setText(one.getName()+ "是" + two.getName() + "的母亲");
							}else {
								Select_Frame.TextField_guanxi.setText(one.getName()+ "是" + two.getName() + "的婶婶/伯母");
							}
						}
					}else if(count == 1){
						DefaultMutableTreeNode node1_father = (DefaultMutableTreeNode) node_1.getParent();
						if(((String)node_2.getUserObject()).equals((String)node1_father.getUserObject())){
							if(one.getName().equals(((String)node_1.getUserObject()))){
								Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的父亲");
								return ;
							}else {
								Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的公公");
								return ;
							}
						}else {
							DefaultMutableTreeNode node2_father = (DefaultMutableTreeNode) node_2.getParent();
							DefaultMutableTreeNode node1_father_father = (DefaultMutableTreeNode) node_1.getParent().getParent();
							if(((String)node2_father.getUserObject()).equals(((String)node1_father_father.getUserObject()))){
								if(one.getName().equals(((String)node_1.getUserObject()))){
									Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的直系叔叔");
								}else {
									Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的直系小叔");
									return ;
								}
							}else{
								if(one.getName().equals(((String)node_1.getUserObject()))){
									Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的旁系叔叔");
								}else {
									Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的旁系小叔");
									return ;
								}
							}
						}					
					}
				}else if(two.getSex().equals("女")){
					if(count == -1){
						if(((String)node_1.getUserObject()).equals(((String)one.getName()))){ // one  真正的女人
							if(((String)node_2.getUserObject()).equals(((String)two.getName()))){ // two 真正的女人
								Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的姑姑");
								return;
							}else {
								Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的姑姑");
							}
						}else{// one不是真正的女人
							if(((String)node_2.getUserObject()).equals(((String)two.getName()))){ // two 真正的女人
								DefaultMutableTreeNode node2_father = (DefaultMutableTreeNode) node_2.getParent();
								if(((String)node_1.getUserObject()).equals(((String)node2_father.getUserObject()))){
									Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的母亲");return;
								}else {
									Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的姑姑");return;
								}								
							}else {
								Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的婆婆");return;
							}
						}
					}else if(count == 1){
						if(((String)node_2.getUserObject()).equals(((String)two.getName()))){ // one  真正的女人
							if(((String)node_1.getUserObject()).equals(((String)one.getName()))){ // two 真正的女人
								Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的姑姑");
								return;
							}else {
								Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的姑姑");
							}
						}else{// one不是真正的女人
							if(((String)node_1.getUserObject()).equals(((String)one.getName()))){ // two 真正的女人
								DefaultMutableTreeNode node1_father = (DefaultMutableTreeNode) node_1.getParent();
								if(((String)node_2.getUserObject()).equals(((String)node1_father.getUserObject()))){
									Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的母亲");return;
								}else {
									Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的姑姑");return;
								}								
							}else {
								Select_Frame.TextField_guanxi.setText(two.getName() + "是" +one.getName() + "的婆婆");return;
							}
						}					
					}
				}
			}
		// 相差两代的比较
		}else if((count == -2) || (count == 2)){
			if(one.getSex().equals("男")){
				if(two.getSex().equals("男")){
					if(count == -2){
						DefaultMutableTreeNode node2_father_father = (DefaultMutableTreeNode) node_2.getParent().getParent(); 
						if(((String)node_1.getUserObject()).equals(((String)node2_father_father.getUserObject()))){
							Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的爷爷");
							return ;
						}else {
							Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的旁系爷爷");
							return ;
						}
					}else if(count == 2){
						DefaultMutableTreeNode node1_father_father = (DefaultMutableTreeNode) node_1.getParent().getParent(); 
						if(((String)node_2.getUserObject()).equals(((String)node1_father_father.getUserObject()))){
							Select_Frame.TextField_guanxi.setText(two.getName() + "是" +one.getName() + "的爷爷");
							return ;
						}else {
							Select_Frame.TextField_guanxi.setText(two.getName() + "是" +one.getName() + "的旁系爷爷");
							return ;
						}
					}
				}else if(two.getSex().equals("女")){
					if(count == -2){
						DefaultMutableTreeNode node2_father_father = (DefaultMutableTreeNode) node_2.getParent().getParent(); 
						if(((String)node_1.getUserObject()).equals(((String)node2_father_father.getUserObject()))){
							Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName()  + "的爷爷");
							return ;
						}else {
							Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的旁系爷爷");
							return ;
						}
					}else if(count == 2){
						DefaultMutableTreeNode node1_father_father = (DefaultMutableTreeNode) node_1.getParent().getParent(); 
						if(((String)node_2.getUserObject()).equals(((String)node1_father_father.getUserObject()))){
							Select_Frame.TextField_guanxi.setText(two.getName() + "是" +one.getName() + "的奶奶");
							return ;
						}else {
							Select_Frame.TextField_guanxi.setText(two.getName() + "是" +one.getName() + "的旁系奶奶");
							return ;
						}
					}
				}
			}else if(one.getSex().equals("女")){
				if(two.getSex().equals("男")){
					if(count == -2){
						DefaultMutableTreeNode node2_father_father = (DefaultMutableTreeNode) node_2.getParent().getParent(); 
						if(((String)node_1.getUserObject()).equals(((String)node2_father_father.getUserObject()))){
							Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName()+ "的奶奶");
							return ;
						}else {
							Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的旁系奶奶");
							return ;
						}
					}else if(count == 2){
						DefaultMutableTreeNode node1_father_father = (DefaultMutableTreeNode) node_1.getParent().getParent(); 
						if(((String)node_2.getUserObject()).equals(((String)node1_father_father.getUserObject()))){
							Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的奶奶");
							return ;
						}else {
							Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName()+ "的旁系奶奶");
							return ;
						}
					}				
				}else if(two.getSex().equals("女")){
					if(count == -2){
						DefaultMutableTreeNode node2_father_father = (DefaultMutableTreeNode) node_2.getParent().getParent(); 
						if(((String)node_1.getUserObject()).equals(((String)node2_father_father.getUserObject()))){
							Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的奶奶");
							return ;
						}else {
							Select_Frame.TextField_guanxi.setText(one.getName() + "是" + two.getName() + "的旁系奶奶");
							return ;
						}
					}else if(count == 2){
						DefaultMutableTreeNode node1_father_father = (DefaultMutableTreeNode) node_1.getParent().getParent(); 
						if(((String)node_2.getUserObject()).equals(((String)node1_father_father.getUserObject()))){
							Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName() + "的奶奶");
							return ;
						}else {
							Select_Frame.TextField_guanxi.setText(two.getName() + "是" + one.getName()+ "的旁系奶奶");
							return ;
						}
					}
				}
			}
		}
	}
}











