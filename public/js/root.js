$.ajax({
	type: "GET",
	url: "http://" + SERVER_LOC + PORT + "/isLoggedIn",
	success: _continueCheckLogin,
	error: function(data, err, thrown) {
		console.log(err);
	}
});
function _continueCheckLogin(data)
{
	if (data != null)
	{
		window.location = "/login/";
	}
}