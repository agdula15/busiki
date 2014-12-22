<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-datepicker.pl.js"></script>

<div class="container">
	<div class="row">
		<div class="col-sm-12 col-lg-12">
			<div class="panel">
				<div class="panel-heading">
					<br>
					<h3>Znajdź rezerwacje dla kursu:</h3>
				</div>
				<div class="panel-body">
					<form>
						<div class="form-group">
							<div class="row">
								<div class="col-lg-2" id="divData">
									<label>Data: </label><input type="text" class="form-control" name="data" placeholder="RRRR-MM-DD" id="data" required="required">
								</div>
								<div class="col-lg-3" id="divTrasa">
									<label>Trasa: </label> <input class="form-control" name="trasa" id="trasa" placeholder="Trasa:" required="required"></input>
								</div>
								<div class="col-lg-3" id="divGodzina">
									<label>Godzina: </label> <input class="form-control"  id="godzina" placeholder="Godzina:" required="required" />
								</div>
								<div class="col-lg-1" id="divSearchButton">
									<button class="btn btn-success" id="searchButton" type="button" style="margin-top: 26px;">
										Szukaj <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
									</button>
								</div>
							</div>
						</div>
					</form>
					<div class="panel-odpowiedź">
					
					</div>
				</div>
			</div>
		</div>
	</div>
	<hr>
</div>
<script>
	$(function() {
		$("#divGodzina").hide();
		$("#divTrasa").hide();
		$("#divSearchButton").hide();
		$('#data').datepicker({
			format : "yyyy-mm-dd",
			language : "pl"
		});
		$("#data").change(function() {
			$("#divTrasa").slideDown("fast");
		});
		$("#trasa").autocomplete({
			source : function(request, response) {
				$.ajax({
					method : "POST",
					url : "reservationForCourse/autocomplete",
					dataType : "json",
					data : {
						'term1' : request.term
					},
					success : response
				});
			},
		});
		$("#trasa").blur(function() {
			
			$.ajax({
				type : "GET",
				url : "searchController/validateInputValue2",
				contentType : "charset=utf-8",
				data : {
					trasa : $(this).val()
				},
				success : function(b) {
					if (b === true) {
						$('#divGodzina').slideDown("fast");

					}
					if (b === false) {
						$('#divGodzina').slideUp("fast");
						$("#divSearchButton").slideUp("fast");
					}
					$('#godzina').val("");
				},
			});
		});
		$("#godzina").autocomplete({
			source : function(request, response) {
				$.ajax({
					method : "POST",
					url : "reservationForCourse/autocomplete2",
					dataType : "json",
					data : {
						'term1' : request.term,
						'term2' : $("#data").val(),
						'term3' : $("#trasa").val()
					},
					success : response
				});
				
			},
			minLength : -1
		});
		$("#godzina").change(function() {
			$("#divSearchButton").slideDown("fast");
		});
		$("#divSearchButton").click(function() {
			alert("Ok");
		});

	});
</script>

