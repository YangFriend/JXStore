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

	// ���ﳵ key=��Ʒid value=��������
	private Map<Long, Integer> cartInformation = null;

	/**
	 * ���һ����Ʒ�����ﳵ
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void addSingle() {
		checkCreatCartInformation();

		// �������ﳵ���Ƿ��Ѿ���
		@SuppressWarnings("rawtypes")
		Iterator it = cartInformation.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Long, Integer> entry = (Entry<Long, Integer>) it.next();
			if (entry.getKey() == goodsId) {
				// �Ѿ��� + num
				int val = (int) (entry.getValue() + num);
				cartInformation.put(entry.getKey(), val);
				super.printHTML(String.valueOf(cartInformation.size()));
				return;
			}
		}

		// û�� ����
		cartInformation.put(goodsId, num);
		getCount();
	}

	/**
	 * 
	 * ��HTML����д�� ���ﳵ�е���Ʒ����
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
	 * ���� cartInformation ���������� session��; ���session���Ѵ���,�򲻻ᴴ��
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
