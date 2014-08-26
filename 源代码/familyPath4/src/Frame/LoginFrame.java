package Frame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import pojo.Family;
import Exception.FamilyIsNotExistException;
import Exception.PasswordIsErrorException;
import anping.AnfunctionImpl;

public class LoginFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	 LoginFrame() {
		setSize(300, 200);
		setTitle("家族登陆");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		InitComponent();
		addComponent();
		setVisible(true);
		 Toolkit tk=Toolkit.getDefaultToolkit();
 		Image image=tk.createImage("./image/home_purple.png");
 		this.setIconImage(image);
 		this.setTitle("家族登陆");
	}

	static JLabel lb_1;
	static JLabel lb_2;
	static JTextField userName;
	static JPasswordField userPD;
	static JButton register;
	static JButton login;
	 
	void InitComponent() {
      lb_1 = new JLabel("家族号:");
		lb_2 = new JLabel("密    码:");
		  userName = new JTextField(10);
		userPD = new JPasswordField(10);
		userName.addFocusListener(new moniterForField());
		userPD.addKeyListener(new pressEnterKeyListener(this));
		register = new JButton("创建");
		login = new JButton("进入");
		register.addActionListener(this);
		login.addActionListener(this);
	}

	void addComponent() {
		Box b1 = new Box(BoxLayout.Y_AXIS);
		JPanel p1, p2, p3;

		p1 = new JPanel();
	 
		p1.add(lb_1);
		p1.add(userName);
	 	p2 = new JPanel();
	        
		p2.add(lb_2);
		p2.add(userPD);
	 	p3 = new JPanel();
		p3.add(register);
		p3.add(login);

		b1.add(p1);
		b1.add(p2);
		b1.add(p3);
		add(b1);
	}

	public static void main(String[] args) {
     	 try
		    {
			 BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
		        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		    UIManager.put("RootPane.setupButtonVisible", false);
		    }
		    catch(Exception e)
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
    
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == register) {
			 try
			    {
				 BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
			        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			    UIManager.put("RootPane.setupButtonVisible", false);
			    }
			    catch(Exception exc)
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
					new RegisterFrame();
				}
			});

			this.setVisible(false);
		} else {
			if (e.getSource() == login) {
				if (userName.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "名字不能为空");
				} else if (userPD.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空");

				} else {
					// 在此登进行检测
					try {
						final Family family = AnfunctionImpl.login(
								userName.getText(), userPD.getText());
						this.setVisible(false);
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
                                
//                         	 TimerTask   task   = new TimerTask() {
//								 public void run() {
//									 MainFrame.mf.setVisible(true);
//								 }
//							};
//							Timer  timer   = new Timer();
//							timer.schedule(task, 2000);
//                             
                                
							}
						});

					} catch (FamilyIsNotExistException e1) {
						JOptionPane.showMessageDialog(null, "抱歉,此家族不存在!");
					} catch (PasswordIsErrorException e2) {
						JOptionPane.showMessageDialog(null, "抱歉, 密码错误!");
					}
				}

			}
		}
	}

	
}
