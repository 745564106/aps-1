package com._520it.rbac.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com._520it.rbac.domain.Employee;
import com._520it.rbac.query.EmployeeQueryObject;

public interface EmployeeMapper {
	
	void save(Employee emp);
	
	void delete(long id);
	
	void update(Employee emp);
	
	Employee get(long id);
	
	List<Employee> listAll();
	//高级查询
	List<Employee> queryForList(EmployeeQueryObject qo);
	
	//查询过滤后的总记录数
	int queryForCount(EmployeeQueryObject qo);
	/**
	 * 将员工和角色的信息保存到中间表中
	 * @param empId 	员工id
	 * @param roleId	角色id
	 */
	void saveRelation(@Param("empId")Long empId, @Param("roleId")Long roleId);
	/**
	 * 根据员工id删除员工和角色表中的对应数据
	 * @param empId		员工id
	 */
	void deleteRelation(Long empId);
	/**
	 * 登录校验
	 * @param username	用户名
	 * @param password	密码
	 * @return
	 */
	Employee checkLogin(@Param("username")String username, @Param("password")String password);
}
