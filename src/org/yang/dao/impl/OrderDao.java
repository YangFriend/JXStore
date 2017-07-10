package org.yang.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.yang.bean.Order;
import org.yang.bean.OrderGoodsDetail;
import org.yang.dao.ParentDao;


@Component("orderDao")
public class OrderDao extends ParentDao {

	/**
	 * �����û���ѯ����
	 * 
	 * @param userId
	 * @return ���ض����б�;û�ж�����Ϊnull
	 */
	@SuppressWarnings("unchecked")
	public List<Order> queryOrderByUser(long userId) {
		String hql = "from Order where user.id = :u";
		Query query = this.getCurrentSession().createQuery(hql)
				.setLong("u", userId);
		return query.list();
	}
	
	
	
	/**
	 * ��ҳ:ȡ����ֵ
	 * @param page
	 * @param max
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Order> getAllOrderList(int page, int max){
		String hql = "from Order";
		Query query = super.getCurrentSession().createQuery(hql);
		query.setFirstResult(page);
		query.setMaxResults(max);
		return query.list();
	}
	/**
	 * ��ҳ:�����ܼ�¼��
	 * 
	 * @return
	 */
	public long getAllOrderCount(){
		String hql = "select count(*) from Order";
		Query query = super.getCurrentSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}
	

	/**
	 * ����ID ��ѯ����
	 * @param orderId
	 * @return
	 */
	public Order queryOrderById(long orderId) {
		return (Order) this.getCurrentSession().get(Order.class, orderId);
	}

	/**
	 * ��ȡ,��������� �������Ϸ/�˺�����
	 * 
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrderGoodsDetail> queryOrderGoodsDetail(long orderId) {
		String hql = "from OrderGoodsDetail where orderId=:id";
		Query query = this.getCurrentSession().createQuery(hql)
				.setLong("id", orderId);
		return query.list();
	}

	/**
	 * �����������Ʒ����,д�뵽����
	 * 
	 * @param orderBean
	 * @param goods
	 * @param accounts
	 */
	public void updateOrder(Order orderBean, List<OrderGoodsDetail> goods) {
		Session session = super.getCurrentSession();
		session.saveOrUpdate(orderBean);

		for (OrderGoodsDetail ogd : goods) {
			session.saveOrUpdate(ogd);
		}
		session.flush();
	}

	/**
	 * ��������
	 * 
	 * @param orderBean
	 * @return ���������bean
	 */
	public Order create(Order orderBean) {
		super.getCurrentSession().save(orderBean);
		super.getCurrentSession().flush();
		return orderBean;
	}

	/**
	 * ɾ��
	 * 
	 * @param orderBean
	 */
	public void delte(Order orderBean) {
		hibernateTemplate.delete(orderBean);
	}

}
