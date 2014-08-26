  create   database   family;
    use  family;
   #  家族表
    create table t_family(
     id  int  auto_increment   not null,
     num   varchar(10),  ### 变量
     name  varchar(20),
     password  varchar(10),
     constraint pk  primary key(id)
      );
      ###############新增的两张表
      create table t_miBao(
       num  varchar(100)   not null  primary  key,
       
       password  varchar(255)
       );
        create table  t_wife(
         hName  varchar(20) ,
         wName varchar(20),
         jid  int,
          constraint  primary key(hName,jid)
        );
     insert  into t_family  values(null,'121210','熊家','1234');
     insert  into t_family  values(null,'121211','李家','1234');
     insert  into t_family  values(null,'121212','邹家','1234');
     insert  into t_family  values(null,'121213','曹家','1234');
     
    #成员表 
    create  table t_member(
       id   int  auto_increment   not  null,
       name     varchar(20),
       sex           char(2),
       birthday    date,
       jid             int ,  ##家族id 
       fid            int ,   ## 父亲id
       mid          int ,   ##母亲id
       wid          int  ,  ##妻子的id  丈夫id
       age          int  ,  ##年代
        constraint pk     primary key(id),
        constraint jfk   foreign  key(jid)  references   t_family(id) on delete cascade on update cascade,
        constraint  ffk  foreign  key(fid)  references   t_member(id)    on delete cascade on update cascade
      );
# drop   table t_member  ;
#drop table t_miBao;
        insert  into t_member  values(null,'熊1','男',now(),1,null,null,2,1);                    
    insert  into t_member  values(null,'熊1妻','女',now(),1,null,null,1,1);   
    insert  into t_member  values(null,'熊2_1','男',now(),1,1,2,null,2);						
    insert  into t_member  values(null,'熊2_2','男',now(),1,1,2,5,2);						
    insert  into t_member  values(null,'熊2_2妻','女',now(),1,null,null,4,2);				
    insert  into t_member  values(null,'熊2_3','女',now(),1,1,2,null,2);						
	insert  into t_member  values(null,'熊3_1','男',now(),1,4,5,8,3);					
    insert  into t_member  values(null,'熊3_1妻','女',now(),1,null,null,7,3);			
    insert  into t_member  values(null,'熊3_2','男',now(),1,4,5,10,3);					
    insert  into t_member  values(null,'熊3_2妻','女',now(),1,null,null,9,3);			
	insert  into t_member  values(null,'熊4_1','男',now(),1,7,8,12,4);					
    insert  into t_member  values(null,'熊4_1妻','女',now(),1,null,null,11,4);			
    insert  into t_member  values(null,'熊4_2','女',now(),1,7,8,null,4);					
    insert  into t_member  values(null,'熊4_3','男',now(),1,9,10,15,4);					
    insert  into t_member  values(null,'熊4_3妻','女',now(),1,null,null,14,4);			
	insert  into t_member  values(null,'熊5_1','男',now(),1,11,12,17,5);					
    insert  into t_member  values(null,'熊5_1妻','女',now(),1,null,null,16,5);			
    insert  into t_member  values(null,'熊5_2','男',now(),1,11,12,19,5);					
    insert  into t_member  values(null,'熊5_2妻','女',now(),1,null,null,18,5);				
    insert  into t_member  values(null,'熊5_4','女',now(),1,14,15,null,5);					
    insert  into t_member  values(null,'熊5_5','女',now(),1,14,15,null,5);				
	insert  into t_member  values(null,'熊6_1','男',now(),1,16,17,null,6);				
    insert  into t_member  values(null,'熊6_2','男',now(),1,16,17,null,6);				
    insert  into t_member  values(null,'熊6_3','女',now(),1,18,19,null,6);			
    insert  into t_member  values(null,'熊6_4','男',now(),1,18,19,null,6);				
    