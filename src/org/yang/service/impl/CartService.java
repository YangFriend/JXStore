package org.yang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yang.bean.Goods;
import org.yang.dao.impl.GoodsDao;


@Component("cartService")
public class CartService {

	private GoodsDao goodsDao;

	public GoodsDao getGoodsDao() {
		return goodsDao;
	}

	@Resource(name = "goodsDao")
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	/**
	 * ����ID��ȡ��Ʒ
	 * 
	 * @param id
	 * @return ��Ʒbean;�����ڷ���null
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Goods getGoodsById(Long id) {
		return goodsDao.getGoodsById(id);
	}
}
