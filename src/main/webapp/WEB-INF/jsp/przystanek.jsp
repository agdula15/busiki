<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Przystanki</h1>
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
								<td>Numer</td>
								<th>Nazwa</th>
								<th>Opcje</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${przystanki}" var="p">
								<tr class="even gradeC" id="${p.id}">
									<td class="id">${p.id}</td>
									<td class="numer">${p.numer}</td>
									<td class="nazwa">${p.nazwa}</td>
									<td id="opcje"><a
										class="btn-sm btn-warning btn-primary edit" id="">Edytuj</a><a
										href="<spring:url value="/przystanek/delete/${p.id}" />"
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
				<form:form method="POST" action="przystanek/addorupdate"
					commandName="przystanek">
					<label class="readOnlyId">Id: </label>
					<div class="form-group readOnlyId">
						<input id="readOnlyId" class="form-control" type='text' name='id'
							readonly />
					</div>
					<div class="form-group">
						<label>Numer: </label> <input class="form-control" type='text'
							name='numer' />
					</div>
					<div class="form-group">
						<label>Nazwa: </label> <input class="form-control" type='text' name="nazwa" ></input>
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
					$(this).closest('tr').find('.id').text());
			$('#addorupdate').modal('show');
		});
		$('.add').click(function() {
			$('h4').text('Dodaj');
			$( ".readOnlyId" ).remove();
			$('#addorupdate').modal('show');
		});

	});
</script>