<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Busy</h1>
	</div>
</div>

<a class="btn btn-primary add" id="">Dodaj</a>
<br></br>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<!-- 		  	<div class="panel-heading">UÃÂ¼ytkownicy</div>  -->
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th>ID</th>
								<th>Marka</th>
								<th>Miejsca siędzące</th>
								<th>Miejsca stojące</th>
								<th>Opis</th>
								<th>Opcje</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${bus}" var="b">
								<tr class="even gradeC" id="${b.id}">
									<td class="id">${b.id}</td>
									<td class="nazwa">${b.nazwa}</td>
									<td class="siedzace">${b.miejscaSiedzace}</td>
									<td class="stojace">${b.miejscaStojace}</td>
									<td class="opis">${b.opis}</td>
									<td id="opcje"><a
										class="btn-sm btn-warning btn-primary edit" id="">Edytuj</a><a
										href="<spring:url value="/bus/delete/${b.id}" />"
										class="btn-sm btn-danger">Usuń</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="modal fade" id="addorupdate" tabindex="-1" role="dialog"
	aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 title="ad" class="modal-title" id="myModalLabel"></h4>
			</div>
			<div class="modal-body">
				<form:form method="POST" action="bus/addorupdate"
					commandName="bus">
					<label class="readOnlyId">Id: </label>
					<div class="form-group readOnlyId">
						<input id="readOnlyId" class="form-control" type='text' name='id'
							readonly />
					</div>
					<div class="form-group">
						<label>Nazwa: </label> <input class="form-control" type='text' name="nazwa" ></input>
					</div>
					<div class="form-group">
						<label>Miejsca siedzące: </label> <input class="form-control" type='text' name="miejscaSiedzace" ></input>
					</div>
					<div class="form-group">
						<label>Miejsca stojące: </label> <input class="form-control" type='text' name="miejscaStojace" ></input>
					</div>
					<div class="form-group">
						<label>Opis: </label> <input class="form-control" type='text'
							name='opis' />
					</div>
					<div style="width: 200px; margin: auto;">
						<button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
						<label>&nbsp;</label> <input type="submit" value="OK"
							class="btn btn-primary">
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		$('.edit').click(function() {
			$('h4').text('Edytuj');
			$('#readOnlyId').val(
					$(this).closest('.panel').find('.id').text());
			$('#addorupdate').modal('show');
		});
		$('.add').click(function() {
			$('h4').text('Dodaj');
			$( ".readOnlyId" ).remove();
			$('#addorupdate').modal('show');
		});

	});
</script>