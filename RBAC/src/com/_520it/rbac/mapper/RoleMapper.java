package com._520it.rbac.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com._520it.rbac.domain.Role;
import com._520it.rbac.query.QueryObject;

public interface RoleMapper {
	
	void save(Role role);
	
	void delete(long id);
	
	void update(Role role);
	
	Role get(long id);
	
	List<Role> listAll();
	
	//高级查询
	List<Role> queryForList(QueryObject qo);
	
	//查询过滤后的总记录数
	int queryForCount(QueryObject qo);
	/**
	 * 更新角色、权限的中间表
	 * @param roleId		角色id
	 * @param permissionId	权限id
	 */
	void saveRelation(@Param("roleId")Long roleId, @Param("permissionId")Long permissionId);
	/**
	 * 根据角色的id删除角色权限表中对应的数据,解除关系
	 * @param id
	 */
	void deleteRelation(Long id);
}
