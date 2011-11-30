/**
 * login.js
 * TrackSys Project.
 * SEng330, UVic.
 * Modus Pwnens Design.
 */

// check if the user is already logged in.  If so, redirect home.
CommHandler.doPost(SERVER_LOC+PORT+"/home/homeLocation", null, function(data){
	debugger;
	if (data != "/public/login/")
		window.location.href = data;	
});

var login = { 
	submit : function()
	{
		var usr = $("#loginInputBox").val();
		var pwd = $("#passwdInputBox").val();
		CommHandler.doPost(SERVER_LOC+PORT+"/login/submitlogin", { username: usr, password: pwd }, login.loginSuccess);
	},
	loginSuccess : function(data)
	{
		if (data == "true"){
			window.location.href = "/public/home/";	
		}
		else
		{
			$("#loginInputBox").val();
			$("#passwdInputBox").val("");
			$(".errorPopup").fadeIn('fast');
		}
	},
	forgotPass: function()
	{
		$(".dlgContent").dialog({
			show:"fade",
			hide:"fade",
			height:'180',
			width: '350',
			title: "Forgot Password"
		});
	},
	forgotPassSubmit: function() {
		$(".dlgContent .success").show('fast');
	}
	
};

function init() {
	$(".loginForm input").focus(function() {
		$(".errorPopup").fadeOut('fast');
	});
	$(".loginForm input").keyup(function(k) {
		if (k.keyCode == '13')
			login.submit();
	});
}

