package com._520it.rbac.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Role extends BaseDomain{

	private static final long serialVersionUID = -8579073329083400962L;
	
	private String name;
	private String sn;
	//保存当前角色所拥有的权限
	private List<Permission> permissions = new ArrayList<>();

	public String toString() {
		return "Role [name=" + name + ", sn=" + sn + "]";
	}
}
