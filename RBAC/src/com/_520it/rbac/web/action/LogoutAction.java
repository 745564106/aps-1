package com._520it.rbac.web.action;

import com.opensymphony.xwork2.ActionContext;
/**
 * 注销登录
 * @author wuc
 *
 */
public class LogoutAction extends BaseAction{

	private static final long serialVersionUID = -8399373537887274926L;
	//注销登录
	public String execute() throws Exception {
		//注销时，获取session中的数据并清空
		ActionContext.getContext().getSession().clear();
		System.out.println(ActionContext.getContext().get("EMPLOYEE_IN_SESSION"));
		return "logout";
	}

}
