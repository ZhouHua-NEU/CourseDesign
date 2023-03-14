package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Mysql {
    //主函数       
    public static void main(String[] args) {
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/streetmanagersystem?useUnicode=true&characterEncoding=utf-8","root","");
            Statement stmt = conn.createStatement();
            //新建一个user表,表中有账号和密码,账号和密码都是字符串,长度为20,不可为空,账号为主键,字符编码为utf-8
            String sql = "create table user(userName varchar(20) not null,userPassword varchar(20) not null,primary key(userName))";

            //新建一个community表，字段有社区名，社区地址，社区负责人，社区负责人电话，主键社区名
            String sql1 = "create table community(communityName varchar(20) not null,communityAddress varchar(20) not null,communityManager varchar(20) not null,communityManagerPhone varchar(20) not null,primary key(communityName))";
            //向表中插入三条真实的数据,编码为utf-8
            String sql2 = "insert into community values('社区1','地址1','负责人1','电话1')";
            String sql3 = "insert into community values('社区2','地址2','负责人2','电话2')";
            String sql4 = "insert into community values('社区3','地址3','负责人3','电话3')";
            
            //新建一个street表，字段有街道名，街道负责人，街道负责人电话，主键街道名
            String sql5 = "create table street(streetName varchar(20) not null,streetManager varchar(20) not null,streetManagerPhone varchar(20) not null,primary key(streetName))";
            //向表中插入三条真实的数据
            String sql6 = "insert into street values('街道1','负责人1','电话1')";
            String sql7 = "insert into street values('街道2','负责人2','电话2')";
            String sql8 = "insert into street values('街道3','负责人3','电话3')";
            
            //新建一个house表，字段有房屋编号，房屋地址，房屋面积，房屋类型，房屋所属社区，房屋所属街道，主键为房屋编号
            String sql9 = "create table house(houseNumber varchar(20) not null,houseAddress varchar(20) not null,houseArea varchar(20) not null,houseType varchar(20) not null,houseCommunity varchar(20) not null,houseStreet varchar(20) not null,primary key(houseNumber))";
            //向表中插入三条真实的数据
            String sql10 = "insert into house values('房屋1','地址1','面积1','类型1','社区1','街道1')";
            String sql11 = "insert into house values('房屋2','地址2','面积2','类型2','社区2','街道2')";
            String sql12 = "insert into house values('房屋3','地址3','面积3','类型3','社区3','街道3')";

            //新建一个household表，字段有户主姓名，户主电话，户主身份证号，户主所属房屋编号，主键为户主身份证号
            String sql13= "create table household(householdName varchar(20) not null,householdPhone varchar(20) not null,householdID varchar(20) not null,householdHouseNumber varchar(20) not null,primary key(householdID))";
            //向表中插入三条真实的数据
            String sql14 = "insert into household values('户主1','电话1','身份证1','房屋1')";
            String sql15 = "insert into household values('户主2','电话2','身份证2','房屋2')";
            String sql16 = "insert into household values('户主3','电话3','身份证3','房屋3')";
          
            //新建一个householdMember表，字段有成员姓名，成员电话，成员身份证号，成员所属户主身份证号，主键为成员身份证号
            String sql17= "create table householdMember(memberName varchar(20) not null,memberPhone varchar(20) not null,memberID varchar(20) not null,memberHouseholdID varchar(20) not null,primary key(memberID))";
            //向表中插入三条真实的数据
            String sql18 = "insert into householdMember values('成员1','电话1','身份证1','户主1')";
            String sql19 = "insert into householdMember values('成员2','电话2','身份证2','户主2')";
            String sql20 = "insert into householdMember values('成员3','电话3','身份证3','户主3')";

            //执行所有sql语句
            try {
            stmt.execute(sql1);stmt.execute(sql5);stmt.execute(sql9);stmt.execute(sql13);stmt.execute(sql17);}
            catch(Exception e) {}
            try {
            stmt.execute(sql2);stmt.execute(sql3);stmt.execute(sql4);
            stmt.execute(sql6);stmt.execute(sql7);stmt.execute(sql8);
            stmt.execute(sql10);stmt.execute(sql11);stmt.execute(sql12);
            stmt.execute(sql14);stmt.execute(sql15);stmt.execute(sql16);
            stmt.execute(sql18);stmt.execute(sql19);stmt.execute(sql20);}
            catch(Exception e) {}
            
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();}
        
}}
