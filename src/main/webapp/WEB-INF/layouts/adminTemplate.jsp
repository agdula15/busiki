<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Panel Administratora</title>

<!-- Bootstrap Core CSS -->
<link href="resources/admin/css/bootstrap.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="resources/admin/css/plugins/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- DataTables CSS -->
<link href="resources/admin/css/plugins/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/admin/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="resources/admin/font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


<!-- jQuery -->
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>


<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="resources/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="resources/admin/js/plugins/metisMenu/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script src="resources/admin/js/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="resources/admin/js/plugins/dataTables/dataTables.bootstrap.js"></script>

<!-- Custom Theme JavaScript -->
<script src="resources/admin/js/sb-admin-2.js"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
	$(document).ready(function() {
		$('#dataTables-example').dataTable();
	});
</script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- Taglibs -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- Taglibs -->


</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">Admin 1.0</a>
			</div>
			<!-- /.navbar-header -->


			<ul class="nav navbar-top-links navbar-right">
				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a> <c:url var="logoutUrl" value="/j_spring_security_logout" />
					<ul class="dropdown-menu dropdown-user">
						<li><a href="#"><i class="fa fa-gear fa-fw"></i>
								Ustawienia</a></li>
						<li class="divider"></li>
						<li><a href="${logoutUrl}"><i
								class="fa fa-sign-out fa-fw"></i> Wyloguj</a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="admin"><i class="fa fa-dashboard fa-fw"></i>Strona
								główna</a></li>
						<li><a href="news"> <i class="fa fa-comment fa-fw"></i>
								Aktualności
						</a></li>
						<li><a href="users"><i class="fa fa-user fa-fw"></i>
								Użytkownicy</a></li>
						<li><a href="promocje"> <i
								class="fa fa-shopping-cart fa-fw"></i> Promocje dla klientów
						</a></li>
						<li><a href="bus"> <i class="fa fa-table fa-fw"></i> Busy
						</a></li>
						<li><a href="przystanek"> <i class="fa fa-table fa-fw"></i>
								Przystanki
						</a></li>
						<li><a href="trasa"> <i class="fa fa-table fa-fw"></i>
								Trasy
						</a></li>
						<li><a href="schedule"> <i class="fa fa-table fa-fw"></i> Rozkład</a>
						</li>
						<li><a href="index"><i class="fa fa-sign-out fa-fw"></i>
								Powrót na stronę</a></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper">
			<div>
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
</body>
</html>
