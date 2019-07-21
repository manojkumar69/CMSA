<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String keylist[] = null;

	if (request.getParameter("list") != null
			&& !request.getParameter("list").equals("")) {
		
		keylist = request.getParameter("list").split(",");
	}
	if (request.getAttribute("list") != null
			&& !request.getAttribute("list").equals("")) {
		keylist = request.getAttribute("list").toString().split(",");
	}
%>

<!DOCTYPE html>
<html>
	<head>

		<!-- /.website title -->
		<!-- Favicons -->
		<link href="images/favicon.png" rel="shortcut icon">
		<title>MicroApps</title>
		<meta name="viewport"
			content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

		<!-- CSS Files -->
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="css/font-awesome.min.css" rel="stylesheet">
		<link href="fonts/icon-7-stroke/css/pe-icon-7-stroke.css"
			rel="stylesheet">
		<link href="css/animate.css" rel="stylesheet" media="screen">
		<link href="css/owl.theme.css" rel="stylesheet">
		<link href="css/owl.carousel.css" rel="stylesheet">
		<link href="css/googlefont.css" rel="stylesheet" media="screen">
		<link href="css/googlefont1.css" rel="stylesheet" media="screen"">

		<link href="css/styles.css" rel="stylesheet" media="screen">
		<link href="css/tablestyle.css" rel="stylesheet" media="screen">


		<!-- Google Fonts -->
		<%--<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>--%>
		<%--<link href='http://fonts.googleapis.com/css?family=Alegreya+Sans:100,300,400,700' rel='stylesheet' type='text/css'>--%>
		<%----%>
		<%----%>
		<%--<!-- Font Awesome -->--%>
		<%--<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet"> --%>
		<!-- admin validation -->
		<script type="text/javascript">
</script>
	</head>

	<body data-spy="scroll" data-target="#navbar-scroll">

		<div id="top"></div>

		<!-- NAVIGATION -->
		<div id="menu">
			<nav class="navbar-wrapper navbar-default" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-themers">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand site-name" href="#top"><img
							src="images/logo.png" alt="logo">
					</a>
				</div>

				<div id="navbar-scroll"
					class="collapse navbar-collapse navbar-themers navbar-right">
					<ul class="nav navbar-nav">
						<li>
							<a href="#features">About</a>
						</li>
						<li>
							<a href="#subscribe">Generate KeyStore</a>
						</li>
						<li>
							<a href="#pricingtablegroup">KeyStoreTable</a>
						</li>
						<li>
							<a href="index.jsp">Logout</a>
						</li>
					</ul>
				</div>
			</div>
			</nav>
		</div>

		<!-- /.subscribe section -->
		<div id="pricingtablegroup">
			<%--	<div class="subscribe fullscreen parallax" style="background-image:url('images/bg.jpg');" data-img-width="1920" data-img-height="1281" data-diff="100">--%>
			<div class="overlay">
				<div class="container">
					<div class="row">
						<!-- PRICING-TABLE CONTAINER -->
						<div id="pricingtableg">
							<h1 class="heading">
								KeyStore overview
							</h1>

							<!-- PROFESSIONAL -->
							<%
								if (keylist != null) {

									for (String keyname : keylist) {
							%>
							<div class="block business fl">
								<h2 class="title"><%=keyname.split("@")[0]%></h2>
								<!-- CONTENT -->
								<div class="content">
									<p class="price">
										<span><%=keyname.split("@")[1]%> </span>
										<sub>/password</sub>
									</p>
									<p class="hint">
										Suitable for Entertainment
									</p>
								</div>
								<!-- /CONTENT -->
								<!-- FEATURES -->
								<ul class="features">
									<li>
										<span class="fontawesome-cog"></span> Unique Identifier
									</li>
									<li>
										<span class="fontawesome-star"></span>Security
									</li>
									<li>
										<span class="fontawesome-dashboard"></span>Tracking of
										applications
									</li>
									<li>
										<span class="fontawesome-cloud"></span>Private Keys
									</li>
								</ul>
								<!-- /FEATURES -->
								<!-- PT-FOOTER -->
								<div class="pt-footer">
									<h2>
										Used:<%=keyname.split("@")[2]%></h2>
								</div>
								<!-- /PT-FOOTER -->
							</div>
							<%
								}
								}
							%>
						</div>
						<!-- /PRICING-TABLE -->
					</div>
				</div>
			</div>
		</div>

		<!-- /.subscribe section -->
		<div id="subscribe">
			<%--	<div class="subscribe fullscreen parallax" style="background-image:url('images/bg.jpg');" data-img-width="1920" data-img-height="1281" data-diff="100">--%>
			<div class="overlay">
				<div class="container">

					<!-- /.mail icon -->
					<div class="col-md-4 col-md-offset-4 text-center">
						<i class="pe-7s-mail pe-va letter wow fadeInUp"></i>
					</div>
					<div class="col-md-8 col-md-offset-2 text-center">
						<p>
							Android Market requires you to sign all apps you publish with a
							certificate, using a public/private key mechanism (the
							certificate is signed with your private key). This provides a
							layer of security that prevents, among other things, remote
							attackers from pushing malicious updates to your application to
							market (all updates must be signed with the same key).
						</p>

						<!-- /.subscribe form -->
						<div class="subscribe-form wow fadeInUp">
							<form class="news-letter mailchimp" action="GeneratingKeystore"
								method="post">

								<input class="form-control" type="text" name="appname"
									placeholder=" KeyStore name..." value="">
								<input class="form-control" type="password" name="password"
									placeholder=" password..." value="">
								<input type="radio" checked="checked" name="type" id="typeadmin"
									value="generate">
								Generate
								<input type="radio" name="type" id="typeuser" value="replace">
								Replace
								<button type="submit" class="subscribe-btn btn">
									Create
								</button>
							</form>
							<%
								if (request.getAttribute("keystatus") != null) {
							%>
							<h2><%=request.getAttribute("keystatus").toString()%></h2>
							<%
								}
							%>
						</div>
					</div>

				</div>
			</div>
		</div>
		<!-- /.feature 2 section -->
		<div id="features">
			<div class="container">
				<div class="row">

					<!-- /.feature image -->
					<div class="col-md-6 feature-2-pic wow fadeInLeft">
						<img src="images/feature2-image.jpg" alt="image"
							class="img-responsive">
					</div>

					<!-- /.feature content -->
					<div class="col-md-6 wow fadeInRight">
						<h2>
							Key Store Files
						</h2>
						<p>
							I would provide is that a keystore file is to authenticate
							yourself to anyone who is asking. It isn't restricted to just
							signing .apk files, you can use it to store personal
							certificates, sign data to be transmitted and a whole variety of
							authentication. In terms of what you do with it for Android and
							probably what you're looking for since you mention signing apk's,
							it is your certificate. You are branding your application with
							your credentials. You can brand multiple applications with the
							same key, in fact, it is recommended that you use one certificate
							to brand multiple applications that you write. It easier to keep
							track of what applications belong to you. But apart from signing
							apks to release into the wild, you can use it to authenticate
							your device to a server over SSL if you so desire, (also Android
							related) among other functions.
						</p>

						<div class="btn-section">
							<a href="#download" class="btn-default">Download Now</a>
						</div>

					</div>

				</div>

			</div>
		</div>
		<!-- /.footer -->
		<footer id="footer">
		<div class="container">
			<div class="col-sm-4 col-sm-offset-4">
				<!-- /.social links -->
				<div class="social text-center">
					<ul>
						<li>
							<a class="wow fadeInUp" href="https://twitter.com/"><i
								class="fa fa-twitter"></i>
							</a>
						</li>
						<li>
							<a class="wow fadeInUp" href="https://www.facebook.com/"
								data-wow-delay="0.2s"><i class="fa fa-facebook"></i>
							</a>
						</li>
						<li>
							<a class="wow fadeInUp" href="https://plus.google.com/"
								data-wow-delay="0.4s"><i class="fa fa-google-plus"></i>
							</a>
						</li>
						<li>
							<a class="wow fadeInUp" href="https://instagram.com/"
								data-wow-delay="0.6s"><i class="fa fa-instagram"></i>
							</a>
						</li>
					</ul>
				</div>
				<div class="text-center wow fadeInUp" style="font-size: 14px;">
					Copyright @ VMC July-2015
				</div>
				<a href="#" class="scrollToTop"><i class="pe-7s-up-arrow pe-va"></i>
				</a>
			</div>
		</div>
		</footer>

		<!-- /.javascript files -->
		<script src="js/jquery.js">
</script>
		<script src="js/bootstrap.min.js">
</script>
		<script src="js/custom.js">
</script>
		<script src="js/jquery.sticky.js">
</script>
		<script src="js/wow.min.js">
</script>
		<script src="js/owl.carousel.min.js">
</script>
		<script src="js/ekko-lightbox-min.js">
</script>
		<script type="text/javascript">
$(document).delegate('*[data-toggle="lightbox"]', 'click', function(event) {
	event.preventDefault();
	$(this).ekkoLightbox();
});
</script>
		<script>
new WOW().init();

</script>
	</body>
</html>
