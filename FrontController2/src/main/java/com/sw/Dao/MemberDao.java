package com.sw.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import MemberDto.MemberDto;

public interface MemberDao {
	public int insertMember(MemberDto mdto);
	public String loginMember(String id);
	public Connection getConnection() ;
	public void closeConnection(ResultSet set, PreparedStatement pstmt, Connection connection);
	}
