package org.yang.web.struts.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.yang.bean.Bulletin;
import org.yang.service.impl.BulletinService;


@Component("bulletinAjaxAction")
@Scope("prototype")
public class BulletinAjaxAction extends SupportAction {
	private static final long serialVersionUID = 4976247858979337156L;


	List<Bulletin> latestBulletin;
	
	
	@Autowired
	private BulletinService bulletinService;

	public BulletinService getBulletinService() {
		return bulletinService;
	}

	public void setBulletinService(BulletinService bulletinService) {
		this.bulletinService = bulletinService;
	}
	
	
	/**
	 * 获得最新发布的三条公告
	 * 
	 * @return 失败返回"error"; 成功"success";
	 */
	public String latestBulletin() {
		latestBulletin = bulletinService.getLatestBulletin();
		
		if(latestBulletin == null){
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public void setLatestBulletin(List<Bulletin> latestBulletin) {
		this.latestBulletin = latestBulletin;
	}

	public List<Bulletin> getLatestBulletin() {
		return latestBulletin;
	}

}
