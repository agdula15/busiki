<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</head>

<hr>
<div class="container">
	<div class="row">
		<div class="col-sm-10">
			<h1>Profil użytkownika</h1>
		</div>
		<div class="col-sm-2">
			<a href="<c:url value="j_spring_security_logout" />"
				class="pull-right"><img title="profile image"
				class="img-circle img-responsive"
				src="http://www.gravatar.com/avatar/28fd20ccec6865e2d5f0e1f4446eb7bf?s=100"></a>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-3">
			<!--left col-->

			<ul class="list-group">
				<li class="list-group-item text-muted">Dane konta</li>
				<li class="list-group-item text-right"><span class="pull-left"><strong>Imię</strong></span>${user.firstName}</li>
				<li class="list-group-item text-right"><span class="pull-left"><strong>Nazwisko</strong></span>${user.lastName}</li>
				<li class="list-group-item text-right"><span class="pull-left"><strong>E-mail</strong></span>${user.email}</li>
				<li class="list-group-item text-right"><span class="pull-left"><strong>Telefon</strong></span>${user.phoneNumber}</li>
				<li class="list-group-item text-right"><span class="pull-left"><strong>Konto
							od</strong></span> <fmt:formatDate pattern="dd.MM.yyyy"
						value="${user.dateCreated}" /></li>
			</ul>

		</div>
		<!--/col-3-->
		<div class="col-sm-9">

			<ul class="nav nav-tabs" id="myTab">
				<li class="active"><a href="#promocje" data-toggle="tab">Promocje</a></li>
				<li><a href="#settings" data-toggle="tab" class="edit">Edycja
						danych</a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane active" id="promocje">
					<div class="col-md-11">Przeprowadzone rezerwacje:</div>
					<div class="col-md-1">XX</div>

					<div class="col-md-11">Pomyślne rezerwacje:</div>
					<div class="col-md-1">xx</div>

					<div class="col-md-11">Niewykorzystane rezerwacje:</div>
					<div class="col-md-1">XxX</div>


					<div class="col-md-12">
						<br>
						<p>
							<strong>Pomyślnych rezerwacji do następnej promocji: </strong> <span
								class="pull-right text-muted">20%</span>
						</p>
						<div class="progress progress-striped active">
							<div class="progress-bar progress-bar-info" role="progressbar"
								aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
								style="width: 20%">
								<span class="sr-only">20%</span>
							</div>
						</div>
					</div>
				</div>
				<!--/tab-pane-->

				<div class="tab-pane" id="settings">
					<hr>
					<form class="form" action="userProfile/changeData" method="post"
						id="editDataForm">
						<div class="form-group">
							<div class="col-xs-6">
								<label for="first_name"><h4>Imię</h4></label> <input
									class="form-control" name="first_name" id="first_name"
									title="Wprowadź swoje imię" type="text"
									value="${user.firstName }">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-6">
								<label for="last_name"><h4>Nazwisko</h4></label> <input
									class="form-control" name="last_name" id="last_name"
									title="Wprowadź swoje nazwisko" type="text"
									value="${user.lastName }">
							</div>
						</div>

						<div class="form-group">
							<div class="col-xs-6">
								<label for="phone"><h4>Telefon</h4></label> <input
									class="form-control" name="phone" id="phone"
									title="Wprowadź swój telefon" type="text"
									value="${user.phoneNumber }">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-6">
								<label for="email"><h4>E-mail</h4></label> <input
									class="form-control" name="email" id="email"
									title="Wprowadź swój email" type="email" value="${user.email }" >
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-6">
								<label for="idnumber"><h4>Dokument identyfikujący</h4></label> <input
									class="form-control" name="idnumber" id="idnumber"
									title="Wprowadź numer swojego dowodu osobistego" type="text"
									value="${user.idCardNumber }">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-6">
								<label for="location"><h4>Adres</h4></label> <input
									class="form-control" name="location" id="location" title="Wprowadź swój adres"
									type="text" value="${user.address }">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-6">
								<label for="password"><h4>Hasło</h4></label> <input
									class="form-control" name="password" id="password"
									title="Wprowadź swoje aktualne hasło" type="password">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-6">
								<label for="password2"><h4>Potwierdź hasło</h4></label> <input
									class="form-control" name="password2" id="password2"
									title="Potwierdź swoje hasło" type="password">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12">
								<br>
								<button class="btn btn-lg btn-success" type="submit"
									onclick="validation()">
									<i class="glyphicon glyphicon-ok-sign"></i> Zapisz
								</button>

								<button class="btn btn-lg" type="reset">
									<i class="glyphicon glyphicon-repeat"></i> Aktualne
								</button>
							</div>
						</div>
					</form>
				</div>

			</div>
			<!--/tab-pane-->
		</div>
		<!--/tab-content-->

	</div>
	<!--/col-9-->
</div>
<!--/row-->
<script>
	$("#editDataForm").validate(
			{
				rules : {
					first_name : {
						required: true,
						minlength: 2
					},
					last_name : {
						required: true,
						minlength: 2
					},
					phone : {
						required: true,
						minlength: 5
					},
					idnumber : {
						required: true
					},
					location: { 
						required: true
					},
					password : {
						required : true,
						minlength : 5
					},
					password2 : {
						required : true,
						minlength : 5,
						equalTo : "#password"
					}
				},
				highlight : function(element) {
					$(element).closest('.form-group')
							.removeClass('has-success').addClass('has-error');
				},
				unhighlight : function(element) {
					$(element).closest('.form-group').removeClass('has-error')
							.addClass('has-success');
				},
				messages : {
					password : "Wprowadź swoje aktualne hasło",
					password2 : "Potwierdź aktualne hasło"
				},
				submitHandler: function (){
					form.submit();
				}
			});
</script>