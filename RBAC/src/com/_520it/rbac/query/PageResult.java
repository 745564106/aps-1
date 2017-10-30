package com._520it.rbac.query;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter@Setter
public class PageResult {
	private List<?> listData;
	private Integer totalCount;
	private Integer pageSize;		//用户传入
	private Integer currentPage;	//用户传入
	//后台计算
	private Integer prevPage;
	private Integer nextPage;
	private Integer totalPage;
	
	public PageResult() {
		
	}

	public PageResult(List<?> listData, Integer totalCount, Integer pageSize,
			Integer currentPage) {
		
		this.listData = listData;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		
		this.totalPage = this.totalCount % this.pageSize == 0 ? 
				this.totalCount / this.pageSize : this.totalCount / this.pageSize +1;
		this.prevPage = this.currentPage - 1 >= 1 ? this.currentPage - 1 : 1;
		this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1 : this.totalPage;
	}
	
	//获取一个空的结果对象
	public PageResult emptyResult(int pageSize){
		return new PageResult(Collections.EMPTY_LIST, 0, pageSize, 1);
	}

}
