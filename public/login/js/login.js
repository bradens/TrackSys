var login = { 
	submit : function()
	{
		var usr = $("#loginInputBox").val();
		var pwd = $("#passwdInputBox").val();
		CommHandler.doPost(SERVER_LOC+PORT+"/login/submitlogin", { username: usr, password: pwd }, login.loginSuccess);
	},
	loginSuccess : function(data)
	{
		if (data == "true")
			window.location = "/home/";
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
}

