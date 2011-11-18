/**
 * login.js
 * TrackSys Project.
 * SEng330, UVic.
 * Modus Pwnens Design.
 */

// check if the user is already logged in.  If so, redirect home.
//CommHandler.doGet(SERVER_LOC+PORT+"/home/homeLocation", null, function(data){
//	if (data != "/login/")
//		setTimeout(function() { window.location.href = data; }, 50);	
//});

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
			CommHandler.doGet(SERVER_LOC+PORT+"/home/homeLocation", null, function(data){
				console.log(data);	
			});
		}
			//setTimeout(function() { window.location.href = "/home/"; }, 50);	
		else
		{
			$("#loginInputBox").val();
			$("#passwdInputBox").val("");
			$(".errorPopup").fadeIn('fast');
		}
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

