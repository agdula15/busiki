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
	<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-body">
				<table class="table table-responsive table-hover">
					<thead>
						<tr>
							<th>Numer</th>
							<th>Początek</th>
							<th>Koniec</th>
							<th>Operacja</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<TrasaInfo> t2 = new ArrayList<TrasaInfo>();
							t2 = (ArrayList<TrasaInfo>) request.getAttribute("trasy");
							for (TrasaInfo t : t2) {
								List<Przystanek> p = new ArrayList<Przystanek>(
										t.getPrzystanek());
						%>
						<tr>
							<td><%=t.getNumer()%></td>
							<td><%=p.get(0).getNazwa()%></td>
							<td><%=p.get(p.size() - 1).getNazwa()%></td>
							<td><a href="scheduleConfigure?rid=${r_info.id}&tid=<%=t.getNumer()%>"
								class="btn btn-sm btn-warning">Konfiguruj</a></td>
						</tr>
						<%
							}
						%>
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