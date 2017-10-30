package com._520it.rbac.web.action;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com._520it.rbac.domain.Department;
import com._520it.rbac.domain.Employee;
import com._520it.rbac.domain.RequiredPermission;
import com._520it.rbac.domain.Role;
import com._520it.rbac.query.EmployeeQueryObject;
import com._520it.rbac.query.PageResult;
import com._520it.rbac.service.IDepartmentService;
import com._520it.rbac.service.IEmployeeService;
import com._520it.rbac.service.IRoleService;
import com.opensymphony.xwork2.ActionContext;

public class EmployeeAction extends BaseAction{

	private static final long serialVersionUID = -5599265026860755709L;
	//引入service
	@Setter
	private IEmployeeService empService;
	@Setter
	private IDepartmentService deptService;
	@Setter
	private IRoleService roleService;
	@Getter
	private EmployeeQueryObject qo = new EmployeeQueryObject();
	@Getter
	private Employee employee = new Employee();
	
	@RequiredPermission("员工列表")
	public String execute() throws Exception {
		//查询部门信息
		List<Department> depts = deptService.listAll();
		putContext("depts", depts);
		PageResult result = empService.queryList(qo);
		ActionContext.getContext().put("result", result);
		return LIST;
	}
	@RequiredPermission("员工删除")
	public String delete(){
		empService.delete(employee.getId());
		return SUCCESS;
	}
	//跳转页面
	@RequiredPermission("员工编辑")
	public String input(){
		List<Department> depts = deptService.listAll();
		putContext("depts", depts);
		List<Role> roles = roleService.listAll();
		putContext("roles", roles);
		if(employee.getId() != null){
			employee = empService.get(employee.getId());
		}
		return INPUT;
	}
	@RequiredPermission("员工保存或者更新")
	public String saveOrUpdate(){
		if(employee.getId() != null){
			empService.update(employee);
		}else {
			empService.save(employee);
		}
		return SUCCESS;
	}
}
