package org.yang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yang.bean.Bulletin;
import org.yang.dao.impl.BulletinDao;


@Component("bulletinService")
public class BulletinService {
	
	
	BulletinDao bulletinDao;

	public BulletinDao getBulletinDao() {
		return bulletinDao;
	}
	
	@Resource(name = "bulletinDao")
	public void setBulletinDao(BulletinDao bulletinDao) {
		this.bulletinDao = bulletinDao;
	}
	
	
	
	
	
	/**
	 * 返回所有公告
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Bulletin> getAllBulletin() {
		return bulletinDao.getAllBulletin();
	}
	
	
	/**
	 * 根据id查询公告.
	 * 
	 * @param Id
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Bulletin getAllBulletin(long Id) {
		return bulletinDao.getBulletinById(Id);
	}
	
	
	
	
	/**
	 * 最近三条
	 * 
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Bulletin> getLatestBulletin() {
		return bulletinDao.getRecentBulletin();
	}
	
	/**
	 * 主页循环
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Bulletin> getLoopBulletin() {
		long [] loopId = new long[3];
		return bulletinDao.getBulletinList(loopId);
		
	}
	
	
	
	
	

}
