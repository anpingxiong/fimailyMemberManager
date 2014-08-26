package anping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import pojo.Family;
import pojo.Member;
import util.StringToDate;
import Dao4MySqlImpl.FamilyDaoImpl;
import Dao4MySqlImpl.MemberDaoImpl;
import Exception.FamilyIsNotExistException;
import Exception.PasswordIsErrorException;
import Frame.CreateFamilyNumByChart;
import Frame.MainFrame;
import Frame.Tree_display;

public class AnfunctionImpl {
      public  static  Stack<List>   stack  = new Stack<List>();
  public  static  Set<String>  treeMember   = new TreeSet<String>();
	public static Object[][] showNage(int nAge) {

		int jid = MainFrame.getFamily().getId();

		List<Member> members = new MemberDaoImpl().showNageMember(nAge, jid);

		Object[][] object = new Object[members.size()][7];
		for (int j = 0; j < members.size(); j++) {
			for (int k = 0; k < 7; k++) {
				object[j][k] = new String();
			}
		}
		int i = 0;
		for (Member member : members) {
			object[i][0] = member.getAge() + "代";
			object[i][1] = member.getName();
			object[i][2] = member.getSex();
			object[i][3] = member.getBirthday();
			object[i][4] = member.getFather().getName();
			object[i][5] = member.getMother().getName();

			object[i][6] = (member.getPeiou().getName() == null ? "未婚" : member
					.getPeiou().getName());

			i++;
		}

		return object;
	}

	public static String[] showNAgeNum() {

		int n = new MemberDaoImpl().getAgeCount(MainFrame.getFamily().getId());

		String[] str = new String[n + 1];
		str[0] = "全部";
		for (int i = 1; i <= n; i++) {
			str[i] = i + "";
		}
		return str;

	}

	public static Member getMemberByName(String name) {
		int jid = MainFrame.getFamily().getId();
		return new MemberDaoImpl().getMemberByName(name, jid);
	}

	public static void updateMember(Member member) {
		System.out.println(member.getSex());
		new MemberDaoImpl().updateMember(member, member.getId());
	}

	public static Family login(String num, String password) {
		Family family = new FamilyDaoImpl().getFamilyByNum(num);
		if (family == null) {
			throw new FamilyIsNotExistException();
		} else if (!password.equals(family.getPassword())) {
			throw new PasswordIsErrorException();
		}
		return family;
	}

	public static int addFamily(Family family, String quePassword) {
		Family family_2 = new FamilyDaoImpl().getFamilyByNum(family.getNum());

		if (family_2 == null) {

			new FamilyDaoImpl().save(family, quePassword);
			return 1;
		} else {
			return 0;
		}
	}

	public String getMemberRelation(String member_1, String member_2) {
		String str = null;
		Member member1 = getMemberByName(member_1);
		Member member2 = getMemberByName(member_2);
		if (member1.getAge() - member2.getAge() > 3
				|| member1.getAge() - member2.getAge() < -3) {
			str = "对不起,你查找的两人关系跨越" + (member1.getAge() - member2.getAge())
					+ "代,我们无法命名";
		} else {
			// 拿到信息
		}
		return str;
	}

	public String relation(Member member1, Member member2) {

		return null;
	}

	public static List<String> checkLoadIsError(List<String> img) {
		List<String> errorImgf = new ArrayList<String>();

		int i = 2;
		for (String str : img) {
			if (str.split(",").length != 7) {

				errorImgf.add("第" + i + "行数据格式错误,请检查是否以' , ' 号分隔");
			} else {
				// 做代系和性别和生日的判断
				String a[] = str.split(",");
				if (!a[1].trim().equals("男") && !a[1].trim().equals("女")) {
					errorImgf.add("第" + i + "行数据性别格式错误,格式为男女");
				}
				try {
					Integer.parseInt(a[5].trim());
				} catch (NumberFormatException e) {
					errorImgf.add("第" + i + "行数据代系格式错误,格式为数字");
					System.out.println("11111111");
					e.printStackTrace();
				}
				if (a[6].trim().split("-").length != 3) {
					errorImgf.add("第" + i + "行数据日期格式错误,格式为2012-2-2");
				} else {
					String birthday[] = a[6].trim().split("-");
					if (birthday[0].length() != 4 || birthday[1].length() > 2
							|| birthday[2].length() > 2) {
						errorImgf.add("第" + i + "行数据日期格式错误,年月日错误");
					}
				}
				if (a[2].trim().equals("NULL") || a[3].trim().equals("NULL")) {
					if (!(a[2].trim().equals("NULL") && a[3].trim().equals(
							"NULL"))) {
						errorImgf.add("第" + i + "行数据如果不需要填写父母,那么父母都以“NULL”代替");
					}
				}
			}
			i++;
		}
		return errorImgf;
	}

	public int updateMemberByTable(String oldValue, String newValue,
			String name, String where) {
		int a = 0;
		String sql_where = null;
		MemberDaoImpl memberDao = new MemberDaoImpl();
		if (where.equals("姓名")) {
			sql_where = "name";
			new MemberDaoImpl().updateMemberByName(name, newValue, sql_where,
					MainFrame.getFamily().getId());
			a = 1;
			// 更新Table的所有old
			Member member = memberDao.getMemberByName(newValue, MainFrame
					.getFamily().getId());
			for (int i = 0; i < MainFrame.Table_body.getRowCount(); i++) {
				if (member.getSex().equals("男")) {
					if (oldValue
							.equals(MainFrame.Table_body.getValueAt(i, 4) == null ? ""
									: MainFrame.Table_body.getValueAt(i, 4)
											.toString())) {
						MainFrame.Table_body.setValueAt(newValue, i, 4);
					}
				} else if (member.getSex().equals("女")) {
					if (oldValue
							.equals(MainFrame.Table_body.getValueAt(i, 5) == null ? ""
									: MainFrame.Table_body.getValueAt(i, 5)
											.toString())) {
						MainFrame.Table_body.setValueAt(newValue, i, 5);
					}
				}
				if (oldValue
						.equals(MainFrame.Table_body.getValueAt(i, 6) == null ? ""
								: MainFrame.Table_body.getValueAt(i, 6)
										.toString())) {
					MainFrame.Table_body.setValueAt(newValue, i, 6);
				}
				// /////////////////////////////////////////////更新结束
			}
		} else if (where.equals("性别")) {
			sql_where = "sex";
			if (memberDao.getMemberByName(name, MainFrame.getFamily().getId())
					.getPeiou().getId() != 0) {
				a = 0;
			} else {
				new MemberDaoImpl().updateMemberByName(name, newValue,
						sql_where, MainFrame.getFamily().getId());
				a = 1;
			}
		} else if (where.equals("出生日期")) {
			sql_where = "birthday";
			new MemberDaoImpl().updateMemberByName(name, newValue, sql_where,
					MainFrame.getFamily().getId());
			a = 1;
		}
		return a;
	}

	public static int checkStringIsRight(String str) {
		int  result    =  1  ;
		for (int i = 0; i < str.length(); i++) {
           if(!((str.charAt(i)>='0' &&  str.charAt(i)<='9') ||  (str.charAt(i)>='a'  && str.charAt(i)<='z' || str.charAt(i)>='A' &&str.charAt(i)<='Z'))  ){
             result    =    0  ;
             break;
           }
		}
		return result;// o 代表没有错误
	}
	//检查是不是空的
	public  static Boolean   checkStringIsNotNull(String  str){
		  if(str.trim().equals("")){
			  JOptionPane.showMessageDialog(null, "请仔细检查,不能有空值");
			  return  false;
		  }  else{
				return  true;
			 } 
	}
	public  static  Boolean  checkDateIsNotError(String  date ){
     	 Boolean      result  =true  ;
	 	try {
	 		StringToDate.parseStringToDate(date);
	 	} catch (Exception e) {
		      result    =    false; 
	 	} 
		 return  result;
		
	}
	public static Boolean  checkUserIsExist(String name){
	      Boolean    result   = true  ;
		  Member  member   =   new MemberDaoImpl().getMemberByName(name, MainFrame.getFamily().getId());
		   
	 	  if(member== null){
			  result   = false; 
     		  }
		  return    result  ;
	}
	
public  static  void fleshMainFrame(){
               ///////////1  刷新table
	  Object[][] members = null;
	 members = AnfunctionImpl.showNage(0);
	 

	MainFrame.Table_body.setModel(new javax.swing.table.DefaultTableModel(
			members, new String[] { "代系", "姓名", "性别", "出生日期", "父亲", "母亲",
					"配偶" }));
	////////////////2刷新图
	MainFrame.jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new  AnfunctionImpl().showNAgeNum()));

	MainFrame.jTabbedPane1.removeAll();
	 MainFrame.createTree();
	 MainFrame.jTabbedPane1.addTab("树状形", MainFrame.jScrollPane1);
	MainFrame.jTabbedPane1.addTab("条形图", new CreateFamilyNumByChart().showChart());

	MainFrame.jTabbedPane1.addTab("趋势图", new CreateFamilyNumByChart().showChartByLine());
	
	///////////////2  刷新树 
	   
     }	
	
   
public  static   void  rememberDelete(String name ,int  jid ){
  MemberDaoImpl   memberDao   = new MemberDaoImpl();
     	Member  member  = memberDao.getMemberByName(name, jid);
    	String	  father=   member.getFather().getName()==null?"NULL":member.getFather().getName(); 
  		String	  mother=   member.getMother().getName()==null?"NULL":member.getMother().getName();
  		String	  peiou=   member.getPeiou().getName()==null?"NULL":member.getPeiou().getName();
    	StringBuilder   sb   = new StringBuilder();
     	sb.append(member.getName()+",").append(member.getSex()+",").append(father+",").append(mother+",")
  		.append(peiou+",").append(member.getAge()+",").append(member.getBirthday());
    	AnfunctionImpl.treeMember.add(sb.toString());
  		if(member.getPeiou().getName()!=null){
  			sb.delete(0, sb.length());
  			father=  "NULL";
  			mother=  "NULL";
  			peiou=   member.getName();
  			sb.append(member.getPeiou().getName()+",").append(member.getPeiou().getSex()+",")  	  
  			.append(father+",").append(mother+",")
  			.append(peiou+",").append(member.getPeiou().getAge()+",").append(member.getPeiou().getBirthday());
       AnfunctionImpl.treeMember.add(sb.toString());
  		}     
  		memberDao.getChildByID(member.getId(),jid);
//////////////////////////////////////////数据保存成功
  		List<String>  members   = new ArrayList<String>();
 	    Iterator   tree  =  AnfunctionImpl.treeMember.iterator();
 	   while(tree.hasNext()){
 		   String a  =(String)  tree.next();
 		     members.add(a);
          
 	   }
    stack.push(members);
}   

   public     int   rollback(){
	     //每次点击都返回剩余的rollback数  提醒恢复  对谁的删除操作!!
	   //如果==0   那吗按钮变为不可点
	   //在此拿到数据并存入数据库中
	   if(stack.empty()==true){
		   return   0  ;
	   }else{
		   List<String> members    = stack.peek();
		   
		   String   password   = new FamilyDaoImpl().foundFamilyById(MainFrame.getFamily().getId()).getPassword();
		   String member  =  members.get(0).split(",")[0];
		   if(!password.equals(JOptionPane.showInputDialog(null, "恢复对"+member+"的删除操作,请输入家族密码！"))){
			   JOptionPane.showMessageDialog(null, "抱歉,你输入的密码错误!");
		   }else{
			   List<String> members2    = stack.pop();
 			   new   MemberDaoImpl().addManyMember(members2,MainFrame.getFamily().getId());
			   JOptionPane.showMessageDialog(null, "数据恢复成功!");
			   fleshMainFrame();
		   }
	    }
	   return   stack.size();
   }
   
  
  
}
 