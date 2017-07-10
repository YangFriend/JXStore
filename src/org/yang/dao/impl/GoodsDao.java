package org.yang.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.yang.bean.Goods;
import org.yang.dao.ParentDao;


@Component("goodsDao")
public class GoodsDao extends ParentDao {

	/**
	 * ����id������Ʒ
	 * 
	 * @param id
	 * @return �����ڷ���null
	 */
	public Goods getGoodsById(long id) {
		return hibernateTemplate.get(Goods.class, id);

	}

	/**
	 * ������Ʒid,���¼������Ʒ
	 * 
	 * @param id
	 * @param status
	 *            true ״̬����Ϊ�ϼ�; false״̬����Ϊ�¼�;
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
	 * ����or����
	 * 
	 * @param goods
	 */
	public void saveOrUpdate(Goods goods) {
		hibernateTemplate.saveOrUpdate(goods);
	}
	
	/**
	 * ����
	 * 
	 * @param goods
	 */
	public void save(Goods goods) {
		hibernateTemplate.save(goods);
	}
	
	
	/**
	 * ����
	 * 
	 * @param goods
	 */
	public void update(Goods goods) {
		hibernateTemplate.update(goods);
	}

	/**
	 * ������Ʒid;������Ʒ
	 * 
	 * @param goodsId
	 * @return
	 */
	public Goods getGoods(long goodsId) {

		return hibernateTemplate.get(Goods.class, goodsId);
	}
	
	
	
	/**
	 * ��ҳ(���¼ܵ�):ȡ���ݼ�
	 * 
	 * @param page
	 *            �����±�
	 * @param max
	 *            ����Ϊһҳ
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
	 * ��ҳ(���¼ܵ�):���ع��ж��ٸ����۵���Ʒ����(�����۵�)
	 * 
	 * @return
	 */
	public long getDropCount() {
		Query query = super.getCurrentSession().createQuery(
				"select count(g) from Goods as g where g.status = 1");
		
		return (Long) query.uniqueResult();

	}
	

	/**
	 * ��ҳ(�����۵�):ȡ���ݼ�
	 * 
	 * @param page
	 *            �����±�
	 * @param max
	 *            ����Ϊһҳ
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
	 * ��ҳ(�����۵�):���ع��ж��ٸ����۵���Ʒ����
	 * 
	 * @return
	 */
	public long getSellCount() {
		Query query = super.getCurrentSession().createQuery(
				"select count(g) from Goods as g where g.status = 0");

		return (Long) query.uniqueResult();

	}

	/**
	 * ��ҳ(�����¼�):ȡ���ݼ�
	 * id ����
	 * @param page
	 *            �����±�
	 * @param max
	 *            ����Ϊһҳ
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
	 * ��ҳ(�����¼�):���ع��ж��ٸ���Ʒ����
	 * 
	 * @return
	 */
	public long getAllCount() {
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("select count(g) from Goods as g ");

		return (Long) query.uniqueResult();

	}

	/**
	 * ȡ�������ϼܵ���Ʒ����
	 * 
	 * @param max
	 *            ����
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
