package com._520it.rbac.query;

import lombok.Getter;
import lombok.Setter;

//查询
@Setter@Getter
public class EmployeeQueryObject extends QueryObject{
	private String keyword;
	private Long deptId;
}
