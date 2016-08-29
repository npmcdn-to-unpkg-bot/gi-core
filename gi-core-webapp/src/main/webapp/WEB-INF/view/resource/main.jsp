<%@ page language="java" pageEncoding="UTF-8"%>
<h3 class="page-header">资源管理</h3>
<div class="col-sm-2">
	<h4 class="page-header">所有资源</h4>
	<div id="resource-tree" class="ztree"></div>
</div>
<div class="col-sm-10">
	<div id="resource-content">
		<h4 class="page-header">资源详细</h4>
		<div id="resource-detail-div">
			<div class="pull-right">
				<button type="button" id="edit-btn" class="btn btn-info">编辑</button>
				<button type="button" id="add-btn" class="btn btn-info">添加</button>
				<button type="button" id="del-btn" class="btn btn-info">删除</button>
			</div>
			<div class="form-group">
				<label class="control-label">资源编码</label><p id="resourceCode" class="form-control-static"></p>
			</div>
			<div class="form-group">
				<label class="control-label">资源名称</label><p id="resourceName" class="form-control-static"></p>
			</div>
			<div class="form-group">
				<label class="control-label">资源图标</label><p id="resourceIcon" class="form-control-static"></p>
			</div>
			<div class="form-group">
				<label class="control-label">资源类型</label><p id="resourceType" class="form-control-static"></p>
			</div>
			<div class="form-group">
				<label class="control-label">资源URL</label><p id="resourceUrl" class="form-control-static"></p>
			</div>
			
		</div>
		<div class="hidden" id="resource-form-div">
			<div class="pull-right">
				<button type="button" id="save-btn" class="btn btn-info">保存</button>
				<button type="button" id="cancel-btn" class="btn btn-info">取消</button>
			</div>
			<form action="#" id="resource-form">
			<input name="id" type="hidden">
			<input name="parentId" type="hidden">
			<div class="form-group">
				<label for="resourceCode">资源编码</label>
				<input type="text" class="form-control" id="resourceCode" name="resourceCode"/>
			</div>
			<div class="form-group">
				<label for="resourceName">资源名称</label>
				<input type="text" class="form-control" id="resourceName" name="resourceName"/>
			</div>
			<div class="form-group">
				<label for="resourceIcon">资源图标</label>
				<input type="text" class="form-control" id="resourceIcon" name="resourceIcon"/>
			</div>
			<div class="form-group">
				<label for="resourceUrl">资源类型</label>
				<select id="resourceType" name="resourceType" class="form-control">
					<option value=""></option>
					<option value="resourceType:1">菜单</option>
					<option value="resourceType:2">操作</option>
				</select>
			</div>
			<div class="form-group">
				<label for="resourceUrl">资源URL</label>
				<input type="text" class="form-control" id="resourcUrl" name="resourceUrl"/>
			</div>
			</form>
		</div>
	</div>
</div>
<script>
var treeLoadDtd;
var settings = {
	async : {
		enable:true,
		url:'resource/getTree'
	},
	data : {
		key : {
			name : 'resourceName'
		}
	},
	view: {
		nameIsHTML: true,
		showIcon : false,
		addDiyDom : function(treeId, treeNode) {
			if(treeNode.resourceIcon != null)
			{
				var aObj = $("#" + treeNode.tId + "_span");
				aObj.prepend(treeNode.resourceIcon);
			}
		}
	},
	callback:{
		onClick : function (event,treeId,node,flag){
			//显示详细信息
			$("#resource-content #resource-detail-div").show();
			$("#resource-content #resource-form-div").addClass('hidden');
			setContent(node);
			setForm(node);
		},
		onAsyncSuccess : function(){
			if(typeof(treeLoadDtd) != 'undefined')
			{
				treeLoadDtd.resolve();
			}
		}
	}
	
};

function setContent(node)
{
	var $detail = $("#resource-detail-div .form-control-static");
	$.each($("#resource-detail-div .form-control-static"),function(){
		var id = $(this).attr('id');
		$(this).empty().text(node[id]);
	});
}
function setForm(node)
{
	var $form = $("#resource-form");
	var fields = $form.serializeArray();
	$.each(fields,function(){
		$form.find('[name="'+this.name+'"]').val(node[this.name]);
	});
}
function getSelectNode()
{
	var nodes = resourceTree.getSelectedNodes();
	if(nodes.length > 0 )
	{
		return nodes[0];
	}
	return null;
}
function showContent()
{
	$("#resource-content #resource-detail-div").show();
	$("#resource-content #resource-form-div").addClass('hidden');
}
function showForm()
{
	$("#resource-content #resource-detail-div").hide();
	$("#resource-content #resource-form-div").removeClass('hidden');
}

var resourceTree;
$(function(){
	resourceTree = $.fn.zTree.init($('#resource-tree'),settings);
	//编辑
	$("#resource-content #edit-btn").click(function(){
		var node = getSelectNode();
		if(node == null)
		{
			alert("请选择资源。");
			return;
		}
		showForm();
	});
	//取消
	$("#resource-content #cancel-btn").click(function(){
		showContent();
	});
	//添加
	$("#resource-content #add-btn").click(function(){
		var node = getSelectNode();
		if(node == null)
		{
			alert("请选择资源。");
			return;
		}
		$("#resource-form")[0].reset();
		$("#resource-form [name='id']").val('0');
		$("#resource-form [name='parentId']").val(node.id);
		showForm();
	});
	//删除
	$("#resource-content #del-btn").click(function(){
		var node = getSelectNode();
		if(node == null)
		{
			alert("请选择资源。");
			return;
		}
		if(node.children != null)
		{
			alert('请删先删除子节点。');
			return;
		}
		var parentId = node.parentId;
		$.postJSON(
			'resource/delete/'+node.id,
			null,
			function(data){
				if(data.status == 'OK')
				{
					treeLoadDtd = $.Deferred();
					resourceTree.reAsyncChildNodes(null, "refresh");
					$.when(treeLoadDtd).done(function(){
						var nodes = resourceTree.getNodes();
						if(parentId > 0)
						{
							nodes = resourceTree.getNodesByParam('id',parentId);
						}
						resourceTree.selectNode(nodes[0]);
						setContent(nodes[0]);
						showContent();
					});
				}
			}
		);
	});
	//保存
	$("#resource-content #save-btn").click(function(){
		var data = $("#resource-form").serializeObject();
		var json = $.parseJSON(data);
		var action = json.id == '0' ? 'add' : 'edit';
		var url = "resource/"+action+"?_="+new Date().getTime();
		$.postJSON(
			url,
			data,
			function(data)
			{
				
				if(data.status== 'OK')
				{
					treeLoadDtd = $.Deferred();
					resourceTree.reAsyncChildNodes(null, "refresh");
					$.when(treeLoadDtd).done(function(){
						var nodes = resourceTree.getNodesByParam('id',data.entity.id);
						resourceTree.selectNode(nodes[0]);
						setContent(nodes[0]);
						showContent();
					});
				}
			}
		);
	});
});
	
</script>