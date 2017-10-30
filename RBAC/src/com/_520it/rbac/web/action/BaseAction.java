package com._520it.rbac.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 8734838916513953542L;
	
	//定义返回的常量
	public static final String LIST = "list";
	
	public void putContext(String key,Object value) {
		// 将查询出来的数据放入到值栈中
		ActionContext.getContext().put(key,value);
	}
}
