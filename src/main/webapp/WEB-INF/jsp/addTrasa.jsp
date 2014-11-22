<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Tworzenie trasy. Wybierz odpowiednie
			przystanki.</h1>
	</div>
</div>
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
								<tr class="even gradeC tr" id="${p.id}">
									<td class="id">${p.id}</td>
									<td class="numer">${p.numer}</td>
									<td class="nazwa">${p.nazwa}</td>
									<td id="opcje">
										<button class="btn-sm btn-outline btn-success start">Start</button>
										<button class="btn-sm btn-outline btn-primary dodaj"
											disabled="disabled">Pośredni</button>

										<button class="btn-sm btn-outline btn-warning end"
											disabled="disabled">End</button>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>

			<!-- /.panel-body -->
		</div>
		<form class="form-horizontal" action="trasaadd/add" method="POST">

			<div class="form-group ">
				<div class=" col-lg-3">
					<input name='numer' class="form-control" placeholder="Numer:" id='numer' />
				</div>
			</div>
			<div class="form-group">
				<div class="input-group col-lg-12">
					<input class="form-control trasa" type="text" id='string' name='string'
						placeholder="Trasa:" readonly></input> <span
						class="input-group-btn">
						<button class="btn btn-default remove" type="button">
							<i class="fa fa-minus"></i>
						</button>
					</span>

				</div>
			</div>
			<div class="form-group">
				<input type="submit" value="Wykonaj" class="btn btn-primary">
			</div>

		</form>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<script>
	$(document).ready(function() {
		var tr = [];
		var temp = 0;
		$('.start').click(function() {

			tr[0] = ($(this).closest('.tr').find('.nazwa').text());
			var s = "";
			for (var i = 0; i < tr.length; i = i + 1) {
				s = s + tr[i];
				if (i != (tr.length - 1)) {
					s += "-";
				}
			}
			$('.trasa').val(s);
			if (temp === 0) {
				$(".dodaj").prop("disabled", false);
				$(".end").prop("disabled", false);
			}
		});
		$('.dodaj').click(function() {
			tr.push($(this).closest('.tr').find('.nazwa').text());
			var s = "";
			for (var i = 0; i < tr.length; i = i + 1) {
				s = s + tr[i];
				s += "-";
			}
			$('.trasa').val(s);
		});
		$('.end').click(function() {
			if (temp != 0) {
				tr.pop();
			}
			tr.push($(this).closest('.tr').find('.nazwa').text());
			var s = "";
			for (var i = 0; i < tr.length; i = i + 1) {
				s = s + tr[i];
				if (i != (tr.length - 1)) {
					s += "-";
				}
			}
			$('.trasa').val(s);
			$(".dodaj").prop("disabled", true);
			temp = 1;

		});
		$('.remove').click(function() {
			tr.pop();
			$(".dodaj").prop("disabled", false);
			$(".end").prop("disabled", false);
			$(".start").prop("disabled", false);
			var s = "";
			for (var i = 0; i < tr.length; i = i + 1) {
				s = s + tr[i];
				s += "-";
			}
			if (tr.length == 0) {
				temp = 0;
				$(".dodaj").prop("disabled", true);
				$(".end").prop("disabled", true);
			}
			$('.trasa').val(s);
		});
	});
</script>
<!-- /.col-lg-12 -->