package com._520it.rbac.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Setter;

import com._520it.rbac.domain.Employee;
import com._520it.rbac.domain.Permission;
import com._520it.rbac.domain.Role;
import com._520it.rbac.mapper.EmployeeMapper;
import com._520it.rbac.query.EmployeeQueryObject;
import com._520it.rbac.query.PageResult;
import com._520it.rbac.service.IEmployeeService;
import com.opensymphony.xwork2.ActionContext;

public class EmployeeServiceImpl implements IEmployeeService{
	
	//引入EmployeeMapper
	@Setter
	private EmployeeMapper empMapper;
	
	public void save(Employee emp) {
		//保存员工的基本信息
		empMapper.save(emp);
		//获取角色的信息
		List<Role> roles = emp.getRoles();
		//保存员工时将信息保存到员工角色的中间表中
		for (Role role : roles) {
			empMapper.saveRelation(emp.getId(),role.getId());
		}
	}

	public void delete(long id) {
		empMapper.delete(id);
	}

	public void update(Employee emp) {
		//更新员工的基本信息
		empMapper.update(emp);
		//更新员工和角色表中的信息
		//1.根据员工id删除员工角色表中的相应的数据
		empMapper.deleteRelation(emp.getId());
		//2.将新添加的数据加入到表中
		//获取角色的信息
		List<Role> roles = emp.getRoles();
		//保存员工时将信息保存到员工角色的中间表中
		for (Role role : roles) {
			empMapper.saveRelation(emp.getId(),role.getId());
		}
	}

	public Employee get(long id) {
		return empMapper.get(id);
	}

	public List<Employee> listAll() {
		return empMapper.listAll();
	}

	public List<Employee> query(EmployeeQueryObject qo) {
		return empMapper.queryForList(qo);
	}

	public PageResult queryList(EmployeeQueryObject qo) {
		int totalCount = empMapper.queryForCount(qo);
		if(totalCount == 0){
			return new PageResult().emptyResult(qo.getPageSize());
		}
		List<Employee> listData = empMapper.queryForList(qo);
		return new PageResult(listData, totalCount, qo.getPageSize(), qo.getCurrentPage());
	}
	
	//登录校验
	public Employee checkLogin(String username, String password) {
		Employee e =  empMapper.checkLogin(username,password);
		//定义set集合存储用户所对应的权限信息
		Set<String> expressions = new HashSet<>();
		//当用户登录成功时,取出当前用户所拥有的权限信息
		if(e != null){
			//取出用户所对应的角色信息
			List<Role> roles = e.getRoles();
			for (Role role : roles) {
				//通过角色取出权限信息
				List<Permission> permissions = role.getPermissions();
				for (Permission permission : permissions) {
					expressions.add(permission.getExpression());
				}
			}
			//将用户所拥有的权限信息设置到session中3
			ActionContext.getContext().getSession().put("EXPRESSIONS_IN_SESSION", expressions);
		}
		return e;
	}
}
