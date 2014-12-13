<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<link rel="stylesheet" href="resources/css/bootstrap.css" media="screen">
</head>

<body>
	<br>
	<div class="container">
		<img src="resources/img/mapa2.png" alt="mapa"> <br> <br>
		<form>
			<div class="form-group">
				<div class="row">
					<div class="col-lg-2" id="divData">
						<label>Data: </label><input type="text" class="form-control"
							name="data" placeholder="RRRR-MM-DD" id="data"
							required="required">
					</div>
					<div class="col-lg-2" id="divGodzina">
						<label>Godzina: </label><input type="text" class="form-control"
							pattern="^([01]?[0-9]|2[0-3]):[0-5][0-9]$" name="godzina"
							placeholder="Godzina:" id="godzina">
					</div>
					<div class="col-lg-4" id="divSearchFrom">
						<label>Przystanek początkowy: </label> <input class="form-control"
							name="search" id="searchFrom" type="search" placeholder="Z:"
							required="required"></input>
					</div>
					<div class="col-lg-4" id="divSearchTo">
						<label>Przystanek końcowy: </label> <input class="form-control"
							name="search" id="searchTo" type="search" placeholder="Do:"
							required="required" />
					</div>
				</div>
			</div>
		</form>
		<div class="table-responsive" id="divRozklad" style="display: none">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>Godzina odjazdu</th>
						<th>Godzina przyjazdu</th>
						<th>Ulga</th>
						<th>Cena biletu</th>
						<th>Opcje</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>08:00</td>
						<td>13:00</td>
						<td><select>
								<c:forEach items="${ulgi}" var="ulga">
									<option>${ulga.opis}-${ulga.wartoscUlgi * 100}%</option>
								</c:forEach>
						</select></td>
						<td>45 PLN</td>
						<td><button type="button" class="btn btn-success">Rezerwacja</button></td>
					</tr>
					<tr>
						<td>2</td>
						<td>10:15</td>
						<td>15:15</td>
						<td><select>
								<c:forEach items="${ulgi}" var="ulga">
									<option>${ulga.opis}-${ulga.wartoscUlgi * 100}%</option>
								</c:forEach>
						</select></td>
						<td>45 PLN</td>
						<td><button type="button" class="btn btn-success">Rezerwacja</button></td>
					</tr>
					<tr>
						<td>3</td>
						<td>12:30</td>
						<td>17:30</td>
						<td><select>
								<c:forEach items="${ulgi}" var="ulga">
									<option>${ulga.opis}-${ulga.wartoscUlgi * 100}%</option>
								</c:forEach>
						</select></td>
						<td>35 PLN</td>
						<td><button type="button" class="btn btn-success">Rezerwacja</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>

<script type="text/javascript"
	src="resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="resources/js/bootstrap-datepicker.pl.js"></script>


<script>
	$(function() {
		$('#divSearchTo').hide();
		$('#data').datepicker({
			format : "yyyy-mm-dd",
			language : "pl"
		});
		$("#searchFrom").autocomplete({
			source : function(request, response) {
				$.ajax({
					method : "POST",
					url : "autocomplete",
					dataType : "json",
					data : {
						'term' : request.term
					},
					success : response
				});
			},
			minLength : 2
		});
		$("#searchTo").autocomplete({
			source : function(request, response) {
				$.ajax({
					method : "POST",
					url : "autocomplete2",
					dataType : "json",
					data : {
						'term1' : $("#searchFrom").val(),
						'term2' : request.term
					},
					success : response
				});
			},
			minLength : 2
		});
		function validate() {
			alert("Hello");
		}
		$(function() {
			$("#searchFrom").blur(function() {
				$.ajax({
					type : "GET",
					url : "searchController/validateInputValue",
					contentType: "charset=utf-8",
					data : {
							przystanek: $(this).val()
					},
					success : function(b) {
						if(b === true){
							$('#divSearchTo').slideDown("fast");
						}
						if(b === false){
							$('#divSearchTo').slideUp("fast");
						}
					},
				});
			});
		});
	});

	/* $(function() {
		$("#divPrzystanekPoczatkowy").change(function() {
			ToggleDropdown();
		});
		ToggleDropdown(); // Done to ensure correct hiding/showing on page reloads due to validation errors
	});

	$(function() {
		$("#divData").change(function() {
			ToggleDropdown();
		});
	});

	$(function() {
		$("#divPrzystanekKoncowy").change(function() {
			ToggleDropdown();
		});
	});

	function ToggleDropdown() {
		if ($("#selectPrzystanekKoncowy").val() == "") {
			$("#divRozklad").hide();
		} else {
			$("#divRozklad").show();
		}

		if ($("#selectPrzystanekPoczatkowy").val() == "") {
			$("#divRozklad").hide();
			$("#divPrzystanekKoncowy").hide();
		} else {
			$("#divPrzystanekKoncowy").show();
		}

		if ($("#selectData").val() == "") {
			$("#divRozklad").hide();
			$("#divPrzystanekKoncowy").hide();
			$("#divPrzystanekPoczatkowy").hide();
		} else {
			$("#divPrzystanekPoczatkowy").show();
		}

	}; */
</script>