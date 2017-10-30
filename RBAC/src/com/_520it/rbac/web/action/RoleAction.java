package com._520it.rbac.web.action;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com._520it.rbac.domain.Permission;
import com._520it.rbac.domain.RequiredPermission;
import com._520it.rbac.domain.Role;
import com._520it.rbac.query.EmployeeQueryObject;
import com._520it.rbac.query.PageResult;
import com._520it.rbac.service.IPermissionService;
import com._520it.rbac.service.IRoleService;
import com.opensymphony.xwork2.ActionContext;

public class RoleAction extends BaseAction{

	private static final long serialVersionUID = -5599265026860755709L;
	//引入service
	@Setter
	private IRoleService roleService;
	@Setter
	private IPermissionService permissionService;
	@Getter
	private EmployeeQueryObject qo = new EmployeeQueryObject();
	@Getter
	private Role role = new Role();
	
	@RequiredPermission("角色列表")
	public String execute() throws Exception {
		PageResult result = roleService.query(qo);
		ActionContext.getContext().put("result", result);
		return LIST;
	}
	@RequiredPermission("角色删除")
	public String delete(){
		roleService.delete(role.getId());
		return SUCCESS;
	}
	//跳转页面
	@RequiredPermission("角色编辑")
	public String input(){
		//查询所有的权限信息
		List<Permission> permissions = permissionService.getAll();
		putContext("permissions", permissions);
		if(role.getId() != null){
			role = roleService.get(role.getId());
		}
		return INPUT;
	}
	@RequiredPermission("角色保存或者更新")
	public String saveOrUpdate(){
		if(role.getId() != null){
			roleService.update(role);
		}else{
			roleService.save(role);
		}
		return SUCCESS;
	}
}
