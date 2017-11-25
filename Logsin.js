$(document).ready(function() {
	$("form#Log").submit(function(e) {
		e.preventDefault();
			var dataPass = $("form#Log").serialize();
			console.log(dataPass);
			$.ajax({
				url : "logmein.php",
				type : "GET",
				data : dataPass,
				success : function(data) {
					//data  = JSON.stringify(data);
					//data = $.trim(data);
					console.log(data);
					/* NOTE: the data setting is different to the data parameter of the success setting.
					The data setting is the data to be passed to the server database.
					The data parameter is the data that is returned from the server.
					Look at the console to observe this difference
					*/


					/* NOTE: when testing for the data returned, be sure to include a \n escape character for the end of line */

					/* The output of the script is checked, and a different action (in this case an alert box) is taken depending on the result.*/
					if (data === "success\n") {
						alert("You've successfully logged in");
	          window.location.href = "progress.html"
						var uname = $("#username")[0].value;
						localStorage.setItem("username", uname);
						if($("#rememberme")[0].checked){
							setCookie("cookiename", uname, 1);
						}
						localStorage.setItem("username", uname);
					}
					else {
						alert("Invalid username or password");
					}
				}
			});
	});
	$('form#Log').on('click', '#cancelbtn', function(event) {
		event.preventDefault();
		/* Act on the event */
		window.location.href = "../index.html";
	});
	$('form#Log').on('click', '#registerbtn', function(event) {
		event.preventDefault();
		window.location.href = "register.html";
	});
});
