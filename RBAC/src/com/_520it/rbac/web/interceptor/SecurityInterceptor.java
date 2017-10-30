package com._520it.rbac.web.interceptor;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import com._520it.rbac.domain.Employee;
import com._520it.rbac.domain.RequiredPermission;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SecurityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 8230706364515209496L;

	public String intercept(ActionInvocation invocation) throws Exception {
		//1.判断用户是否是超级管理员:是--放行
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		Employee currentEmp = (Employee) session.get("EMPLOYEE_IN_SESSION");
		if(currentEmp.isAdmin()){
			return invocation.invoke();
		}
		//2.判断用户访问的方法是否是贴了RequiredPermission标签
		//获取访问的action的对象
		Object action = invocation.getProxy().getAction();
		//获取到访问的action对象中的方法对象
		String methodName = invocation.getProxy().getMethod();
		Method method = action.getClass().getMethod(methodName);
		if(!method.isAnnotationPresent(RequiredPermission.class)){
			return invocation.invoke();
		}
		//3.判断当前用户访问的权限是否在用户所拥有的权限表达式中:在--放行    不在--跳转到提示页面
		//获取到用户所拥有的权限表达式
		Set<String> expressions = (Set<String>) session.get("EXPRESSIONS_IN_SESSION");
		//拼接用户访问的action中的方法对应的权限表达式
		String expression = action.getClass().getName()+ ":" +methodName;
		if(expressions.contains(expression)){
			return invocation.invoke();
		}
		return "nopermission";
	}
}
