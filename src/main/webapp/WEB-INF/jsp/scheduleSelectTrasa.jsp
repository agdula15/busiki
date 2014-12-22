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
						boolean temp=true;
						String poczatek="",koniec="";
						Integer numer = null;
					    for(int i = 0 ; i < t2.size() ; i++){
					    	
							 if(temp){
								poczatek=t2.get(i).getPoczatek();
								koniec=t2.get(i).getKoniec();
								numer= t2.get(i).getNumer();
								temp=false;
								
							}
							if(!(t2.get(i).getPoczatek().equals(koniec)) && !(t2.get(i).getKoniec().equals(poczatek)) && t2.get(i).getNumer()!=(numer+1)){
						 %>
						<tr>
							<td><%=t2.get(i).getNumer()%></td>
							<td> <%=t2.get(i).getPoczatek()%></td>
							<td><%=t2.get(i).getKoniec()%></td>
							<td><a
								href="scheduleConfigure?rid=${r_info.id}&tid=<%=t2.get(i).getId()%>&tid2=<%=t2.get(i+1).getId()%>"
								class="btn btn-sm btn-warning">Konfiguruj</a></td>
						</tr>
						<%
							}
							if((t2.get(i).getPoczatek().equals(koniec)) && (t2.get(i).getKoniec().equals(poczatek))  && t2.get(i).getNumer()==(numer+1) ){
								temp=true;
							} 
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
<script>
	$('.panel-body').tooltip({
		selector : "[data-toggle=tooltip]",
		container : "body"
	})
</script>