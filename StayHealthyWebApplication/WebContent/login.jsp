<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stay Healthy | Login</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/style.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.12.1.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/script.js"></script>
</head>
<body>
	<div class="header">
		<div class="header_container">
			<h1>Stay Healthy</h1>
		</div>
	</div>
	<div class="main">
		<div class="basic-grey" style="width: 400px;">
			<h1>Login</h1>

			<label> <span>Username <span class="req">*</span>:
			</span> <input id="Email" type="text" name="Email"
				placeholder="Email" maxlength="50" />
			</label> <label> <span>Password <span class="req">*</span>:
			</span> <input id="Password" type="text" name="Password"
				placeholder="Password" />				
				
			</label> <label>  <span>&nbsp;</span><input type="button" class="button"
				id="btnLogin" value="Login" />
			 
			</label> <label><div id="divLoginMessage" class="messageText error"></div></label>

		</div>
	</div>
</body>
</html>