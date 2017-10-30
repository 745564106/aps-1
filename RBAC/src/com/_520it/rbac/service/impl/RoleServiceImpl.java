package com._520it.rbac.service.impl;

import java.util.List;

import lombok.Setter;

import com._520it.rbac.domain.Permission;
import com._520it.rbac.domain.Role;
import com._520it.rbac.mapper.RoleMapper;
import com._520it.rbac.query.PageResult;
import com._520it.rbac.query.QueryObject;
import com._520it.rbac.service.IRoleService;

public class RoleServiceImpl implements IRoleService{
	//引入RoleMapper
	@Setter
	private RoleMapper roleMapper;
	
	public void save(Role role) {
		//保存角色中的name,sn
		roleMapper.save(role);
		//通过角色获取权限信息
		List<Permission> permissions = role.getPermissions();
		//保存角色信息时同时更新角色、权限的中间表的信息:一个角色中可以拥有多个权限
		for (Permission permission : permissions) {
			roleMapper.saveRelation(role.getId(),permission.getId());
		}
	}

	public void delete(long id) {
		//1.删除角色表中的数据时,先解除角色权限表中的关系
		roleMapper.deleteRelation(id);
		//2.再根据id删除角色表中的数据
		roleMapper.delete(id);
	}

	public void update(Role role) {
		//更新角色表中的基本数据（name/sn）
		roleMapper.update(role);
		//修改角色所对应的权限
		//先删除相应角色和权限的对应关系
		roleMapper.deleteRelation(role.getId());
		//在保存角色和权限
		//1.通过角色获取权限信息
		List<Permission> permissions = role.getPermissions();
		//2.保存角色信息时同时更新角色、权限的中间表的信息:一个角色中可以拥有多个权限
		for (Permission permission : permissions) {
			roleMapper.saveRelation(role.getId(), permission.getId());
		}
	}

	public Role get(long id) {
		return roleMapper.get(id);
	}

	public List<Role> listAll() {
		return roleMapper.listAll();
	}

	public PageResult query(QueryObject qo) {
		int totalCount = roleMapper.queryForCount(qo);
		if(totalCount == 0){
			return new PageResult().emptyResult(qo.getPageSize());
		}
		List<Role> listData = roleMapper.queryForList(qo);
		return new PageResult(listData, totalCount, qo.getPageSize(), qo.getCurrentPage());
	}
}
