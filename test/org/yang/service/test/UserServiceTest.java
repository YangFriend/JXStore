package org.yang.service.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.yang.bean.User;
import org.yang.service.impl.UserService;
import org.yang.util.SpringHelper;


/**
 * 这些jutil 测试 ,基本怎么开心怎么写请勿在意. <br >
 * <!>请勿手抖 手抖 手抖 ! run as all!
 * 
 * @author Administrator
 * 
 */
public class UserServiceTest {
	ApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = SpringHelper.getApplicationContext();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void loadUserTest() {
		UserService service = context.getBean(UserService.class, "userService");
		
		User u = new User();
		u.setUserName("yang");
		u.setUserPassword("123321");
		
		u = service.loadUser(u);
		if (u.getStatus() != null)
			System.out.println("登陆成功:" + u);
		else
			System.out.println("登陆失败:" + u);
		
		
	}

	@Test
	public void queryUserTest() {
		UserService service = context.getBean(UserService.class, "userService");
		User u = new User();
		u.setUserName("中文2");
		u.setUserPassword("pass");
		u = service.loadUserByName(u);
		if (u != null)
			System.out.println("登陆成功:" + u);
		else
			System.out.println("登陆失败:" + u);
	}

	@Test
	public void testRegUser() {

		UserService service = context.getBean(UserService.class, "userService");
		User u = new User();
		u.setUserName("中文2");
		u.setUserPassword("pass");
		u.setEmail("1231111@eecom");
		boolean ret = service.regUser(u);
		Assert.assertEquals(true, ret);
	}

}
