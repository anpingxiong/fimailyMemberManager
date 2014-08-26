package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import pojo.MemberVo;
import Dao4MySqlImpl.MemberDaoImpl;

public class CreateFamilyNumByChart {
    private static  	 List<MemberVo> memberVos; 
	public   static  ChartPanel    showChart(){
    	 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 //从数据库中读取来
     	   memberVos  = new  MemberDaoImpl().getNageByNAndSex(MainFrame.getFamily().getId());
     	 for(MemberVo memberVo  : memberVos){
    
    			 dataset.addValue(memberVo.getMenCount(), "男", "第"+memberVo.getAge()+"代");		  
    			 dataset.addValue(memberVo.getWomenCount(), "女", "第"+memberVo.getAge()+"代");		  
    	  }
		JFreeChart chart = ChartFactory.createBarChart("家谱代系",  
				 "家谱代系人口",  
				"人数",  
				dataset,  
				PlotOrientation.VERTICAL,  
				true,  
				true, 
			    false
				);
	 
		chart.setBackgroundPaint(Color.orange);
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.orange);
		plot.setRangeGridlinePaint(Color.red);
		ChartPanel chartPanel = new ChartPanel(chart, false);
		BarRenderer renderer = (BarRenderer)plot .getRenderer();
		renderer.setSeriesPaint(0, Color.red);
		renderer.setItemMargin(0.0);
		renderer.setSeriesPaint(1, Color.pink);
		renderer.setDrawBarOutline(false);
		 chartPanel.setPreferredSize(new Dimension(500, 270));
	  return   chartPanel;
    }
    public   static  ChartPanel    showChartByLine(){
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
  	   for(MemberVo memberVo  : memberVos){
        	 dataset.addValue(memberVo.getMenCount() + memberVo.getWomenCount(), "人数", "第"+memberVo.getAge()+"代");		  
   		  
  	  }
    	
     	JFreeChart chart = ChartFactory.createLineChart(
    			"家族人口趋势图", // chart title
    			"代系", // domain axis label
    			"人数", // range axis label
    			dataset, // data
    			PlotOrientation.VERTICAL, // orientation
    			false, // include legend
    			true, // tooltips
    			false // urls
    			);
    		chart.setBackgroundPaint(Color.orange);
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.orange);
		plot.setRangeGridlinePaint(Color.red);
		ChartPanel chartPanel = new ChartPanel(chart, false);
      
		chartPanel.setPreferredSize(new Dimension(500, 270));
	  return   chartPanel;
   }
   
}
