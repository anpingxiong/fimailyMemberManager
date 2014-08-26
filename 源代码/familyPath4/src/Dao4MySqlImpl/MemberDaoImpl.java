package Dao4MySqlImpl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Member;
import pojo.MemberVo;
import util.DBUtils;
import util.StringToDate;

import anping.AnfunctionImpl;

import com.mysql.jdbc.Buffer;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class MemberDaoImpl {
	
	/////删除没有男人没有爸妈的女人
public  String  selectAllId(){
       StringBuilder a = new StringBuilder()  ;
	 a.append("(");
       String sql  ="select id from t_member ";
	Connection   conn  = null;
	PreparedStatement  pstmt =  null;
	conn  = (Connection) DBUtils.getConn();
	pstmt= (PreparedStatement) DBUtils.getPstmt(conn, sql);
	ResultSet  rs  =  null;
	try {
		rs=pstmt.executeQuery();
		while(rs.next()){
		a.append(rs.getInt(1)+" ,");
		}
		a.deleteCharAt(a.length()-1);
		a.append(")");
	} catch (SQLException e) {
		 e.printStackTrace();
	}finally{
		DBUtils.close(null, pstmt, conn);
	}
	 return  a.toString();
	
}
	public  void deleteWomen(){
		            
		String sql  ="delete from  t_member where wid  not in "+this.selectAllId()+" ";
System.out.println(sql);
		Connection   conn  = null;
		PreparedStatement  pstmt =  null;
		conn  = (Connection) DBUtils.getConn();
		pstmt= (PreparedStatement) DBUtils.getPstmt(conn, sql);
		try {
 			pstmt.executeUpdate();
		} catch (SQLException e) {
			 e.printStackTrace();
		}finally{
			DBUtils.close(null, pstmt, conn);
		}
		
	}

	// 删除成员 参数是：名字，家族编号
	public void deleteMemberByName(String name, int jid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from t_member where name=?  and jid=?  ";
		conn = (Connection) DBUtils.getConn();
		pstmt = (PreparedStatement) DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setString(1, name);
			pstmt.setInt(2, jid);
		 
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, pstmt, conn);
		}
	}

	// 修改资料 参数：成员， 成员id
	public void updateMember(Member member, int id) {

	 String 		sql = "update t_member set name=?,sex=? ,birthday=?,age=? where id=?";
	 
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = (Connection) DBUtils.getConn();
		pstmt = (PreparedStatement) DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getSex());
			pstmt.setDate(3, new Date(member.getBirthday().getTime()));
		 
				pstmt.setInt(4, member.getAge());
				pstmt.setInt(5, id);
		 
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, pstmt, conn);
		}
	}

	// 保存一个成员
	public void saveMember(Member member) {
		String sql = "insert  into  t_member  values(null,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = (Connection) DBUtils.getConn();
		pstmt = (PreparedStatement) DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getSex());
			pstmt.setDate(3,new  Date(member.getBirthday().getTime()));
			pstmt.setInt(4, member.getFamily().getId());
		
			if(member.getFather()==null){
				pstmt.setNull(5, 0);
			}else{
				pstmt.setInt(5, member.getFather().getId());
			}
			if(member.getMother()==null){
				pstmt.setNull(6, 0);
			}else{
				pstmt.setInt(6, member.getMother().getId());
			}
			if(member.getPeiou()==null){
				pstmt.setNull(7, 0);
			}else{
				pstmt.setInt(7, member.getPeiou().getId());
			}
		 
			pstmt.setInt(8, member.getAge());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, pstmt, conn);
		}
	}

	// 显示n代人的信息

	public List<Member> showNageMember(int n, int jid) {
		List<Member> members = new ArrayList<Member>();
		int a = -1;
		String sql = "select  * from  t_member where    jid =?   order by age ";

		if (!(n + "").equals(0 + "")) {
			if (n == 1) {
				a = 2;
				sql = "select  *from  t_member where    jid=?  and age =1";
			} else {
				a = 1;
				sql = "select  * from  t_member where    age = ?  and jid=?  order by  birthday";
			}
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = (Connection) DBUtils.getConn();
		pstmt = (PreparedStatement) DBUtils.getPstmt(conn, sql);

		try {
			if (a == -1) {
				pstmt.setInt(1, jid);

			}
			if (a == 1) {
				pstmt.setInt(1, n);
				pstmt.setInt(2, jid);
			} else if (a == 2) {
				pstmt.setInt(1, jid);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt(1));
				member.setName(rs.getString(2));
				member.setSex(rs.getString(3));
				member.setBirthday(rs.getDate(4));
				member.setFamily(new FamilyDaoImpl().foundFamilyById(rs
						.getInt(5)));
				member.setFather(getMember(rs.getInt(6)));
				member.setMother(getMember(rs.getInt(7)));
				member.setPeiou(getMember(rs.getInt(8)));
				member.setAge(rs.getInt(9));

				members.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, pstmt, conn);
		}
		return members;
	}

	// 拿到成员的妻子和孩子 参数：名字，家族
	public List<Member> getMemberAndWifeAndChildByName(String name, int jid) {
		List<Member> members = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select  * from  t_member f  where f.name = ?  and f.jid=?  or f.wid=(select o.id from t_member  o  where o.name =? and o.jid=?)  or fid=(select c.id from   t_member c where c.name =? and c.jid=?)";
		ResultSet rs = null;
		conn = (Connection) DBUtils.getConn();
		pstmt = (PreparedStatement) DBUtils.getPstmt(conn, sql);

		try {
			pstmt.setString(1, name);
			pstmt.setInt(2, jid);
			pstmt.setString(3, name);
			pstmt.setInt(4, jid);
			pstmt.setString(5, name);
			pstmt.setInt(6, jid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt(1));
 				member.setName(rs.getString(2));
				member.setSex(rs.getString(3));
				member.setBirthday(rs.getDate(4));
				member.setFamily(new FamilyDaoImpl().foundFamilyById(rs
						.getInt(5)));
				member.setFather(getMember(rs.getInt(6)));
				member.setMother(getMember(rs.getInt(7)));
				member.setPeiou(getMember(rs.getInt(8)));
				member.setAge(rs.getInt(9));
				members.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, pstmt, conn);
		}
		return members;
	}

	// 通过生日显示
	public List<Member> showMembersBirthday(Date Birthday, int gid) {
		List<Member> members = new ArrayList<Member>();
		String sql = "select  * from  t_member where birthday  =? and gid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = (Connection) DBUtils.getConn();
		pstmt = (PreparedStatement) DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setDate(1, Birthday);
			pstmt.setInt(2, gid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt(1));
				member.setName(rs.getString(2));
				member.setSex(rs.getString(3));
				member.setBirthday(rs.getDate(4));
				member.setFamily(new FamilyDaoImpl().foundFamilyById(rs
						.getInt(5)));
				member.setFather(getMember(rs.getInt(6)));
				member.setMother(getMember(rs.getInt(7)));
				member.setPeiou(getMember(rs.getInt(8)));
				member.setAge(rs.getInt(9));
				members.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, pstmt, conn);
		}
		return members;
	}

	// 通过id显示

	public Member getMember(int id) {
		Member member = new Member();
		String sql = "select  * from t_member where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = (Connection) DBUtils.getConn();
		pstmt = (PreparedStatement) DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setId(rs.getInt(1));
				member.setName(rs.getString(2));
				member.setSex(rs.getString(3));
				member.setBirthday(rs.getDate(4));
				member.setFamily(new FamilyDaoImpl().foundFamilyById(rs
						.getInt(5)));
				member.setFather(getMember_2(rs.getInt(6)));
				member.setMother(getMember_2(rs.getInt(6)));
				member.setPeiou(getMember_2(rs.getInt(6)));
				member.setAge(rs.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, pstmt, conn);
		}
		return member;
	}

	// 拿到年代数
	public int getAgeCount(int  jid) {
		int count = -1;
		String sql = "select count(*)  from  (  select   count(*)  from  t_member   where  jid = ? group by age ) o;";
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = (Connection) DBUtils.getConn();
		pstmt = (PreparedStatement) DBUtils.getPstmt(conn, sql);
		ResultSet rs = null;
		try {
			pstmt.setInt(1, jid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtils.close(rs, pstmt, conn);
		}
		return count;
	}

	private Member getMember_2(int id) {
		Member member = new Member();
		String sql = "select  * from t_member where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = (Connection) DBUtils.getConn();
		pstmt = (PreparedStatement) DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setId(rs.getInt(1));
				member.setName(rs.getString(2));
				member.setSex(rs.getString(3));
				member.setBirthday(rs.getDate(4));
				member.setFamily(new FamilyDaoImpl().foundFamilyById(rs
						.getInt(5)));
				member.setFather(null);
				member.setMother(null);
				member.setPeiou(null);
				member.setAge(rs.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, pstmt, conn);
		}
		return member;
	}

	// 拿到成员 参数:名字和家族的id
	public Member getMemberByName(String name, int jid) {
		Member member = null;
		 	String sql = "select     *  from  t_member where  name =?  and  jid = ? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = (Connection) DBUtils.getConn();
		pstmt = (PreparedStatement) DBUtils.getPstmt(conn, sql);
                  
		try {
			pstmt.setString(1, name);
			pstmt.setInt(2, jid);
		 	rs = pstmt.executeQuery();
			if (rs.next()) {
		     	member= new Member();
				member.setId(rs.getInt(1));
				member.setName(rs.getString(2));
				member.setSex(rs.getString(3));
				member.setBirthday(rs.getDate(4));
				member.setFamily(new FamilyDaoImpl().foundFamilyById(rs
						.getInt(5)));
				member.setFather(getMember_2(rs.getInt(6)));
				member.setMother(getMember_2(rs.getInt(7)));
				member.setPeiou(getMember_2(rs.getInt(8)));
				member.setAge(rs.getInt(9));
 	}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, pstmt, conn);
		}

		return member;
	}

	public List<MemberVo> getNageByNAndSex(int jid) {
		List<MemberVo> memberVos = new ArrayList<MemberVo>();
		String sql = ""
				+ "  select  n,m,n.age from "
				+ "(select    count(*)  as 'n'  ,  age     from  t_member where jid=? and sex='男' group  by age ) n   left  outer   join  "
				+ "(select    count(*)  as 'm'  ,  age   from    t_member where jid=? and sex='女' group  by age ) m "
				+ "on  m.age  = n.age  "
				+ "union    "
				+ " select   n,m,b.age   from "
				+

				"(select    count(*)  as 'n'  ,   age     from  t_member where jid=? and sex='男' group  by age )a   right  outer   join "
				+ " (select    count(*)  as 'm'  ,   age     from  t_member  where jid=? and sex='女' group  by age ) b "
				+ " on  b.age  = a.age ;      ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = (Connection) DBUtils.getConn();
		pstmt = (PreparedStatement) DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setInt(1, jid);
			pstmt.setInt(2, jid);
			pstmt.setInt(3, jid);
			pstmt.setInt(4, jid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberVo memberVo = new MemberVo();
				memberVo.setMenCount(rs.getInt(1));
				memberVo.setWomenCount(rs.getInt(2));
				memberVo.setAge(rs.getInt(3));
				memberVos.add(memberVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, pstmt, conn);
		}
		return memberVos;
	}
	   
	public   List<String>  addManyMember(List<String> strings,int jid){
	        List<String>  error   = new ArrayList<String>();
 	        String   sql  = "insert  into  t_member  values(null,?,?,?,?,?,?,?,?)";
	        String sql_2  = "select  * from  t_member where name = ?  and jid=?";
		   String sql_4  = "select  * from  t_wife where hname = ? and jid=?";
	       String sql_3  = "insert into t_wife values(?,?,?)";
		  String sql_5   = "select  * from  t_member where id =?";
	      String  sql_6   = "update  t_member  set  wid = ? where  name=? and jid=? ";
		 String   sql_7   = "delete from  t_wife";
	      Connection   conn  = null;
	      PreparedStatement  pstmt_1   =  null;
	      PreparedStatement  pstmt_2   =  null;
	      PreparedStatement  pstmt_3   =  null;
	      PreparedStatement  pstmt_4   =  null;
	      PreparedStatement  pstmt_5  =  null;
	      PreparedStatement  pstmt_6  =  null;
	      PreparedStatement  pstmt_7  =  null;
	      ResultSet rs  = null;
	      conn   = (Connection) DBUtils.getConn();
	      pstmt_1 = (PreparedStatement) DBUtils.getPstmt(conn, sql);
	      pstmt_2 = (PreparedStatement) DBUtils.getPstmt(conn, sql_2);
	      pstmt_3 = (PreparedStatement) DBUtils.getPstmt(conn, sql_3);
	      pstmt_4 = (PreparedStatement) DBUtils.getPstmt(conn, sql_4);
	      pstmt_5 = (PreparedStatement) DBUtils.getPstmt(conn, sql_5);
	      pstmt_6 = (PreparedStatement) DBUtils.getPstmt(conn, sql_6);
	      pstmt_7 = (PreparedStatement) DBUtils.getPstmt(conn, sql_7);
	      int i = 2;
	      for(String str:strings){
	    	  String []  a = str.split(",");
	    	   try {
////////////////////判断我们是不是人已经存在啊  
	 	    	    pstmt_2.setString(1, a[0].trim());
	 	    	    pstmt_2.setInt(2, jid);
			        rs  = pstmt_2.executeQuery();
			        if(rs.next()){
			        	error.add("第"+i+"行成员以存在,无需重复插入,请清除");
			           System.out.println("第"+i+"行成员以存在,无需重复插入,请清除");
			        	break;
			        }
			        Member  father   =null;
			        Member  mother  =null;
		   	////////////////////////////////判断父母是否存在        
		int wifeId = 0 ;
	     if(!a[2].trim().equals("NULL")){
				   pstmt_2.setString(1, a[2].trim());
				   pstmt_2.setInt(2, jid);
				   rs=  pstmt_2.executeQuery();
				    if(rs.next()==false){
					   error.add("第"+i+"行成员父亲不存在,注意父母数据应放在前面,插入终止");
                       System.out.println("第"+i+"行成员父亲不存在,注意父母数据应放在前面，插入终止");
					   break;
				   }else{
					   father  = new Member();
					   father.setId(rs.getInt(1));
					   wifeId= rs.getInt(8);
				   }
				   pstmt_2.setString(1, a[3].trim());
                  pstmt_2.setInt(2,jid);
				   rs=  pstmt_2.executeQuery();
				   if(rs.next()==false){
					   error.add("第"+i+"行成员母亲不存在,注意父母数据应放在前面，插入终止");
					   System.out.println("第"+i+"行成员母亲不存在,注意父母数据应放在前面，插入终止");
					   break;
				   }else{
                        mother  = new Member();
					   mother.setId(rs.getInt(1));
				   }
			   }
	     
	        //////////判断是否父母配对成功过
	         if(father!=null && wifeId !=mother.getId() ){
	    	   error.add("第"+i+"行成员父母配对失败，插入终止");
			   System.out.println("第"+i+"行成员父母配对失败，插入终止");
			   break;
	         }
			   System.out.println("------------------插入-------------------");
			   /////////////////判断是否有老婆
			   Member peiou   = null;
			   if(a[1].trim().equals("男")  && (!a[4].trim().equals("NULL"))){
		 
				   pstmt_3.setString(1, a[0].trim());
				   pstmt_3.setString(2, a[4].trim());
				   pstmt_3.setInt(3,jid);
				    pstmt_3.executeUpdate();
				    System.out.println("--------------------妻子名字已保存-------");
			   }
			   System.out.println("检测老婆");
			  ///////////////////////////////////判断女人的丈夫的女人的名字是不是自己
			if(a[1].trim().equals("女") &&  (!a[4].trim().equals("NULL"))){ 
				pstmt_2.setString(1, a[4].trim());
				pstmt_2.setInt(2,jid);
				   rs=  pstmt_2.executeQuery();
				   if(rs.next()==false){
					    error.add("第"+i+"行成员丈夫不存在，插入终止");
					    System.out.println("第"+i+"行成员丈夫不存在，插入终止");
					   break;
				    }else{
				     	peiou  = new Member();
				    	peiou.setId(rs.getInt(1));
//				    	//////////////////如果为NULL 就去t_wife中查一遍  
				    	if(rs.getInt(8)==0){
				     		pstmt_4.setString(1, a[4].trim());
				     		pstmt_4.setInt(2,jid);
				    		   rs  = pstmt_4.executeQuery();
				    		    if(rs.next()){
				    			    if(!rs.getString(2).equals(a[0].trim())){
				    				   error.add("第"+i+"行成员名字应该为"+rs.getString(2)+"，插入终止");
									   System.out.println("第"+i+"行成员名字应该为"+rs.getString(2)+"，插入终止");
									  			   break;
				                           		   } 
				    		                      }else{
				    		                    	  error.add("第"+i+"行成员名字丈夫无妻子,请对丈夫数据修改，插入终止");
													   System.out.println("第"+i+"行成员名字丈夫无妻子,请对丈夫数据修改，插入终止");
					                                  break;
				    		                      }  
				         	}else{
				         pstmt_5.setInt(1, rs.getInt(8));
				       
				       String wname   = null;
				          rs   = pstmt_5.executeQuery();
				          if(rs.next()){
				        	  wname  = rs.getString(2);
				          }
				         if(!wname.equals(a[1].trim())){
				      	   error.add("第"+i+"行成员名字应该为"+rs.getString(2)+"，插入终止");
						   System.out.println("第"+i+"行成员名字应该为"+rs.getString(2)+"，插入终止");
					    break;
				         }
				      	}
				    }
				    }
 				    	//////////////////////////////////////////////////查找结束，存入数据
				         pstmt_1.setString(1, a[0].trim());
				    	pstmt_1.setString(2, a[1].trim());
				    	try {
							pstmt_1.setDate(3,  new Date( StringToDate.parseStringToDate(a[6].trim()).getTime()));
						} catch (Exception e) {
							e.printStackTrace();
						}
				    	 
				    	pstmt_1.setInt(4, jid);if(father!=null){
				    	pstmt_1.setInt(5, father.getId());
				    	pstmt_1.setInt(6,mother.getId());}else{
				    		pstmt_1.setNull(5, 0);  //空值的set
				    		pstmt_1.setNull(6,0);
				    	}
				     if(peiou!=null){	pstmt_1.setInt(7,peiou.getId());}else{
				 		pstmt_1.setNull(7, 0);
			     	 }
				    	pstmt_1.setInt(8,Integer.parseInt(a[5].trim()));
				    	pstmt_1.executeUpdate();
				    	/////////////插入结束
				 
				  //更新他丈夫的妻子id
				    	if(!a[4].trim().equals("NULL")  && a[1].equals("女")){
				    		pstmt_2.setString(1, a[0].trim());
				    	   pstmt_2.setInt(2, jid);
				    		rs   = pstmt_2.executeQuery();
				    		int wid  = 0 ;
				    		if(rs.next()){
				    			wid   = rs.getInt(1);
				    		}
				    		pstmt_6.setInt(1, wid);
				    		pstmt_6.setString(2, a[4].trim());
				    		pstmt_6.setInt(3, jid);
				    		pstmt_6.executeUpdate();
				    	}
				    	
            ////////////////////////////////////////////////
			    	        
	    	   } catch (SQLException e) {
				   e.printStackTrace();
			    }
	    	   i++;
	      }
	      try {
			pstmt_7.executeUpdate();
		} catch (SQLException e) {
	 		e.printStackTrace();
		}
		  return  error;
	}
	     
    public  void  updateMemberByName(String  name ,String value,String sql_where,int  jid){
    	
    	String sql   = "update  t_member  set  "+sql_where+" = ?  where name=? and  jid=?";
    	Connection   conn = null;
    	PreparedStatement   pstmt = null;
    	conn  = (Connection) DBUtils.getConn();
    	pstmt   = (PreparedStatement) DBUtils.getPstmt(conn, sql);
    	try {
        	if(sql_where.equals("birthday")){
     	      pstmt.setDate(1, new Date(StringToDate.parseStringToDate(value).getTime()));
		       }
       else  {
    	pstmt.setString(1, value);   
       }
    	pstmt.setString(2,name);
    	pstmt.setInt(3, jid);
    	pstmt.executeUpdate();
    	
   
    	} catch (SQLException e) {
		 	e.printStackTrace();
		} catch (Exception e) {
			 e.printStackTrace();
		 }

     }
       
    public void getChildByID(  int fid,int  jid){
    	     String sql  = "select   * from  t_member where (fid=?  or mid=?  )  and jid=?";
    	     ResultSet  rs   = null;
    	    Connection  conn  = (Connection) DBUtils.getConn();
    	    PreparedStatement  pstmt=  (PreparedStatement) DBUtils.getPstmt(conn, sql);
    	    try {
		    	pstmt.setInt(1, fid);
		    	pstmt.setInt(2, fid);
			    pstmt.setInt(3,jid);
			   rs=  pstmt.executeQuery();
		      while(rs.next()){
		       StringBuilder  sb   = new StringBuilder();
		       String  father   =  getMember_2(rs.getInt(6)).getName()==null?"NULL":getMember_2(rs.getInt(6)).getName();
		       String  mother   =  getMember_2(rs.getInt(7)).getName()==null?"NULL":getMember_2(rs.getInt(7)).getName();
		       String  peiou   =  getMember_2(rs.getInt(8)).getName()==null?"NULL":getMember_2(rs.getInt(8)).getName();
		    
		      
				     sb.append(rs.getString(2)+",").append(rs.getString(3)+",").append(father+",")
					.append(mother+",").append(peiou+",")
					.append(rs.getInt(9)+",").append(rs.getDate(4));
				     Member 	member= getMember(rs.getInt(8));
				     AnfunctionImpl.treeMember.add(sb.toString());
			    	  if(member.getName()!=null){
					  sb.delete(0, sb.length());
					  father=   member.getFather().getName()==null?"NULL":member.getFather().getName();
					  mother=   member.getMother().getName()==null?"NULL":member.getMother().getName();
					  peiou=   member.getPeiou().getName()==null?"NULL":member.getPeiou().getName();
				      sb.append(member.getName()+",").append(member.getSex()+",")  	  
				      .append(father+",").append(mother+",")
			    	  .append(rs.getString(2)+",").append(member.getAge()+",").append(member.getBirthday());
				     AnfunctionImpl.treeMember.add(sb.toString());
			    	  }     
				   getChildByID(rs.getInt(1),rs.getInt(5));
		      }
			     
		} catch (SQLException e) {
			 e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, conn);
		}
    	   
    	 }   

 }
