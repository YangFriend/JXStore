package org.yang.web.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.yang.bean.Goods;
import org.yang.bean.User;
import org.yang.dto.CartDto;
import org.yang.dto.CartGoodsList;
import org.yang.exception.GameAccountNotEnoughException;
import org.yang.exception.UserMoneyNotEnoughException;
import org.yang.service.impl.CartService;
import org.yang.service.impl.UserService;


@Component
@Scope("prototype")
public class CartAction extends SupportAction {
	private static final long serialVersionUID = 1L;

	/**
	 * ���ﳵ��Ϣ ���� key=������Ʒ��id; value=������Ʒ������
	 */
	Map<Long, Integer> cartInformation = null;

	private CartDto cartDto;
	private long goodsId;
	private int quantity;

	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;

	/**
	 * ����..
	 * 
	 * @return
	 */
	// ���ٹ���? �Ӹ�ͬ��,�����Ѿ�������=.=
	synchronized public String buy() {
		this.checkCreatCartInformation();
		User userInformation = (User) super.session.get("userInformation");
		if (cartInformation.isEmpty()) {
			super.request.put("info", "�����û����Ҫ����Ķ���,�ظ�����˽���? ��鶩����ʷ��.");
			return "error";
		}
		try {
			userService.buy(userInformation, cartInformation);
			super.session.remove("cartInformation");
		} catch (UserMoneyNotEnoughException e1) {
			super.request.put("info", "��Ǹ,���������֧�����ι���!");
			return "error";
		} catch (GameAccountNotEnoughException e2) {
			super.request.put("info", "��Ǹ,����Ҫ�����ĳ����Ʒ ��治��!");
			return "error";
		}
		return "order";
	}

	public String add() {
		this.checkCreatCartInformation();
		cartInformation.put(goodsId, quantity);

		Object user = super.session.get("userInformation");
		if (user == null) {
			// �û�δ��½
			super.request.put("info", "����������ﳵ,���½��鿴��ϸ��Ϣ");
			return "error";
		}
		cartDto = this.createCartDto(cartInformation);
		super.request.put("cartDto", cartDto);
		return "all";
	}

	public String checkout() {
		this.checkCreatCartInformation();
		if (cartInformation == null || cartInformation.isEmpty()) {
			// ���ﳵΪ��
			super.request.put("info", "����տ���Ҳ");
			return "error";
		}
		CartDto newCartDto = this.createCartDto(cartInformation);
		super.request.put("cartDto", newCartDto);
		return "all";
	}

	public String remove() {
		this.checkCreatCartInformation();
		// ����context �Ƴ�
		for (Long key : cartInformation.keySet()) {
			if (key == goodsId) {
				cartInformation.remove(key);
				// continue;
				break;
			}
		}
		if (cartInformation.isEmpty()) {
			// ���һ��Ҳ���Ƴ�
			super.request.put("info", "����տ���Ҳ");
			return "error";
		}

		// �ٴ���һ���µ�
		cartDto = this.createCartDto(cartInformation);
		super.request.put("cartDto", cartDto);
		return "all";
	}
	/**
	 * �Ƴ�ȫ��
	 * @return
	 */
	public String removeAll() {
		this.checkCreatCartInformation();
		cartInformation.clear();
		super.request.put("info", "����տ���Ҳ");
		return "error";
	
	}

	/**
	 * 
	 * ����
	 * TODO �����߳��Ĵ���,���Ը�createCartDto ��ȡ�ϲ�Ϊһ������
	 * @param cartInformation
	 *            context
	 */
	private CartDto update(Map<Long, Integer> cartInformation) {
		User userInformation = (User) super.session.get("userInformation");
		double allValue = 0;
		double couponValue = 0;
		
		// ����Total,��context�й�������
		for (CartGoodsList cgl : cartDto.getGoodsL()) {
			int num = cgl.getNum();
			double price = cgl.getPrice();
			if (num > 3) {
				//����>3
				num = 3;
				cartInformation.put(cgl.getId(), num);
				cgl.setNum(num);
				cartDto.setTips("ĳЩ��Ʒ������������,����Ϊ��߿ɹ�������!");
			} else if(num <= 0){
				//����0��?
				cartDto.getGoodsL().remove(cgl);
				cartInformation.remove(cgl.getId());
			}else{
				cartInformation.put(cgl.getId(), num);
			}
			cgl.setTotal(price * num);
			allValue += price * num;
		}
		// ���� allValue
		cartDto.setAllValue(allValue);

		// ����ActPrice/couponValue/ userMoney
		if (userInformation.getGread() == 5) {
			cartDto.setCouponCode("�׵��Զ��Ż���һ��,��������");
			couponValue = allValue * 0.9;
		}
		cartDto.setActPrice(allValue - couponValue);
		cartDto.setCouponValue(couponValue);
		cartDto.setUserMoney(userInformation.getMoney());
		return cartDto;

	}

	/**
	 * ���� cartDto
	 * 
	 * @param cartInformation
	 * @return
	 */
	private CartDto createCartDto(Map<Long, Integer> cartInformation) {
		User userInformation = (User) super.session.get("userInformation");
		double allValue = 0;
		double couponValue = 0;
		CartDto newCartDto = new CartDto();
		newCartDto.setGoodsL(new ArrayList<CartGoodsList>());
		
		String addTips1 = "",addTips2="";
		// goodsL
		for (Long key : cartInformation.keySet()) {
			Goods g = cartService.getGoodsById(key);
			if(g.getStatus() == 1){
				addTips1="ĳЩ�¼ܵ���Ʒ,�ѱ��ӹ��ﳵ�Ƴ�.<br />";
				cartInformation.remove(key);
				continue;
			}
			int num = cartInformation.get(g.getId());
			double price = g.getPrice();
			CartGoodsList cgl = new CartGoodsList();
			cgl.setId(g.getId());
			cgl.setImage(g.getImage());
			cgl.setName(g.getName());
			cgl.setSurplus(g.getSurplus());
			cgl.setPrice(price);
			if (num > 3) {
				cgl.setNum(3);
				cartInformation.put(key, 3);
				addTips2="ĳЩ��Ʒ������������,����Ϊ��߿ɹ�������!";
			} else {
				cgl.setNum(num);
			}
			
			cgl.setTotal(price * num);
			allValue += price * num;
			newCartDto.getGoodsL().add(cgl);
		}

		newCartDto.setTips(addTips1+addTips2);
		// allValue
		newCartDto.setAllValue(allValue);

		// ActPrice/userMoney
		if (userInformation.getGread() == 5) {
			newCartDto.setCouponCode("�׵��Զ��Ż���һ��,��������");
			couponValue = allValue * 0.9;
		}
		newCartDto.setActPrice(allValue - couponValue);
		newCartDto.setCouponValue(couponValue);
		newCartDto.setUserMoney(userInformation.getMoney());
		return newCartDto;

	}
	
	
	
	
	
	
	/**
	 * ����Cart
	 * @return
	 */
	public String updateCart() {
		this.checkCreatCartInformation();
		cartDto = this.update(cartInformation);
		super.request.put("cartDto", cartDto);
		return "all";
	}
	/**
	 * ��session�ж�ȡ�򴴽�cartInformation .
	 * 
	 * ���session���Ѵ���,���ᴴ��
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

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public CartDto getCartDto() {
		return cartDto;
	}

	public void setCartDto(CartDto cartDto) {
		this.cartDto = cartDto;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
