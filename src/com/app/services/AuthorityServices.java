package com.app.services;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.app.mapper.AuthorityMapper;
import com.app.utils.DBUtils;

public class AuthorityServices {
		
	private static Logger logger=Logger.getLogger(AuthorityServices.class);
	public int getCountAuthority(){
		int total = -1;
		SqlSession session = DBUtils.getSessionFactory().openSession();
		try{
			AuthorityMapper m = session.getMapper(AuthorityMapper.class);
			total= m.countAuthority();
			
		}catch(Exception e){
			logger.info("exeption"+e.getMessage());
		}finally{
			session.close();
		}
		return total;
		
		
	}
}
