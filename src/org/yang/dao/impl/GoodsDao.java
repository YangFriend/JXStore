package org.yang.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.yang.bean.Goods;
import org.yang.dao.ParentDao;


@Component("goodsDao")
public class GoodsDao extends ParentDao {

	/**
	 * 根据id返回商品
	 * 
	 * @param id
	 * @return 不存在返回null
	 */
	public Goods getGoodsById(long id) {
		return hibernateTemplate.get(Goods.class, id);

	}

	/**
	 * 根据商品id,上下架这个商品
	 * 
	 * @param id
	 * @param status
	 *            true 状态设置为上架; false状态设置为下架;
	 * 
	 */
	public void setStatus(long id, boolean status) {
		Goods g = hibernateTemplate.get(Goods.class, id);
		if (status) {
			g.setStatus(0);
		} else {
			g.setStatus(1);
		}

		hibernateTemplate.save(g);

	}

	/**
	 * 保存or更新
	 * 
	 * @param goods
	 */
	public void saveOrUpdate(Goods goods) {
		hibernateTemplate.saveOrUpdate(goods);
	}
	
	/**
	 * 保存
	 * 
	 * @param goods
	 */
	public void save(Goods goods) {
		hibernateTemplate.save(goods);
	}
	
	
	/**
	 * 更新
	 * 
	 * @param goods
	 */
	public void update(Goods goods) {
		hibernateTemplate.update(goods);
	}

	/**
	 * 根据商品id;返回商品
	 * 
	 * @param goodsId
	 * @return
	 */
	public Goods getGoods(long goodsId) {

		return hibernateTemplate.get(Goods.class, goodsId);
	}
	
	
	
	/**
	 * 分页(已下架的):取数据集
	 * 
	 * @param page
	 *            数据下标
	 * @param max
	 *            多少为一页
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Goods> getDropGoodsList(int page, int max) {
		String hql = "from Goods where status = 1 order by id";
		Query query = super.getCurrentSession().createQuery(hql);
		query.setFirstResult(page);
		query.setMaxResults(max);
		return query.list();
	}
	/**
	 * 分页(已下架的):返回共有多少个在售的商品数量(仅在售的)
	 * 
	 * @return
	 */
	public long getDropCount() {
		Query query = super.getCurrentSession().createQuery(
				"select count(g) from Goods as g where g.status = 1");
		
		return (Long) query.uniqueResult();

	}
	

	/**
	 * 分页(仅在售的):取数据集
	 * 
	 * @param page
	 *            数据下标
	 * @param max
	 *            多少为一页
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Goods> getSellGoodsList(int page, int max) {
		String hql = "from Goods where status = 0 order by id";
		Query query = super.getCurrentSession().createQuery(hql);
		query.setFirstResult(page);
		query.setMaxResults(max);
		return query.list();
	}

	/**
	 * 分页(仅在售的):返回共有多少个在售的商品数量
	 * 
	 * @return
	 */
	public long getSellCount() {
		Query query = super.getCurrentSession().createQuery(
				"select count(g) from Goods as g where g.status = 0");

		return (Long) query.uniqueResult();

	}

	/**
	 * 分页(包含下架):取数据集
	 * id 排序
	 * @param page
	 *            数据下标
	 * @param max
	 *            多少为一页
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Goods> getAllGoodsList(int page, int max) {
		String hql = "from Goods order by id";
		Query query = super.getCurrentSession().createQuery(hql);

		query.setFirstResult(page);
		query.setMaxResults(max);
		return query.list();
	}

	/**
	 * 分页(包含下架):返回共有多少个商品数量
	 * 
	 * @return
	 */
	public long getAllCount() {
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("select count(g) from Goods as g ");

		return (Long) query.uniqueResult();

	}

	/**
	 * 取得最新上架的商品集合
	 * 
	 * @param max
	 *            数量
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Goods> getLatest(int max) {

		Query query = super.getCurrentSession().createQuery(
				"from Goods order by addedDate");
		query.setMaxResults(max);
		return query.list();
	}

}
