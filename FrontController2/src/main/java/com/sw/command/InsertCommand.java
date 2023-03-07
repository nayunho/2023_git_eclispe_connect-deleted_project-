package com.sw.command;

import com.sw.Dao.MemberDao;
import com.sw.Dao.MemberDaoImpl;

import MemberDto.MemberDto;

public class InsertCommand implements service {

	@Override
	public int execute(MemberDto mdto) {
		
		MemberDao mdao = new MemberDaoImpl();
		int retVal=mdao.insertMember(mdto);
		return retVal;
	}

}
