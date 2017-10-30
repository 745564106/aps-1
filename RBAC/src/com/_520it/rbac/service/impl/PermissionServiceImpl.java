package com._520it.rbac.service.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Setter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com._520it.rbac.domain.Permission;
import com._520it.rbac.domain.RequiredPermission;
import com._520it.rbac.mapper.PermissionMapper;
import com._520it.rbac.query.PageResult;
import com._520it.rbac.query.QueryObject;
import com._520it.rbac.service.IPermissionService;
import com._520it.rbac.web.action.BaseAction;

public class PermissionServiceImpl implements IPermissionService,ApplicationContextAware{
	//引入PermissionMapper
	@Setter
	private PermissionMapper permissionMapper;
	private ApplicationContext ctx;
	//通过该方法获取对应的容器对象
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}
	public void delete(Long id) {
		permissionMapper.delete(id);
	}

	public void save(Permission permission) {
		permissionMapper.save(permission);
	}

	public List<Permission> getAll() {
		return permissionMapper.getAll();
	}

	public PageResult query(QueryObject qo) {
		int totalCount = permissionMapper.queryForCount(qo);
		if(totalCount == 0){
			return new PageResult().emptyResult(qo.getPageSize());
		}
		List<Permission> listData = permissionMapper.queryForList(qo);
		return new PageResult(listData, totalCount, qo.getPageSize(), qo.getCurrentPage());
	}

	public void reload() {
		//查询出所有的权限
		List<Permission> expressList = permissionMapper.getAll();
		//将权限表达式存放到set集合中
		Set<String> set = new HashSet<>();
		for (Permission express : expressList) {
			set.add(express.getExpression());
		}
		//获取到所有的action
		//action现在交给spring容器管理,所有Action对象都在Spring容器中
		//从容器中取出所有的Action对象
		Map<String, BaseAction> actionBean = ctx.getBeansOfType(BaseAction.class);
		//获取到所有的action对象
		Collection<BaseAction> actions = actionBean.values();
		for (BaseAction action : actions) {
			//获取action的全限定名
			String actionName = action.getClass().getName();
			//获取贴了注解的方法
			Method[] methods = action.getClass().getDeclaredMethods();
			for (Method method : methods) {
				//判断方法是否贴了RequiredPermission注解
				if(method.isAnnotationPresent(RequiredPermission.class)){
					//获取到方法的名称
					String methodName = method.getName();
					//拼接一个权限表达式
					String expressions = actionName+":"+methodName;
					//判断当前的权限表达式是否在数据库中存在：不存在,保存到数据库中
					if(!set.contains(expressions)){
						RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
						//获取到权限的名称
						String value = annotation.value();
						Permission permission = new Permission();
						permission.setExpression(expressions);
						permission.setName(value);
						//将权限相关的数据保存到数据库中
						permissionMapper.save(permission);
					}
				}
			}
		}
	}
}
