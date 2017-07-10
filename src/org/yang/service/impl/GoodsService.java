package org.yang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yang.bean.Goods;
import org.yang.dao.impl.GoodsDao;


@Component("goodsService")
public class GoodsService {
	private GoodsDao goodsDao;

	// ��ҳ��С(�����۵�)
	private int pageSize = 6;
	private long pageCount = -1;

	public GoodsDao getGoodsDao() {
		return goodsDao;
	}

	@Resource(name = "goodsDao")
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	/**
	 * �������Ʒ
	 * 
	 * @param goods
	 */
	@Transactional
	public void add(Goods goods) {
		
		goodsDao.save(goods);
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

	/**
	 * ��ҳ:ȡĳҳ����(�����۵�)
	 * 
	 * @param pageNow
	 *            ȡ�ڼ�ҳ
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Goods> getPageList(int pageNow) {

		int first = pageNow * this.pageSize - this.pageSize;

		return goodsDao.getSellGoodsList(first, this.pageSize);
	}

	/**
	 * ��ҳ: ���ö�������¼Ϊһҳ (�����۵�)
	 * 
	 * @param pageSize
	 *            ��������¼Ϊһҳ
	 * @return ����һ������ҳ
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long setPageSize(int pageSize) {
		long count = goodsDao.getSellCount();
		this.pageSize = pageSize;

		if (count % pageSize == 0) {
			pageCount = count / pageSize;
		} else {
			pageCount = count / pageSize + 1;
		}
		return pageCount;
	}

	/**
	 * ��ҳ: ����һ������ҳ (�����۵�)
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long getCount() {
		if (this.pageCount < 0) {
			return setPageSize(this.pageSize);
		}
		return pageCount;
	}

	/**
	 * ���������ϼ�
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Goods> getLatestGoods() {
		return goodsDao.getLatest(6);
	}

}
