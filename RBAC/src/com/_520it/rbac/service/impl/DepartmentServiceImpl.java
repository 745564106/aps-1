package com._520it.rbac.service.impl;

import java.util.List;

import lombok.Setter;

import com._520it.rbac.domain.Department;
import com._520it.rbac.mapper.DepartmentMapper;
import com._520it.rbac.service.IDepartmentService;

public class DepartmentServiceImpl implements IDepartmentService {

	@Setter
	private DepartmentMapper deptMapper;

	public void save(Department dept) {
		deptMapper.save(dept);
	}

	public void delete(long id) {
		deptMapper.delete(id);
	}

	public void update(Department dept) {
		deptMapper.update(dept);
	}

	public Department get(long id) {
		return deptMapper.get(id);
	}

	public List<Department> listAll() {
		return deptMapper.listAll();
	}
}
