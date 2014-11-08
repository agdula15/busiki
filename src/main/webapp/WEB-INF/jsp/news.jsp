<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" /> -->
<!-- <script src="resources/js/bootstrap.min.js"></script> -->
<!-- <link href="resources/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" /> -->
<!-- <script src="resources/js/bootstrap-dialog.min.js"></script> -->

<!-- POPUP DEFINITION -->
<div class="modal fade" id="createNewsModal" tabindex="-1" role="dialog"
	aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">Tworzenie newsa</h4>
			</div>
			<div class="modal-body">

<!-- 				<form name="newsCreation" action="newsCreation" method="POST"> -->
					<form name="newsCreation" action="news" method="POST">
					<div class="form-group">
						<label>Tytuł: </label> <input class="form-control" type='text'
							name='tytul' />
					</div>
					<div class="form-group">
						<label>Treść: </label>
						<textarea class="form-control" rows="3" name="tresc" maxlength="900"></textarea> 
					</div>
					<div style="width: 200px; margin: auto;">
						<button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
						<label>&nbsp;</label> <input type="submit" value="OK"
							class="btn btn-primary">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Aktualności</h1>
	</div>
</div>

<a class="btn btn-lg btn-primary" data-toggle="modal"
	data-target="#createNewsModal">Utwórz</a>



<br>
<br>
<div class="row">
	<c:forEach var="news" items="${newsy}">
		<div class="col-lg-4">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<c:out value="${news.tytul }" /> 
				</div>
				<div class="panel-body">
					<p>
						<c:out value="${news.tresc }" />
					</p>
				</div>
				<div class="panel-footer">
					<a href="<spring:url value="/news/edit/${news.id}" />" class="btn btn-warning"
						type="button">Edytuj</a> 
					<a href="<spring:url value="/news/delete/${news.id}" />"
						class="btn btn-danger" type="button">Usuń</a>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
