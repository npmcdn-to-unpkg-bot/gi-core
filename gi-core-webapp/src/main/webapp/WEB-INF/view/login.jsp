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

<title>Galaxy Internet Login</title>

<!-- Bootstrap core CSS -->
<link href="plugins/bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="plugins/menu/css/jquery-accordion-menu.css" rel="stylesheet" type="text/css" />
<link href="plugins/menu/css/font-awesome.css" rel="stylesheet" type="text/css" />

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
<script src="script/jsencrypt.min.js"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
</script>
<script src="script/common.js"></script>
<script type="text/javascript">
$(function(){
	$('#signBtn').click(function(e){
		e.preventDefault();
        var encrypt = new JSEncrypt();
        encrypt.setPublicKey("${publicKey}");
        var encrypted = encrypt.encrypt($('input[name="password_t"]').val());
        
        $('input[name="password_t"]').val('');
        $('input[name="password"]').val(encrypted);
        var data = $("#loginForm").serializeObject();
        $.post(
        	"login/auth",
        	$("#loginForm").serialize(),
        	function(data){
        		console.log(data);
        	}
        );
       // $("#loginForm")[0].submit();
	});
});	
</script>
</head>
<body>
	<div class="container">
		<form id="loginForm" class="form-signin" action="login/auth" method="post">
			<input name="password" type="hidden">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label class="sr-only" for="username">User name</label> 
			<input type="user" autofocus="" required="" placeholder="Email address" class="form-control" id="username" name="username"> 
			<label class="sr-only" for="password">Password</label> 
			<input type="password" required="" placeholder="Password" class="form-control" name="password_t">
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me"> Remember me</label>
			</div>
			<button id="signBtn" class="btn btn-lg btn-primary btn-block">Sign in</button>
		</form>

	</div>

</body>
</html>
