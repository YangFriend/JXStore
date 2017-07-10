package org.yang.web.struts.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CartAjaxAction extends SupportAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long goodsId;
	private int num;

	// 购物车 key=商品id value=购买数量
	private Map<Long, Integer> cartInformation = null;

	/**
	 * 添加一个商品到购物车
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void addSingle() {
		checkCreatCartInformation();

		// 遍历购物车中是否已经有
		@SuppressWarnings("rawtypes")
		Iterator it = cartInformation.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Long, Integer> entry = (Entry<Long, Integer>) it.next();
			if (entry.getKey() == goodsId) {
				// 已经有 + num
				int val = (int) (entry.getValue() + num);
				cartInformation.put(entry.getKey(), val);
				super.printHTML(String.valueOf(cartInformation.size()));
				return;
			}
		}

		// 没有 放入
		cartInformation.put(goodsId, num);
		getCount();
	}

	/**
	 * 
	 * 在HTML流中写入 购物车中的商品数量
	 */
	@SuppressWarnings("unchecked")
	public void getCount() {
		cartInformation = (Map<Long, Integer>) super.session
				.get("cartInformation");
		int count = 0;
		if (cartInformation != null)
			count = cartInformation.size();
		super.printHTML(String.valueOf(count));

	}



	/**
	 * 创建 cartInformation 并把它放入 session中; 如果session中已存在,则不会创建
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void checkCreatCartInformation() {
		this.cartInformation = (Map<Long, Integer>) super.session
				.get("cartInformation");
		if (cartInformation == null) {
			cartInformation = new HashMap<Long, Integer>();
			super.session.put("cartInformation", this.cartInformation);
		}
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Map<Long, Integer> getCarInformation() {
		return cartInformation;
	}

	public void setCarInformation(Map<Long, Integer> cartInformation) {
		this.cartInformation = cartInformation;
	}

}
