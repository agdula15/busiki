<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$(".registrationForm")
								.validate(
										{
											rules : {
												firstName : {
													required : true,
													minlength : 2
												},
												lastName : {
													required : true,
													minlength : 2
												},
												email : {
													required : true,
													email : true,
													remote : {
														url : "<spring:url value='/register/available.html' />",
														type : "get",
														data : {
															email : function() {
																return $(
																		"#email")
																		.val();
															}
														}
													}
												},
												idCardNumber : {
													required : true
												},
												address : {
													required : true
												},
												password : {
													required : true,
													minlength : 5
												},
												password_again : {
													required : true,
													equalTo : "#password"
												},
												checkbox1 : {
													required : true
												}
											},
											highlight : function(element) {
												$(element).closest(
														'.form-group')
														.removeClass(
																'has-success')
														.addClass('has-error');
											},
											unhighlight : function(element) {
												$(element)
														.closest('.form-group')
														.removeClass(
																'has-error')
														.addClass('has-success');
											},
											messages : {
												email : {
													required : "Wprowadź email",
													email : "Wprowadź prawidłowy email",
													remote : "Taki email już istnieje w bazie"
												},

												firstName : "Prosze wprowadzić przynajmniej 2 znaki",
												lastName : "Prosze wprowadzić przynajmniej 2 znaki",
												idCardNumber : "Numer dokumentu potwierdzającego twoją tożsamość",
												password : "Hasło musi zawierać przynajmniej 5 znaków",
												password_again : "Wpisane hasła różnią się od siebie",
												checkbox1 : "Akceptacja regulaminu wymagana"
											}
										});
					});
</script>

<style>
.mybackground {
	background: url('resources/img/7.png') no-repeat scroll center center/cover
		transparent;
	margin-top: 30px;
	min-height: 600px;
}

.myform-control {
	display: inline;
}

.header {
	color: #36A0FF;
	font-size: 27px;
	padding: 10px;
}

.panel {
	background-color: rgba(167, 209, 241, 0.14);
}
</style>

<br>
<br>
<div class="container">
	<!-- 	<div class="well well-sm"> -->
	<div class="panel">
		<form:form commandName="user"
			cssClass="form-vertical registrationForm">
			<div class="panel-heading">
				<h3 class="text-center">
					<spring:message code="registration.message.info" />
				</h3>
			</div>
			<div class="form-group">
				<div class="">
					<form:input path="email" class="form-control" placeholder="Email:" />
					<form:errors path="email" />
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-6">
						<form:input path="firstName" class="form-control"
							placeholder="Imię:" />
						<form:errors path="firstName" />

					</div>
					<div class="col-sm-6">
						<form:input path="lastName" class="form-control"
							placeholder="Nazwisko:" />
						<form:errors path="lastName" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="">
					<form:input path="phoneNumber" class="form-control"
						placeholder="Numer telefonu:" />
					<form:errors path="phoneNumber" />
				</div>
			</div>
			<div class="form-group">
				<div class="">
					<form:input path="idCardNumber" class="form-control"
						placeholder="Numer dokumentu potwierdzającego:" />
					<form:errors path="idCardNumber" />
				</div>
			</div>
			<div class="form-group">
				<div class="">
					<form:input path="address" class="form-control"
						placeholder="Adres:" />
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-6">
						<form:password path="password" class="form-control myform-control"
							placeholder="Hasło:" />
						<form:errors path="password" />

					</div>
					<div class="col-sm-6">
						<input type="password" name="password_again" id="password_again"
							class="form-control " placeholder="Potwierdź hasło:" />
					</div>
				</div>
			</div>
			<div class="form-group  ">
				<input type="checkbox" name="checkbox1" id="checkbox1" class="" />
				<label for="checkbox1" class=""> Akceptuję regulamin</label>
			</div>
			<div>
				<input type="submit" value="Zarejestruj"
					class="btn btn-primary col-sm-offset-5-2" />
			</div>
			<br>
		</form:form>
	</div>
	<!-- 	</div> -->
</div>
<br>
