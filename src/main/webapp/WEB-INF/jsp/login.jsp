<%-- 
    Document   : login
    Created on : 2014-10-01, 15:44:22
    Author     : Artur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="postLoginUrl" value="/j_spring_security_check" />
<style>
.form-horizontal {
	max-width: 550px;
	padding: 15px;
	margin: 0 auto;
	margin-top: 5%;
}

.form-horizontal .form-group, .form-horizontal .checkbox {
	margin-bottom: 10px;
}

.form-horizontal .checkbox {
	font-weight: normal;
}

.form-horizontal .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-horizontal .form-control:focus {
	z-index: 2;
}

.form-horizontal input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-horizontal input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>


<form class="form-horizontal" action="${postLoginUrl}" method="post">

	<div class="form-group">

		<label for="inputEmail" class="control-label col-xs-2">Email</label>

		<div class="col-xs-10">

			<input type="text" name="j_username" class="form-control" required autofocus>

		</div>

	</div>

	<div class="form-group">

		<label for="inputPassword" class="control-label col-xs-2">Hasło</label>

		<div class="col-xs-10">

			<input type="password" class="form-control" placeholder="Password"
				name="j_password" required>

		</div>

	</div>

	<div class="form-group">

		<div class="col-xs-offset-2 col-xs-10">

			<button type="submit" class="btn btn-primary">Login</button>

		</div>

	</div>

</form>


