package com.app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.app.mapper.MemberMapper;
import com.app.utils.DBUtils;

public class MemberServices {
	private Logger logger = Logger.getLogger(MemberServices.class);
	public List<Map<String,Object>> getMemberStatus(String noid){
		List<Map<String,Object>> r = new ArrayList<Map<String,Object>>();
		SqlSession session = DBUtils.getSessionFactory().openSession();
		try{
			MemberMapper m = session.getMapper(MemberMapper.class);
			r= m.getMemberStatus(noid);
			
		}catch(Exception e){
			logger.info("exeption"+e.getMessage());
		}finally{
			session.close();
		}
		return r;
	}
}
