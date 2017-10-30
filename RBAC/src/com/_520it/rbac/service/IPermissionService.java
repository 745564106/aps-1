package com._520it.rbac.service;

import java.util.List;

import com._520it.rbac.domain.Permission;
import com._520it.rbac.query.PageResult;
import com._520it.rbac.query.QueryObject;

public interface IPermissionService {
	
	void delete(Long id);

	void save(Permission permission);

	List<Permission> getAll();

	PageResult query(QueryObject qo);

	void reload();
}

