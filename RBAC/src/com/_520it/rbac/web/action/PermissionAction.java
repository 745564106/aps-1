package com._520it.rbac.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.rbac.domain.Permission;
import com._520it.rbac.query.EmployeeQueryObject;
import com._520it.rbac.query.PageResult;
import com._520it.rbac.service.IPermissionService;

public class PermissionAction extends BaseAction{

	private static final long serialVersionUID = -5599265026860755709L;
	@Setter
	private IPermissionService permissionService;
	@Setter
	private Permission permission = new Permission();
	@Getter
	private EmployeeQueryObject qo = new EmployeeQueryObject();
	
	public String execute() throws Exception {
		PageResult result = permissionService.query(qo);
		putContext("result", result);
		return LIST;
	}
	
	public String delete(){
		if(permission.getId() != null){
			permissionService.delete(permission.getId());
		}
		return SUCCESS;
	}
	
	public String reload(){
		permissionService.reload();
		return NONE;
	}
}
