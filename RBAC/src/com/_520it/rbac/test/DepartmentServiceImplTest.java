package com._520it.rbac.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com._520it.rbac.domain.Department;
import com._520it.rbac.service.IDepartmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceImplTest {

	@Autowired
	private IDepartmentService service;

	@Test
	public void testListAll() {
		List<Department> list = service.listAll();
		for (Department department : list) {
			System.out.println(department);
		}
	}

}
