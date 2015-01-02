<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.busiki.model.Bus"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<style>
.borderless tbody tr td, .borderless thead tr th {
	border: none;
	padding: 0px;
}

.borderless label {
	display: block;
	padding-left: 15px;
	text-indent: -15px;
	font-family: The Times New Roman;
	text-size: 10px;
}

.borderless input {
	width: 5px;
	height: 5px;
	padding: 0;
	margin: 0;
	vertical-align: bottom;
	position: relative;
	top: -15px;
	*overflow: hidden;
}
</style>
<br>
<br>
<br>
<div class="container">
	<div class="row">
		<div class="col-sm-7 col-sm-offset-05 " style="font-family: Arial;">
			<p class="h3">Rezerwacja:
			<p>
			<div class="form-group " style="margin-right: 5%;">
				<p id="start">
					<c:out value="${kursStart.rozklad.przystanek.nazwa}" />
					<c:out value="${kursStart.rozklad.godzina}" />
				<p>
			</div>
			<div class="form-group " style="margin-right: 5%;">
				<p id="end">
					<c:out value="${kursEnd.rozklad.przystanek.nazwa}" />
					<c:out value="${kursEnd.rozklad.godzina}" />
				</p>
			</div>
			<div class="form-group " style="margin-right: 5%;">
				<p id="busOpis">
					Bus:
					<c:out value="${bus.nazwa}" />
					, Szczegóły:
					<c:out value="${bus.opis}" />
				</p>
			</div>
			<div class="form-group" style="margin-right: 5%;">
				<p id="ilosc">Ilość: 0</p>
			</div>
			<div class="form-group" style="margin-right: 5%;">
				<p id="miejsca">Miejsca:</p>
			</div>
			<div class="form-group " style="margin-right: 5%;">
				<p id="koszt">Koszt:</p>
			</div>

			<div class="form-group text-center center" style="margin-right: 5%;">
				<button class="btn btn-success" id="rezerwuj" type="button">
					Rezerwuj</button>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="">
				<p>Wybierz miejsca:</p>
				<table class="table borderless">
					<thead>
						<tr>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody class="text-center">
						<tr>
							<td><label>1</label><input id="1" class="myCheckbox"
								type="checkbox" disabled /></td>
							<td><label>2</label><input id="2" class="myCheckbox"
								type="checkbox" disabled /></td>
							<td></td>
							<td><label>3</label><input id="3" class="myCheckbox"
								type="checkbox" disabled /></td>
							<td><label>4</label><input id="4" class="myCheckbox"
								type="checkbox" disabled /></td>
						</tr>
						<%
							Bus b = (Bus) request.getAttribute("bus");
							int temp = 5;
							for (int i = 1; i < (b.getMiejscaSiedzace()) / 4; i++) {
						%>
						
						<tr>
							<td><label><%=temp++%></label><input class="myCheckbox"
								id="<%=temp - 1%>" type="checkbox" /></td>
							<td><label><%=temp++%> </label><input class="myCheckbox"
								id="<%=temp - 1%>" type="checkbox" /></td>
							<td>
								<%
									if (temp == (b.getMiejscaSiedzace() - 2)) {
								%><label><%=temp++%></label><input class="myCheckbox"
								id="<%=temp - 1%>" type="checkbox" /> <%
 	}
 %>
							</td>
							<td><label><%=temp++%></label> <input class="myCheckbox"
								id="<%=temp - 1%>" type="checkbox" /></td>
							<td><label><%=temp++%></label> <input class="myCheckbox"
								id="<%=temp - 1%>" type="checkbox" /></td>
						</tr>

						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script>
	$(function() {
	
		var iloscString = "Ilość: ";
		var miejscaString = "Miejsca: ";
		var miejsca = [];
		var kosztString = "Koszt: ";
		var koszt=0;
		
		<c:forEach var="z" items="${zajete}">
		 $( "#<c:out value='${z}' />" ).prop( "disabled", true );
		</c:forEach>
		
		$(".myCheckbox").change(function() {
			var temp = miejscaString;
			$(".myCheckbox").each(function() {
				if (this.checked) {
					var temp2 = true;
					for (var int = 0; int < miejsca.length; int++) {
						if (miejsca[int] == this.id) {
							temp2 = false;
						}
					}
					if (temp2) {
						miejsca.push(this.id);
						temp2 = true;
					}
					temp += this.id + ", "
				} else {
					for (var int = 0; int < miejsca.length; int++) {
						if (miejsca[int] == this.id) {
							miejsca.splice(int, 1);
						}
					}

				}
			});
			$("#miejsca").text(temp);
			$("#ilosc").text(iloscString + miejsca.length);
		});
		$("#rezerwuj").click(function() {
			alert(miejsca.toString());
			alert(miejsca);
			if (miejsca.length != 0) {
				$.ajax({
					type : "POST",
					url : "rezerwacja/zatwierdzenie",
					data : {
						dorezerwacji : miejsca,
						kurs1 : "<c:out value='${kursStart.id}' />",
						kurs2 : "<c:out value='${kursEnd.id}' />"
					},
					success : function(data) {
							window.location = "userProfile";
					},
					error : function(e) {
						alert('Błąd: ' + e);
					}
				});
			} else
				alert("Nie wybrałeś miejsca.");
		});

	});
</script>

