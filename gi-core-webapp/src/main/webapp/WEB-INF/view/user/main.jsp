<%@ page language="java" pageEncoding="UTF-8"%>
<h3 class="page-header">用户管理</h3>
<div class="pull-left">
	<button type="button" id="add-btn" class="btn btn-info">添加</button>
</div>
<form id="user-query-form">
	
</form>
</div>
<table id="user-table" 
	data-toggle="table" 
	data-url="user/select" 
	data-method="post" 
	data-query-params="userQueryParams"
	data-sort-name="user_name" 
	data-response-handler="responseHandler" 
	data-pagination="true" >
	<thead>
		<tr>
			<th data-field="userName">姓名</th>
			<th data-field="userLogin">登录名</th>
			<th data-field="email">邮箱</th>
			<th data-field="sex">性别</th>
			<th data-field="createdTime">创建时间</th>
		</tr>
	</thead>
</table>
<script>
function responseHandler(data)
{
	return data.page.list;
}
function userQueryParams(params)
{
	var query = $("#user-query-form").serializeObject();
	var data = $.parseJSON(query);
	$.extend(params,data);
	return params;
}
$("#user-table").bootstrapTable();
</script>