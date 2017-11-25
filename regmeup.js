$(document).ready(function() {
	$("form#reg").submit(function(e) {
		e.preventDefault();

		var pw = $("#passwd").val();

		var confirmpw = $("#confirmpasswd").val();
		var dataPass = $("form#reg").serialize();
		console.log(dataPass);
		$.ajax({
			url : "regsin.php",
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
        if(pw != confirmpw)
        {
            alert("Passwords do not match");
        }

				/* NOTE: when testing for the data returned, be sure to include a \n escape character for the end of line */

				/* The output of the script is checked, and a different action (in this case an alert box) is taken depending on the result.*/
				if (data === "present\n") {
					alert("The Username already exists!, please change your username");
				}
				if(data == "success\n") {
					alert("You have been registered, please log in with your details");
					var uname = $("#username").val();
					var em = $("#email").val();
					localStorage.setItem("username", uname);
					localStorage.setItem("email", em);
          window.location.href = "login.html";
				}
				if(data == "Failure\n"){
					alert("Failed to create an account.");
				}
			}
		});
	});
	$('form#Log').on('click', '.cancel', function(event) {
		event.preventDefault();
		/* Act on the event */
		window.location.href = "../index.html";
	});
});
