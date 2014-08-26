package Frame;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import pojo.Family;
import pojo.Member;
import util.StringToDate;
import Dao4MySqlImpl.MemberDaoImpl;
import Exception.FamilyIsNotExistException;
import Exception.PasswordIsErrorException;
import anping.AnfunctionImpl;

public class Moniter_a implements ActionListener {

	public void actionPerformed(ActionEvent e) {

	}
}

class  jTextFieldsListener_a implements  FocusListener{
     public void focusGained(FocusEvent e) {
	       JTextField   jTextField   =(JTextField) e.getSource();
	       jTextField.selectAll();
	  }

	public void focusLost(FocusEvent e) {
		 
	}
 }
class jComboBoxListener_a implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		MainFrame.jSplitPane1.setDividerLocation(
    			MainFrame.jSplitPane1.getMinimumDividerLocation()
    	);   // 默认为放大的状态
		Object[][] members = null;
		if (MainFrame.jComboBox1.getSelectedItem() != null) {
			String a = (String) MainFrame.jComboBox1.getSelectedItem() == "全部" ? 0 + ""
					: (String) MainFrame.jComboBox1.getSelectedItem();
			int nAge = Integer.parseInt(a);
			members = AnfunctionImpl.showNage(nAge);
		}

		MainFrame.Table_body.setModel(new javax.swing.table.DefaultTableModel(
				members, new String[] { "代系", "姓名", "性别", "出生日期", "父亲", "母亲",
						"配偶" }));
	}
}

// 用来查询修改的
class ButtonForUpdateAndFound implements ActionListener {
	public void actionPerformed(ActionEvent e) { 
       
		String name = ModifyLogin_Frame.TextField_selectName.getText();
		Member member = AnfunctionImpl.getMemberByName(name);
		modify_Frame modifyFrame = new modify_Frame();
	          modifyFrame.jTextField1.setText(member.getName());
     
	          if (member.getSex().equals("男")) {
 	    	   modifyFrame.jRadioButton1.setSelected(true);
		    	modifyFrame.jRadioButton2.setSelected(false);
         }
	     
 	    	if (member.getSex().equals("女")) {
			modifyFrame.jRadioButton2.setSelected(true);
		   	modifyFrame.jRadioButton1.setSelected(false);
			
		}
		 
		String date[] = member.getBirthday().toString().split("-");
		modifyFrame.jTextField3.setText(date[0]);
		modifyFrame.jTextField4.setText(date[1]);
		modifyFrame.jTextField5.setText(date[2]);
		modifyFrame.setVisible(true);
		  MainFrame.mf.createTree();
		MainFrame.Table_body.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { member.getAge() + "代", member.getName(),
						member.getSex(), member.getBirthday().toString(),
						member.getFather().getName(),
						member.getMother().getName(),
						member.getPeiou().getName() } }, new String[] { "代系",
						"姓名", "性别", "出生日期", "父亲", "母亲", "配偶" }));
	}
	 
}

class ButtonModifyListerner implements ActionListener {
	public void actionPerformed(ActionEvent e) {
    	
		 if( (AnfunctionImpl.checkStringIsNotNull(modify_Frame.jTextField1.getText()+"")
	        		&&   AnfunctionImpl.checkStringIsNotNull(modify_Frame.jTextField3.getText()+"")
	        		&&  AnfunctionImpl.checkStringIsNotNull(modify_Frame.jTextField4.getText()+"")
	        		&& AnfunctionImpl.checkStringIsNotNull(modify_Frame.jTextField5.getText()+"")
 				    )==false ){
  			return ;
	        }
		
		 
		 String name = modify_Frame.jTextField1.getText();
  		String sex = null;
		if (modify_Frame.jRadioButton1.isSelected() == true) {
			sex = "男";
		}

		if (modify_Frame.jRadioButton2.isSelected() == true) {
			sex = "女";
		}
		
		String   strTodate   = modify_Frame.jTextField3
				.getText()
				+ "-"
				+ modify_Frame.jTextField4.getText()
				+ "-"
				+ modify_Frame.jTextField5.getText();
 
	 	if(!AnfunctionImpl.checkDateIsNotError(strTodate)){
		      JOptionPane.showMessageDialog(null, "请认真填写出生日期");
		 	return ;
	 	}
		Date date = null;
		try {
			date = StringToDate.parseStringToDate(strTodate);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Member   member   = new MemberDaoImpl().getMemberByName(
				(String) MainFrame.Table_body.getValueAt(0, 1),
				MainFrame.getFamily().getId());
 
 
		if(member.getPeiou().getName()  != null   &&   !sex .equals(member.getSex())){
		      JOptionPane.showMessageDialog(null, "已婚,更改性别有违伦理");
			 	return ;
		 }
				Member member_2 = new Member();
		member_2.setSex(sex);
		member_2.setBirthday(date);
		member_2.setFamily(MainFrame.getFamily());
 		member_2.setName(name);
 		member_2.setId(member.getId());
		int nage = member.getAge();
		member_2.setAge(nage);
		AnfunctionImpl.updateMember(member_2);
		Member   mem  = new MemberDaoImpl().getMemberByName(member_2.getName(), MainFrame.getFamily().getId());
		MainFrame.Table_body.removeAll();
		MainFrame.Table_body.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { {
						member_2.getAge() + "代",
						member_2.getName(),
						member_2.getSex(),
						modify_Frame.jTextField3.getText() + "-"
								+ modify_Frame.jTextField4.getText() + "-"
								+ modify_Frame.jTextField5.getText(),
						mem.getFather().getName(),
						mem.getMother().getName(),
						mem.getPeiou().getName() } }, new String[] { "代系",
						"姓名", "性别", "出生日期", "父亲", "母亲", "配偶" }));
                  JOptionPane.showMessageDialog(null, "修改成功!");
	}

}

class moniterForField implements FocusListener {

	public void focusGained(FocusEvent e) {
       
	}

	public void focusLost(FocusEvent e) {
		
		if (e.getSource() == LoginFrame.userPD) {
 		String userName1 = LoginFrame.userPD.getPassword().toString();
	  String  usename    = LoginFrame.userName.getText()+"";
	    if(usename.equals("")  ||  userName1.equals("")){
	    	JOptionPane.showMessageDialog(null, "账号或密码未填写");
 		 	}  
		}
	}
}

class filechoosrActionListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	      
		JMenuItem jmenuItem = (JMenuItem) e.getSource();
		if (jmenuItem == MainFrame.jMenuItem1) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					".txt", "txt");
			MainFrame.file.setFileFilter(filter);
			int returnVal = MainFrame.file.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = MainFrame.file.getSelectedFile();
				BufferedReader buffer = null;
				try {
					
					try {
						buffer = new BufferedReader(new InputStreamReader(
								new FileInputStream(file), "gbk"));
						try {
							String str = null;
							final List<String> sb222 = new ArrayList<String>();

							while ((str = buffer.readLine()) != null) {
								if (!str.equals("名字,性别,父亲,母亲,配偶,代系,生日") && !str.equals("")) {
									sb222.add(str);
			                    }
							}
						
							
							if (AnfunctionImpl.checkLoadIsError(sb222).size() == 0) {
								System.out.println("-----------在此增删改查操作--");
						final List<String> sqq =       new MemberDaoImpl().addManyMember(sb222, MainFrame.getFamily().getId());
							if(sqq.size()==0){
								Object[][] members = null;
								 members = AnfunctionImpl.showNage(0);
								 

								MainFrame.Table_body.setModel(new javax.swing.table.DefaultTableModel(
										members, new String[] { "代系", "姓名", "性别", "出生日期", "父亲", "母亲",
												"配偶" }));
							
							}
                          else if(sqq.size()!=0){
                          	 try
                 		    {
                 			 BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
                 		        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
                 		    UIManager.put("RootPane.setupButtonVisible", false);
                 		    }
                 		    catch(Exception ess)
                 		    {
                 		    System.out.println("出错啦");
                 		    }

								  try {
							            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
							                if ("Nimbus".equals(info.getName())) {
							                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
							                    break;
							                }
							            }
							        } catch (ClassNotFoundException ex) {
							            java.util.logging.Logger.getLogger(AddChild_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
							        } catch (InstantiationException ex) {
							            java.util.logging.Logger.getLogger(AddChild_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
							        } catch (IllegalAccessException ex) {
							            java.util.logging.Logger.getLogger(AddChild_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
							        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
							            java.util.logging.Logger.getLogger(AddChild_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
							        }
                                     java.awt.EventQueue.invokeLater(new Runnable() {
							            public void run() {
							            	Object columnNames[]= new Object []{"错误日志"};  
									           int  h = 0 ;
									         
									            Object value[][]= new Object [sqq.size()][1];
									              for(int i = 0  ; i<value.length;i++){
									            	     value[i][0]   = new Object(); 
									              }
									            for (String strm : sqq) {
												      value[h][0]  = strm;
												      h++;
												}	           
							            	new ShowErrorMessageFrame(columnNames,value).setVisible(true);
							            }
							        });
							  }
							} else {
						     	 try
								    {
									 BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
								        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
								    UIManager.put("RootPane.setupButtonVisible", false);
								    }
								    catch(Exception ess)
								    {
								    System.out.println("出错啦");
								    }

								try {
							            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
							                if ("Nimbus".equals(info.getName())) {
							                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
							                    break;
							                }
							            }
							        } catch (ClassNotFoundException ex) {
							            java.util.logging.Logger.getLogger(AddChild_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
							        } catch (InstantiationException ex) {
							            java.util.logging.Logger.getLogger(AddChild_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
							        } catch (IllegalAccessException ex) {
							            java.util.logging.Logger.getLogger(AddChild_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
							        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
							            java.util.logging.Logger.getLogger(AddChild_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
							        }
                                       java.awt.EventQueue.invokeLater(new Runnable() {
							            public void run() {
							            	Object columnNames[]= new Object []{"错误日志"};  
									           int  h = 0 ;
									            List<String> strmge = AnfunctionImpl.checkLoadIsError(sb222);
									            Object value[][]= new Object [strmge.size()][1];
									              for(int i = 0  ; i<value.length;i++){
									            	     value[i][0]   = new Object(); 
									              }
									            for (String strm : strmge) {
												      value[h][0]  = strm;
												      h++;
												}	           
							            	new ShowErrorMessageFrame(columnNames,value).setVisible(true);
							            }
							        });
				 			    
							}

						 } catch (IOException e1) {
							e1.printStackTrace();
						}
					} catch (UnsupportedEncodingException e2) {
						e2.printStackTrace();
					}
				} catch (FileNotFoundException e1) {
					System.out.println("文件没有找到");
					e1.printStackTrace();
				}finally{
					 try {
						buffer.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		}
	}

}

class    pressEnterKeyListener  extends KeyAdapter{
	 private Frame  frame ; 
	public pressEnterKeyListener(Frame frame){
		this.frame = frame;
	}
 	public void keyPressed(KeyEvent e) {
  	  	super.keyPressed(e);
	  if(e.getKeyChar()==e.VK_ENTER){  
		  
		  if(e.getSource() == Select_Frame.TextField_selectNameOrBirthday){
		   new monitor_Button_select().doSelected();	   
		  }
		   /////////////////////查询///////////////
		  
		  else if(e.getSource()==ModifyLogin_Frame.TextField_selectName){
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
			;
			if (member.getSex().equals("女")) {
				 modifyFrame.jRadioButton1.setSelected(true);
					
			}
			;
			String date[] = member.getBirthday().toString().split("-");
			 modifyFrame.jTextField3.setText(date[0]);
			 modifyFrame.jTextField4.setText(date[1]);
			 modifyFrame.jTextField5.setText(date[2]);
			 frame.setVisible(false);
			 modifyFrame.setVisible(true);
			MainFrame.Table_body.setModel(new javax.swing.table.DefaultTableModel(
					new Object[][] { { member.getAge() + "代", member.getName(),
							member.getSex(), member.getBirthday().toString(),
							member.getFather().getName(),
							member.getMother().getName(),
							member.getPeiou().getName() } }, new String[] { "代系",
							"姓名", "性别", "出生日期", "父亲", "母亲", "配偶" }));
	     
		  }
		  /////////////////添加配偶//////////////////////
		  else  if(e.getSource()==AddPO_Frame.TextField_peiou){
			  String na = AddPO_Frame.TextField_name.getText().trim();	 
			    //在此检测是否已经存在
		    	 if( !(AnfunctionImpl.checkStringIsNotNull(AddPO_Frame.TextField_name.getText().trim())  &&
		    			 AnfunctionImpl.checkStringIsNotNull(AddPO_Frame.TextField_peiou.getText().trim()) 
		    			) ){
		   		     return ;
		    	 }  
		    	 else if( (AddPO_Frame.TextField_year.getText().trim()+"").equals("") && (AddPO_Frame.TextField_month.getText().trim()+"").equals("") &&(AddPO_Frame.TextField_day.getText().trim()+"").equals("")){
		    		  JOptionPane.showMessageDialog(null, "日期怎么能为空呢");
		    	     return ;   
		    	 }
		    	 
		     if(!AnfunctionImpl.checkDateIsNotError(AddPO_Frame.TextField_year.getText().trim()+"-"+AddPO_Frame.TextField_month.getText().trim()+""+AddPO_Frame.TextField_day.getText().trim())
			        ){
		   	  JOptionPane.showMessageDialog(null, "日期填写错误,请仔细检查");
			     return ;   
			     }
		     	Date  da=null;
				try {
					String day =  AddPO_Frame.TextField_day.getText().trim() ;
					String mouth =  AddPO_Frame.TextField_month.getText( ).trim();
					String year =  AddPO_Frame.TextField_year.getText( ).trim();
					da= StringToDate.parseStringToDate((year+"-"+mouth+"-"+day));
				} catch (Exception e1) {
					 e1.printStackTrace();
				}  

				MemberDaoImpl a = new MemberDaoImpl();
				 
				Member pe = a.getMemberByName(AddPO_Frame.TextField_peiou.getText().trim(), MainFrame.getFamily().getId());
					if(pe==null) {
					JOptionPane.showMessageDialog(null, AddPO_Frame.TextField_peiou.getText()+"不存在,请修改名称！");
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
				JOptionPane.showMessageDialog(null, "添加成功！");
 				        
			 	frame.setVisible(false);
		  }
		  
		  
		  ///////////////增加孩子///////////
		  else  if(e.getSource()== AddChild_Frame.TextField_mother){
				
		 		////////////////判断是否是正常的
		 		if(!(AnfunctionImpl.checkStringIsNotNull(AddChild_Frame.TextField_name.getText().trim())&&
		 				AnfunctionImpl.checkStringIsNotNull(AddChild_Frame.TextField_day.getText().trim())&&
		 				   AnfunctionImpl.checkStringIsNotNull(AddChild_Frame.TextField_month.getText().trim())&&
		 				     AnfunctionImpl.checkStringIsNotNull(AddChild_Frame.TextField_year.getText().trim())&&
		 				       AnfunctionImpl.checkStringIsNotNull(AddChild_Frame.TextField_father.getText().trim())&&
		 				        AnfunctionImpl.checkStringIsNotNull(AddChild_Frame.TextField_mother.getText().trim())
				 		)){
		 	   JOptionPane.showMessageDialog(null, "不能有空数据,请仔细检测");		
		 	 	return ;
		 		}
		 		
		 		//////////////////检查孩子名字是否已经存在
		 		if(AnfunctionImpl.checkUserIsExist(AddChild_Frame.TextField_name.getText().trim())){
		 		   JOptionPane.showMessageDialog(null,AddChild_Frame.TextField_name.getText()+ "已存在,请修改,或名字后加上标识");		
			 	 	return ;
			 	}
		 	
		 	String na = AddChild_Frame.TextField_name.getText();	 
		    Date  da=null;
			try {
				String day =  AddChild_Frame.TextField_day.getText().trim() ;
				String mouth =  AddChild_Frame.TextField_month.getText( ).trim();
				String year =  AddChild_Frame.TextField_year.getText( ).trim();
				da= StringToDate.parseStringToDate((year+"-"+mouth+"-"+day));
			} catch (Exception e1) {
//				 e1.printStackTrace();
			}  
			MemberDaoImpl a = new MemberDaoImpl();
	 
			Member pe = a.getMemberByName(AddChild_Frame.TextField_father.getText().trim(), MainFrame.getFamily().getId());
	 		Member pe1 = a.getMemberByName(AddChild_Frame.TextField_mother.getText().trim(), MainFrame.getFamily().getId());
			if(pe1==null )  {
				JOptionPane.showMessageDialog(null, "此家族中并无" + AddChild_Frame.TextField_father.getText()+"！");
				return ;
			}
			if(pe==null) {
				JOptionPane.showMessageDialog(null, "此家族中并无" + AddChild_Frame.TextField_mother.getText()+"！");
				return ;
			}
			if(pe.getSex().equals("女")){
				  JOptionPane.showMessageDialog(null, AddChild_Frame.TextField_father.getText()+"是女性,请正确填写"); 
       	return;
			}
			if(pe1.getSex().equals("男")){
				  JOptionPane.showMessageDialog(null, AddChild_Frame.TextField_mother.getText()+"是男性,请正确填写"); 
		         		return ;
			}
			if(pe.getPeiou().getId() != pe1.getId()){
				JOptionPane.showMessageDialog(null, pe.getName()+"不是"+pe1.getName()+"的老婆");
				return ;
			}
			Member mb = new Member();
			if(AddChild_Frame.RadioButton_man.isSelected()){   
				mb.setSex(AddChild_Frame.RadioButton_man.getText()); }  
			 else if(AddChild_Frame.RadioButton_woman.isSelected()){   
				mb.setSex(AddChild_Frame.RadioButton_woman.getText()); }
			 else{
				 JOptionPane.showMessageDialog(null, "请选择性别");
				 return;
			 }
			mb.setFamily(MainFrame.getFamily());
			mb.setName(na);
			mb.setBirthday(da);
			mb.setPeiou(pe1);
			mb.setPeiou(pe);
			if(a.getMemberByName(na,MainFrame.getFamily().getId())!= null)  {
				JOptionPane.showMessageDialog(null, "此家族中已有此人！");
				return;
			}
			a.saveMember(mb);
		 	JOptionPane.showMessageDialog(null, "孩子添加成功");
			 				frame.setVisible(false);
	    	
	    	
	    }
		 
		   ///////////////////////登陆//////////////////////
	    else  if(e.getSource()  == LoginFrame.userPD   ){
	      if (LoginFrame.userName.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "名字不能为空");
			} else if (LoginFrame.userPD.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "密码不能为空");

			} else {
				// 在此登进行检测
				try {
					final Family family = AnfunctionImpl.login(
							LoginFrame.userName.getText(), LoginFrame.userPD.getText());
					frame.setVisible(false);
					 try
					    {
						 BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
					        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		 			        UIManager.put("RootPane.setupButtonVisible", false);
					    }
					    catch(Exception exx)
					    {
					    System.out.println("出错啦");
					    }
              		java.awt.EventQueue.invokeLater(new Runnable() {
						public void run() {
							MainFrame.setFamily(family);
                            MainFrame.mf = new MainFrame();
			             	MainFrame.mf.createTableBody();
                            MainFrame.mf.createTree();
                            MainFrame.mf.addTableListener();
                            MainFrame.mf.setVisible(true);
                     	 TimerTask   task   = new TimerTask() {
							 public void run() {
								 MainFrame.mf.setVisible(true);
							 }
						};
						Timer  timer   = new Timer();
						timer.schedule(task, 2000);
						}
					});

				} catch (FamilyIsNotExistException e1) {
					JOptionPane.showMessageDialog(null, "抱歉,此家族不存在!");
				} catch (PasswordIsErrorException e2) {
					JOptionPane.showMessageDialog(null, "抱歉, 密码错误!");
				}
			}
	      }
	      
	    else if(e.getSource()==RegisterFrame.reposeInput ){
 
	    	  if (RegisterFrame.judgeInfor()) {
	    			//在這裡註冊	  
	    		  if(	0==	AnfunctionImpl.checkStringIsRight(RegisterFrame.idInput.getText())){
		 	    	  JOptionPane.showMessageDialog(null, "抱歉,家族编号只能有英文字母和数组组合");
		 		 		 return    ;
		 	       }   
		 		    
	    		     Family  family   = new Family();
	    	 		   family.setNum(RegisterFrame.idInput.getText());
	    	 		   family.setName(RegisterFrame.nameInput.getText());
	    	 		   family.setPassword(RegisterFrame.passwordInput.getText());
	    	 		   if(0== AnfunctionImpl.addFamily(family, RegisterFrame.reposeInput.getText())){
	    	 			  JOptionPane.showMessageDialog(null, "抱歉,家族编号已存在,请修改家族编号");
	    	 		    }else{
	    	 		    	JOptionPane.showMessageDialog(null, "注册成功,现在登录");
	    	 		    //关闭
	    	 		    	frame.setVisible(false);
	    	 				 try
	    	 			    {
	    	 				 BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
	    	 			        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    	  			    UIManager.put("RootPane.setupButtonVisible", false);
	    	 			    }
	    	 			    catch(Exception ea)
	    	 			    {
	    	 			    System.out.println("出错啦");
	    	 			    }

	    	 				java.awt.EventQueue.invokeLater(new Runnable() {
	    	 					public void run() {
	    	 						LoginFrame loginFarme = new LoginFrame();
	    	 					}
	    	 				});
	    	 		 		    	
	    	 		    }
	    	 		}
	         }
	  }
 	}
}