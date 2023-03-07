package com.sw.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import MemberDto.MemberDto;

public class MemberDaoImpl implements MemberDao {

	@Override
	public int insertMember(MemberDto mdto) {
		Connection con = getConnection();
		ResultSet set=null;
		PreparedStatement pstmt=null;
		int ret=0;
		
		String sql="insert into members (id,pw,name) values (?, ?, ?)";
		try {
			pstmt=con.prepareStatement(sql);
			String id=mdto.getId();
			String pw=mdto.getPw();
			String name=mdto.getName();
			pstmt.setString(1,id);
			pstmt.setString(2,pw);
			pstmt.setString(3,name);
			ret=pstmt.executeUpdate();
			System.out.println("insert ok");
		}catch (SQLException e){
			ret=-1;
			System.out.println("access error.");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			closeConnection(set,pstmt,con);
		}
		return ret;
	}

	@Override
	public String loginMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String pwDb=null;
		
		String sql="select pw from members where id = ?";
		try{
			con=getConnection(); 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			while (rs.next()) {
				   pwDb = rs.getString("pw");
				   System.out.println(" pw : "+pwDb);
			   }
		}catch(SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally{
			closeConnection(rs,pstmt,con); 
		}
		return pwDb;
		
		
	}

	@Override
	public Connection getConnection() {
		 Connection con=null;
			//step2
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("JDBC driver load success");
				} catch (ClassNotFoundException e) {
				System.out.println("JDBC driver load fail");
				}
			//step3
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3300/jsp_servlet_db?useSSL=false", "root","skdbsgh4520");
					System.out.println("Connect success");
				} catch (SQLException e) {
					System.out.println("Connect fail");
				}
			return con;
	}

	@Override
	public void closeConnection(ResultSet set, PreparedStatement pstmt, Connection connection) {
		if(set!=null){
			   try {
				   set.close();
			   } catch (Exception e2) {
				   	e2.printStackTrace();
			   }
		   }
		   if(pstmt!=null){
			   try {
				   pstmt.close();
			   } catch (Exception e2) {
				   	e2.printStackTrace();
			   }
		   }
		   if(connection!=null){
			   try {
				   	connection.close();
			   } catch (Exception e2) {
				   	e2.printStackTrace();
			   }
		   }
	   }
		
	}
