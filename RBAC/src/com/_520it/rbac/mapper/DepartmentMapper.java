package com._520it.rbac.mapper;

import java.util.List;

import com._520it.rbac.domain.Department;

public interface DepartmentMapper {

	void save(Department dept);

	void delete(long id);

	void update(Department dept);

	Department get(long id);

	List<Department> listAll();
}
