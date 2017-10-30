package com._520it.rbac.web.action;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com._520it.rbac.domain.Department;
import com._520it.rbac.domain.RequiredPermission;
import com._520it.rbac.service.IDepartmentService;

public class DepartmentAction extends BaseAction{

	private static final long serialVersionUID = -4728737738314996773L;
	//引入service
	@Setter
	private IDepartmentService deptService;
	//封装参数
	@Getter
	private Department department = new Department();
	
	@RequiredPermission("部门列表")
	public String execute() throws Exception {
		List<Department> list = deptService.listAll();
		putContext("list", list);
		return LIST;
	}
	@RequiredPermission("部门删除")
	public String delete(){
		if(department.getId() != null){
			deptService.delete(department.getId());
		}
		return SUCCESS;
	}
	//跳转页面
	@RequiredPermission("部门编辑")
	public String input(){
		if(department.getId() != null){
			department = deptService.get(department.getId());
		}
		return INPUT;
	}
	@RequiredPermission("部门保存或者更新")
	public String saveOrUpdate(){
		if(department.getId() != null){
			deptService.update(department);
		}else{
			deptService.save(department);
		}
		return SUCCESS;
	}
}
