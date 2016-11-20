<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
  <meta charset="utf-8">
  <title>Home | Stay Healthy</title>
  <meta name="keywords" content="HTML5 Template">
  <meta name="description" content="Progressive — Responsive Multipurpose HTML Template">
  <meta name="author" content="itembridge.com">
  <meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <!-- Favicon -->
  <link rel="shortcut icon" href="resources/img/favicon.ico">
  
  <!-- Font -->
  <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Arimo:400,700,400italic,700italic'>

  <!-- Plugins CSS -->
  <link rel="stylesheet" href="resources/css/bootstrap.css">
	<link rel="stylesheet" href="resources/css/font-awesome.min.css">
  <link rel="stylesheet" href="resources/css/jslider.css">
  <link rel="stylesheet" href="resources/css/revslider/settings.css">
  <link rel="stylesheet" href="resources/css/jquery.fancybox.css">
  <link rel="stylesheet" href="resources/css/animate.css">
  <link rel="stylesheet" href="resources/css/video-js.min.css">
  <link rel="stylesheet" href="resources/css/morris.css">
  <link rel="stylesheet" href="resources/css/royalslider/royalslider.css">
  <link rel="stylesheet" href="resources/css/royalslider/skins/minimal-white/rs-minimal-white.css">
  <link rel="stylesheet" href="resources/css/layerslider/css/layerslider.css">
  <link rel="stylesheet" href="resources/css/ladda.min.css">
  <link rel="stylesheet" href="resources/css/datepicker.css">
  <link rel="stylesheet" href="resources/css/jquery.scrollbar.css">
  
  <!-- Theme CSS -->
  <link rel="stylesheet" href="resources/css/style.css">
    
  <link rel="stylesheet" href="resources/css/site.css">
  
  <!-- Custom CSS -->
  <link rel="stylesheet" href="resources/css/customizer/pages.css">
  <link rel="stylesheet" href="resources/css/customizer/pages-pages-customizer.css">

  <!-- IE Styles-->
  <link rel='stylesheet' href="resources/css/ie/ie.css">
  
  <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<link rel='stylesheet' href="resources/css/ie/ie8.css">
  <![endif]-->
  

</head>
<body>
<div class="page-box">
<div class="page-box-content">
  
<div id="top-box">
  <div class="top-box-wrapper">
	<div class="container">
	  <div class="row">
		<div class="col-xs-9 col-sm-5">
		  <div class="btn-group language btn-select">		 
			 
		  </div>
		  <div class="btn-group currency btn-select">		 
			 
		  </div>
		</div>
		
		<div class="col-xs-3 col-sm-7">
		  <div class="navbar navbar-inverse top-navbar top-navbar-right" role="navigation">
			<button type="button" class="navbar-toggle btn-navbar collapsed" data-toggle="collapse" data-target=".top-navbar .navbar-collapse">
			  <span class="icon-bar"></span>
			  <span class="icon-bar"></span>
			  <span class="icon-bar"></span>
			</button>  
			<nav class="collapse collapsing navbar-collapse">
			  <ul class="nav navbar-nav navbar-right">	
				<li><a href="my-profile.jsp"><span id="spanUser"></span></a></li>
				<li><a href="my-settings.jsp">Settings</a></li>
				<li><a href="#" onclick="logout()">Log out <i class="fa fa-lock after"></i></a></li>
			  </ul>
			</nav>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</div><!-- #top-box -->
<header class="header">
  <div class="header-wrapper">
	<div class="container">
	  <div class="row">
		<div class="col-xs-6 col-md-2 col-lg-3 logo-box">
		  <div class="logo">
			<a href="index.html">
			  <img src="resources/img/logo.png" class="logo-img" alt="">
			</a>
		  </div>
		</div><!-- .logo-box -->
		
		<div class="col-xs-6 col-md-10 col-lg-9 right-box">
		  <div class="right-box-wrapper">
			<div class="header-icons">
			  <div class="search-header hidden-600">
				<a href="#">
				  <svg x="0" y="0" width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
					<path d="M12.001,10l-0.5,0.5l-0.79-0.79c0.806-1.021,1.29-2.308,1.29-3.71c0-3.313-2.687-6-6-6C2.687,0,0,2.687,0,6
					s2.687,6,6,6c1.402,0,2.688-0.484,3.71-1.29l0.79,0.79l-0.5,0.5l4,4l2-2L12.001,10z M6,10c-2.206,0-4-1.794-4-4s1.794-4,4-4
					s4,1.794,4,4S8.206,10,6,10z"></path>
					<image src="resources/img/png-icons/search-icon.png" alt="" width="16" height="16" style="vertical-align: top;">
				  </svg>
				</a>
			  </div><!-- .search-header
			  
			  --><div class="phone-header hidden-600">
				<a href="#">
				  <svg x="0" y="0" width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
					<path d="M11.001,0H5C3.896,0,3,0.896,3,2c0,0.273,0,11.727,0,12c0,1.104,0.896,2,2,2h6c1.104,0,2-0.896,2-2
					c0-0.273,0-11.727,0-12C13.001,0.896,12.105,0,11.001,0z M8,15c-0.552,0-1-0.447-1-1s0.448-1,1-1s1,0.447,1,1S8.553,15,8,15z
					M11.001,12H5V2h6V12z"></path>
					<image src="resources/img/png-icons/phone-icon.png" alt="" width="16" height="16" style="vertical-align: top;">
				  </svg>
				</a>
			  </div><!-- .phone-header
			  
			  --><div class="btn-group cart-header">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
				  <div class="icon">
					<svg x="0" y="0" width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
					  <g>
						<path d="M15.001,4h-0.57l-3.707-3.707c-0.391-0.391-1.023-0.391-1.414,0c-0.391,0.391-0.391,1.023,0,1.414L11.603,4
						H4.43l2.293-2.293c0.391-0.391,0.391-1.023,0-1.414s-1.023-0.391-1.414,0L1.602,4H1C0.448,4,0,4.448,0,5s0.448,1,1,1
						c0,2.69,0,7.23,0,8c0,1.104,0.896,2,2,2h10c1.104,0,2-0.896,2-2c0-0.77,0-5.31,0-8c0.553,0,1-0.448,1-1S15.554,4,15.001,4z
						M13.001,14H3V6h10V14z"></path>
						<path d="M11.001,13c0.553,0,1-0.447,1-1V8c0-0.553-0.447-1-1-1s-1,0.447-1,1v4C10.001,12.553,10.448,13,11.001,13z"></path>
						<path d="M8,13c0.553,0,1-0.447,1-1V8c0-0.553-0.448-1-1-1S7,7.447,7,8v4C7,12.553,7.448,13,8,13z"></path>
						<path d="M5,13c0.553,0,1-0.447,1-1V8c0-0.553-0.447-1-1-1S4,7.447,4,8v4C4,12.553,4.448,13,5,13z"></path>
					 </g>
					</svg>
				  </div>
				  Cart <span class="count">$94</span>
				</a>
				<div class="dropdown-menu">
				  <strong>Recently added item(s)</strong>
				  <ul class="list-unstyled">
					<li>
					  <a href="shop-product-view.html" class="product-image"><img class="replace-2x" src="content/img/product-1.jpg" width="70" height="70" alt=""></a>
					  <a href="#" class="product-remove">
						<svg x="0" y="0" width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
						  <g>
							<path d="M6,13c0.553,0,1-0.447,1-1V7c0-0.553-0.447-1-1-1S5,6.447,5,7v5C5,12.553,5.447,13,6,13z"></path>
							<path d="M10,13c0.553,0,1-0.447,1-1V7c0-0.553-0.447-1-1-1S9,6.447,9,7v5C9,12.553,9.447,13,10,13z"></path>
							<path d="M14,3h-1V1c0-0.552-0.447-1-1-1H4C3.448,0,3,0.448,3,1v2H2C1.447,3,1,3.447,1,4s0.447,1,1,1
							c0,0.273,0,8.727,0,9c0,1.104,0.896,2,2,2h8c1.104,0,2-0.896,2-2c0-0.273,0-8.727,0-9c0.553,0,1-0.447,1-1S14.553,3,14,3z M5,2h6v1
							H5V2z M12,14H4V5h8V14z"></path>
						  </g>
						</svg>
					  </a><!-- .product-remove -->
					  <h4 class="product-name"><a href="shop-product-view.html" title="">New Apple iPad mini Wi-Fi + with special offer</a></h4>
					  <div class="product-price">1 x <span class="price">$1199.00</span></div>
					  <div class="clearfix"></div>
					</li>
					<li>
					  <a href="shop-product-view.html" class="product-image"><img class="replace-2x" src="content/img/product-2.jpg" width="70" height="70" alt=""></a>
					  <a href="#" class="product-remove">
						<svg x="0" y="0" width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
						  <g>
							<path d="M6,13c0.553,0,1-0.447,1-1V7c0-0.553-0.447-1-1-1S5,6.447,5,7v5C5,12.553,5.447,13,6,13z"></path>
							<path d="M10,13c0.553,0,1-0.447,1-1V7c0-0.553-0.447-1-1-1S9,6.447,9,7v5C9,12.553,9.447,13,10,13z"></path>
							<path d="M14,3h-1V1c0-0.552-0.447-1-1-1H4C3.448,0,3,0.448,3,1v2H2C1.447,3,1,3.447,1,4s0.447,1,1,1
							c0,0.273,0,8.727,0,9c0,1.104,0.896,2,2,2h8c1.104,0,2-0.896,2-2c0-0.273,0-8.727,0-9c0.553,0,1-0.447,1-1S14.553,3,14,3z M5,2h6v1
							H5V2z M12,14H4V5h8V14z"></path>
						  </g>
						</svg>
					  </a><!-- .product-remove -->
					  <h4 class="product-name"><a href="shop-product-view.html" title="">New Apple iPad mini Wi-Fi + with special offer</a></h4>
					  <div class="product-price">1 x <span class="price">$1199.00</span></div>
					  <div class="clearfix"></div>
					</li>
				  </ul>
				  <div class="cart-button">
					<button class="btn btn-default">View Cart</button>
					<button class="btn checkout btn-default">Checkout</button>
				  </div>
				</div>
			  </div><!-- .cart-header -->
			</div><!-- .header-icons -->
			
			<div class="primary">
			  <div class="navbar navbar-default" role="navigation">
				<button type="button" class="navbar-toggle btn-navbar collapsed" data-toggle="collapse" data-target=".primary .navbar-collapse">
				  <span class="text">Menu</span>
				  <span class="icon-bar"></span>
				  <span class="icon-bar"></span>
				  <span class="icon-bar"></span>
				</button>
	  
				<nav class="collapse collapsing navbar-collapse">
				  <ul class="nav navbar-nav navbar-center">
					<li class="parent">
					  <a href="index.html">Food</a>
					  <ul class="sub">
						<li><a href="index.html">My Foods</a></li>
						<li><a href="home-2.html">Add Food</a></li>						
					  </ul>
					</li>
					<li class="parent">
					  <a href="#">Activity</a>
					  <ul class="sub">						 
						<li><a href="sidebar-blocks.html">My Activities</a></li>
						<li><a href="full-width.html">Add Activity</a></li>		
					  </ul>
					</li>
					<li class="parent">
					  <a href="shop.html">Report</a>					 
					</li>					 
				  </ul>
				</nav>
			  </div>
			</div><!-- .primary -->
		  </div>
		</div>
		
		<div class="phone-active col-sm-9 col-md-9">
		  <a href="#" class="close"><span>close</span>×</a>
		  <span class="title">Call Us</span> <strong>+1 (777) 123 45 67</strong>
		</div>
		<div class="search-active col-sm-9 col-md-9">
		  <a href="#" class="close"><span>close</span>×</a>
		  <form name="search-form" class="search-form">
			<input class="search-string form-control" type="search" placeholder="Search here" name="search-string">
			<button class="search-submit">
			  <svg x="0" y="0" width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
				<path fill="#231F20" d="M12.001,10l-0.5,0.5l-0.79-0.79c0.806-1.021,1.29-2.308,1.29-3.71c0-3.313-2.687-6-6-6C2.687,0,0,2.687,0,6
				s2.687,6,6,6c1.402,0,2.688-0.484,3.71-1.29l0.79,0.79l-0.5,0.5l4,4l2-2L12.001,10z M6,10c-2.206,0-4-1.794-4-4s1.794-4,4-4
				s4,1.794,4,4S8.206,10,6,10z"></path>
				<image src="resources/img/png-icons/search-icon.png" alt="" width="16" height="16" style="vertical-align: top;">
			  </svg>
			</button>
		  </form>
		</div>
	  </div><!--.row -->
	</div>
  </div><!-- .header-wrapper -->
</header><!-- .header -->

<div class="breadcrumb-box">
  <div class="container">
    <ul class="breadcrumb">
      <li><a href="index.html">Home</a> </li>
    </ul>	
  </div>
</div><!-- .breadcrumb-box -->

<section id="main" class="login-register">
  <header class="page-header">
    <div class="container">
      <h1 class="title">Home</h1>
    </div>	
  </header>
  <div class="container">
    <div class="row">
    Home page
    </div>
  </div><!-- .container -->
</section><!-- #main -->

</div><!-- .page-box-content -->
</div><!-- .page-box -->

<footer id="footer">
  <div class="footer-top">
    <div class="container">
      <div class="row sidebar">
		<aside class="col-xs-12 col-sm-6 col-md-3 widget social">
		  <header>
			<h3 class="title">Follow Us</h3>
		  </header>
          <p>Follow us in social media</p>
          <div class="social-list">
			<a class="icon rounded icon-facebook" href="#"><i class="fa fa-facebook"></i></a>
			<a class="icon rounded icon-twitter" href="#"><i class="fa fa-twitter"></i></a>
			<a class="icon rounded icon-google" href="#"><i class="fa fa-google"></i></a>
			<a class="icon rounded icon-linkedin" href="#"><i class="fa fa-linkedin"></i></a>
		  </div>
		  <div class="clearfix"></div>
        </aside>
		
		<aside class="col-xs-12 col-sm-6 col-md-3 widget newsletter">
		  <header>
			<h3 class="title">Newsletter Signup</h3>
		  </header>
		  <div>
			<p>Sign up for newsletter</p>
			<div class="clearfix"></div>
			<form class="subscribe-form" method="post" action="php/subscribe.php">
			  <input class="form-control email" type="email" name="subscribe">
			  <button class="submit">
				<span class="glyphicon glyphicon-arrow-right"></span>
			  </button>
			  <span class="form-message" style="display: none;"></span>
			</form>
		  </div>
		</aside><!-- .newsletter -->
		
		<aside class="col-xs-12 col-sm-6 col-md-3 widget links">
		  <header>
			<h3 class="title">My account</h3>
		  </header>
		  <nav>
			<ul>
			  <li><a href="#">My account</a></li>
			  <li><a href="#">Order History</a></li>
			  <li><a href="#">Wish List</a></li>
			  <li><a href="#">Newsletter</a></li>
			</ul>
		  </nav>
        </aside>
		
		<aside class="col-xs-12 col-sm-6 col-md-3 widget links">
		  <header>
			<h3 class="title">Information</h3>
		  </header>
		  <nav>
			<ul>
			  <li><a href="#">About us</a></li>
			  <li><a href="#">Privacy Policy</a></li>
			  <li><a href="#">Terms &amp; Condotions</a></li>
			  <li><a href="#">Secure payment</a></li>
			</ul>
		  </nav>
        </aside>
      </div>
    </div>
  </div><!-- .footer-top -->
  <div class="footer-bottom">
    <div class="container">
      <div class="row">
        <div class="copyright col-xs-12 col-sm-3 col-md-3">
		  Copyright © ItemBridge Inc., 2013
		</div>
		
        <div class="phone col-xs-6 col-sm-3 col-md-3">
          <div class="footer-icon">
			<svg x="0" y="0" width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
			  <path fill="#c6c6c6" d="M11.001,0H5C3.896,0,3,0.896,3,2c0,0.273,0,11.727,0,12c0,1.104,0.896,2,2,2h6c1.104,0,2-0.896,2-2
			   c0-0.273,0-11.727,0-12C13.001,0.896,12.105,0,11.001,0z M8,15c-0.552,0-1-0.447-1-1s0.448-1,1-1s1,0.447,1,1S8.553,15,8,15z
				M11.001,12H5V2h6V12z"></path>
			</svg>
		  </div>
          <strong class="title">Call Us:</strong> +1 (877) 123-45-67 <br>
          <strong>or</strong> +1 (777) 123-45-67
        </div>
		
        <div class="address col-xs-6 col-sm-3 col-md-3">
          <div class="footer-icon">
			<svg x="0" y="0" width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
			  <g>
				<g>
				  <path fill="#c6c6c6" d="M8,16c-0.256,0-0.512-0.098-0.707-0.293C7.077,15.491,2,10.364,2,6c0-3.309,2.691-6,6-6
					c3.309,0,6,2.691,6,6c0,4.364-5.077,9.491-5.293,9.707C8.512,15.902,8.256,16,8,16z M8,2C5.795,2,4,3.794,4,6
					c0,2.496,2.459,5.799,4,7.536c1.541-1.737,4-5.04,4-7.536C12.001,3.794,10.206,2,8,2z"></path>
				</g>
				<g>
				  <circle fill="#c6c6c6" cx="8.001" cy="6" r="2"></circle>
				</g>
			  </g>
			</svg>
		  </div>
          49 Archdale, 2B Charleston 5655, Excel Tower<br> OPG Rpad, 4538FH
        </div>
		
        <div class="col-xs-12 col-sm-3 col-md-3">
          <a href="#" class="up">
			<span class="glyphicon glyphicon-arrow-up"></span>
		  </a>
        </div>
      </div>
    </div>
  </div><!-- .footer-bottom -->
</footer>
<div class="clearfix"></div>

<!--[if (!IE)|(gt IE 8)]><!-->
  <script src="resources/js/jquery-2.1.3.min.js"></script>
<!--<![endif]-->

<!--[if lte IE 8]>
  <script src="resources/js/jquery-1.9.1.min.js"></script>
<![endif]-->
  <script src="resources/scripts/script.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/price-regulator/jshashtable-2.1_src.js"></script>
<script src="resources/js/price-regulator/jquery.numberformatter-1.2.3.js"></script>
<script src="resources/js/price-regulator/tmpl.js"></script>
<script src="resources/js/price-regulator/jquery.dependClass-0.1.js"></script>
<script src="resources/js/price-regulator/draggable-0.1.js"></script>
<script src="resources/js/price-regulator/jquery.slider.js"></script>
<script src="resources/js/jquery.carouFredSel-6.2.1-packed.js"></script>
<script src="resources/js/jquery.touchwipe.min.js"></script>
<script src="resources/js/jquery.elevateZoom-3.0.8.min.js"></script>
<script src="resources/js/jquery.imagesloaded.min.js"></script>
<script src="resources/js/jquery.appear.js"></script>
<script src="resources/js/jquery.sparkline.min.js"></script>
<script src="resources/js/jquery.easypiechart.min.js"></script>
<script src="resources/js/jquery.easing.1.3.js"></script>
<script src="resources/js/jquery.fancybox.pack.js"></script>
<script src="resources/js/isotope.pkgd.min.js"></script>
<script src="resources/js/jquery.knob.js"></script>
<script src="resources/js/jquery.selectBox.min.js"></script>
<script src="resources/js/jquery.royalslider.min.js"></script>
<script src="resources/js/jquery.tubular.1.0.js"></script>
<script src="resources/js/SmoothScroll.js"></script>
<script src="resources/js/country.js"></script>
<script src="resources/js/spin.min.js"></script>
<script src="resources/js/ladda.min.js"></script>
<script src="resources/js/masonry.pkgd.min.js"></script>
<script src="resources/js/morris.min.js"></script>
<script src="resources/js/raphael.min.js"></script>
<script src="resources/js/video.js"></script>
<script src="resources/js/pixastic.custom.js"></script>
<script src="resources/js/livicons-1.4.min.js"></script>
<script src="resources/js/layerslider/greensock.js"></script>
<script src="resources/js/layerslider/layerslider.transitions.js"></script>
<script src="resources/js/layerslider/layerslider.kreaturamedia.jquery.js"></script>
<script src="resources/js/revolution/jquery.themepunch.tools.min.js"></script>
<script src="resources/js/revolution/jquery.themepunch.revolution.min.js"></script>
<!-- SLIDER REVOLUTION 5.0 EXTENSIONS  
	(Load Extensions only on Local File Systems !
	The following part can be removed on Server for On Demand Loading) -->	
  <script src="resources/js/revolution/extensions/revolution.extension.actions.min.js"></script>
  <script src="resources/js/revolution/extensions/revolution.extension.carousel.min.js"></script>
  <script src="resources/js/revolution/extensions/revolution.extension.kenburn.min.js"></script>
  <script src="resources/js/revolution/extensions/revolution.extension.layeranimation.min.js"></script>
  <script src="resources/js/revolution/extensions/revolution.extension.migration.min.js"></script>
  <script src="resources/js/revolution/extensions/revolution.extension.navigation.min.js"></script>
  <script src="resources/js/revolution/extensions/revolution.extension.parallax.min.js"></script>
  <script src="resources/js/revolution/extensions/revolution.extension.slideanims.min.js"></script>
  <script src="resources/js/revolution/extensions/revolution.extension.video.min.js"></script>
<script src="resources/js/bootstrapValidator.min.js"></script>
<script src="resources/js/bootstrap-datepicker.js"></script>
<script src="resources/js/jplayer/jquery.jplayer.min.js"></script>
<script src="resources/js/jplayer/jplayer.playlist.min.js"></script>
<script src="resources/js/jquery.scrollbar.min.js"></script>
<script src="resources/js/main.js"></script>
<script type="text/javascript"> 	
		 getUserInfo(); 
	</script>
</body>
</html>



