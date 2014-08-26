 
package Frame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;

import javax.swing.JOptionPane;

import anping.AnfunctionImpl;

import pojo.Member;
import util.StringToDate;
import Dao4MySqlImpl.MemberDaoImpl;

 
public class AddChild_Frame extends javax.swing.JFrame {
 
    public AddChild_Frame() {
    	setLocation(500, 300);
        initComponents();
        Toolkit tk=Toolkit.getDefaultToolkit();
    		Image image=tk.createImage("./image/home_purple.png");
    		this.setIconImage(image);
    		this.setTitle("添加孩子");
    }

 
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TextField_name = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        RadioButton_man = new javax.swing.JRadioButton();
        RadioButton_woman = new javax.swing.JRadioButton();
        Button_exit = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        TextField_father = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        TextField_year = new javax.swing.JTextField();
        TextField_month = new javax.swing.JTextField();
        TextField_day = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Button_ok = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TextField_mother = new javax.swing.JTextField();
        TextField_mother.addKeyListener(new pressEnterKeyListener(this));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setText("姓名：");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addComponent(TextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 42, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("性别：");

        RadioButton_man.setText("男");
        RadioButton_man.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton_manActionPerformed(evt);
            }
        });

        RadioButton_woman.setText("女");
        Button_exit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			dispose();	 
			}
		});
        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(46, 46, 46)
                .addComponent(RadioButton_man)
                .addGap(27, 27, 27)
                .addComponent(RadioButton_woman)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(RadioButton_man)
                    .addComponent(RadioButton_woman))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        Button_exit.setText("取消");

        jLabel6.setText("父亲：");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(27, 27, 27)
                .addComponent(TextField_father, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TextField_father, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("出生日期：");

        jLabel7.setText("年");

        jLabel8.setText("月");

        jLabel10.setText("日");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextField_year, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextField_month, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextField_day, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TextField_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TextField_father.addFocusListener(new FocusAdapter() {
	    public void focusLost(FocusEvent e) {
		    super.focusLost(e);
             if((TextField_father.getText().trim()+"").equals("")){
            	   JOptionPane.showMessageDialog(null, "父亲不能为空!"); 
                 }
             else{
            	 if( !AnfunctionImpl.checkUserIsExist(TextField_father.getText().trim())){
            		  JOptionPane.showMessageDialog(null, TextField_father.getText()+"不存在"); 
                  }else if(     AnfunctionImpl.getMemberByName(TextField_father.getText().trim()).getSex().equals("女")) {
                	  JOptionPane.showMessageDialog(null, TextField_father.getText()+"是女性,请正确填写"); 
                  }else{
                	  TextField_mother.setText(AnfunctionImpl.getMemberByName(TextField_father.getText().trim()).getPeiou().getName());
                  }
             }
	          }
        });
        Button_ok.setText("确定");
        Button_ok.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
  			
			 		////////////////判断是否是正常的
			 		if(!(AnfunctionImpl.checkStringIsNotNull(TextField_name.getText().trim())&&
			 				AnfunctionImpl.checkStringIsNotNull(TextField_day.getText().trim())&&
			 				   AnfunctionImpl.checkStringIsNotNull(TextField_month.getText().trim())&&
			 				     AnfunctionImpl.checkStringIsNotNull(TextField_year.getText().trim())&&
			 				       AnfunctionImpl.checkStringIsNotNull(TextField_father.getText().trim())&&
			 				        AnfunctionImpl.checkStringIsNotNull(TextField_mother.getText().trim())
					 		)){
			 	   JOptionPane.showMessageDialog(null, "不能有空数据,请仔细检测");		
			 	 	return ;
			 		}
			 		
			 		//////////////////检查孩子名字是否已经存在
			 		if(AnfunctionImpl.checkUserIsExist(TextField_name.getText().trim())){
			 		   JOptionPane.showMessageDialog(null,TextField_name.getText()+ "已存在,请修改,或名字后加上标识");		
				 	 	return ;
				 	}
			 	
 			 	String na = TextField_name.getText();	 
			    Date  da=null;
				try {
					String day =  TextField_day.getText().trim() ;
					String mouth =  TextField_month.getText( ).trim();
					String year =  TextField_year.getText( ).trim();
					da= StringToDate.parseStringToDate((year+"-"+mouth+"-"+day));
				} catch (Exception e1) {
//					 e1.printStackTrace();
				}  
				MemberDaoImpl a = new MemberDaoImpl();
		 
	 			Member pe = a.getMemberByName(TextField_father.getText().trim(), MainFrame.getFamily().getId());
		 		Member pe1 = a.getMemberByName(TextField_mother.getText().trim(), MainFrame.getFamily().getId());
				if(pe1==null )  {
					JOptionPane.showMessageDialog(null, "此家族中并无" + TextField_mother.getText()+"！");
					return ;
				}
				if(pe==null) {
					JOptionPane.showMessageDialog(null, "此家族中并无" + TextField_father.getText()+"！");
					return ;
				}
				if(pe.getSex().equals("女")){
					  JOptionPane.showMessageDialog(null, TextField_father.getText()+"是女性,请正确填写"); 
	         	return;
				}
				if(pe1.getSex().equals("男")){
					  JOptionPane.showMessageDialog(null, TextField_mother.getText()+"是男性,请正确填写"); 
			         		return ;
				}
				if(pe.getPeiou().getId() != pe1.getId()){
					JOptionPane.showMessageDialog(null, pe.getName()+"不是"+pe1.getName()+"的老婆");
					return ;
				}
				Member mb = new Member();
				if(RadioButton_man.isSelected()){   
					mb.setSex(RadioButton_man.getText()); }  
				 else if(RadioButton_woman.isSelected()){   
					mb.setSex(RadioButton_woman.getText()); }		
				 else{
					 JOptionPane.showMessageDialog(null, "请选择性别");
					 return;
				 }
				mb.setFamily(MainFrame.getFamily());
				mb.setName(na);
				mb.setBirthday(da);
				mb.setFather(pe);
				mb.setMother(pe1);
               mb.setAge(pe.getAge()+1);
				if(a.getMemberByName(na,MainFrame.getFamily().getId())!= null)  {
					JOptionPane.showMessageDialog(null, "此家族中已有此人！");
					return;
				}
				a.saveMember(mb);
			 	JOptionPane.showMessageDialog(null, "孩子添加成功");
              AnfunctionImpl.fleshMainFrame();
			 	dispose();
			}
		});

        jLabel1.setText("母亲：");

        TextField_mother.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_motherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(TextField_mother, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TextField_mother, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Button_ok)
                .addGap(47, 47, 47)
                .addComponent(Button_exit)
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_ok)
                    .addComponent(Button_exit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    } 
    private void TextField_motherActionPerformed(java.awt.event.ActionEvent evt) {
     }

    private void RadioButton_manActionPerformed(java.awt.event.ActionEvent evt) {
     }

      
     private javax.swing.JButton Button_exit;
    private javax.swing.JButton Button_ok;
    static    javax.swing.JRadioButton RadioButton_man;
    static javax.swing.JRadioButton RadioButton_woman;
    static javax.swing.JTextField TextField_day;
    static javax.swing.JTextField TextField_father;
    static javax.swing.JTextField TextField_month;
    static javax.swing.JTextField TextField_mother;
    static javax.swing.JTextField TextField_name;
    static javax.swing.JTextField TextField_year;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
 }
