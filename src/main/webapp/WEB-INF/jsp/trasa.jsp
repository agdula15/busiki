<%@page import="com.busiki.model.User"%>
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
							<th>Długość</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<TrasaInfo> t2 = new ArrayList<TrasaInfo>();
							t2 = (ArrayList<TrasaInfo>)request.getAttribute("trasy");
							for (TrasaInfo t: t2){
								 List<Przystanek> p = new ArrayList<Przystanek>(t.getPrzystanek());
						%>

						<tr>
							<td><i class="fa fa-list"
								onclick="rozwin(<%=t.getNumer()%>)"></i></td>
							<td><%=t.getNumer()%></td>
							<td><%=p.get(0).getNazwa()%></td>
							<td><%=p.get(p.size()-1).getNazwa()%></td>
							<td></td>
						</tr>
						<%
							for(int i = 0; i<p.size(); i++){
						%>
						<tr class="collapse out <%=t.getNumer()%>" id="row1">
							<td colspan="5">
								<table>
									<tr>
										<td width="20px;"><%=(i+1)%></td>
										<td><%=p.get(i).getNazwa()%></td>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
						<%
							}}
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