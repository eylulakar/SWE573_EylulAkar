$(document).ready(function() {

					//============Login======================================
	
					$("#formLogin").submit(function(e) {
						e.preventDefault();
					});
	
	 
					$("#btnLogin").click(function(e) {
					
						$("#divLoginMessage").text("");
						
						var validationResult = validateLoginForm();
						 
						if(validationResult!="")
							{
								$('#divLoginMessage').html(validationResult);
								return;
							}				
						
						var dataString = $("#formLogin").serialize();
						 
						$.ajax({
							 headers: { 
							        'Accept': 'application/json',
							        'Content-Type': 'application/json' 
							    },
							type : "GET",
							url : "/LoginProcess",
							data : dataString,
							dataType : "json",

							success : function(data, textStatus, jqXHR) {
								updateButtonLoginStatus(0);
								
								console.log("data.success:" + data.success);
								if (data.success) {
									window.location.href = "/home";		
								} else {
									console.log("Error occured: data.message:" + data.message);
									$("#divLoginMessage").html("<span class='errorMessage'>"+ data.message +"</span>");				
								}
							},

							error : function(jqXHR, textStatus, errorThrown) {								
								console.log("Error occured: textStatus:" + textStatus + " jqXHR.responseText:" + jqXHR.responseText );
								$("#divLoginMessage").html("<span class='errorMessage'>Error occured. Please try again later.</span>");			
								updateButtonLoginStatus(0);
							},

							beforeSend : function(jqXHR, settings) {
								updateButtonLoginStatus(1);
							},

							complete : function(jqXHR, textStatus) {
								updateButtonLoginStatus(0);
							}

						});					
						
						
					});
					
					function updateButtonLoginStatus(status){
						 
						if (status == "0") {
							    $('#btnLogin').attr("disabled", false);
								$('#btnLogin').html('Login');
								$('#btnLogin').removeClass("button_sending");
						}
						else
							{
							    $('#btnLogin').attr("disabled", true);
								$('#btnLogin').html('Logging in');
								$('#btnLogin').addClass("button_sending");
							}
					}
				 
					function validateLoginForm() {
						var validationText = "";						 

						if ($('#formLoginEmail').val() == "")
						{
							validationText += "<li>Please type your email.</li>";								
						}							
						 
						
						if ($('#formLoginPassword').val() == "")
						{
							validationText += "<li>Please type your password.</li>"; 						
						}
												
						if(validationText != "")
							{validationText = "<span class='error'>" + validationText+ "</span>";}	

						return validationText;
					}
					
 					
					function isEmail(email) {
						  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
						  return regex.test(email);
						}
					//============Login======================================
					
					//============Register======================================
					$("#formRegister").submit(function(e) {
						e.preventDefault();
					});
					
					$("#btnRegister").click(function(e) {
						 
						$('#divRegisterMessage').html("");						
					
						
						if ($('#formRegisterFullName').val() == "" || $('#formRegisterPassword').val() == ""  || $('#formRegisterDateOfBirth').val() == ""
							|| $('#formRegisterPassword').val() == "" || $('#formRegisterEmail').val() == "")
						{
							$('#divRegisterMessage').html("<span class='errorMessage'>Please fill all the required fields.</span>");
							return;
						}
						var dataString = $("#formRegister").serialize();
						 
						$.ajax({
							 headers: { 
							        'Accept': 'application/json',
							        'Content-Type': 'application/json' 
							    },
							type : "GET",
							url : "/RegisterProcess",
							data : dataString,
							dataType : "json",

							success : function(data, textStatus, jqXHR) {
								updateButtonRegisterStatus(0);
								
								console.log("data.success:" + data.success);
								if (data.success) {
									$('#divRegisterMessage').html("<span class='successMessage'>" + data.message + "</span>");				
								} else {
									console.log("Error occured: data.message:" + data.message);
									$("#divRegisterMessage").html("<span class='errorMessage'>Error occured. Please try again later.</span>");				
								}
							},

							error : function(jqXHR, textStatus, errorThrown) {
								console.log("Error occured: textStatus:" + textStatus + " jqXHR.responseText:" + jqXHR.responseText );
								$("#divMessage").html("<span class='errorMessage'>Error occured. Please try again later.</span>");			
								updateButtonRegisterStatus(0);
							},

							beforeSend : function(jqXHR, settings) {
								updateButtonRegisterStatus(1);
							},

							complete : function(jqXHR, textStatus) {
								updateButtonRegisterStatus(0);
							}

						});
					});
					
					
					function updateButtonRegisterStatus(status){
						 
						if (status == "0") {
							    $('#btnRegister').attr("disabled", false);
								$('#btnRegister').html('Register');
								$('#btnRegister').removeClass("button_sending");
						}
						else
							{
							    $('#btnRegister').attr("disabled", true);
								$('#btnRegister').html('Registering');
								$('#btnRegister').addClass("button_sending");
							}
					}
					//============Register======================================
					
				});


function getUserInfo() {
	 
	$.get("/GetLoggedInUserInfo",
			function(responseText) {
				if (responseText == "") {
					window.location.href = "/login";
				} else {
					$("#spanUser").text(responseText);
				}				
			});
	
}


function logout(){		 
	$.get("/Logout",
			function(responseText) {								
					window.location.href = "/login";												
			});
};


function GetActivityList(){
	$('#divActivityList').html("");
	 
	$.ajax({
		 headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
		type : "GET",
		url : "/GetActivityList",
		data : [],
		dataType : "json",

		success : function(data, textStatus, jqXHR) {
			console.log("data.success:" + data.success);
			if (data.success) {
				$('#divActivityList').html(data.list);	
						
			} else {
				console.log("Error occured: data.message:" + data.message);
				//$("#divRegisterMessage").html("<span class='errorMessage'>Error occured. Please try again later.</span>");				
			}
		},

		error : function(jqXHR, textStatus, errorThrown) {
			console.log("Error occured: textStatus:" + textStatus + " jqXHR.responseText:" + jqXHR.responseText );
			$("#divMessage").html("<span class='errorMessage'>Error occured. Please try again later.</span>");
		},

		beforeSend : function(jqXHR, settings) {
			$("#divLoading").removeClass("hide");
			$("#divArticleActivity").addClass("hide");
		},

		complete : function(jqXHR, textStatus) {
			$("#divLoading").addClass("hide");
			$("#divArticleActivity").removeClass("hide");
		}

	});
	
}

