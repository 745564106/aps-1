package com._520it.rbac.web.action;

import lombok.Setter;

import com._520it.rbac.domain.Employee;
import com._520it.rbac.service.IEmployeeService;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 6840332812347325760L;
	
	//获取前台页面的参数值
	@Setter
	private String username;
	@Setter
	private String password;
	@Setter
	private IEmployeeService empService;
	//登录校验
	public String execute() throws Exception {
		Employee emp = empService.checkLogin(username,password);
		if(emp != null){
			ActionContext.getContext().getSession().put("EMPLOYEE_IN_SESSION", emp);
			return "main";
		}else{
			//将错误信息添加到值栈中
			addActionError("亲,登录的用户名或者密码不正确");
			return LOGIN;
		}
	}
}	
