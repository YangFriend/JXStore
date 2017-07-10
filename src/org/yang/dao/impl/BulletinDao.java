package org.yang.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.yang.bean.Bulletin;
import org.yang.dao.ParentDao;


@Component("bulletinDao")
public class BulletinDao extends ParentDao {

	public void save(Bulletin bean) {
		hibernateTemplate.save(bean);
	}

	public void delete(long bulletinId) {
		Bulletin bean = hibernateTemplate.get(Bulletin.class, bulletinId);
		hibernateTemplate.delete(bean);

	}
	
	
	
	public Bulletin getBulletinById(long Id) {
		Bulletin b = hibernateTemplate.get(Bulletin.class,Id);
		return b;
	}
	
	

	/**
	 * ��ѯ�б� id��inId�����е�
	 * 
	 * 
	 * 
	 * @param inId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Bulletin> getBulletinList(long []inId) {
		String idStr = "";
		int len = idStr.length();
		for(int i=0; i<len; i++ ){
			if(i == (len-1) ){
				//���һ��û�ж���,
				idStr += idStr;
			}else{
				idStr += idStr+",";
			}
		}
		String hql = "from Bulletin order by releaseDate where id in("+ idStr +"+)";
		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(3);
		return query.list();
	}

	/**
	 * ��ѯ���������3�����
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<Bulletin> getRecentBulletin() {
		String hql = "from Bulletin order by releaseDate ";
		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(3);
		return query.list();
	}

	/**
	 * ��ѯ���н��
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Bulletin> getAllBulletin() {
		String hql = "from Bulletin order by releaseDate";
		Query query = getCurrentSession().createQuery(hql);
		return query.list();
	}

}