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
<div class="row">
	<c:forEach var="r" items="${rozklady}">
		<div class="col-lg-4">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<p class="rId">
						<c:out value="${r.id}" />
					</p>
				</div>
				<div class="panel-body">
					<p>
						Od:
						<fmt:formatDate pattern="yyyy-MM-dd" value="${r.dataOd}" />
					</p>
					<p>
						Do:
						<fmt:formatDate pattern="yyyy-MM-dd" value="${r.dataDo}" />
					</p>
					<c:if test='${r.zatwierdzony != true}'>
						<a href="<spring:url value="scheduleEdit?rid=${r.id}" />"
							class="btn btn-primary">Uzupełniaj</a>
						<a id='zatwierdz' class="btn btn-success"> Zatwierdź </a>
					</c:if>
					<a href="<spring:url value="schedule/delete/${r.id}" />"
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
		$('#dp2').datepicker({
			format : "yyyy-mm-dd",
			language : "pl"
		});
		$('#dodajRozkladButton').click(function() {
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
		var rid;
		$(function() {
			$('#zatwierdz')
					.click(
							function() {
								rid = $(this).closest('.panel').find('.rId')
										.text();
								/* http://nakupanda.github.io/bootstrap3-dialog/ */
								BootstrapDialog
										.show({
											message : 'Może to potrwać do kilku minut, w zależności od długości rozkładu. Po tym czasie Klienci bedą mogli rezerwować miejsca w danym rozkladzie!',
											buttons : [
													{
														icon : 'glyphicon glyphicon-send',
														label : 'Zatwierdź rozkład',
														cssClass : 'btn-primary',
														autospin : true,
														action : function(
																dialogRef) {

															dialogRef
																	.enableButtons(false);
															dialogRef
																	.setClosable(false);
															dialogRef
																	.getModalBody()
																	.html(
																			rid
																					+ 'Zatwierdzanie rozkladu...Może to potrwać do kilkunastu minut, w zależności od długości rozkładu. Nie odświeżaj strony');
															setTimeout(function() {
																$
																		.ajax({
																			type : "GET",
																			url : "serviceConfigureDynamic/zatwierdz",
																			data : "rid="
																					+ rid,
																			success : function(
																					data) {
																				dialogRef
																						.close();
																				 location.reload();
																			},
																			error : function(
																					e) {
																				alert(e);

																			}
																		});
															});
														}
													},
													{
														label : 'Close',
														action : function(
																dialogRef) {
															dialogRef.close();
														}
													} ]
										});
							});
		});

	});
</script>