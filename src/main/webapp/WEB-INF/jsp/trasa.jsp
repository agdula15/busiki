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
					<%-- 	<c:forEach items="${trasy.przystanek}" var="t">
 --%><%-- 						<c:forEach items="${t.przystanki}" var="t2">
 --%>
								<tr>
										<td><i class="fa fa-list" onclick="rozwin()"></i></td>
										<%-- <td><c:out value="${t.numer}" /></td>
										<td><c:out value="${t.numer}" /></td>
										<td><c:out value="${t.numer}" /></td> --%>
									</tr>
								<tr class="collapse out" id="row1">
									<td colspan="5">
										<table class="table table-striped table-bordered table-hover">
											<tr>
												<td>test</td>
												<td>test</td>
												<td>test</td>
											</tr>
											<tr>
												<td>test</td>
												<td>test</td>
												<td>test</td>
											</tr>
										</table>
									</td>
								</tr>
						<%-- </c:forEach> --%>
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
	function rozwin() {
		if ($(".collapse").hasClass("out")) {
			$(".collapse").addClass("in");
			$(".collapse").removeClass("out");
		} else {
			$(".collapse").addClass("out");
			$(".collapse").removeClass("in");
		}
	}
</script>