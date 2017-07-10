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
	 * 保存
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
	 * 根据一个商品id 返回未售的账号列表,并将其标为已售. <br >
	 * 可能抛出版本异常,需要处理 <br >
	 * 库存为零时List为空需要处理
	 * 
	 * @param goodsId
	 * @param num
	 *            需要多少个账号
	 * @return 列表.
	 */
	public List<GameAccounts> sellGameAccounts(long goodsId, int num) {
		/*rownum :oracle 语句....真的 不建议用sql
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
			session.saveOrUpdate(ga); // 该方法 当关闭,更新,刷新 session最终会同步到数据库
		}
		session.flush(); // 刷新
		return all;
	}

	/**
	 * 取数据集,(包括已售)
	 * 
	 * @param page
	 *            数据下标
	 * @param max
	 *            结果集大小
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
	 * 根据一个商品id 返回库存数量(包括已售).
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
	 * 根据一个商品id 返回账号表中未售的库存数量.
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
