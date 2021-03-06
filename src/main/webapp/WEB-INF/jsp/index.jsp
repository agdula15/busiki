<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<style>
.mybackground {
	background: url('resources/img/7.png') no-repeat scroll center center/cover transparent;
	min-height: 600px;
}
</style>

<div class="mybackground ">
	<div class="container">
		<div class="row">
			<div class="col-sm-7 col-sm-offset-05 ">
				<div class="quote">
					<i class="fa fa-quote-left fa-4x"></i>
				</div>
				<div id="fade-quote-carousel" class="carousel slide"
					data-interval="3000" data-ride="carousel">
					<ol class="carousel-indicators">
						<li class="active" data-slide-to="0"
							data-target="#fade-quote-carousel"></li>
						<li class="" data-slide-to="1" data-target="#fade-quote-carousel"></li>
						<li class="" data-slide-to="2" data-target="#fade-quote-carousel"></li>
					</ol>
					<div class="carousel-inner">
						<c:forEach items="${newsy}" var="item" varStatus="loop">
							<c:if test="${loop.first}">
								<div class="item active">
									<blockquote>
										<h2>${item.tytul}</h2>
										<p>${item.tresc}</p>
									</blockquote>
								</div>
							</c:if>
							<c:if test="${!loop.first}">
								<div class="item">
									<blockquote>
										<h2>${item.tytul}</h2>
										<p>${item.tresc}</p>
									</blockquote>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<h3>
					<spring:message code="search_travel.index_h3" />
				</h3>
				<p>
					<spring:message code="search_travel.index_p" />
				</p>
				<div class="form-horizontal ">

					<form action="search" method="post" >
						<div class="form-group search" style="margin-right: 5%;">
							<input class="form-control" name="searchFrom" id="searchFrom" type="search"
								placeholder="Z:" required="required"></input>
						</div>
						<div class="form-group search" style="margin-right: 5%;">
							<input class="form-control" name="searchTo" id="searchTo" type="search"
								placeholder="Do:" required="required"></input>
						</div>
						<div class="form-group" style="margin-right: 5%;">
							<input type="text"  class="form-control"  name="data" placeholder="Data:"
								id="dp1" required="required">
						</div>
						<div class="form-group" style="margin-right: 5%;">
							<input type="text" class="form-control" pattern="^([01]?[0-9]|2[0-3]):[0-5][0-9]$"  name="godzina" placeholder="Godzina:"
								id="dp2" required="required">
						</div>

						<div class="form-group" style="margin-right: 5%;">
							<button class="btn btn-block btn-primary" type="submit">
								<spring:message code="search_travel.index_btn" />
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(function() {
		$('#dp1').datepicker({
			format : "yyyy-mm-dd",
			language : "pl"
		});
		$("#searchTo").autocomplete({
			source: function( request, response ) {
	            $.ajax({
	            	method: "POST",
	                url: "autocomplete",
	                dataType: "json",
	                data: {'term':request.term},
	                success: response
	            });
	        },
	        minLength: 2
		});
		$("#searchFrom").autocomplete({
			source: function( request, response ) {
	            $.ajax({
	            	method: "POST",
	                url: "autocomplete",
	                dataType: "json",
	                data: {'term':request.term},
	                success: response
	            });
	        },
	        minLength: 2
		});
	});
</script>

