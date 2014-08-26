package Frame;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ShowErrorMessageFrame extends JFrame{
        static  JTable  tab;
        static JScrollPane  sp ;
        private   Container container;
	      public   ShowErrorMessageFrame( Object columnNames[],Object value[][]){
    	   super("错误日志");
    	   container = this.getContentPane();  
    	   setLocation(500, 300);
    	      tab  =  new JTable(value, columnNames);
    	       sp = new JScrollPane(tab);
    	       container.add(sp);
    	       this.setVisible(true);
    	       this.setBounds(500,300,400,200);
    	       Toolkit tk=Toolkit.getDefaultToolkit();
       		Image image=tk.createImage("./image/home_purple.png");
       		this.setIconImage(image);
       		this.setTitle("错误日志");
	  }
       
}
