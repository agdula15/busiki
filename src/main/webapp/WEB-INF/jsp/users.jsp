<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="resources/js/bootbox.js"></script>

<div id="modal-content" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">X</button>
				<h3>Użytkownik</h3>
			</div>
			<div class="modal-body">
				<form id="formEdit" action="users/update" method="POST">
					<div class="form-group">
						<label>Id: </label> <input class="form-control" id="id"
							type='text' name='id' readonly />
					</div>
					<div class="form-group">
						<label>Email: </label> <input class="form-control" id="email"
							type='text' name='email' />
					</div>
					<div class="form-group">
						<label>Imie: </label> <input class="form-control" id="firstName"
							type='text' name='firstName' />
					</div>
					<div class="form-group">
						<label>Nazwisko: </label> <input class="form-control"
							id="lastName" type='text' name='lastName' />
					</div>
					<div class="form-group">
						<label>Numer telefonu: </label> <input class="form-control"
							id="phoneNumber" type='text' name='phoneNumber' />
					</div>
					<div class="form-group">
						<label>Numer dokumentu potwierdzającego: </label> <input
							class="form-control" id="idCardNumber" type='text'
							name='idCardNumber' />
					</div>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" onclick="postData()">Zapisz</button>
				</form>

			</div>
		</div>
	</div>
</div>

<div id="removeUser" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">X</button>
				<h3>Użytkownik</h3>
			</div>
			<div class="modal-body">
				<h4>Czy na pewno usunąć?</h4><br>			
				<form id="formEdit">
					<button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
					<a id="confirmbutton" href="" class="btn btn-danger">Usuń</a>
				</form>
			</div>
		</div>
	</div>
</div>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Użytkownicy</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<!-- 		  	<div class="panel-heading">UÃÂ¼ytkownicy</div>  -->
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th>ID</th>
								<th>E-mail</th>
								<th>Imię</th>
								<th>Nazwisko</th>
								<th>Telefon</th>
								<th>Numer dokumentu potwierdzającego</th>
								<th>Opcje</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="user">
								<tr class="even gradeC" id="${user.id}">
									<td class="id">${user.id}</td>
									<td class="email">${user.email}</td>
									<td class="firstName">${user.firstName}</td>
									<td class="lastName">${user.lastName}</td>
									<td class="phoneNumber">${user.phoneNumber}</td>
									<td class="idCardNumber">${user.idCardNumber}</td>
									<td id="test2"><a
										class="btn-sm btn-warning btn-primary edit" id="">Edytuj</a>  <a
										class="btn-sm btn-danger remove">Usuń</a></td>
								</tr>


							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<script>
	$(document)
			.ready(
					function() {
						$('.edit').click(
								function() {
									$('#id').val(
											$(this).closest('tr').find('.id')
													.text());
									$('#email').val(
											$(this).closest('tr')
													.find('.email').text());
									$('#firstName').val(
											$(this).closest('tr').find(
													'.firstName').text());
									$('#lastName').val(
											$(this).closest('tr').find(
													'.lastName').text());
									$('#phoneNumber').val(
											$(this).closest('tr').find(
													'.phoneNumber').text());
									$('#idCardNumber').val(
											$(this).closest('tr').find(
													'.idCardNumber').text());
									$('#modal-content').modal('show');
								});
						$('.remove').click(
								function() {

									$('#confirmbutton').attr('href',
											'users/remove/'+$(this).closest('tr').find('.id')
											.text());
									$('#removeUser').modal('show');
								});
						$("#formEdit")
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
														url : "<spring:url value='/users/available' />",
														type : "get",
														data : {
															email : function() {
																return $(
																		"#email")
																		.val();
															},
															id : function() {
																return $("#id")
																		.val();
															}
														}
													}
												},
												idCardNumber : {
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
													required : "WprowadÃÂº email",
													email : "WprowadÃÂº prawidÃÂowy email",
													remote : "Taki email juÃÂ¼ istnieje w bazie"
												},
												firstName : "Prosze wprowadziÃÂ przynajmniej 2 znaki",
												lastName : "Prosze wprowadziÃÂ przynajmniej 2 znaki",
												idCardNumber : "Numer dokumentu potwierdzajÃÂcego twojÃÂ toÃÂ¼samoÃÂÃÂ"
											}
										});
					});
	function postData() {
		var form = $("#formEdit");
		if ($("#firstName").val() === $('#' + $("#id").val())
				.find('.firstName').text()
				&& $("#lastName").val() === $('#' + $("#id").val()).find(
						'.lastName').text()
				&& $("#email").val() === $('#' + $("#id").val()).find('.email')
						.text()
				&& $("#phoneNumber").val() === $('#' + $("#id").val()).find(
						'.phoneNumber').text()
				&& $("#idCardNumber").val() === $('#' + $("#id").val()).find(
						'.idCardNumber').text()) {
			alert("Nic niezmieniłeś!");
		} else {
			alert("Wyedytowałeś dane użytkownika o id = " +  $("#id").val());
			form.submit();
		}
	}
</script>



