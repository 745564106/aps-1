package com._520it.rbac.service;

import java.util.List;

import com._520it.rbac.domain.Role;
import com._520it.rbac.query.PageResult;
import com._520it.rbac.query.QueryObject;

public interface IRoleService {

	void save(Role role);

	void delete(long id);

	void update(Role role);

	Role get(long id);

	List<Role> listAll();

	// 高级查询 + 分页
	PageResult query(QueryObject qo);
}
