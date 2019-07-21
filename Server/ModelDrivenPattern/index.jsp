<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>

<!-- /.website title -->
 <!-- Favicons -->
    <link href="images/favicon.png" rel="shortcut icon">
<title>MicroApps</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<!-- CSS Files -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="fonts/icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet" media="screen">
<link href="css/owl.theme.css" rel="stylesheet">
<link href="css/owl.carousel.css" rel="stylesheet">
 <link href="css/googlefont.css" rel="stylesheet" media="screen">
<link href="css/googlefont1.css" rel="stylesheet" media="screen"">
<link href="css/styles.css" rel="stylesheet" media="screen">    

<!-- Google Fonts --> 
<%--<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>--%>
<%--<link href='http://fonts.googleapis.com/css?family=Alegreya+Sans:100,300,400,700' rel='stylesheet' type='text/css'>--%>
<%----%>
<%----%>
<%--<!-- Font Awesome -->--%>
<%--<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet"> --%>
<!-- admin validation -->
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
function validateAdmin()
{
	if(document.getElementById("typeadmin").checked) 
	{
      	if(document.getElementById("adminname").value.indexOf("admin@gmail.com")>-1&&document.getElementById("adminpass").value.indexOf("admin")>-1)
			{
				makeRequest("GeneratingKeystore?ajaxcall=admin");
			}
			else
			{ 
				document.getElementById("adminname").value="";
				document.getElementById("adminpass").value="";
				document.getElementById("loginstatus").innerHTML="Username/Password is Incorrect";
			}
	}
	if(document.getElementById("typeuser").checked)
	{
		
		makeRequest("Signin.action?b.email="+document.getElementById("adminname").value+"&b.password="+document.getElementById("adminpass").value);
	}
}
</script>
</head>
  
<body data-spy="scroll" data-target="#navbar-scroll">
 
 <div id="top"></div>

<!-- NAVIGATION -->
<div id="menu">
	<nav class="navbar-wrapper navbar-default" role="navigation">
		<div class="container">
			  <div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-themers">
				  <span class="sr-only">Toggle navigation</span>
				  <span class="icon-bar"></span>
				  <span class="icon-bar"></span>
				  <span class="icon-bar"></span>
				</button>
<%--				<a class="navbar-brand site-name" href="#top"><img src="images/logo.png" alt="logo"></a>--%>
			  </div>
	 
			  <div id="navbar-scroll" class="collapse navbar-collapse navbar-themers navbar-right">
				<ul class="nav navbar-nav">
					<li><a href="#intro">About</a></li>
					<li><a href="#feature">Features</a></li>
					<li><a href="#subscribe">Login</a></li>
				</ul>
			  </div>
		</div>
	</nav>
</div>

<!-- /.parallax full screen background image -->
<div class="fullscreen landing parallax" style="background-image:url('images/bg.jpg');" data-img-width="2000" data-img-height="1325" data-diff="100">
	
	<div class="overlay">
		<div class="container">
			<div class="row">
				
				
				
				<div class="col-md-6">
				
						<!-- /.main title -->
						<h1 class="wow fadeInLeft">
						 Mobile Computing
						</h1>

					<!-- /.header paragraph -->
					<div class="landing-text wow fadeInLeft">
						<p>To enable Mobile End-Users to graphically compose their own applications directly on their mobile phone, mainly integrating the functionalities available on the device.</p>
					</div>				  

					<!-- /.header button -->
					<div class="head-btn wow fadeInLeft">
						
						<a href="#subscribe" class="btn-primary play-market">
						<i class="fa fa-android"></i> <span>Download now</span>
						</a>
					</div>
	
					 				

				</div> 
				
				<!-- /.phone image -->
				<div class="col-md-6">
				<img src="images/header-phone.png" alt="phone" class="header-phone img-responsive wow fadeInRight">
				</div>
			</div>
		</div> 
	</div> 
</div>
   
<!-- /.intro section -->
<div id="intro">
	<div class="container">
		<div class="row">
			<!-- /.intro content -->
			<div class="col-md-12 wow slideInRight">
				<h2>Well performed best app of the year</h2>
				<p>Lorem Ipsum is simply dummy text of the <strong>printing</strong> and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing.
				</p>

					<div class="btn-section"><a href="#feature" class="btn-default">Read More</a></div>
		
			</div>
		</div>			  
	</div>
</div>

<!-- /.feature section -->
<div id="feature">
	<div class="container">
		<div class="row">
			<div class="col-md-10 col-md-offset-1 col-sm-12 text-center feature-title">

			<!-- /.feature title -->
				<h2>Our Amazing beautiful features</h2>
				<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.</p>
			</div>
		</div>
		<div class="row row-feat">
			
			<div class="col-md-12">

				<!-- /.feature 1 -->
				<div class="col-sm-4 feat-list">
					<i class="pe-7s-global pe-5x pe-va wow fadeInUp"></i>
					<div class="inner">
						<h4>Fully Customizable</h4>
						<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature.
						</p>
					</div>
				</div>
			
				<!-- /.feature 2 -->
				<div class="col-sm-4 feat-list">
					<i class="pe-7s-like2 pe-5x pe-va wow fadeInUp" data-wow-delay="0.2s"></i>
					<div class="inner">
						<h4>Responsive Design</h4>
						<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature.</p>
					</div>
				</div>
			
				<!-- /.feature 3 -->
				<div class="col-sm-4 feat-list">
					<i class="pe-7s-coffee pe-5x pe-va wow fadeInUp" data-wow-delay="0.4s"></i>
					<div class="inner">
						<h4>Amazing Design</h4>
						<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature.</p>
					</div>
				</div>
			
				
			</div>
		</div>
	</div>
</div>

<!-- /.feature 2 section -->
<div id="feature-2">
	<div class="container">
		<div class="row">
	
	<!-- /.feature image -->
			<div class="col-md-6 feature-2-pic wow fadeInLeft">
				<img src="images/feature2-image.jpg" alt="image" class="img-responsive">
			</div>	
			
			<!-- /.feature content -->
			<div class="col-md-6 wow fadeInRight">
				<h2>Learn how to make your app marketing efficient</h2>
				<p>The MicroApp programmer focuses just on the
					app behaviour, since the environment automatically
					creates a user interface for the app starting from the
					Web Service Description Language (WSDL) . The
					MicroApp execution can be triggered by various conditions,
					including environmental and proximity ones,
					and gestures. If a service is unavailable at runtime, the
					tool will attempt to find another compatible service
					to replace it; MicroApps can be used as services
					in other MicroApps, supporting an incremental and
					iterative development process. The execution of a
					MicroApp is performed by interpreting a data-flow
					DAG of services represented as an XML description.
					This interpreter-based strategy makes it easy to test
					MicroApps while they are being created, and to load
					and execute MicroApps from the MicroAppStore, a
					shared MicroApp repository; thus, MicroApps can
					easily be shared with others and remixed to form
					new apps. The hardware required both to run the
					development environment (i.e., MicroApp Generator)
					and the generated application is a smartphone. All
					these design decisions are the results of trade-offs
					among the system simplicity, expressiveness and programming
					power.
				</p>

<%--					<div class="btn-section"><a href="#download" class="btn-default">Download Now</a></div>--%>
		
			</div>
			 			  
		</div>			  
  
	</div>
</div>


<!-- /.subscribe section -->
<div id="subscribe">
	<div class="subscribe fullscreen parallax" style="background-image:url('images/bg.jpg');" data-img-width="1920" data-img-height="1281" data-diff="100">
		<div class="overlay">
			<div class="container">

				<!-- /.mail icon -->
				<div class="col-md-4 col-md-offset-4 text-center">
				<i class="pe-7s-mail pe-va letter wow fadeInUp"></i>
				</div>
				<div class="col-md-8 col-md-offset-2 text-center">
					<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley.</p>

					<!-- /.subscribe form -->
						<h3 id="loginstatus" style="color: red"></h3>
					<div class="subscribe-form wow fadeInUp">
<%--						<form name="formadmin"class="news-letter mailchimp" >--%>
						<input class="form-control" type="text" id="adminname" placeholder=" email..."  value="" required>
						<input class="form-control" type="password" id="adminpass" placeholder=" password..."  value="" required>
						<input type="radio" name="login" id="typeadmin" value="admin">Admin
						<input type="radio" checked="true" name="login" id="typeuser" value="user">User							
						<button type="button" onclick="validateAdmin()" class="subscribe-btn btn">Sign-in</button>
<%--						</form>--%>
					
					</div>					
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
						<li><a class="wow fadeInUp" href="https://twitter.com/"><i class="fa fa-twitter"></i></a></li>
						<li><a class="wow fadeInUp" href="https://www.facebook.com/" data-wow-delay="0.2s"><i class="fa fa-facebook"></i></a></li>
						<li><a class="wow fadeInUp" href="https://plus.google.com/" data-wow-delay="0.4s"><i class="fa fa-google-plus"></i></a></li>
						<li><a class="wow fadeInUp" href="https://instagram.com/" data-wow-delay="0.6s"><i class="fa fa-instagram"></i></a></li>
					</ul>
				</div>	
			<div class="text-center wow fadeInUp" style="font-size: 14px;">Copyright @ VMC July-2015 </div>
			<a href="#" class="scrollToTop"><i class="pe-7s-up-arrow pe-va"></i></a>
		</div>	
	</div>	
</footer>
	
	<!-- /.javascript files -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/custom.js"></script>
    <script src="js/jquery.sticky.js"></script>
	<script src="js/wow.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/ekko-lightbox-min.js"></script>
	<script type="text/javascript">
	$(document).delegate('*[data-toggle="lightbox"]', 'click', function(event) { event.preventDefault(); $(this).ekkoLightbox(); }); 
	</script>
	<script>
		new WOW().init();
	</script>
  </body>
</html>
