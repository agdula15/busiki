<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="postLoginUrl" value="/j_spring_security_check" />
<form class="navbar-form" action="${postLoginUrl}" method="post">
	<div class="form-group">
		<input class="form-control" type="text" placeholder="Email"
			name="j_username" required />
	</div>
	<div class="form-group">
		<input type="password" class="form-control" placeholder="Hasło"
				name="j_password" required>
	</div>
	<button class="btn btn-sm btn-info" type="submit">Zaloguj</button>
	
</form>