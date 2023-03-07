package com.sw.command;



import com.sw.Dao.MemberDao;
import com.sw.Dao.MemberDaoImpl;

import MemberDto.MemberDto;

public class LoginCommand implements service {

	@Override
	public int execute(MemberDto mdto) {
		int ret=0;
		MemberDao mdao =new MemberDaoImpl();
		String id =mdto.getId();
		String pw =mdto.getPw();
		String dbpw=mdao.loginMember(id);
		if(pw.equals(dbpw)) {   //success
			ret=1;
		}else{  //fail
			ret=0;
		}
		return ret;
	}

}
