package org.yang.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.yang.bean.User;
import org.yang.dao.ParentDao;


@Component("userDao")
public class UserDao extends ParentDao {

	/**
	 * 使用用户名 登陆/填充数据 * @param u
	 * 
	 * @return 返回新的完整的User对象;未找到返回null
	 */
	public User queryUserByName(User u) {
		String hql = "from User where RTRIM(userName)=:n and RTRIM(userPassword)=:p";
		Query query = getCurrentSession().createQuery(hql)
				.setString("n", u.getUserName())
				.setString("p", u.getUserPassword());
		User user = (User) query.uniqueResult();

		return user;

	}

	/**
	 * 使用用户 email登陆/填充数据
	 * 
	 * @param u
	 * @return 返回新的完整的User对象;未找到返回null
	 */
	public User queryUserByEmail(User u) {
		String hql = "from User where RTRIM(email)=:e and RTRIM(userPassword)=:p";
		Query query = getCurrentSession().createQuery(hql)
				.setString("e", u.getEmail())
				.setString("p", u.getUserPassword());

		User user = (User) query.uniqueResult();

		return user;

	}

	/**
	 * 检查 该email 在数据库 是否存在
	 * 
	 * @param email
	 * @return 存在返回true; 否则false;
	 */
	public boolean checkUserByEmail(String email) {
		boolean b = true;

		String sql = "select id from t_user where EMAIL=:e";
		Query q = getCurrentSession().createSQLQuery(sql).setString("e", email);

		if (q.list().isEmpty())
			b = false;
		return b;
	}

	/**
	 * 检查 该userName 在数据库 是否存在
	 * 
	 * @param userName
	 * @return 存在返回true; 否则false;
	 */
	public boolean checkUserByUserName(String userName) {
		boolean b = true;
		String sql = "select id from t_user where USERNAME=:n";
		Query q = getCurrentSession().createSQLQuery(sql).setString("n",
				userName);
		if (q.list().isEmpty())
			b = false;
		return b;
	}

	/**
	 * 更新User
	 * 
	 * @param user
	 */
	public User loadUser(long userId) {
		return hibernateTemplate.load(User.class, userId);
	}

	/**
	 * 保存User
	 * 
	 * @param user
	 */
	public void save(User user) {
		hibernateTemplate.save(user);
	}

	/**
	 * 刷新User 被坑了,重新在session 读取,而不是刷新到数据库
	 * 
	 * @param user
	 */
	public void refresh(User user) {
		hibernateTemplate.refresh(user);
	}

	/**
	 * 更新 User
	 * 
	 * @param user
	 */
	public void update(User user) {
		hibernateTemplate.update(user);
	}

	/**
	 * 返回所有用户
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUserList() {
		String hql = "from User";
		Query q = getCurrentSession().createQuery(hql);
		return q.list();
	}

	/**
	 * 根据id查询用户
	 * 
	 * @param userId
	 * @return
	 */
	public User getUser(long userId) {
		return hibernateTemplate.get(User.class, userId);
	}

	/**
	 * 设置用户Status <br >
	 * 
	 * @param userId
	 *            用户id
	 * @param status
	 *            true设置为1;
	 */
	public void setUserStatus(long userId, boolean status) {
		User u = hibernateTemplate.get(User.class, userId);
		if (status)
			u.setStatus(1);
		else
			u.setStatus(0);
		hibernateTemplate.save(u);
	}

	/**
	 * 重置用户密码
	 * 
	 * @param userId
	 *            用户id
	 * @param newPassword
	 *            新的密码
	 */
	public void restPassword(long userId, String newPassword) {
		User u = hibernateTemplate.get(User.class, userId);
		u.setUserPassword(newPassword);

	}

}
