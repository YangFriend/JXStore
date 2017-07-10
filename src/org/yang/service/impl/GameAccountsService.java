package org.yang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yang.bean.GameAccounts;
import org.yang.dao.impl.GameAccountsDao;


@Component("gameAccountsService")
public class GameAccountsService {

	GameAccountsDao gameAccountsDao;

	// 10条记录为 一页
	private int pageSize = 10;

	public GameAccountsDao getGameAccountsDao() {
		return gameAccountsDao;
	}

	@Resource(name = "gameAccountsDao")
	public void setGameAccountsDao(GameAccountsDao gameAccountsDao) {
		this.gameAccountsDao = gameAccountsDao;
	}

	/**
	 * 根据一个商品id 返回库存分页的总页数(包括已售的).
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long getPageCount(long goodsId) {
		long pageCount;

		long count = gameAccountsDao.getCount(goodsId);
		if (count % pageSize == 0) {
			pageCount = count / pageSize;
		} else {
			pageCount = count / pageSize + 1;
		}

		return pageCount;

	}

	/**
	 * 根据一个商品id 返回库存账号(剩余未售的) .
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long getSurplusCount(long goodsId) {
		return gameAccountsDao.getSurplusCount(goodsId);

	}

	/**
	 * 根据一个商品id 返回库存账号(所有的).
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long getCount(long goodsId) {
		return gameAccountsDao.getCount(goodsId);

	}

	/**
	 * 根据商品id,取所有库存分页数据(包括已售的)
	 * 
	 * @param pageNow
	 *            取第几页
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<GameAccounts> getPageList(int pageNow, long goodsId) {

		int first = pageNow * pageSize - pageSize;
		;

		return gameAccountsDao.getPageList(first, pageSize, goodsId);
	}

	/**
	 * 根据商品id,取一定数量未出售的账号(并将其标为已售.)
	 * 
	 * @param goodsId
	 * @param num
	 * @return
	 */
	@Transactional
	public List<GameAccounts> sellAccounts(long goodsId, int num) {

		return gameAccountsDao.sellGameAccounts(goodsId, num);
	}

	/**
	 * 根据商品id 添加一个库存账号
	 * 
	 * @param goodsId
	 *            商品的id
	 * @param bean
	 */
	@Transactional
	public void add(long goodsId, GameAccounts bean) {
		bean.setGoodsId(goodsId);
		gameAccountsDao.save(bean);
	}

}
