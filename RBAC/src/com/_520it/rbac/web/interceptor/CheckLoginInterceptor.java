package com._520it.rbac.web.interceptor;

import com._520it.rbac.domain.Employee;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
//登录校验拦截器
public class CheckLoginInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 4082216469265937484L;
	//登录拦截器,判断是否登录,没有登录,跳转到登录页面
	public String intercept(ActionInvocation invocation) throws Exception {
		Employee currentEmp = (Employee) invocation.getInvocationContext().getSession().get("EMPLOYEE_IN_SESSION");
		//如果session中的用户为空,拦截
		if(currentEmp == null){
			return Action.LOGIN;
		}
		//放行
		return invocation.invoke();
	}
}
