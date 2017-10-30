package com._520it.rbac.mapper;

import java.util.List;

import com._520it.rbac.domain.Permission;
import com._520it.rbac.query.QueryObject;

public interface PermissionMapper {
	
	void delete(Long id);
	
	void save(Permission permission);
	
	List<Permission> getAll();
	
	int queryForCount(QueryObject qo);

	List<Permission> queryForList(QueryObject qo);
}






