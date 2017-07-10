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
	 * �������й���
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Bulletin> getAllBulletin() {
		return bulletinDao.getAllBulletin();
	}
	
	
	/**
	 * ����id��ѯ����.
	 * 
	 * @param Id
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Bulletin getAllBulletin(long Id) {
		return bulletinDao.getBulletinById(Id);
	}
	
	
	
	
	/**
	 * �������
	 * 
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Bulletin> getLatestBulletin() {
		return bulletinDao.getRecentBulletin();
	}
	
	/**
	 * ��ҳѭ��
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Bulletin> getLoopBulletin() {
		long [] loopId = new long[3];
		return bulletinDao.getBulletinList(loopId);
		
	}
	
	
	
	
	

}
