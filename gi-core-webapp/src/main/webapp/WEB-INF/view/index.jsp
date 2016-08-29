<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Dashboard Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="plugins/bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="style/dashboard.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<link href="plugins/menu/css/jquery-accordion-menu.css" rel="stylesheet" type="text/css" />
<link href="plugins/menu/css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet" type="text/css" />
<!-- 
<link href="plugins/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" >
 -->
 <link href="plugins/zTree/css/metroStyle/metroStyle.css" rel="stylesheet" type="text/css" >
<link href="style/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
</style>
<!-- Bootstrap core JavaScript
 ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<!-- 
<script src="plugins/menu/js/jquery-1.11.2.min.js"></script>
 -->
 <jsp:include page="common/common.jsp" flush="true"></jsp:include>
 <script src="script/jquery-1.11.2.js"></script>
<script>
window.jQuery
		|| document
				.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
</script>
<script src="plugins/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<script src="plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="plugins/zTree/js/jquery.ztree.all.min.js"></script>
<script src="plugins/zTree/js/jquery.ztree.exhide.min.js"></script>
<script src="plugins/menu/js/jquery-accordion-menu.js"></script>
<script src="script/common.js"></script>
<script type="text/javascript">
window.onhashchange = function()
{
	var hash = window.location.hash;
	var url = hash.replace("#","");
	if(url.length>0)
	{
		$("#main-content-div").load(url);
	}
}

$(function() {
	var url = "index/getMenus";
	$.postJSON(
		url,
		null,
		function(data){
			if(data.status == 'OK')
			{
				var menus = data.list;
				addMenus($("#demo-list"),menus);
				jQuery("#jquery-accordion-menu").jqueryAccordionMenu();
				//顶部导航切换
				$("#demo-list li").click(function() {
					$("#demo-list li.active").removeClass("active")
					$(this).addClass("active");
				});
			}
		}
	);
	
});
function addMenus(parent,menus)
{
	$.each(menus,function(index,item){
		var content = item.resourceIcon != null ? (item.resourceIcon+item.resourceName) : item.resourceName;
		var li = $('<li><a href="#'+item.resourceUrl+'">'+content+'</a></li>');
		parent.append(li);
		if(item.children != null)
		{
			var ul = $('<ul class="submenu"></ul>');
			li.append(ul);
			addMenus(ul,item.children)
		}
	});
}

</script>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Project name</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Dashboard</a></li>
					<li><a href="#">Settings</a></li>
					<li><a href="#">Profile</a></li>
					<li><a href="#">Help</a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<div id="jquery-accordion-menu" class="jquery-accordion-menu blue">
					<ul id="demo-list"></ul>
				</div>

			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" id="main-content-div">

				<jsp:include page="/resource/show"></jsp:include>
		</div>
	</div>
</body>
</html>
