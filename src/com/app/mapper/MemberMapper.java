package com.app.mapper;

import java.util.List;
import java.util.Map;

public interface MemberMapper {
	
		public List<Map<String,Object>> getMemberStatus(String noid);

}
