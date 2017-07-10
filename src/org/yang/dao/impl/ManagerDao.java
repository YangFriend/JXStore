package org.yang.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.yang.bean.Manager;
import org.yang.dao.ParentDao;


@Component
public class ManagerDao extends ParentDao {

	/**
	 * 
	 * @param name
	 *            µÇÂ½Ãû
	 * @param password
	 *            ÃÜÂë
	 * @return Manager¶ÔÏó;µÇÂ½Ê§°Ü·µ»Ønull
	 */
	public Manager queryLogin(String name, String password) {
		String hql = "from Manager where name=:n and password=:p";
		Query query = super.getCurrentSession().createQuery(hql)
				.setString("n", name).setString("p", password);
		return (Manager) query.uniqueResult();
	}

}
