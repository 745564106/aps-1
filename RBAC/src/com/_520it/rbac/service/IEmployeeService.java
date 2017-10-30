package com._520it.rbac.service;

import java.util.List;

import com._520it.rbac.domain.Employee;
import com._520it.rbac.query.EmployeeQueryObject;
import com._520it.rbac.query.PageResult;

public interface IEmployeeService {
	
	void save(Employee emp);

	void delete(long id);

	void update(Employee emp);

	Employee get(long id);

	List<Employee> listAll();
	
	//高级查询
	List<Employee> query(EmployeeQueryObject qo);
	
	//高级查询+分页
	PageResult queryList(EmployeeQueryObject qo);
	/**
	 * 登录校验
	 * @param username	用户名
	 * @param password	密码
	 * @return
	 */
	Employee checkLogin(String username, String password);
}
