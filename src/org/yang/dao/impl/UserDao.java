package org.yang.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.yang.bean.User;
import org.yang.dao.ParentDao;


@Component("userDao")
public class UserDao extends ParentDao {

	/**
	 * ʹ���û��� ��½/������� * @param u
	 * 
	 * @return �����µ�������User����;δ�ҵ�����null
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
	 * ʹ���û� email��½/�������
	 * 
	 * @param u
	 * @return �����µ�������User����;δ�ҵ�����null
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
	 * ��� ��email �����ݿ� �Ƿ����
	 * 
	 * @param email
	 * @return ���ڷ���true; ����false;
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
	 * ��� ��userName �����ݿ� �Ƿ����
	 * 
	 * @param userName
	 * @return ���ڷ���true; ����false;
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
	 * ����User
	 * 
	 * @param user
	 */
	public User loadUser(long userId) {
		return hibernateTemplate.load(User.class, userId);
	}

	/**
	 * ����User
	 * 
	 * @param user
	 */
	public void save(User user) {
		hibernateTemplate.save(user);
	}

	/**
	 * ˢ��User ������,������session ��ȡ,������ˢ�µ����ݿ�
	 * 
	 * @param user
	 */
	public void refresh(User user) {
		hibernateTemplate.refresh(user);
	}

	/**
	 * ���� User
	 * 
	 * @param user
	 */
	public void update(User user) {
		hibernateTemplate.update(user);
	}

	/**
	 * ���������û�
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
	 * ����id��ѯ�û�
	 * 
	 * @param userId
	 * @return
	 */
	public User getUser(long userId) {
		return hibernateTemplate.get(User.class, userId);
	}

	/**
	 * �����û�Status <br >
	 * 
	 * @param userId
	 *            �û�id
	 * @param status
	 *            true����Ϊ1;
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
	 * �����û�����
	 * 
	 * @param userId
	 *            �û�id
	 * @param newPassword
	 *            �µ�����
	 */
	public void restPassword(long userId, String newPassword) {
		User u = hibernateTemplate.get(User.class, userId);
		u.setUserPassword(newPassword);

	}

}
