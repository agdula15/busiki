<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.busiki.model.TrasaInfo"%>
<%@page import="com.busiki.model.Przystanek"%>
<%@page import="java.util.List"%>

<br></br>
<p>
	Rozklad od:
	<fmt:formatDate pattern="yyyy-MM-dd" value="${r_info.dataOd}" />

	do:
	<fmt:formatDate pattern="yyyy-MM-dd" value="${r_info.dataDo}" />
	dla trasy:
	<c:out value="${trasa.numer}" />
	<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-body " id="panel-body">
				<div class='table-responsive'>
					<table id='table' class='table'>
						<thead>
							<tr></tr>
						</thead>
					</table>
				</div>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<script type='text/javascript'>
	$(document)
			.ready(
					function() {
						var dni = []; //wszystki dni dla który usupełniam rozkład
						var dniLabel = []; //dni wyświetlane w kolejnym polu checkboxów
						var tb = 1; //numer tabeli
						var przystanki = []; //wszystkie przystanki 
						var wr = 0; //numer wiersza
						var godziny = []; //godziny odjazdów
						<c:forEach var="d" items="${dni}">
						dni.push('${d.dzien}');
						</c:forEach>
						<c:forEach var="p" items="${przystanki}">
						przystanki.push('${p.nazwa}');
						</c:forEach>
						var dniDalej = dni;
						var pattern = /^([01]?[0-9]|2[0-3]):[0-5][0-9]$/i;
						wypiszChecBoxy(dni, tb); //pierwsze checkboxy
						function wypiszChecBoxy(d, numer) {

							if (!dniDalej.length == 0) {
								$("#panel-body")
										.append(
												"<fieldset id='fieldset" + numer + "' ><div id='checkbox" + numer + "' class='checkbox'>");
								for (var i = 0; i < d.length; i++) {
									$("#checkbox" + numer)
											.append(
													"<label class='col-sm-offset-015'><input name='checkboxDzien" + numer + "' type='checkbox'  class='col-sm-offset-015' value='" + d[i] +"' /> "
															+ d[i]
															+ " </label>");
								}
								$("#checkbox" + numer)
										.append(
												"<a class='btn btn-info dalej col-sm-offset-05 ' id='btnDalej"+numer+"' type='button'>Dalej</a>");
								$("#panel-body").append("</div></fieldset>");
								$("#btnDalej" + numer).bind(
										"click",
										function() {
											if (!$(
													'input[name="checkboxDzien'
															+ numer + '"]').is(
													":checked")) {
												alert("Wybierz dni!");
												return false;
											}
											uzupełniajDni(numer);
											$(this).slideUp("slow");
											wypisztabele(przystanki, tb);
										});
							}
						}
						function wypisztabele(p, numer) {
							$("#fieldset" + numer).prop("disabled", true);
							$("#panel-body")
									.append(
											"<div class='table-responsive'><label id='labeltable" + numer + "'></label><table id='table" + numer + "' class='table table-hover'><thead><tr id='trthead" + numer + "'><th>#</th>");
							for (var i = 0; i < dniLabel.length; i++) {
								$("#labeltable" + numer).append(
										dniLabel[i] + " ");
							}
							for (var i = 0; i < p.length; i++) {
								$("#trthead" + numer).append(
										"<th>" + p[i] + " </th> ");
							}
							$("#panel-body")
									.append(
											"</tr></thead></table><button class='btn btn-success col-sm-offset-10 zakoncz' id='zakoncz" + numer + "'type='button'>Dalej</button></div>");
							$("#table" + numer).append(
									"<tbody id='tbody" + numer + "'></tbody>");
							wr += 1;
							dodajwiersz(p, numer, wr);
							$("#zakoncz" + numer).bind("click", function() {
								tb = tb + 1;
								wypiszChecBoxy(dniDalej, tb)
								$(this).hide("fast");
							});

						}
						function dodajwiersz(p, numer, numerwiersza) {
							$("#tbody" + numer)
									.append(
											"<tr id='trbody" + numer + numerwiersza + "'><td><button class='btn btn-default btn-circle' id='dodajwiersz" + numer + numerwiersza + "' type='button'><i class='fa fa-plus'></i></button></td>");
							for (var i = 0; i < p.length; i++) {
								$("#trbody" + numer + numerwiersza)
										.append(
												"<td><input name='godzina"
														+ numer
														+ numerwiersza
														+ "' pattern='^([01]?[0-9]|2[0-3]):[0-5][0-9]' type='text' placeholder='GG:MM' value=''> </td> ");
							}
							$("#tbody" + numer).append("</tr>");
							$("#dodajwiersz" + numer + numerwiersza)
									.bind(
											"click",
											function() {
												godziny = [];
												$(
														'input[name="godzina'
																+ numer
																+ numerwiersza
																+ '"]')
														.each(
																function() {

																	godziny
																			.push(this.value);
																});
												for (var i = 0; i < godziny.length; i++) {
													if (!pattern
															.exec(godziny[i])) {
														alert("Zła godzina dla "
																+ (i + 1)
																+ " kolumny");
														return false;
													}
												}
												var r = confirm("Czy jesteś pewien ? ");

												if (r == true) {

													$(
															"#dodajwiersz"
																	+ numer
																	+ numerwiersza)
															.hide("slow");
													$(
															'input[name="godzina'
																	+ numer
																	+ numerwiersza
																	+ '"]')
															.each(
																	function() {

																		$(this)
																				.prop(
																						"disabled",
																						true);
																	});
													var dniTemp = [];
													$(
															'input[name="checkboxDzien'
																	+ numer
																	+ '"]:checked')
															.each(
																	function() {
																		dniTemp
																				.push(this.value);
																	});
													var daneInfo = {
														"godz" : godziny,
														"dni" : dniTemp
													}
													$
															.ajax({
																type : "POST",
																url : "serviceConfigureDynamic/dodajGodzine",
																data : {
																	godz : godziny,
																	dni : dniTemp,
																	trasa : "<c:out value='${trasa.id}' />",
																	r_info : "<c:out value='${r_info.id}' />"
																},
																success : function() {
																	wr += 1;
																	dodajwiersz(
																			p,
																			numer,
																			wr);

																},
																error : function(
																		e) {
																	alert('Błąd: '
																			+ e);
																	$(
																			"#dodajwiersz"
																					+ numer
																					+ numerwiersza)
																			.show(
																					"slow");
																	$(
																			'input[name="godzina'
																					+ numer
																					+ numerwiersza
																					+ '"]')
																			.each(
																					function() {

																						$(
																								this)
																								.prop(
																										"disabled",
																										false);
																					});

																}
															});

												} else {

												}

											});

						}

						function uzupełniajDni(numer) {
							dniDalej = [];
							dniLabel = [];
							$(
									'input[name="checkboxDzien' + numer
											+ '"]:unchecked').each(function() {
								dniDalej.push(this.value);
							});
							$(
									'input[name="checkboxDzien' + numer
											+ '"]:checked').each(function() {
								dniLabel.push(this.value);
							});
						}
					});
</script>