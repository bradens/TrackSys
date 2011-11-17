var login = { 
	submit : function()
	{
		var usr = $("#loginInputBox").val();
		var pwd = $("#passwdInputBox").val();
		CommHandler.doPost(SERVER_LOC+PORT+"/login/submitlogin", { username: usr, password: pwd }, login.loginSuccess);
	},
	loginSuccess : function(data)
	{
		console.log(data);
	}
};
