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
			<div class='table-responsive'><table id='table' class='table'><thead><tr></tr></thead></table></div>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<script type='text/javascript'>
	var dniDalej = [];
	var dni = [];
	var j = 1; 
	var przystanki = [];
	var numerwiersza =1;
	<c:forEach var="d" items="${dni}">
	dni.push('${d.dzien}');
	</c:forEach>
	<c:forEach var="p" items="${przystanki}">
	przystanki.push('${p.nazwa}');
	</c:forEach>
	

	$(document).ready(function() {
		wypiszChecBoxy(dni,j);
		function wypiszChecBoxy(d, numer) {
			$("#panel-body").append(
					"<fieldset id='fieldset" + numer + "' ><div id='checkbox" + numer + "' class='checkbox'>");
			for (var i = 0; i < d.length; i++) {
				$("#checkbox" + numer ).append(
						"<label><input name='checkboxDzien" + numer + "' type='checkbox'  value='" + d[i] +"' /> "
								+ d[i] + " </label>");
			}
			$("#checkbox"+numer).append(
					"<a class='btn btn-info dalej' id='btnDalej"+numer+"' type='button'>Dalej</a>");
			$("#panel-body").append("</div></fieldset>");
			$( "#btnDalej"+numer ).bind( "click", function() {
				if (!$('input[name="checkboxDzien' + numer + '"]').is(":checked")) {
			        alert("Wybierz dni!");
			        return false;
			    }
				buttonDalej(numer);
				wypisztabele(przystanki,j);
				});
		}
		function wypisztabele(p, numer) {
			$("#fieldset"+numer).prop("disabled",true);
			$("#panel-body").append("<div class='table-responsive'><table id='table" + numer + "' class='table table-hover'><thead><tr id='trthead" + numer + "'><th>#</th>");
			
			for (var i = 0; i < p.length; i++) {
				$("#trthead" + numer ).append("<th>" + p[i] +" </th> ");
			}
			$("#panel-body").append("</tr></thead></table><button class='btn btn-default btn-circle  ' id='dodajwiersz" + numer+ "' type='button'><i class='fa fa-plus'></i></button><button class='btn btn-success col-sm-offset-1 zakoncz' id='zakoncz" + numer + "'type='button'>Dalej</button></div>");
			$("#table"+numer).append("<tbody id='tbody" + numer + "'></tbody>");
			
			$( "#zakoncz"+numer ).bind( "click", function() {
				buttonNastepnaTabela()
				});
			$( "#dodajwiersz"+numer ).bind( "click", function() {
				dodajwiersz(p,numer,numerwiersza);
				numerwiersza+=1;
				});
		}
		function dodajwiersz(p, numer, numerwiersza){
			$("#tbody" + numer).append("<tr id='trbody" + numerwiersza + "'><td></td>");
			for (var i = 0; i < p.length; i++) {
				$("#trbody" + numerwiersza ).append("<td><input type='text' placeholder='GG:MM'/> </td> ");
			}
			$("#tbody" + numer).append("</tr>");
			
		}
		function buttonDalej(numer) {
			dniDalej = [];
			$('input[name="checkboxDzien' + numer + '"]:unchecked').each(function() {
				dniDalej.push(this.value);
			});
			
			alert(dniDalej);
			//$("#btnDalej"+j);
			/* $.ajax({
				type : "POST",
				url : "serviceConfigureDynamic",
				success : function(data) {
					$("#firstCheckBox").append(data);
				}
			}); */

		}
		function buttonNastepnaTabela() {
		 j=j+1;
		 wypiszChecBoxy(dniDalej, j)
		}
		

	});
</script>