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
	 * 购物车信息 其中 key=购买商品的id; value=购买商品的数量
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
	 * 购买..
	 * 
	 * @return
	 */
	// 手速够快? 加个同步,反正已经乱来了=.=
	synchronized public String buy() {
		this.checkCreatCartInformation();
		User userInformation = (User) super.session.get("userInformation");
		if (cartInformation.isEmpty()) {
			super.request.put("info", "你好像没有需要购买的东西,重复点击了结算? 查查订单历史吧.");
			return "error";
		}
		try {
			userService.buy(userInformation, cartInformation);
			super.session.remove("cartInformation");
		} catch (UserMoneyNotEnoughException e1) {
			super.request.put("info", "抱歉,你的余额不足以支付本次购买!");
			return "error";
		} catch (GameAccountNotEnoughException e2) {
			super.request.put("info", "抱歉,你需要购买的某个商品 库存不足!");
			return "error";
		}
		return "order";
	}

	public String add() {
		this.checkCreatCartInformation();
		cartInformation.put(goodsId, quantity);

		Object user = super.session.get("userInformation");
		if (user == null) {
			// 用户未登陆
			super.request.put("info", "已添加至购物车,请登陆后查看详细信息");
			return "error";
		}
		cartDto = this.createCartDto(cartInformation);
		super.request.put("cartDto", cartDto);
		return "all";
	}

	public String checkout() {
		this.checkCreatCartInformation();
		if (cartInformation == null || cartInformation.isEmpty()) {
			// 购物车为空
			super.request.put("info", "这里空空如也");
			return "error";
		}
		CartDto newCartDto = this.createCartDto(cartInformation);
		super.request.put("cartDto", newCartDto);
		return "all";
	}

	public String remove() {
		this.checkCreatCartInformation();
		// 遍历context 移除
		for (Long key : cartInformation.keySet()) {
			if (key == goodsId) {
				cartInformation.remove(key);
				// continue;
				break;
			}
		}
		if (cartInformation.isEmpty()) {
			// 最后一个也被移除
			super.request.put("info", "这里空空如也");
			return "error";
		}

		// 再创建一个新的
		cartDto = this.createCartDto(cartInformation);
		super.request.put("cartDto", cartDto);
		return "all";
	}
	/**
	 * 移除全部
	 * @return
	 */
	public String removeAll() {
		this.checkCreatCartInformation();
		cartInformation.clear();
		super.request.put("info", "这里空空如也");
		return "error";
	
	}

	/**
	 * 
	 * 更新
	 * TODO 这里冗长的代码,可以跟createCartDto 抽取合并为一个方法
	 * @param cartInformation
	 *            context
	 */
	private CartDto update(Map<Long, Integer> cartInformation) {
		User userInformation = (User) super.session.get("userInformation");
		double allValue = 0;
		double couponValue = 0;
		
		// 更新Total,及context中购买数量
		for (CartGoodsList cgl : cartDto.getGoodsL()) {
			int num = cgl.getNum();
			double price = cgl.getPrice();
			if (num > 3) {
				//购买>3
				num = 3;
				cartInformation.put(cgl.getId(), num);
				cgl.setNum(num);
				cartDto.setTips("某些商品超出购买上限,已设为最高可购买数量!");
			} else if(num <= 0){
				//购买0个?
				cartDto.getGoodsL().remove(cgl);
				cartInformation.remove(cgl.getId());
			}else{
				cartInformation.put(cgl.getId(), num);
			}
			cgl.setTotal(price * num);
			allValue += price * num;
		}
		// 更新 allValue
		cartDto.setAllValue(allValue);

		// 更新ActPrice/couponValue/ userMoney
		if (userInformation.getGread() == 5) {
			cartDto.setCouponCode("首单自动优惠至一折,无需输入");
			couponValue = allValue * 0.9;
		}
		cartDto.setActPrice(allValue - couponValue);
		cartDto.setCouponValue(couponValue);
		cartDto.setUserMoney(userInformation.getMoney());
		return cartDto;

	}

	/**
	 * 生成 cartDto
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
				addTips1="某些下架的商品,已被从购物车移除.<br />";
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
				addTips2="某些商品超出购买上限,已设为最高可购买数量!";
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
			newCartDto.setCouponCode("首单自动优惠至一折,无需输入");
			couponValue = allValue * 0.9;
		}
		newCartDto.setActPrice(allValue - couponValue);
		newCartDto.setCouponValue(couponValue);
		newCartDto.setUserMoney(userInformation.getMoney());
		return newCartDto;

	}
	
	
	
	
	
	
	/**
	 * 更新Cart
	 * @return
	 */
	public String updateCart() {
		this.checkCreatCartInformation();
		cartDto = this.update(cartInformation);
		super.request.put("cartDto", cartDto);
		return "all";
	}
	/**
	 * 在session中读取或创建cartInformation .
	 * 
	 * 如果session中已存在,不会创建
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
