package Frame;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpFrame  extends JFrame {
        private Container   container;  
        private  JScrollPane  sp ;
        private  JTextArea    jtextArea;
	    public HelpFrame(){
	    	super("帮助文档");
	    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        Toolkit tk=Toolkit.getDefaultToolkit();
			Image image=tk.createImage("./image/home_purple.png");
			this.setIconImage(image);

		    container=  this.getContentPane();
		    jtextArea=  new JTextArea(20,50); 
		    jtextArea.setEditable(false);
		    jtextArea.setText("软件功能和使用的介绍:\n" +
		    		"\n1.添加功能中包括添加祖先,配偶和孩子.操作可以点击" +
		    		"工具栏" +
		    						"中的图标或者在左边的树形显示框中先点击\n    要被添加项然后点击鼠标右键即可进行快捷键添加.\n" +
		    		"\n2.修改功能中先查询要修改的名字,在根据其信息做出修改.使用修改操作可以在工具栏中点击图标响应.\n" +
		    		"\n3.查询功能中可以按姓名和出生日期查询,显示信息是在主界面的表格.使用同上.\n" +
		    		"\n4.删除功能中是删除其妻子和后代（如果都存在的话）的信息.使用同上.\n" +
		    		"\n5.撤销键是在删除之后使用.\n" +
		    		"\n6.手动更新键的存在是和实时更新做渲染,实时更新是在对数据进行操作时系统自动进行的更新操作." +
		    		"如果\n    你没有得到自己想要的数据可以尝试手动刷新.使用方式同上.");
		     
		       sp  =  new JScrollPane(jtextArea);
		    
		       container.add(sp);
		  
		     this.setLocation(400,250); 	
		     this.pack();
        	 this.setVisible(true);
         } 
}
