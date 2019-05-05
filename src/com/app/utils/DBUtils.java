package com.app.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtils {
	 private static SqlSessionFactory factory;
	 private static Reader reader;

	 static
	 {
	   init();
	 }
	 public static SqlSessionFactory getSessionFactory(){
		 if(factory !=null){
			 return factory;
		 }
		 return null;
	 }
	 private static void init(){
		  try {
			   reader = new FileReader("conf/mybatis-config.xml");
			  } catch (IOException e) {
			   throw new RuntimeException(e.getMessage());
			  }
		  factory = new SqlSessionFactoryBuilder().build(reader);
		  
	 }
}
