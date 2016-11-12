$(document).ready(function() {
	 
					$("#btnLogin").click(function(e) {
					 
						$("#divLoginMessage").text("");
						
						var validationResult = validateLoginForm();
						 
						if(validationResult!="")
							{
								$('#divLoginMessage').html(validationResult);
								return;
							}
						
						$.get("/StayHealthyApplication/LoginServlet?Email=" + $('#Email').val() + "&Password=" + $('#Password').val(),
								function(responseText) {
									if (responseText == "") {
										window.location.href = "/StayHealthyApplication/index.jsp";
									} else {
										$("#divLoginMessage").text(responseText);
									}				
								});
					});
					
					function validateLoginForm() {
						var validationText = "";						 

						if ($('#Email').val() == "")
							{
								validationText += "<li>Please type your email.</li>";
								$('#Email').addClass("input_error");
							}
						else
							{$('#Email').removeClass("input_error");}						
						 
						
						if ($('#Password').val() == "")
						{
							validationText += "<li>Please type your password.</li>"; 
							$('#Password').addClass("input_error");
						}
						else
						{$('#Password').removeClass("input_error");}
						
						if(validationText != "")
							{validationText = "<span class='error'><ul class='errorlist'>" + validationText+ "</ul></span>";}	

						return validationText;
					}
					
 					
					function isEmail(email) {
						  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
						  return regex.test(email);
						}

				});