package Frame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import pojo.Family;
import util.StringToDate;
import anping.AnfunctionImpl;

public class MainFrame extends javax.swing.JFrame implements ActionListener {

 
	private static final long serialVersionUID = 1L;
	public static Family getFamily() {
		return MainFrame.family;
	}

	public static void setFamily(Family family) {
		MainFrame.family = family;
	}

	public MainFrame() {
		setLocation(200,80);
        initComponents();
        createMonitor();
        Toolkit tk=Toolkit.getDefaultToolkit();
		Image image=tk.createImage("./image/home_purple.png");
		this.setIconImage(image);
		this.setTitle("家谱管理系统");
    }
	

    private void createMonitor() {
    	
    	MainFrame.jSplitPane1.setDividerLocation(
    			MainFrame.jSplitPane1.getMaximumDividerLocation()
    	);   // 默认为放大的状态
    	jTabbedPane1.addMouseListener(new MouseAdapter(){
    			
    			public void mouseClicked(MouseEvent e) {
    					if(jSplitPane1.getLastDividerLocation() < jSplitPane1.getMaximumDividerLocation()){
    						jSplitPane1.setDividerLocation(jSplitPane1.getMaximumDividerLocation());
  
    					}else {
    						jSplitPane1.setDividerLocation(jSplitPane1.getMinimumDividerLocation());
    						
    					}
    				}   			
    	});  // 添加缩放功能
    	
	}


    private void initComponents() {
    	jMenu4 = new JMenu();
    	jMenuItem4_1  =  new JMenuItem();
		jMenuItemz = new JMenuItem();
		jPanel1 = new javax.swing.JPanel();
       file=  new JFileChooser();
        jToolBar1 = new javax.swing.JToolBar();
        Button_add = new javax.swing.JButton(new ImageIcon("image\\增.png"));
        Button_select = new javax.swing.JButton(new ImageIcon("image\\找.png"));
        Button_modify = new javax.swing.JButton(new ImageIcon("image\\改.png"));
        Button_delete = new javax.swing.JButton(new ImageIcon("image\\删.png"));
       Button_rollBack  = new javax.swing.JButton(new ImageIcon("image\\回退.png"));
       Button_reflesh= new  javax.swing.JButton(new ImageIcon("image\\refresh.png"));
        jToolBar2 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_body = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        Label_loginFamily = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        exit = new javax.swing.JMenuItem();
         jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);


        Button_add.setFocusable(false);
        Button_add.addActionListener(this);
        Button_add.setToolTipText("增加家族成员");
        Button_add.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_add.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(Button_add);

        Button_delete.setFocusable(false);
        Button_delete.setToolTipText("删除成员");
        Button_delete.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
			 	new Delete_Frame().setVisible(true);
			}
        	
        });
        Button_delete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_delete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(Button_delete);
        
        Button_modify.setFocusable(false);
        Button_modify.setToolTipText("修改成员信息");
        Button_modify.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MainFrame.jSplitPane1.setDividerLocation(
		    			MainFrame.jSplitPane1.getMinimumDividerLocation()
		    	);   // 默认为放大的状态
				new ModifyLogin_Frame().setVisible(true);
			}
		} );
        Button_modify.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_modify.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(Button_modify);

        

        
        Button_select.setFocusable(false);
        Button_select.setToolTipText("查询和判断两人关系");
        Button_select.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				MainFrame.jSplitPane1.setDividerLocation(
		    			MainFrame.jSplitPane1.getMinimumDividerLocation()
		    	);   // 默认为放大的状态
				new Select_Frame().setVisible(true);
			}        	
        });
        Button_select.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_select.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(Button_select);  
        
        Button_rollBack.setFocusable(false);
        Button_rollBack.setToolTipText("撤销删除");
        Button_rollBack.setEnabled(false);
        Button_rollBack.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) { 
		    int  a   = 	JOptionPane.showConfirmDialog(null, "确定撤销上一步的删除?");
		    if(a==0){
			 ///撤销上一步删除
		        if(0==new AnfunctionImpl().rollback()){
		        	 Button_rollBack.setEnabled(false);
		               }
		          } 
			 }
		} );
        Button_rollBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_rollBack.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(Button_rollBack);
   
        Button_reflesh.setFocusable(false);
        Button_reflesh.setToolTipText("刷新");
        Button_reflesh.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			 AnfunctionImpl.fleshMainFrame();
			 }
		} );
        Button_reflesh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_reflesh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(Button_reflesh);
        
        jToolBar2.setRollover(true);

        jLabel1.setText("显示第");
        jToolBar2.add(jLabel1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new  AnfunctionImpl().showNAgeNum()));
       jComboBox1.addActionListener(new jComboBoxListener_a());
        jToolBar2.add(jComboBox1);

        jLabel2.setText("代的信息");
        jToolBar2.add(jLabel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(250, 67));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(250, 451));

      
//        createTree();
        
        

        jTabbedPane1.addTab("树状形", jScrollPane1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 185, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("条形图", new CreateFamilyNumByChart().showChart());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 185, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("趋势图", new CreateFamilyNumByChart().showChartByLine());

        jSplitPane1.setLeftComponent(jTabbedPane1);
        
        
 //       createTableBody();    // 添加表格
    
        jScrollPane2.setViewportView(Table_body);

        jSplitPane1.setRightComponent(jScrollPane2);

        jLabel3.setText("当前登录的家族为：");

        Label_loginFamily.setText(this.getFamily().getName());

        jMenu1.setText("文件");

        jMenuItem1.setText("导入");
        jMenuItem1.addActionListener(new filechoosrActionListener());
        jMenu4.setText("帮助");
        jMenuItem4_1.setText("查看帮助");
        jMenu4.add(jMenuItem4_1);
        jMenuItem4_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			 new HelpFrame().setVisible(true);	 
			}
		});
        jMenuItem2.setText("导入规范");
 
		jMenuItemz.setText("注销");
        jMenuItem2.addActionListener(new ActionListener() {
 		
 		public void actionPerformed(ActionEvent e) {
 			new FileTishi().setVisible(true);
 		}
 	});
        
        jMenuItemz.addActionListener(this);
        jMenu1.add(jMenuItem1);
        jMenu1.add(jMenuItem2);
        jMenu1.add(jMenuItemz);

        exit.setText("退出");
        exit.addActionListener(this);
        jMenu1.add(exit);

        jMenuBar1.add(jMenu1);
    
       
        
        jMenu3.setText("关于作者");

        jMenuItem4.setText("曹雯琪");
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("熊安平");
        
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("邹金勇");
        
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);
        jMenuBar1.add(jMenu4);
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(183, 183, 183))
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label_loginFamily)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Label_loginFamily))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    } 
    void createTableBody(){
    	Table_body.setModel(new javax.swing.table.DefaultTableModel(
                tableBody,
                new String [] {
                    "代系", "姓名", "性别", "出生日期", "父亲", "母亲", "配偶"
                }
            ) { 
    			private static final long serialVersionUID = 1L;
    		  
            });
     }    
    public  static void createTree() {
         	tree_display = new Tree_display();
         	jTree1 = Tree_display.getJt();
        	jScrollPane1.setViewportView(jTree1);		

	}
    public  void addTableListener(){
 	   Table_body.addPropertyChangeListener(new PropertyChangeListener() {
 			String  oldValue=null;
 			String  newValue=null;
 			String name  =  null;
 			public void propertyChange(PropertyChangeEvent evt) {
 				 if(Table_body.getSelectedRows().length==1){
 			       if(Table_body.getEditingColumn()==-1 ){
 			   	  oldValue=  Table_body.getValueAt(Table_body.getSelectedRows()[0],Table_body.getSelectedColumns()[0])==null?"": Table_body.getValueAt(Table_body.getSelectedRows()[0],Table_body.getSelectedColumns()[0]).toString();
 			   	name=Table_body.getValueAt(Table_body.getSelectedRows()[0],1)==null?"": Table_body.getValueAt(Table_body.getSelectedRows()[0],1).toString();
 	 		        }else if(Table_body.getSelectedColumns()[0]==1 ||Table_body.getSelectedColumns()[0]==2||
 		            		Table_body.getSelectedColumns()[0]==3){
 			    		  newValue=  Table_body.getValueAt(Table_body.getSelectedRows()[0],Table_body.getSelectedColumns()[0])==null?"": Table_body.getValueAt(Table_body.getSelectedRows()[0],Table_body.getSelectedColumns()[0]).toString();
 					if(!oldValue.equals(newValue)){
 			    		  int  aa  =    JOptionPane.showConfirmDialog(null, "确定把"+oldValue+"修改为"+newValue+"??");
 			             if(aa==0  ){
 			        	//修改
 	 		           if(0==new AnfunctionImpl().updateMemberByTable(oldValue,newValue, name,Table_body.getColumnName(Table_body.getSelectedColumns()[0]))) 	  
 	 		               {
 	 		    	   Table_body.setValueAt(oldValue, Table_body.getSelectedRows()[0],Table_body.getSelectedColumns()[0]);
 	 		   	        JOptionPane.showMessageDialog(null, "抱歉,已有配偶,修改性别有违伦理");
 	 		    	        }
 			               }else if(aa==1 ||aa==2 	){
 			        	Table_body.setValueAt(oldValue, Table_body.getSelectedRows()[0],Table_body.getSelectedColumns()[0]);
 			              }} 
 			          }
 			       else if(	  Table_body.getSelectedColumns()[0]==0 ||Table_body.getSelectedColumns()[0]==4||
 		            		Table_body.getSelectedColumns()[0]==5 || Table_body.getSelectedColumns()[0]==6){
 			    	 	Table_body.setValueAt(oldValue, Table_body.getSelectedRows()[0],Table_body.getSelectedColumns()[0]);
 					 }
 				 }
 			}
 		});
    	
    }      static String[][] tableBody;
    public static  Family family =  new Family();
    static MainFrame mf;
    static Tree_display  tree_display;
    static javax.swing.JButton Button_add;
    static javax.swing.JButton Button_delete;
    static javax.swing.JButton Button_modify;
    static javax.swing.JButton Button_select;
    static javax.swing.JButton Button_rollBack;
   static  javax.swing.JButton Button_reflesh;
    static javax.swing.JLabel Label_loginFamily;
    public static javax.swing.JTable Table_body;
    static javax.swing.JMenuItem exit;
    public static javax.swing.JComboBox jComboBox1;
    static javax.swing.JLabel jLabel1;
    static javax.swing.JLabel jLabel2;
    static javax.swing.JLabel jLabel3;
    static javax.swing.JMenu jMenu1;
    static javax.swing.JMenu jMenu4;
     static javax.swing.JMenu jMenu3;
    static javax.swing.JMenuBar jMenuBar1;
    static  javax.swing.JMenuItem jMenuItem1;
    static  javax.swing.JMenuItem jMenuItem2;
    static javax.swing.JMenuItem jMenuItem3;
    static javax.swing.JMenuItem jMenuItem4;
    static javax.swing.JMenuItem jMenuItem5;
    static javax.swing.JMenuItem jMenuItem6;
    static javax.swing.JMenuItem jMenuItem4_1;
    static javax.swing.JPanel jPanel1;
    static javax.swing.JPanel jPanel2;
    static javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    static javax.swing.JScrollPane jScrollPane2;
    static  javax.swing.JSplitPane jSplitPane1;
    public static javax.swing.JTabbedPane jTabbedPane1;
    static javax.swing.JToolBar jToolBar1;
    static javax.swing.JToolBar jToolBar2;
    public static javax.swing.JTree jTree1;
    static  JFileChooser  file;
   static   JMenuItem 		jMenuItemz; 
	
   public void actionPerformed(ActionEvent e) {
    
   		 if(e.getSource() == Button_add) {
   			new Add_select_Frame().setVisible(true);
   
   	}else if(e.getSource()==jMenuItemz){
   		this.setVisible(false);
   		new LoginFrame().setVisible(true);
   	}else if(e.getSource()==exit){
 this.dispose();   	                        
   	}
	}
}
