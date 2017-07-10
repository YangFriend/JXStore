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
	 * 根据用户查询订单
	 * 
	 * @param userId
	 * @return 返回订单列表;没有订单则为null
	 */
	@SuppressWarnings("unchecked")
	public List<Order> queryOrderByUser(long userId) {
		String hql = "from Order where user.id = :u";
		Query query = this.getCurrentSession().createQuery(hql)
				.setLong("u", userId);
		return query.list();
	}
	
	
	
	/**
	 * 分页:取数据值
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
	 * 分页:返回总记录数
	 * 
	 * @return
	 */
	public long getAllOrderCount(){
		String hql = "select count(*) from Order";
		Query query = super.getCurrentSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}
	

	/**
	 * 根据ID 查询订单
	 * @param orderId
	 * @return
	 */
	public Order queryOrderById(long orderId) {
		return (Order) this.getCurrentSession().get(Order.class, orderId);
	}

	/**
	 * 获取,这个订单中 购买的游戏/账号详情
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
	 * 这个订单的商品详情,写入到表中
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
	 * 创建订单
	 * 
	 * @param orderBean
	 * @return 返回这个单bean
	 */
	public Order create(Order orderBean) {
		super.getCurrentSession().save(orderBean);
		super.getCurrentSession().flush();
		return orderBean;
	}

	/**
	 * 删除
	 * 
	 * @param orderBean
	 */
	public void delte(Order orderBean) {
		hibernateTemplate.delete(orderBean);
	}

}
