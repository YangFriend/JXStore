package org.yang.exception;

public class UserMoneyNotEnoughException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {

		return "用户余额不足以支付订单金额";
	}

}
