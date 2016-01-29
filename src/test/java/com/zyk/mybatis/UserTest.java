package com.zyk.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.zyk.mybatis.mapper.UserMapper;
import com.zyk.mybatis.pojo.User;

public class UserTest 
{
	
	private static SqlSessionFactory sqlSessionFactory = null;
	
	@Before
	public void testbefore() throws IOException
	{
		String resource = "mybatis-config.xml";
		InputStream inputStream =  Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
	}
	
	@Test
	public void findUserbyId()
	{
		SqlSession session =  sqlSessionFactory.openSession();
		User user = session.selectOne("findUserById",1);
		System.out.println(user);
		System.out.println("=================================");
		UserMapper userMapper =  session.getMapper(UserMapper.class);
		user = userMapper.findUserById(1);
		System.out.println(user);
	}
	
	@Test
	public void testInsertUser()
	{
		SqlSession session =  sqlSessionFactory.openSession();
		User user = new User();
		user.setUserAddress("qinzhou");
		user.setUserAge(20);
		user.setUserName("java");
		session.insert("insertUser", user);
		session.commit();
	}
	
	@Test
	public void testUpdateUser()
	{
		SqlSession session = sqlSessionFactory.openSession();
		User user = session.selectOne("findUserById", 15);
		user.setUserAddress("beijing");
		session.update("updateUser", user);
		session.commit();
	}
	
	@Test
	public void testListUsers()
	{
		SqlSession session = sqlSessionFactory.openSession();
		List<User> users = session.selectList("listUsers");
		System.out.println(users);
	}
	
	
	
}
