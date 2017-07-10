package org.yang.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.yang.bean.GameAccounts;
import org.yang.dao.ParentDao;


@Component("gameAccountsDao")
public class GameAccountsDao extends ParentDao {

	/**
	 * ����
	 * 
	 * @param accounts
	 */
	public void save(GameAccounts accounts) {
		hibernateTemplate.save(accounts);
	}

	public void update(GameAccounts accounts) {
		hibernateTemplate.update(accounts);
	}

	/**
	 * ����һ����Ʒid ����δ�۵��˺��б�,�������Ϊ����. <br >
	 * �����׳��汾�쳣,��Ҫ���� <br >
	 * ���Ϊ��ʱListΪ����Ҫ����
	 * 
	 * @param goodsId
	 * @param num
	 *            ��Ҫ���ٸ��˺�
	 * @return �б�.
	 */
	public List<GameAccounts> sellGameAccounts(long goodsId, int num) {
		/*rownum :oracle ���....��� ��������sql
		 * 
			String hql = "from GameAccounts where sell=0 and rownum<=:n and goodsId=:gid ";
			Query q = session.createQuery(hql).setInteger("n", num)
					.setLong("gid", goodsId);
		*/
		
		String hql = "from GameAccounts where sell=0 and goodsId=:gid ";
		Session session = hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query q = session.createQuery(hql).setLong("gid", goodsId)
				.setMaxResults(num);
		
		@SuppressWarnings("unchecked")
		List<GameAccounts> all = q.list();
		for (GameAccounts ga : all) {
			ga.setSell(1);
			session.saveOrUpdate(ga); // �÷��� ���ر�,����,ˢ�� session���ջ�ͬ�������ݿ�
		}
		session.flush(); // ˢ��
		return all;
	}

	/**
	 * ȡ���ݼ�,(��������)
	 * 
	 * @param page
	 *            �����±�
	 * @param max
	 *            �������С
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GameAccounts> getPageList(int page, int max, long goodsId) {
		String hql = "from GameAccounts as ga where ga.goodsId=:gid";
		Query query = super.getCurrentSession().createQuery(hql)
				.setLong("gid", goodsId);
		query.setFirstResult(page);
		query.setMaxResults(max);
		return query.list();
	}

	/**
	 * ����һ����Ʒid ���ؿ������(��������).
	 * 
	 * @return
	 */
	public long getCount(long goodsId) {
		String hql = "select count(*) from GameAccounts as ga where ga.goodsId=:gid";

		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(hql).setLong("gid", goodsId);

		return (Long) query.uniqueResult();

	}

	/**
	 * ����һ����Ʒid �����˺ű���δ�۵Ŀ������.
	 * 
	 * @return
	 */
	public long getSurplusCount(long goodsId) {
		String hql = "select count(*) from GameAccounts as ga where ga.goodsId=:gid and ga.sell=0";

		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(hql).setLong("gid", goodsId);

		return (Long) query.uniqueResult();

	}

}
