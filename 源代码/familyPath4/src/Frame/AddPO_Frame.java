 package Frame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import anping.AnfunctionImpl;

import pojo.Member;
import util.StringToDate;
import Dao4MySqlImpl.MemberDaoImpl;

 public class AddPO_Frame extends javax.swing.JFrame implements FocusListener {

     public AddPO_Frame() {
    	 setLocation(500, 300);
        initComponents();  
        Toolkit tk=Toolkit.getDefaultToolkit();
		Image image=tk.createImage("./image/home_purple.png");
		this.setIconImage(image);
		this.setTitle("添加配偶");
    }
   private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        TextField_year = new javax.swing.JTextField();
        TextField_month = new javax.swing.JTextField();
        TextField_day = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        RadioButton_man = new javax.swing.JRadioButton();
        RadioButton_woman = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        TextField_peiou = new javax.swing.JTextField();
        Button_up = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        TextField_peiou.addKeyListener(new pressEnterKeyListener(this));
        TextField_day.addFocusListener(this);
        TextField_year.addFocusListener(this);
        TextField_month.addFocusListener(this);
        TextField_name.addFocusListener(this);
        TextField_peiou.addFocusListener(this);

        jLabel1.setText("jLabel1");

        jLabel9.setText("jLabel9");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setText("姓名");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addComponent(TextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
       jLabel5.setText("出生日期");

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

        jLabel4.setText("性别");

        ButtonGroup bg = new ButtonGroup();
        RadioButton_man.setText("男");
        RadioButton_man.setEnabled(false);
        RadioButton_woman.setText("女");
        RadioButton_woman.setSelected(true);
        RadioButton_man.setEnabled(false);
        bg.add(RadioButton_man);
        bg.add(RadioButton_woman);


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

        jLabel6.setText("丈夫");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(27, 27, 27)
                .addComponent(TextField_peiou, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TextField_peiou, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

                Button_up.setText("提交");
        
        Button_up.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			        String na = TextField_name.getText().trim();	 
			       
				    //在此检测是否已经存在
			    	 if( !(AnfunctionImpl.checkStringIsNotNull(TextField_name.getText().trim()+"")  &&
			    			 AnfunctionImpl.checkStringIsNotNull(TextField_peiou.getText().trim()+"") 
			    			) ){
			   		     return ;
			    	 }  
			    	 else if( (TextField_year.getText().trim()+"").equals("") && (TextField_month.getText().trim()+"").equals("") &&(TextField_day.getText().trim()+"").equals("")){
			    		  JOptionPane.showMessageDialog(null, "日期怎么能为空呢");
			    	     return ;   
			    	 }
			    	 
			     if(!AnfunctionImpl.checkDateIsNotError(TextField_year.getText().trim()+"-"+TextField_month.getText().trim()+""+TextField_day.getText().trim())
				        ){
			   	  JOptionPane.showMessageDialog(null, "日期填写错误,请仔细检查");
				     return ;   
				     }
			     	Date  da=null;
					try {
						String day =  TextField_day.getText().trim() ;
						String mouth =  TextField_month.getText( ).trim();
						String year =  TextField_year.getText( ).trim();
						da= StringToDate.parseStringToDate((year+"-"+mouth+"-"+day));
					} catch (Exception e1) {
						 e1.printStackTrace();
					}  

					MemberDaoImpl a = new MemberDaoImpl();
					 
					Member pe = a.getMemberByName(TextField_peiou.getText().trim(), MainFrame.getFamily().getId());
						if(pe==null) {
						JOptionPane.showMessageDialog(null, TextField_peiou.getText()+"不存在,请修改名称！");
			   		   	return  ;
				   	}else   if( pe.getPeiou().getName()!= null  && pe.getSex().equals("男")){
					     JOptionPane.showMessageDialog(null, "已有配偶,名为"+pe.getPeiou().getName());
					 	return  ;
					}
					if(pe.getSex().equals("女")){
						JOptionPane.showMessageDialog(null, "抱歉,配偶局限于给男方添加！");
					 	return  ;
					}
				 
					Member mb = new Member();
			     	mb.setSex("女");	
					mb.setFamily(MainFrame.getFamily());
					mb.setName(na);
					mb.setBirthday(da);
					mb.setPeiou(pe);
			       mb.setAge(pe.getAge());
					if(a.getMemberByName(na,MainFrame.getFamily().getId())!= null)  {
						JOptionPane.showMessageDialog(null, na+"已在存在,请重新填写姓名");
						return;
					}
					a.saveMember(mb);
				MemberDaoImpl  memberDao  =   new MemberDaoImpl();
					Member   women  = memberDao.getMemberByName(na, MainFrame.getFamily().getId());
					Member   men     =   memberDao.getMemberByName(women.getPeiou().getName(), MainFrame.getFamily().getId());
					men.setPeiou(women);
					memberDao.updateMember(men, men.getId());
					JOptionPane.showMessageDialog(null, "添加成功！");
					 AnfunctionImpl.fleshMainFrame();
					dispose();
					        
			  }
		});

        exit.setText("取消");
        exit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			  	dispose();
			}
		});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(Button_up)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit)
                .addGap(51, 51, 51))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_up)
                    .addComponent(exit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    } 
    private javax.swing.JButton Button_up;
    private javax.swing.JRadioButton RadioButton_man;
    private javax.swing.JRadioButton RadioButton_woman;
    static javax.swing.JTextField TextField_day;
    static javax.swing.JTextField TextField_month;
    static javax.swing.JTextField TextField_name= new javax.swing.JTextField();
    static javax.swing.JTextField TextField_peiou;
    
    static javax.swing.JTextField TextField_year;
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
 
   
     
	public void focusGained(FocusEvent e) {
	 
		
	}

	public void focusLost(FocusEvent e) {
//        if(e.getSource()==TextField_name){
//               if(!(TextField_name.getText()+"").equals("")){     
//             	if( AnfunctionImpl.checkUserIsExist(TextField_name.getText().trim())){
//        	       JOptionPane.showMessageDialog(null, TextField_name.getText()+"以存在,请修改名称");
//                }
//        	}else{
//        	    JOptionPane.showMessageDialog(null, "姓名不能为空");
//               }
//          }else if(e.getSource()==TextField_day){
//               if( (TextField_year.getText().trim()+"").equals("") && (TextField_month.getText().trim()+"").equals("") &&(TextField_day.getText().trim()+"").equals("")){
//         		  JOptionPane.showMessageDialog(null, "日期怎么能为空呢");
//         	    }else{
//        	  	   if(!AnfunctionImpl.checkDateIsNotError(TextField_year.getText().trim()+"-"+TextField_month.getText().trim()+"-"+TextField_day.getText().trim())){
//                   	 JOptionPane.showMessageDialog(null, "日期填写错误,请仔细检查");
//                       }
//                 }
//               
//          }else if(e.getSource()==TextField_peiou){
//                	if(!(TextField_peiou.getText().trim()+"").equals("")){
//                 	   if(!AnfunctionImpl.checkUserIsExist(TextField_peiou.getText().trim())){
//                 		   JOptionPane.showMessageDialog(null, TextField_peiou.getText()+"不存在,请修改名称");
//                          }
//                      }else{
//                		  JOptionPane.showMessageDialog(null, "丈夫不能为空");
//                          }
// 	        }
	}
 
}
