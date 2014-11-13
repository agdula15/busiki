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
					<div class="col-lg-4" id="divData">
						<label>Data: </label> <input class="form-control" id="selectData">
					</div>
					<div class="col-lg-4" id="divPrzystanekPoczatkowy"
						style="display: none">
						<label>Przystanek początkowy: </label> <select
							class="form-control" id="selectPrzystanekPoczatkowy">
							<option></option>
							<c:forEach items="${przystanki}" var="przystanek">
								<option>${przystanek.nazwa}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-lg-4" id="divPrzystanekKoncowy"
						style="display: none">
						<label>Przystanek końcowy: </label> <select class="form-control"
							id="selectPrzystanekKoncowy">
							<option></option>
							<c:forEach items="${przystanki}" var="przystanek">
								<option>${przystanek.nazwa}</option>
							</c:forEach>
						</select>
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
								<option>${ulga.opis} - ${ulga.wartoscUlgi * 100}%</option>
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
									<option>${ulga.opis}- ${ulga.wartoscUlgi * 100}%</option>
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
									<option>${ulga.opis}- ${ulga.wartoscUlgi * 100}%</option>
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

<script src="resources/js/jquery.js"></script>
<script type="text/javascript"
	src="resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="resources/js/bootstrap-datepicker.pl.js"></script>


<script>
	$(function() {
		$('#selectData').datepicker({
			format : "dd-mm-yyyy",
			language : "pl"
		});
	});
</script>
<script>
	$(function() {
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

	};
</script>