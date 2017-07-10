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

	// 分页大小(仅在售的)
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
	 * 新添加商品
	 * 
	 * @param goods
	 */
	@Transactional
	public void add(Goods goods) {
		
		goodsDao.save(goods);
	}

	/**
	 * 根据ID获取商品
	 * 
	 * @param id
	 * @return 商品bean;不存在返回null
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Goods getGoodsById(Long id) {
		return goodsDao.getGoodsById(id);
	}

	/**
	 * 分页:取某页数据(仅在售的)
	 * 
	 * @param pageNow
	 *            取第几页
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Goods> getPageList(int pageNow) {

		int first = pageNow * this.pageSize - this.pageSize;

		return goodsDao.getSellGoodsList(first, this.pageSize);
	}

	/**
	 * 分页: 设置多少条记录为一页 (仅在售的)
	 * 
	 * @param pageSize
	 *            多少条记录为一页
	 * @return 返回一共多少页
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
	 * 分页: 返回一共多少页 (仅在售的)
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
	 * 返回最新上架
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Goods> getLatestGoods() {
		return goodsDao.getLatest(6);
	}

}
