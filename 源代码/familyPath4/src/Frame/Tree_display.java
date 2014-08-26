package Frame;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolTip;
import javax.swing.JTree;
import javax.swing.plaf.metal.MetalIconFactory;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import pojo.Member;
import util.DBUtils;
import Dao4MySqlImpl.MemberDaoImpl;

public class Tree_display implements ActionListener{

	 static   IconNode dmtRoot;
	DefaultTreeModel dtm ;
	private static JTree jt ;
	private JPopupMenu jpm = new JPopupMenu();
	private JMenu add_Menu = new JMenu("添加");
	JMenuItem m1 = new JMenuItem("配偶");
	JMenuItem m2 = new JMenuItem("孩子");
	Icon image_man = new ImageIcon("image\\user.png");
	Icon image_woman = new ImageIcon("image\\user_red.png");
	Icon image_wife = new ImageIcon("image\\user_orange.png");
	IconNode temp_node;
	
	
	
	private JMenuItem[] jmi = { add_Menu };
	
	
	
	public static JTree getJt() {
		return jt;
	}

	public static void setJt(JTree jt) {
		Tree_display.jt = jt;
	}

	Tree_display(){
		dmtRoot = new IconNode(getFamilyName());
		dmtRoot.setIcon ( MetalIconFactory.getFileChooserHomeFolderIcon ()) ;
		
		jt = new JTree(dmtRoot);
	
	//	jt.createToolTip();
	jt.setToolTipText("蓝色--男人  红色--女人   黄色--妻子");
		
		
		
		
		
		createTree();		
		monitor_JPopupMenu jm = new monitor_JPopupMenu();
		jt.addMouseListener(jm);
		
		jt.putClientProperty ( "JTree.icons" , makeIcons ()) ; 
	    jt.setCellRenderer ( new IconNodeRenderer ()) ; 
		
		m1.addActionListener(this);
		m2.addActionListener(this);
		
	}
	
	private Hashtable<String, Icon> makeIcons () { 
	     Hashtable<String, Icon> icons = new Hashtable<String, Icon> () ; 
	     icons.put ( "m" , TextIcons.getIcon ( "m" )) ; 
	     icons.put ( "w" , TextIcons.getIcon ( "w" )) ; 
	   //  icons.put ( "java" , TextIcons.getIcon ( "java" )) ; 
	     return icons; 
	   }
	
	// 拿到当前登录家族的名字
	private static String getFamilyName(){		
		return MainFrame.family.getName();
	}
	
	// 创建树
	private void createTree(){
		String sql = "select * from t_member where age = 1 and jid = " + MainFrame.getFamily().getId();
		Connection conn = DBUtils.getConn();
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
			while(rs.next()){
				IconNode temp = new IconNode(rs.getString(2));
				dmtRoot.add(temp);
				if(rs.getString(3).equals("男")){ // 如果为男性
					temp.setIcon(image_man);
					createChildTree_2(temp,rs.getInt(1));					
				}else{
					temp.setIcon(image_wife);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, conn);
		}
	}
	
	// 增加子节点
	private void createChildTree_2(IconNode temp,int father_id) {
		String sql = "select * from t_member where jid = " + MainFrame.getFamily().getId() + " and fid = " + father_id;
		Connection conn = DBUtils.getConn();
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
			while(rs.next()){
				IconNode temp2 = new IconNode(rs.getString(2));
				temp.add(temp2);
				if(rs.getString(3).equals("男")){// 如果为男性
					temp2.setIcon(image_man);
					createWifeNode(temp,rs.getInt(1));
					createChildTree_2(temp2,rs.getInt(1));
					
				}else{
					temp2.setIcon(image_woman);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 添加配偶节点
	private void createWifeNode(IconNode temp, int wife_id){
		String sql = "select * from t_member where jid = " + MainFrame.getFamily().getId() + " and wid = " + wife_id;
		Connection conn = DBUtils.getConn();
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
			while(rs.next()){
				IconNode temp2 = new IconNode(rs.getString(2));
				temp2.setIcon(image_wife);
				temp.add(temp2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, conn);
		}
	}
	
	// 响应鼠标右键操作
	class monitor_JPopupMenu extends MouseAdapter {
		
		monitor_JPopupMenu(){
			add_Menu.add(m1);
			add_Menu.add(m2);
			for(int i = 0; i < jmi.length; i++){
				jpm.add(jmi[i]);
			}
		}
		TreePath tp;
		public void mouseClicked(MouseEvent e){
			MainFrame.jSplitPane1.setDividerLocation(
	    			MainFrame.jSplitPane1.getMinimumDividerLocation()
	    	);
			tp = jt.getPathForLocation(e.getX(), e.getY());
			try{
				temp_node = (IconNode) tp.getLastPathComponent();
				if(temp_node.isLeaf()){  // 如果没有子节点则返回true
					MemberDaoImpl md = new MemberDaoImpl();
					Member mb = new Member();
					mb = md.getMemberByName((String) temp_node.getUserObject(), MainFrame.getFamily().getId());
					MainFrame.tableBody = new String[1][7];
					MainFrame.tableBody[0][0] = mb.getAge() + "";
					MainFrame.tableBody[0][1] = mb.getName() + "";
					MainFrame.tableBody[0][2] = mb.getSex() + "";
					MainFrame.tableBody[0][3] = mb.getBirthday() + "";
					MainFrame.tableBody[0][4] = mb.getFather().getName() == null ? "" : mb.getFather().getName();
					MainFrame.tableBody[0][5] = mb.getMother().getName() == null ? "" : mb.getMother().getName();
					MainFrame.tableBody[0][6] = mb.getPeiou().getName() == null? "未婚" : mb.getPeiou().getName();
				MainFrame.mf.createTableBody();
				}else{
						@SuppressWarnings("unchecked")
						Enumeration<IconNode> em = temp_node.breadthFirstEnumeration();
						List<String[]> bf = new ArrayList<String[]>();
						String s = null;
						while(em.hasMoreElements()){							
							if(!((s =(String)em.nextElement().getUserObject()).equals(MainFrame.getFamily().getName()))){
								MemberDaoImpl md = new MemberDaoImpl();
								Member mb = new Member();
								mb = md.getMemberByName(s, MainFrame.getFamily().getId());
								String str[] = new String[7];
								str[0] = mb.getAge() + "";
								str[1] = mb.getName() + "";
								str[2] = mb.getSex() + "";
								str[3] = mb.getBirthday() + "";
						        str[4] = mb.getFather().getName() == null ? "" : mb.getFather().getName();
							    str[5] = mb.getMother().getName() == null ? "" : mb.getMother().getName();
								str[6] = mb.getPeiou().getName() == null? "未婚" : mb.getPeiou().getName();
											bf.add(str);
							}
						}
						MainFrame.tableBody = new String[bf.size()][7];
						for(int i = 0; i < bf.size(); i++){
							MainFrame.tableBody[i][0] = bf.get(i)[0]+ "";
							MainFrame.tableBody[i][1] = bf.get(i)[1]+ "";
							MainFrame.tableBody[i][2] = bf.get(i)[2]+ "";
							MainFrame.tableBody[i][3] = bf.get(i)[3]+ "";
							MainFrame.tableBody[i][4] = bf.get(i)[4]+ "";
							MainFrame.tableBody[i][5] = bf.get(i)[5]+ "";
							MainFrame.tableBody[i][6] = bf.get(i)[6]+ "";
						}
						MainFrame.mf.createTableBody();
						
				}
			}catch(NullPointerException e3){
				
			}
		}
		public void mouseReleased(MouseEvent e) {// 当鼠标释放时执行的动作
			try{
            	if (e.isPopupTrigger()) {// 显示弹出式菜单
            		if(!(temp_node = (IconNode) tp.getLastPathComponent()).equals(null)){
            			if(!jt.getPathForLocation(e.getX(), e.getY()).equals(null)){
            				jpm.show(e.getComponent(), e.getX(), e.getY());
   
            			}
            		}
            	}
			}catch(NullPointerException e3){}
		}
	}
	
	// 对树节点的实现
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == m1){ // 添加配偶
			if(!temp_node.getUserObject().equals(null)){
				new AddPO_Frame().setVisible(true);
				AddPO_Frame.TextField_peiou.setText(temp_node.getUserObject().toString());
				AddPO_Frame.TextField_peiou.setEditable(false);
			
			}
			
		}else if(e.getSource() == m2){ // 添加小孩
			if(!temp_node.getUserObject().equals(null)){
			
				new AddChild_Frame().setVisible(true);
				MemberDaoImpl a = new MemberDaoImpl();
				Member bMember = a.getMemberByName(temp_node.getUserObject().toString(), MainFrame.getFamily().getId());
				if(bMember.getSex().equals("男")){
					AddChild_Frame.TextField_father.setText(temp_node.getUserObject().toString());
					AddChild_Frame.TextField_mother.setText(bMember.getPeiou().getName());
				}else if(bMember.getSex().equals("女")){
					AddChild_Frame.TextField_mother.setText(temp_node.getUserObject().toString());
					AddChild_Frame.TextField_father.setText(bMember.getPeiou().getName());
				}
				AddChild_Frame.TextField_father.setEditable(false);
				AddChild_Frame.TextField_mother.setEditable(false);
			}
		}
	}
}


class IconNodeRenderer extends DefaultTreeCellRenderer { 

	   
	private static final long serialVersionUID = 1L;

	public Component getTreeCellRendererComponent ( JTree tree, Object value, 
	       boolean sel, boolean expanded, boolean leaf, int row, 
	       boolean hasFocus ) { 

	     super .getTreeCellRendererComponent ( tree, value, sel, expanded, leaf, 
	         row, hasFocus ) ; 

	     Icon icon = (( IconNode ) value ) .getIcon () ; 

	     if ( icon == null ) { 
	       Hashtable<?, ?> icons = ( Hashtable<?, ?> ) tree.getClientProperty ( "JTree.icons" ) ; 
	       String name = (( IconNode ) value ) .getIconName () ; 
	       if (( icons != null ) && ( name != null )) { 
	         icon = ( Icon ) icons.get ( name ) ; 
	         if ( icon != null ) { 
	           setIcon ( icon ) ; 
	         } 
	       } 
	     } else { 
	       setIcon ( icon ) ; 
	     } 

	     return this ; 
	   } 
	} 

	class IconNode extends DefaultMutableTreeNode { 

	   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	protected Icon icon; 

	   protected String iconName; 

	   public IconNode () { 
	     this ( null ) ; 
	   } 

	   public IconNode ( Object userObject ) { 
	     this ( userObject, true, null ) ; 
	   } 

	   public IconNode ( Object userObject, boolean allowsChildren, Icon icon ) { 
	     super ( userObject, allowsChildren ) ; 
	     this .icon = icon; 
	   } 

	   public void setIcon ( Icon icon ) { 
	     this .icon = icon; 
	   } 

	   public Icon getIcon () { 
	     return icon; 
	   } 

	   public String getIconName () { 
	     if ( iconName != null ) { 
	       return iconName; 
	     } else { 
	       String str = userObject.toString () ; 
	       int index = str.lastIndexOf ( "." ) ; 
	       if ( index != - 1 ) { 
	         return str.substring ( ++index ) ; 
	       } else { 
	         return null ; 
	       } 
	     } 
	   } 

	   public void setIconName ( String name ) { 
	     iconName = name; 
	   } 

	} 

	class TextIcons extends MetalIconFactory.TreeLeafIcon { 

	   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	protected String label; 

	   private static Hashtable<String, String> labels; 

	   protected TextIcons () { 
	   } 

	   public void paintIcon ( Component c, Graphics g, int x, int y ) { 
	     super .paintIcon ( c, g, x, y ) ; 
	     if ( label != null ) { 
	       FontMetrics fm = g.getFontMetrics () ; 

	       int offsetX = ( getIconWidth () - fm.stringWidth ( label )) / 2 ; 
	       int offsetY = ( getIconHeight () - fm.getHeight ()) / 2 - 2 ; 

	       g.drawString ( label, x + offsetX, y + offsetY + fm.getHeight ()) ; 
	     } 
	   } 

	   public static Icon getIcon ( String str ) { 
	     if ( labels == null ) { 
	       labels = new Hashtable<String, String> () ; 
	       setDefaultSet () ; 
	     } 
	     TextIcons icon = new TextIcons () ; 
	     icon.label = ( String ) labels.get ( str ) ; 
	     return icon; 
	   } 

	   public static void setLabelSet ( String ext, String label ) { 
	     if ( labels == null ) { 
	       labels = new Hashtable<String, String> () ; 
	       setDefaultSet () ; 
	     } 
	     labels.put ( ext, label ) ; 
	   } 

	   private static void setDefaultSet () { 
	     labels.put ( "m" , "男" ) ; 
	     labels.put ( "w" , "女" ) ; 
	   } 
	} 