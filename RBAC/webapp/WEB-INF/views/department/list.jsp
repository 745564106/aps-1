<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<script type="text/javascript" src="js/commonAll.js"></script>
<title>PSS-账户管理</title>
<style>
	.alt td{ background:black !important;}
</style>
<script type="text/javascript">
	function input(){
		window.location.href="/department_input.action"
	}
</script>
</head>
<body>
	<s:form id="searchForm" action="#" >
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							姓名/邮箱
							<input type="text" class="ui_input_txt02" name="" />
							所属部门
							<select class="ui_select01" name="">
								<option>全部</option>
								<option>总经办</option>
								<option>技术部</option>
							</select>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input"  onclick="input();"/> 
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>名称</th>
							<th>编码</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#list">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" /></td>
									<td><s:property value="id"/></td>
									<td><s:property value="name"/></td>
									<td><s:property value="sn"/></td>
									<td>
										<s:a action="department_input" namespace="/">
											<s:param name="department.id" value="id"></s:param>
											编辑
										</s:a>
										<s:a action="department_delete" namespace="/">
											<s:param name="department.id" value="id"></s:param>
											删除
										</s:a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div class="ui_tb_h30">
					<div class="ui_flt" style="height: 30px; line-height: 30px;">
						共有
						<span class="ui_txt_bold04">100</span>
						条记录，当前第
						<span class="ui_txt_bold04">1/10</span>
						页
					</div>
					<div class="ui_frt">
						<input type="button" value="首页" class="ui_input_btn01"/>
						<input type="button" value="上一页" class="ui_input_btn01"/>
						<input type="button" value="下一页" class="ui_input_btn01"/>
						<input type="button" value="尾页" class="ui_input_btn01"/>
						
						<select list="{10,20,50}" name="" class="ui_select02">
							<option>10</option>
							<option>20</option>
							<option>50</option>
						</select>
						转到第<input type="text" name="" value="" class="ui_input_txt01" />页
							 <input type="button" class="ui_input_btn01" value="跳转"/>
					</div>
				</div>
			</div>
		</div>
	</s:form>
</body>
</html>
