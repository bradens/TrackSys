CommHandler.doGet(SERVER_LOC + PORT + "/isLoggedIn", null, _continueCheckLogin, null);
function _continueCheckLogin(data)
{
	debugger;
	if (data != null)
	{
		window.location = "/login/";
	}
}