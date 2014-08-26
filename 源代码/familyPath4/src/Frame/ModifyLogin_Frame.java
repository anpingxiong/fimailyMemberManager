 package Frame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import pojo.Member;
import anping.AnfunctionImpl;
 
public class ModifyLogin_Frame extends javax.swing.JFrame implements ActionListener {

     public ModifyLogin_Frame() {
    	 setLocation(500, 300);
        initComponents();
        this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(true);
         	}
        });
        Toolkit tk=Toolkit.getDefaultToolkit();
		Image image=tk.createImage("./image/home_purple.png");
		this.setIconImage(image);
		this.setTitle("查询");
     }
  
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        TextField_selectName = new javax.swing.JTextField();
        TextField_selectName.addKeyListener(new pressEnterKeyListener(this));
        Button_enter = new javax.swing.JButton();
        ModifyLogin_exit = new javax.swing.JButton();

    	
        jLabel1.setText("通过名字查询,在修改");

        Button_enter.setText("查找");
        Button_enter.addActionListener(this);

        ModifyLogin_exit.setText("取消");
        ModifyLogin_exit.addActionListener(this);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(97, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(Button_enter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ModifyLogin_exit)
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TextField_selectName)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextField_selectName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_enter)
                    .addComponent(ModifyLogin_exit))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    } 
    private void setDefaultCloseOperation(Object setVisible) {
	 
}
	     static  javax.swing.JButton Button_enter;
    static  javax.swing.JButton ModifyLogin_exit;
    static  javax.swing.JTextField TextField_selectName;
    static  javax.swing.JLabel jLabel1;
	public void actionPerformed(ActionEvent e) {
		
	          JButton button  =	 (JButton)e.getSource()  ;
		   if( button==Button_enter){
      
					String name = ModifyLogin_Frame.TextField_selectName.getText();
			           Member member = AnfunctionImpl.getMemberByName(name);
			           if(member==null){
			        	   JOptionPane.showMessageDialog(null, "查无此人");
			        	   return  ;
			           }
			           modify_Frame  modifyFrame   = new modify_Frame();
			            
			           modifyFrame.jTextField1.setText(member.getName());
 
  					if (member.getSex().equals("男")) {
						 modifyFrame.jRadioButton1.setSelected(true);
				
  					}
					 
					if (member.getSex().equals("女")) {
						 modifyFrame.jRadioButton2.setSelected(true);
							
					}
					 
					String date[] = member.getBirthday().toString().split("-");
					 modifyFrame.jTextField3.setText(date[0]);
					 modifyFrame.jTextField4.setText(date[1]);
					 modifyFrame.jTextField5.setText(date[2]);
					 this.setVisible(false);
					 modifyFrame.setVisible(true);
					MainFrame.Table_body.setModel(new javax.swing.table.DefaultTableModel(
							new Object[][] { { member.getAge() + "代", member.getName(),
									member.getSex(), member.getBirthday().toString(),
									member.getFather().getName(),
									member.getMother().getName(),
									member.getPeiou().getName() } }, new String[] { "代系",
									"姓名", "性别", "出生日期", "父亲", "母亲", "配偶" }));
				}else if(button== ModifyLogin_exit){
			 		this.setVisible(false);
			 	}

 	}  
 }
