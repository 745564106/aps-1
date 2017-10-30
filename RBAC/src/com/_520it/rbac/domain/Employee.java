package com._520it.rbac.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Employee extends BaseDomain{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String password;
	private String email;
	private Integer age;
	private boolean admin;
	private Department dept;
	private List<Role> roles = new ArrayList<>();
	@Override
	public String toString() {
		return "Employee [name=" + name + ", password="
				+ password + ", email=" + email + ", age=" + age + ", admin="
				+ admin + "]";
	}
}
