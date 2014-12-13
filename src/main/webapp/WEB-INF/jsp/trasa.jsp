<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.busiki.model.PrzystankiTrasy"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.busiki.model.TrasaInfo"%>
<%@page import="com.busiki.model.Przystanek"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Trasy</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">
		<a class="btn btn-lg btn-primary" href="trasaadd">Dodaj Trasę</a> <br></br>
	</div>
</div>

<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-body">
				<table class="table table-responsive table-hover">
					<thead>
						<tr>
							<th></th>
							<th>Numer</th>
							<th>Początek</th>
							<th>Koniec</th>
							<th>Operacje</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<TrasaInfo> t = new ArrayList<TrasaInfo>();
							t = (ArrayList<TrasaInfo>)request.getAttribute("trasy");
			
							Map<Integer, String> p2 = new HashMap<Integer, String>();
							
							List<PrzystankiTrasy> pt = new ArrayList<PrzystankiTrasy>();
							pt = (ArrayList<PrzystankiTrasy>)request.getAttribute("przystankitrasy");
							
							for (TrasaInfo tr: t){
						%>

						<tr>
							<td><i class="fa fa-list"
								onclick="rozwin(<%=tr.getNumer()%>)"></i></td>
							<td><%=tr.getNumer()%></td>
							<td><%=tr.getPoczatek()%></td>
							<td><%=tr.getKoniec()%></td>
							<td><a href="trasa/remove/<%=tr.getId()%>" class="btn btn-sm btn-danger ">Usuń</a></td>
						</tr>
						<%
							for (PrzystankiTrasy ptr: pt){
								if(ptr.getTrasaInfo().getId() == tr.getId()){
									p2.put(ptr.getNumerPrzystanku(), ptr.getPrzystanek().getNazwa());
								}}
						%>
						<tr class="collapse out <%=tr.getNumer()%>" id="row1">
							<td colspan="5">
								<table>
								<%for (Integer i :p2.keySet()){ %>
	
									<tr>
										<td width="20px;"><%=i %></td>
										<td><%=p2.get(i) %></td>
										<td></td>
									</tr>
								<%}%>
								</table>
							</td>
						</tr>
						<%
							p2.clear();
							}
						%>
						<%-- 	</c:forEach> --%>
						<%-- </c:forEach> --%>
					</tbody>
				</table>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<script>
	$(document).ready(function() {

	});
	function rozwin(numer) {
		if ($("."+numer).hasClass("out")) {
			$("."+numer).addClass("in");
			$("."+numer).removeClass("out");
		} else {
			$("."+numer).addClass("out");
			$("."+numer).removeClass("in");
		}
	}
</script>