package Frame;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import anping.AnfunctionImpl;

import pojo.Family;
import Dao4MySqlImpl.FamilyDaoImpl;

public class RegisterFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	RegisterFrame() {
		setSize(300, 400);
		setTitle("账号注册");
		setLocationRelativeTo(null);
		 Toolkit tk=Toolkit.getDefaultToolkit();
 		Image image=tk.createImage("./image/home_purple.png");
 		this.setIconImage(image);
 		this.setTitle("家族注册");
		initiInfor();
		createInfor();
		setVisible(true);
	}

	JLabel id;
	JLabel name;
	JLabel password;
	JLabel rePassword;
	JLabel probleam;
	JLabel repose;
	static JTextField idInput;
	static JTextField nameInput;
	static JTextField reposeInput;
	static JPasswordField passwordInput;
	static JPasswordField RePasswordInput;
	JComboBox cb;
	Vector<String> vc;
	String s = "你最喜欢什么类型的女生？";
	JButton login, reInput, rollBack;

	private void initiInfor() {
		id = new JLabel("家族编号:");
		name = new JLabel("家族姓氏:");
		idInput = new JTextField(17);
		nameInput=new JTextField(17);
		password = new JLabel("        密码:");
		passwordInput = new JPasswordField(17);
		rePassword = new JLabel("重复密码:");
		RePasswordInput = new JPasswordField(17);
		probleam = new JLabel("密保问题:");
		vc = new Vector<String>();
		vc.add(s);
		cb = new JComboBox(vc);
		repose = new JLabel("        答案:");
		reposeInput = new JTextField(17);
		reposeInput.addKeyListener(new pressEnterKeyListener(this));
		login = new JButton("提交");
		reInput = new JButton("重置");
		rollBack = new JButton("返回");
		login.addActionListener(this);
		reInput.addActionListener(this);
		rollBack.addActionListener(this);
	}

	private void createInfor() {
		reposeInput.setToolTipText("需要注意填写哦,要是密码忘记了可以找回哦");
		idInput.setToolTipText("只能有数字和字母组合");
		
		JPanel pl[] = new JPanel[7];
		pl[0] = new JPanel();
		pl[0].setLayout(new FlowLayout(FlowLayout.LEFT));
		pl[0].add(id);
		pl[0].add(idInput);
		pl[1] = new JPanel();
		pl[1].setLayout(new FlowLayout(FlowLayout.LEFT));
		pl[1].add(name);
		pl[1].add(nameInput);
		pl[2] = new JPanel();
		pl[2].setLayout(new FlowLayout(FlowLayout.LEFT));
		pl[2].add(password);
		pl[2].add(passwordInput);
		pl[3] = new JPanel();
		pl[3].setLayout(new FlowLayout(FlowLayout.LEFT));
		pl[3].add(rePassword);
		pl[3].add(RePasswordInput);
		pl[4] = new JPanel();
		pl[4].setLayout(new FlowLayout(FlowLayout.LEFT));
		pl[4].add(probleam);
		pl[4].add(cb);
		pl[5] = new JPanel();
		pl[5].setLayout(new FlowLayout(FlowLayout.LEFT));
		pl[5].add(repose);
		pl[5].add(reposeInput);
		pl[6] = new JPanel();
		pl[6].setLayout(new FlowLayout(FlowLayout.CENTER));
		pl[6].add(login);
		pl[6].add(reInput);
		pl[6].add(rollBack);
		Box b1 = new Box(BoxLayout.Y_AXIS);
		for (int i = 0; i < pl.length; i++) {
			b1.add(pl[i]);
		}
		add(b1);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == login) {
	 		if (judgeInfor()) {
			//在這裡註冊	   
	 	       if(	0==	AnfunctionImpl.checkStringIsRight(idInput.getText())){
	 	    	  JOptionPane.showMessageDialog(null, "抱歉,家族编号只能有英文字母和数组组合");
	 		 		 return    ;
	 	       }   
	 		    
	 			Family  family   = new Family();
	 		   family.setNum(idInput.getText());
	 		   family.setName(nameInput.getText());
	 		   family.setPassword(passwordInput.getText());
	 		   if(0== AnfunctionImpl.addFamily(family, reposeInput.getText())){
	 			  JOptionPane.showMessageDialog(null, "抱歉,家族编号已存在,请修改家族编号");
	 		    }else{
	 		    	JOptionPane.showMessageDialog(null, "注册成功,现在登录");
	 		    	this.setVisible(false);
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

	 				   try {
	 		               for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	 		                   if ("Nimbus".equals(info.getName())) {
	 		                       javax.swing.UIManager.setLookAndFeel(info.getClassName());
	 		                       break;
	 		                   }
	 		               }
	 		           } catch (ClassNotFoundException ex) {
	 		               java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	 		           } catch (InstantiationException ex) {
	 		               java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	 		           } catch (IllegalAccessException ex) {
	 		               java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	 		           } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	 		               java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	 		           }
	 		   	
	 			
	 				java.awt.EventQueue.invokeLater(new Runnable() {
	 					public void run() {
	 						LoginFrame loginFarme = new LoginFrame();
	 					}
	 				});
	 		 		    	
	 		    }
	 		}
		} else if (e.getSource() == reInput) { 
			idInput.setText("");
			passwordInput.setText("");
			RePasswordInput.setText("");
			reposeInput.setText("");
			nameInput.setText("");

		} else {
			this.setVisible(false);
			 try
			    {
				 BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			    UIManager.put("RootPane.setupButtonVisible", false);
			    }
			    catch(Exception es)
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
	               java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	           } catch (InstantiationException ex) {
	               java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	           } catch (IllegalAccessException ex) {
	               java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	           } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	               java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	           }
	   	
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					LoginFrame loginFarme = new LoginFrame();
				}
			});
		}
	}

	static boolean  judgeInfor() {
		String s = "";
		s = idInput.getText().trim();
		    
		  if (!s.trim().equals("")) {
			      if(nameInput.getText().trim().length()!=0){    
			char ch[];
			ch = passwordInput.getPassword();
			if (ch.length != 0) {
				char ch2[];
				ch2 = RePasswordInput.getPassword();
				int count = 0;
				if (ch.length == ch2.length) {
					for (int i = 0; i < ch.length; i++) {
						if (ch[i] == ch2[i]) {
							count++;
						}
					}
					if (count == ch.length) {
						s = "";
						s = reposeInput.getText().trim();
						if (!s.equals("")) {
							return true;
						} else {
							JOptionPane.showMessageDialog(null, "请输入密保");
							return false;
						}
					} else {
						JOptionPane.showMessageDialog(null, "两次输入的密码不一致");
						return false;
					}
				} else {
					JOptionPane.showMessageDialog(null, "两次输入的密码长度不一致");
					return false;
				}
			} else {
				JOptionPane.showMessageDialog(null, "密码为空");
				return false;
			 
			}
			      }else{
			    		JOptionPane.showMessageDialog(null, "家族姓氏没填写");
			           return false;
			      }
		} else {
			JOptionPane.showMessageDialog(null, "家族编号未填写");
			return false;
		}
	 
	}
	 }
