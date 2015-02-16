<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<div class="container">
	<div class="row">
		<div class="col-sm-12 col-lg-12">
			<div class="panel">
				<div class="panel-heading">
					<br>
					<h3>Znajdź rezerwacje dla kursu:</h3>
				</div>
				<div class="panel-body">
					<div id="panelForm">
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
										<label>Godzina: </label> <input class="form-control" id="godzina" placeholder="Godzina:" required="required" />
									</div>
									<div class="col-lg-1" id="divSearchButton">
										<button class="btn btn-success" id="searchButton" type="button" style="margin-top: 26px;">
											Szukaj <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
										</button>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="panel-odpowiedź" >
						<div class="table-responsive" id="divResultTable" >
							<table class="table" id="resultTable" style="font-family: The Times New Roman;">
								<thead>
									<tr class="">
										<th><p class="text-center ">Rezerwacja</p></th>
										<th><p class="text-center ">Start</p></th>
										<th><p class="text-center ">Koniec</p></th>
										<th><p class="text-center ">Użytkownik</p></th>
										<th><p class="text-center ">Dokument Tożsamości</p></th>
										<th><p class="text-center ">Zarezerwowane miejsca</p></th>
										<th><p class="text-center ">Status</p></th>
									</tr>
								</thead>
								<tbody id="bodyResultTable">

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<hr>
</div>
<script>
	$(function() {
		var wyniki;
		$("#divResultTable").hide();
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
				type : "POST",
				url : "searchController/validateInputValue2",
				dataType: 'json',   
		        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				data : {
					'trasa' : $(this).val()
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
			if ($("#godzina").val() != "") {
				var numerKursu = $("#godzina").val().split(".");
				$.ajax({
					type : "POST",
					url : "reservation/edycja",
					data : {
						term2 : $("#data").val(),
						term1 : $("#trasa").val(),
						term3 : numerKursu[0]
					},
					success : function(data) {
						$('#panelForm').remove();
						wyniki = JSON.parse(data);
						wyswietlWyniki();
					},
					error : function(e) {
						BootstrapDialog.show({
							type : BootstrapDialog.TYPE_WARNING,
							message : "Spróbuj ponownie. Błąd. " + e
						});
					}
				});
			} else
				BootstrapDialog.show({
					type : BootstrapDialog.TYPE_INFO,
					message : "Wypełnij godzine. "
				});
		});
		function wyswietlWyniki() {
			$('#divResultTable').slideDown("fast");
			$(".panel-heading").children('h3').text(
					wyniki.trasa[0].poczatek + "-" + wyniki.trasa[0].koniec
							+ ", " + wyniki.rozklad[0].godzina + "-"
							+ wyniki.rozklad[(wyniki.rozklad.length-1)].godzina);
			for (var i = 0; i < wyniki.rezerwacje.length; i++) {
				$('#bodyResultTable').append('<tr id="tr'+i+'">');
				$('#tr'+i).append("<td><p class='text-center'>"+wyniki.rezerwacje[i].idRezerwacja+"</p></td>");
				$('#tr'+i).append("<td><p class='text-center'>"+wyniki.rezerwacje[i].start+"</p></td>"); 
				$('#tr'+i).append("<td><p class='text-center'>"+wyniki.rezerwacje[i].koniec+"</p></td>"); 
				$('#tr'+i).append("<td><p class='text-center'>"+wyniki.rezerwacje[i].fullName+"</p></td>"); 
				$('#tr'+i).append("<td><p class='text-center'>"+wyniki.rezerwacje[i].numerDokumentu+"</p></td>"); 
				$('#tr'+i).append("<td><p class='text-center'>"+wyniki.rezerwacje[i].jakieMiejsca+"</p></td>"); 
				$('#tr'+i).append('<td><select class="select form-control col-xs-offset-3" style="font-size: 11px; width: 65%; height: 29px;" id="select'+wyniki.rezerwacje[i].idRezerwacja+'"></select></td>');
				var temp = 0;
				for(var j = 0; j< wyniki.statusy.length; j++){
					$('#select'+wyniki.rezerwacje[i].idRezerwacja).append("<option  value="+j+">"+wyniki.statusy[j]+"</option>");
					if(wyniki.rezerwacje[i].status == wyniki.statusy[j])
						temp=j;
				}
				$('#select'+wyniki.rezerwacje[i].idRezerwacja + ' option[value="' + temp + '"]').attr("selected",true);
				
				$('#bodyResultTable').append('</tr>');
			}
		}
		$('body').on('change', 'select.select', function(){
			var temp = $(this).closest('tr').find('td:first').text();
			var temp2 = wyniki.statusy[$(this).val()];
			$.ajax({
				type : "POST",
				url : "reservation/edycjaStatusu",
				data : {
					term1 : temp,
					term2 : temp2
				},
				success : function(data) {
					for (var k = 0; k < wyniki.rezerwacje.length; k++) {
						if(wyniki.rezerwacje[k].idRezerwacja == temp)
							wyniki.rezerwacje[k].status=temp2;
					}
				},
				error : function(e) {
					BootstrapDialog.show({
						type : BootstrapDialog.TYPE_WARNING,
						message : "Spróbuj ponownie. Błąd. " + e
					});
				}
			});
		});

	});
</script>

