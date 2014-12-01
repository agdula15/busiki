<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link href="resources/css/bootstrap-dialog.css" rel="stylesheet">
<link href="resources/css/datepicker.css" rel="stylesheet">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Rozkład jazdy</h1>
	</div>
</div>
<h4>Tworzenie:</h4>
<form id="form1" action="dodajRozklad" method="POST">
	<div class="form-group">
		<div class="row">
			<div class="col-lg-5">
				<input class="form-control" id="dp1" type='date' name='dp1'
					placeholder="Początek:" />
			</div>
			<div class="col-lg-5">
				<input class="form-control" id="dp2" type='date' name="dp2"
					placeholder="Koniec:">
			</div>
			<div class="col-lg-1">
				<button type="button" class="btn btn-primary"
					id="dodajRozkladButton">Dodaj</button>
			</div>
		</div>
	</div>
</form>

<h4 class="page-header">Istniejące:</h4>
<br>
<br>
<div class="row">
	<c:forEach var="r" items="${rozklady}">
		<div class="col-lg-4">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<p class="newId">
						<c:out value="${r.id}" />
					</p>
				</div>
				<div class="panel-body">
					<p> Od:
<%-- 						<fmt:formatDate pattern="yyyy-MM-dd" value="${r.dataOd}" /> --%>
<fmt:formatDate pattern="yyyy-MM-dd" value="${r.dataOd}" />
					</p>
					<p> Do:
<%-- 						<fmt:formatDate pattern="yyyy-MM-dd" value="${r.dataDo}" /> --%>
<fmt:formatDate pattern="yyyy-MM-dd" value="${r.dataDo}" />
					</p>
						<a href="<spring:url value="scheduleEdit?rid=${r.id}" />" class="btn btn-primary">Uzupełniaj</a> <a
							href="<spring:url value="schedule/delete/${r.id}" />"
							class="btn btn-danger" type="button">Usuń</a>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<!-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% -->

<script src="resources/js/bootstrap-dialog.js"></script>
<script type="text/javascript"
	src="resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="resources/js/bootstrap-datepicker.pl.js"></script>
<script src="resources/js/date-pl-PL.js"></script>

<script>
	$(function() {
		$('#dp1').datepicker({
			format : "yyyy-mm-dd",
			language : "pl"
		});
	});
	$(function() {
		$('#dp2').datepicker({
			format : "yyyy-mm-dd",
			language : "pl"
		});
	});

	$(function() { //cos jest zle - sprobuj wklepac date rozpoczecia 12-11-2014 i konca 22-11-2014
		$('#dodajRozkladButton').click(function() { //sprawdza czy data końca rozkladu jest wieksza niz poczatku rozkladu, jesli tak to przekierowuje
			var start = Date.parse($("#dp1").val());
			var end = Date.parse($("#dp2").val());
			if (start == null || end == null) {
				BootstrapDialog.show({
					title : 'Błąd!',
					message : 'Uzupełnij pola dat!'
				});
			} else if (start > end) {
				BootstrapDialog.show({
					title : 'Błąd!',
					message : 'Data rozpoczęcia jest większa od daty końca!'
				});
			} else {
				$("#form1").submit();
			}
		});
	});
</script>