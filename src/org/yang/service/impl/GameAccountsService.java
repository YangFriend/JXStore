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

	// 10����¼Ϊ һҳ
	private int pageSize = 10;

	public GameAccountsDao getGameAccountsDao() {
		return gameAccountsDao;
	}

	@Resource(name = "gameAccountsDao")
	public void setGameAccountsDao(GameAccountsDao gameAccountsDao) {
		this.gameAccountsDao = gameAccountsDao;
	}

	/**
	 * ����һ����Ʒid ���ؿ���ҳ����ҳ��(�������۵�).
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
	 * ����һ����Ʒid ���ؿ���˺�(ʣ��δ�۵�) .
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long getSurplusCount(long goodsId) {
		return gameAccountsDao.getSurplusCount(goodsId);

	}

	/**
	 * ����һ����Ʒid ���ؿ���˺�(���е�).
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long getCount(long goodsId) {
		return gameAccountsDao.getCount(goodsId);

	}

	/**
	 * ������Ʒid,ȡ���п���ҳ����(�������۵�)
	 * 
	 * @param pageNow
	 *            ȡ�ڼ�ҳ
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<GameAccounts> getPageList(int pageNow, long goodsId) {

		int first = pageNow * pageSize - pageSize;
		;

		return gameAccountsDao.getPageList(first, pageSize, goodsId);
	}

	/**
	 * ������Ʒid,ȡһ������δ���۵��˺�(�������Ϊ����.)
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
	 * ������Ʒid ���һ������˺�
	 * 
	 * @param goodsId
	 *            ��Ʒ��id
	 * @param bean
	 */
	@Transactional
	public void add(long goodsId, GameAccounts bean) {
		bean.setGoodsId(goodsId);
		gameAccountsDao.save(bean);
	}

}
