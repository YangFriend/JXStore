package org.yang.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// Auto-generated method stub

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Auto-generated method stub
		resp.setContentType("text/html;charSet=utf-8");
		PrintWriter pw = resp.getWriter();

		String name = req.getParameter("userName");
		System.out.println("servlet����:" + name);
		if (name == null) {
			pw.println("error is not  �������Ϊ��!");
			return;
		}

		if (name.equals("123") || name.equals("yang")) {
			pw.println(name + "������");
		} else {
			pw.println(name + "�ǳ�����");
		}
		pw.close();

	}

}
