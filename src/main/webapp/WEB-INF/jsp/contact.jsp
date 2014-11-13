<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Le styles -->
<!-- GOOGLE FONT-->
<link
	href='http://fonts.googleapis.com/css?family=Roboto:400,300,700italic,700,500&amp;subset=latin,latin-ext'
	rel='stylesheet' type='text/css'>
<!-- /GOOGLE FONT-->


<!-- Le styles -->
<!-- Latest compiled and minified CSS BS 3.0. -->
<!-- <link href="resources/css/bootstrap.min.css" rel="stylesheet"> -->


<link
	href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css"
	rel="stylesheet">
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
}

header .jumbotron {
	background: #358cce;
	color: #fff;
	border-radius: 0px;
}

header .jumbotron small {
	color: #fff;
}

.main-color {
	color: #358cce;
}

.panel {
	background-color: rgba(167, 209, 241, 0.14);
}

#map {
	width: 100%;
	height: 250px;
	display: block;
}
/* Fix Google Maps canvas
	 *
	 * Wrap your Google Maps embed in a `.google-map-canvas` to reset Bootstrap's
	 * global `box-sizing` changes. You may optionally need to reset the `max-width`
	 * on images in case you've applied that anywhere else. (That shouldn't be as
	 * necessary with Bootstrap 3 though as that behavior is relegated to the
	 * `.img-responsive` class.)
	 */
.google-map-canvas, .google-map-canvas * { .box-sizing (content-box);
	
}

/* Optional responsive image override */
img {
	max-width: none;
}

@media ( max-width : 768px) {
	.nav-tabs.nav-justified>li {
		float: left;
	}
}
</style>

<!--[if lt IE 7]>
	<link href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome-ie7.min.css" rel="stylesheet">
	<![endif]-->
<!-- Fav and touch icons -->


<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
    <![endif]-->
<!-- Le fav and touch icons -->
<!-- <link rel="shortcut icon" href="assets/ico/favicon.ico"> -->
<!-- <link rel="apple-touch-icon-precomposed" sizes="144x144" -->
<!-- 	href="assets/ico/apple-touch-icon-144-precomposed.png"> -->
<!-- <link rel="apple-touch-icon-precomposed" sizes="114x114" -->
<!-- 	href="assets/ico/apple-touch-icon-114-precomposed.png"> -->
<!-- <link rel="apple-touch-icon-precomposed" sizes="72x72" -->
<!-- 	href="assets/ico/apple-touch-icon-72-precomposed.png"> -->
<!-- <link rel="apple-touch-icon-precomposed" -->
<!-- 	href="assets/ico/apple-touch-icon-57-precomposed.png"> -->

<body>
	<!-- 	<header> -->
	<!-- 		Main hero unit for a primary marketing message or call to action -->
	<!-- 		<div class="jumbotron">      -->

	<!-- 				<div class="container"> -->
	<!-- 					<div class="row"> -->
	<!-- 						<div class="col-sm-12 col-lg-12"> -->
	<!-- 							<h1>Contact us <small>Contact page with working PHP contact form & jQuery</small></h1> -->


	<!-- 						</div> -->
	<!-- 					</div> -->
	<!-- 				</div> -->
	<!-- 		</div> -->
	<!-- 	</header> -->
	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-sm-12 col-lg-12">
				<div class="panel">
					<div class="panel-heading">
						<br>
						<h3>
							<i class="icon-map-marker main-color"></i> Lokalizacja
						</h3>
					</div>
					<div class="panel-body">
						<!-- gMap script container !Do not remove!! -->
						<div id="map"></div>
						<!-- gMap script container !Do not remove!! -->
					</div>
				</div>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-sm-4 col-lg-4">
				<div class="panel">
					<div class="panel-heading">
						<h3>
							<i class="icon-pushpin main-color"></i> Biuro:
						</h3>
					</div>
					<div class="panel-body">
						<address>
							<strong>Bus S.A.</strong><br> Lubartowska 55b<br>
							Lublin<br> <i class="icon-phone-sign"></i> + 48 (123)
							456-7890
						</address>
					</div>
				</div>

				<div class="panel">
					<div class="panel-heading">
						<h3>
							<i class="icon-time main-color"></i> Godziny otwarcia:
						</h3>
					</div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>

									<th>Dzień</th>
									<th>Godzina</th>
								</tr>
							</thead>
							<tbody>
								<tr class="success">
									<td>Poniedziałek</td>
									<td>9:00 - 18:00</td>
								</tr>
								<tr class="success">
									<td>Wtorek</td>
									<td>9:00 - 18:00</td>
								</tr>
								<tr class="success">

									<td>Środa</td>
									<td>9:00 - 18:00</td>
								</tr>
								<tr class="success">

									<td>Czwartek</td>
									<td>9:00 - 18:00</td>
								</tr>
								<tr class="success">

									<td>Piątek</td>
									<td>9:00 - 18:00</td>
								</tr>
								<tr class="warning">

									<td>Sobota</td>
									<td>9:00 - 14:00</td>
								</tr>
								<tr class="danger">

									<td>Niedziela</td>
									<td>-</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-12 col-lg-8">

				<div class="panel">
					<div class="panel-heading">
						<h3 class="">
							<i class="icon-envelope main-color"></i> Prześlij nam swoje
							uwagi:
						</h3>
					</div>
					<div class="panel-body">
						<!-- CONTACT FORM https://github.com/jonmbake/bootstrap3-contact-form -->

						<form role="form" id="feedbackForm">
							<div class="form-group">
								<input type="text" class="form-control" id="title" name="title"
									placeholder="Tytuł: "> <span class="help-block"
									style="display: none;">Wprowadź tytuł</span>
							</div>
							<!-- 							<div class="form-group"> -->
							<!-- 								<input type="email" class="form-control" id="email" name="email" -->
							<!-- 									placeholder="E-mail: "> <span class="help-block" -->
							<!-- 									style="display: none;">Wprowadź poprawny adres email</span> -->
							<!-- 							</div> -->
							<div class="form-group">
								<textarea rows="10" cols="100" class="form-control" id="message"
									name="message" placeholder="Twoja opinia: "></textarea>
								<span class="help-block" style="display: none;">Wprowadź
									wiadomość</span>
							</div>
							<button type="submit" id="feedbackSubmit"
								class="btn btn-primary btn-lg"
								style="display: block; margin-top: 10px;"
								onclick="e_friend(this.form.title.value,this.form.message.value)">Wyślij</button>
						</form>
						<!-- END CONTACT FORM -->
					</div>
				</div>
			</div>
		</div>

		<hr>

	</div>
	<!-- /container -->

	<!-- Placed at the end of the document so the pages load faster -->
	<script src="resources/js/jquery.js" type="text/javascript"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="resources/js/bootstrap.min.js"></script>

	<!-- gMap PLUGIN -->
	<script type="text/javascript"
		src="http://maps.google.com/maps/api/js?sensor=false"></script>
	<script type="text/javascript" src="resources/js/jquery.gmap.js"></script>

	<!-- CONTACTS SCRIPT-->
	<script src="resources/js/contact-form.js"></script>
	<!-- / CONTACTS SCRIPT-->


	<SCRIPT language="JavaScript">
		function e_friend(subject1, body) {
			window.location = "mailto:mateusz.grudzien.pl@gmail.com?subject="
					+ subject1 + "&body=" + body;
		}
	</SCRIPT>

	<script type="text/javascript">
		jQuery(document).ready(function() {

			jQuery('#map').gMap({
				address : 'Lublin',
				panControl : true,
				zoomControl : true,
				zoomControlOptions : {
					style : google.maps.ZoomControlStyle.SMALL
				},
				mapTypeControl : true,
				scaleControl : true,
				streetViewControl : false,
				overviewMapControl : true,
				scrollwheel : true,
				icon : {
					image : "http://www.google.com/mapfiles/marker.png",
					shadow : "http://www.google.com/mapfiles/shadow50.png",
					iconsize : [ 20, 34 ],
					shadowsize : [ 37, 34 ],
					iconanchor : [ 9, 34 ],
					shadowanchor : [ 19, 34 ]
				},
				zoom : 14,
				markers : [ {
					'address' : 'Lublin'
				} ]

			});
		});
	</script>

</body>
</html>