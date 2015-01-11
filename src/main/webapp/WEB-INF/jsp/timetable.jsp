<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body style="font-family: The Times New Roman;">
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
					<div class="col-lg-3" id="divSearchFrom">
						<label>Przystanek początkowy: </label> <input class="form-control"
							name="search" id="searchFrom" type="search" placeholder="Z:"
							required="required"></input>
					</div>
					<div class="col-lg-3" id="divSearchTo">
						<label>Przystanek końcowy: </label> <input class="form-control"
							name="search" id="searchTo" type="search" placeholder="Do:"
							required="required" />
					</div>
					<div class="col-lg-1" id="divSearchButton">
						<button class="btn btn-success" id="searchButton" type="button"
							style="margin-top: 26px;">
							Szukaj <span class="glyphicon glyphicon-chevron-right"
								aria-hidden="true"></span>
						</button>
					</div>

				</div>
			</div>
		</form>

		<div class="table-responsive" id="divResultTable">
			<table class="table" id="resultTable">
				<thead>
					<tr class="warning">
						<th><p class="text-center ">#</p></th>
						<th><p class="text-center ">Godzina odjazdu</p></th>
						<th><p class="text-center ">Godzina przyjazdu</p></th>
						<th><p class="text-center ">Trasa</p></th>
						<th><p class="text-center ">Pojazd</p></th>
						<!-- <th><p class="text-center ">Wolne rezerwacje</p></th> -->
						<th><p class="text-center ">Opcje</p></th>
					</tr>
				</thead>
				<!-- <tbody id="bodyResultTable">

				</tbody> -->
			</table>
		</div>
	</div>
</body>

<style>
</style>
<script>
	$(function() {
		$(document).tooltip();
		$('#divResultTable').hide();
		var pattern = /^([01]?[0-9]|2[0-3]):[0-5][0-9]$/i;
		var pattern2 = /^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/i;
		var wyniki;
		var tip;
		var data, godzina, start, end;
		$('#divSearchTo').hide();
		$('#divSearchButton').hide();
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
		$("#searchFrom").blur(function() {
			$.ajax({
				type : "GET",
				url : "searchController/validateInputValue",
				contentType : "charset=utf-8",
				data : {
					przystanek : $(this).val()
				},
				success : function(b) {
					if (b === true) {
						$('#divSearchTo').slideDown("fast");

					}
					if (b === false) {
						$('#divSearchTo').slideUp("fast");
						$('#divSearchButton').slideUp("fast");
					}
					$('#searchTo').val("");
				},
			});
		});
		$("#searchTo").blur(function() {
			$.ajax({
				type : "GET",
				url : "searchController/validateInputValue",
				contentType : "charset=utf-8",
				data : {
					przystanek : $(this).val()
				},
				success : function(b) {
					if (b === true) {
						$('#divSearchButton').slideDown("fast");
					}
					if (b === false) {
						$('#divSearchButton').slideUp("fast");
					}
				},
			});
		});
		$("#searchTo").focus(function() {
			$('#divSearchButton').slideUp("fast");
		});
		function validateCzas() {
			if (!pattern.exec($("#godzina").val())
					|| !pattern2.exec($("#data").val())) {
				BootstrapDialog.show({
					message : 'Zła godzina lub data!'
				});
				return false;
			}
			return true;
		}
		$("#searchButton")
				.click(
						function() {
							if (validateCzas()) {
								$
										.ajax({
											type : "POST",
											url : "searchController/searchRezerwacja",
											data : {
												godz : $("#godzina").val(),
												dzien : $("#data").val(),
												start : $("#searchFrom").val(),
												end : $("#searchTo").val()
											},
											success : function(data) {
												if (data == 'false1') {
													BootstrapDialog
															.show({
																type : BootstrapDialog.TYPE_INFO,
																message : 'Przykro nam. Jeszcze nie ma możliwości rezerwacji miejsca dla wybranej daty. Spróbuj wkrótce '
															});
												} else {
													wyniki = JSON.parse(data);
													$('#bodyResultTable')
															.remove();
													wyswietlWyniki(wyniki);
												}
											},
											error : function(e) {
												BootstrapDialog
														.show({
															type : BootstrapDialog.TYPE_WARNING,
															message : "Spróbuj ponownie. Błąd: "
																	+ e
														});
											}
										});
							}
						});
		//wyświetla tabele wyszukiwania
		function wyswietlWyniki(w) {
			$('#divResultTable').show();
			var kurs2;
			var licz = 1;
			$('#resultTable').append('<tbody id="bodyResultTable">');
			for (var i = 0; i < w.kurs.length; i++) {
				tip = "";
				if ($("#searchFrom").val() == w.kurs[i].rozklad.przystanek.nazwa) {

					$('#bodyResultTable').append('<tr id="tr'+licz+'">');
					$('#tr' + licz)
							.append(
									'<td><p class="text-center ">' + licz
											+ '</p></td>');
					//z:
					$('#tr' + licz).append(
							'<td style="width: 15%;"><p class="text-center godz1">'
									+ w.kurs[i].rozklad.godzina + '</p></td>');
					/* $('#tr' + licz).append(
							'<td style="width: 15%; display:none"><p class="text-center trasaid">'
									+ w.kurs[i].rozklad.trasaInfo.id + '</p></td>'); */
					//do:
					for (var j = 0; j < w.kurs.length; j++) {
						if (w.kurs[i].rozklad.trasaInfo.id == w.kurs[j].rozklad.trasaInfo.id
								&& w.kurs[i].rozklad.dniKursu.id == w.kurs[j].rozklad.dniKursu.id
								&& w.kurs[i].rozklad.numer == w.kurs[j].rozklad.numer
								&& w.kurs[j].rozklad.przystanek.nazwa == $(
										"#searchTo").val()) {
							$('#tr' + licz).append(
									'<td style="width: 15%;"><p class="text-center godz2 ">'
											+ w.kurs[j].rozklad.godzina
											+ '</p></td>');
							kurs2 = j;
							break;
						}
					}
					//przez:
					for (var k = 0; k < w.kurs.length; k++) {
						if (w.kurs[i].rozklad.trasaInfo.id == w.kurs[k].rozklad.trasaInfo.id
								&& w.kurs[i].rozklad.numer == w.kurs[k].rozklad.numer)
							tip += " " + w.kurs[k].rozklad.przystanek.nazwa;
					}
					$('#tr' + licz)
							.append(
									'<td id="przez'+w.kurs[i].rozklad.trasaInfo.id+'numer'+w.kurs[i].rozklad.numer+'"><span class="col-xs-offset-5 glyphicon glyphicon-info-sign trasa" data-toggle="tooltip"  data-placement="bottom" title="'+ tip +'" aria-hidden="true"></span></td>');
					$('#tr' + licz).append(
							'<td style="width: 20%;"><p style="font-size: 65%;"  class="text-center  ">'
									+ w.kurs[i].bus.nazwa + ": "
									+ w.kurs[i].bus.opis + '</p></td>');
					//liczy wolne miejsca na wybranym kursie:
					/* var suma = w.kurs[i].bus.miejscaSiedzace - 4;
					alert(suma + " " + kurs2 + " " + i);

					var set = {};
					for (var u = i; u < kurs2; u++) {
						if (w.kurs[u].miejscaZajete != null) {
							for (var int = 0; int < w.kurs[u].miejscaZajete.length; int++) {
								set[w.kurs[int].nr_miejsca_zajetego] = 'true';
							}
						}
					}
					for ( var s in set) {
						suma=suma-1;
					} */
					
				/* 	$('#tr' + licz).append(
							'<td class="wolne"><p class="text-center wolne">'
									+ suma + '</p></td>'); */
					$('#tr' + licz)
							.append(
									'<td><a href="rezerwacja?kursIdStart='
											+ w.kurs[i].id
											+ "&kursIdEnd="
											+ w.kurs[kurs2].id
											+ '" style="margin-top: 3px;margin-bottom: 3px;" class="col-xs-offset-5 btn btn-sm btn-success rezerwuj" id="rezerwuj'
											+ w.kurs[i].rozklad.trasaInfo.id
											+ 'numer'
											+ w.kurs[i].rozklad.numer
											+ '" type="button">Rezerwuj</a></td>');

					$('#bodyResultTable').append('</tr>');
					++licz;
				}
			}
			$('#resultTable').append('</tbody>');
		}

	});
</script>