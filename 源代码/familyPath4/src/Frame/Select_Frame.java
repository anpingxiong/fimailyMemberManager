 package Frame;

import java.awt.Image;
import java.awt.Toolkit;


public class Select_Frame extends javax.swing.JFrame  {

    
     
	private static final long serialVersionUID = 1L;
	public Select_Frame() {
		setLocation(500, 300);
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        Toolkit tk=Toolkit.getDefaultToolkit();
		Image image=tk.createImage("./image/home_purple.png");
		this.setIconImage(image);
		this.setTitle("查询");
    }

    
    private void initComponents() {

        TextField_selectNameOrBirthday = new javax.swing.JTextField();
        Button_select = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TextField_panduan_1 = new javax.swing.JTextField();
        TextField_panduan_2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Button_panduan = new javax.swing.JButton();
        TextField_guanxi = new javax.swing.JTextField();

        TextField_selectNameOrBirthday.setText("在此输入要查询的姓名或者出生日期(YYYY-MM-DD)");
        TextField_selectNameOrBirthday.addKeyListener(new pressEnterKeyListener(this));
        TextField_selectNameOrBirthday.addFocusListener(new jTextFieldsListener_a());
        Button_select.setText("确定");
        
        Button_select.addActionListener(new monitor_Button_select());

        jLabel1.setText("判断两人关系：");
  
        TextField_panduan_1.setText("路人甲");
        TextField_panduan_1.addFocusListener(new jTextFieldsListener_a());
        TextField_panduan_2.setText("路人乙");
        TextField_panduan_2.addFocusListener(new jTextFieldsListener_a());
        jLabel2.setText("和");

        Button_panduan.setText("判断");
        Button_panduan.addActionListener(new monitor_Button_panduan());

        TextField_guanxi.setText("这里显示两人的关系");
       
        TextField_guanxi.addFocusListener(new jTextFieldsListener_a());
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TextField_panduan_1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextField_panduan_2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TextField_selectNameOrBirthday)
                            .addComponent(TextField_guanxi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Button_select)
                            .addComponent(Button_panduan))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Button_select, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(TextField_selectNameOrBirthday))
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextField_panduan_1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextField_panduan_2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(Button_panduan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(TextField_guanxi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }

   

    
    static  javax.swing.JButton Button_panduan;
    private javax.swing.JButton Button_select;
    static  javax.swing.JTextField TextField_guanxi;
    static  javax.swing.JTextField TextField_panduan_1;
    static  javax.swing.JTextField TextField_panduan_2;
    static javax.swing.JTextField TextField_selectNameOrBirthday;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
	
}
