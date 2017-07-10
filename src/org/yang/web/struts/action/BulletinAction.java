package org.yang.web.struts.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.yang.bean.Bulletin;
import org.yang.service.impl.BulletinService;





@Component("bulletinAction")
@Scope("prototype")
public class BulletinAction extends SupportAction {

	private static final long serialVersionUID = -4524778938491844268L;
	
	private long id;
	
	@Autowired
	private BulletinService bulletinService;

	public BulletinService getBulletinService() {
		return bulletinService;
	}

	public void setBulletinService(BulletinService bulletinService) {
		this.bulletinService = bulletinService;
	}
	
	/**
	 * 所有公告
	 * @return
	 */
	public String all(){
		List<Bulletin> bulletinList = bulletinService.getAllBulletin();
		super.request.put("bulletinList", bulletinList);
		return "all";
	}
	
	/**
	 * 某个公告详情
	 * @return
	 */
	public String detail(){
		Bulletin bulletin = bulletinService.getAllBulletin(id);
		if(bulletin == null){
			return "global-error";
		}
		
		super.request.put("bulletin", bulletin);
		return "detail";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	
	
	
}
