/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 public class Add_select_Frame extends javax.swing.JFrame   implements ActionListener{

     
    public Add_select_Frame() {
    	setLocation(500, 300);
        initComponents();
        Toolkit tk=Toolkit.getDefaultToolkit();
		Image image=tk.createImage("./image/home_purple.png");
		this.setIconImage(image);
		this.setTitle("添加选择");

    }

         private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Button_first = new javax.swing.JButton();
        Button_peiou = new javax.swing.JButton();
        Button_child = new javax.swing.JButton();
         
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("请选择添加的类型：");

        Button_first.setText("添加祖先");
        Button_first.addActionListener(this);
       Button_peiou.setText("添加配偶");
       Button_peiou.addActionListener(this);
        Button_child.setText("添加孩子");
        Button_child.addActionListener(this);
                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Button_peiou)
                            .addComponent(Button_first)
                            .addComponent(Button_child))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(Button_first)
                .addGap(18, 18, 18)
                .addComponent(Button_peiou)
                .addGap(18, 18, 18)
                .addComponent(Button_child)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    } 
         
         private javax.swing.JButton Button_child;
    private javax.swing.JButton Button_first;
    private javax.swing.JButton Button_peiou;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration
	public void actionPerformed(ActionEvent e) {
 	     if(e.getSource()==Button_first){
 	    	 new AddZuxian_Frame().setVisible(true);
		      this.setVisible(false);   	  
            }else  if (e.getSource()==Button_peiou){
    			new AddPO_Frame().setVisible(true);
    			 this.setVisible(false);		  
           }else if(e.getSource()==Button_child){
       		System.out.println("11111111111111");
        	   new AddChild_Frame().setVisible(true);
       	      this.setVisible(false);
        } 
	}
}