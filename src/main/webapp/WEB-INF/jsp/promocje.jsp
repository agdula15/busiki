<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% MODALS %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% -->

<div class="modal fade" id="createUlga" tabindex="-1" role="dialog"
	aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">Dodaj nową ulgę:</h4>
			</div>
			<div class="modal-body">
				<form name="nowaUlgaForm" action="promocje/dodaj" method="POST">
					<div class="form-group">
						<label>Opis: </label> <input class="form-control" type='text'
							name='opis' />
					</div>
					<div class="form-group">
						<label>Wartość (podaj w postaci dziesiętnej, np. 50%=0.5
							itd.): </label> <input class="form-control" type='text' name='wartosc' />
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

<div class="modal fade" id="editUlga" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">Edycja:</h4>
			</div>
			<div class="modal-body">
				<form name="editUlgaForm" id="editUlgaForm" action="promocje/edytuj"
					method="POST">
					<div class="form-group">
						<label>ID: </label> <input class="form-control" id="id"
							type='text' name='id' readonly />
					</div>
					<div class="form-group">
						<label>Opis: </label> <input class="form-control" id="opis" type='text'
							name='opis' />
					</div>
					<div class="form-group">
						<label>Wartość: </label> <input class="form-control" id="wartosc" type='text' name='wartosc' />
					</div>
					<div style="width: 200px; margin: auto;">
						<button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
						<label>&nbsp;</label> <input type="submit" value="OK" class="btn btn-primary">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- <div id="removeUlga" class="modal fade" tabindex="-1" role="dialog"> -->
<!-- 	<div class="modal-dialog"> -->
<!-- 		<div class="modal-content"> -->
<!-- 			<div class="modal-header"> -->
<!-- 				<button type="button" class="close" data-dismiss="modal">X</button> -->
<!-- 				<h3>Potwierdź usunięcie</h3> -->
<!-- 			</div> -->
<!-- 			<div class="modal-body"> -->
<!-- 				<h4>Czy na pewno usunąć?</h4><br>			 -->
<!-- 				<form id="formEdit" action=""> -->
<!-- 					<button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button> -->
<!-- 					<a id="confirmbutton" class="btn btn-danger">Usuń</a> -->
<!-- 				</form> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->

<!-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% /MODALS %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% -->

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Ulgi:</h1>
	</div>
</div>

<a class="btn btn-lg btn-primary" data-toggle="modal"
	data-target="#createUlga">Utwórz</a>
<br>
<br>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th>ID</th>
								<th>Opis</th>
								<th>Wartość</th>
								<th>Opcje</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ulgi}" var="ulga">
								<tr class="even gradeC" id="${ulga.id}">
									<td class="id">${ulga.id}</td>
									<td class="opis">${ulga.opis}</td>
									<td class="wartosc">${ulga.wartoscUlgi * 100}%</td>
									<td id="test2">
										<a class="btn-sm btn-warning btn-primary edit">Edytuj</a>
										<a href="promocje/usun/${ulga.id}" class="btn-sm btn-danger">Usuń</a></td>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% SCRIPTS %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% -->

<script>
$(document) 
	.ready(
		function() {
			$('.edit').click(
					function() {
						$('#id').val(
								$(this).closest('tr').find('.id')
										.text());
						$('#opis').val(
								$(this).closest('tr')
										.find('.opis').text());
						$('#wartosc').val(
								$(this).closest('tr').find(
										'.wartosc').text());

						$('#editUlga').modal('show');
					});
		});
</script>
