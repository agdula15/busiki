<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title><tiles:getAsString name="title"></tiles:getAsString></title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>


<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="resources/css/bootstrap.css" media="screen">
<link rel="stylesheet" href="resources/css/bootswatch.min.css">
<link rel="stylesheet" href="resources/css/datepicker3.css"></link>
<link href="resources/css/bootstrap-dialog.css" rel="stylesheet">

<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.min.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script src="resources/js/bootstrap-dialog.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-datepicker.pl.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/bootswatch.js"></script>

</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="./index" class="navbar-brand">Busy</a>
				<button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse" id="navbar-main">
				<ul class="nav navbar-nav">
					<li><a href="timetable">Rozkład</a></li>
					<li><a href="contact">Kontakt</a></li>
					<li><a href="register">Rejestracja</a></li>
					<security:authorize access="(!hasRole('ROLE_USER') or hasRole('ROLE_KIEROWCA')) and isAuthenticated()">
						<li><a href="reservationForCourse">Rezerwacje Dla Kursu</a></li>
					</security:authorize>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<security:authorize access="!isAuthenticated()">
						<tiles:insertAttribute name="nav-form" />
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<c:url var="logoutUrl" value="/j_spring_security_logout" />
						<li class="dropdown"><a class="dropdown-toggle" href="#" data-toggle="dropdown"> <i class="glyphicon glyphicon-user"></i> <i class="caret"></i>
						</a>
							<ul class="dropdown-menu" aria-labelledby="account">
								<security:authorize url="/admin">
									<!-- 									<li><a href="/busiki/admin">Panel administratora</a></li> -->
									<li><a href="admin">Panel administratora</a></li>
								</security:authorize>
								<li><a href="userProfile">Profil</a></li>
								<li><a href="${logoutUrl}">Wyloguj</a></li>
							</ul></li>
					</security:authorize>
				</ul>
			</div>

		</div>
	</div>
	<div class="panel-body" style="min-height:600px;">
		<tiles:insertAttribute name="body"></tiles:insertAttribute>
	</div>
	<div class="panel-footer">
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>
</body>
</html>
