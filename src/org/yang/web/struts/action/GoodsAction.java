package org.yang.web.struts.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.yang.bean.Goods;
import org.yang.service.impl.BulletinService;
import org.yang.service.impl.GoodsService;


@Component("goodsAction")
@Scope("prototype")
public class GoodsAction extends SupportAction {
	private static final long serialVersionUID = 1L;

	private long goodsId;

	private int pageNow;

	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	BulletinService bulletinService;
	
	
	public GoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	public BulletinService getBulletinService() {
		return bulletinService;
	}

	public void setBulletinService(BulletinService bulletinService) {
		this.bulletinService = bulletinService;
	}

	/**
	 * ת��ҳ-��Ϸ����
	 * 
	 * @return
	 */
	public String turnIndex() {
		List<Goods> latest = goodsService.getLatestGoods();
		request.put("latest", latest);
		return "index";
	}

	/**
	 * �鿴����
	 * 
	 * @return
	 */
	public String detail() {
		Goods goods = goodsService.getGoodsById(goodsId);
		if (goods == null) {
			request.put("info", "û���ҵ�����Ʒ.");
			return "error";
		}

		request.put("goods", goods);
		return "detail";
	}

	/**
	 * ȫ����Ʒ/��ҳ
	 * 
	 * @return
	 */
	public String all() {
		long pageCount = goodsService.getCount();
		if (pageNow > pageCount) {
			request.put("info", "����û����Ʒ��.");
			return "error";
		}
		List<Goods> goodsList = goodsService.getPageList(pageNow);
		request.put("goodsList", goodsList);
		request.put("pageCount", pageCount);
		return "all";

	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

}
