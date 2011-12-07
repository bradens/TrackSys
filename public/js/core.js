var SERVER_LOC 	= "http://localhost:";
var PORT 		= "1234/servlet";

var checkForWebkit = function() {
	if (navigator.userAgent.match(/Chrome/) == null)
	{
		$('<div style="font-size:17px;"><p>We\'re sorry, currently TrackSys only supports webkit browsers.  Please use Chrome or Safari.<br/><span style="color: lightgreen;"><b>Thank you.</b><span></p></div>').dialog({
			autoOpen: true,
			title: "Browser Incompatibility",
			modal: true,
			show: 'fade',
			hide: 'fade'
			height:'300',
			width:'400'
		});
	}
};

$(document).ready(checkForWebkit);


function logout() 
{
	CommHandler.doPost(SERVER_LOC+PORT+"/login/logout", null, onLogout);
}

function onLogout()
{
	window.location = "/public/login/";
}

var CommHandler = {
	defaultErr: function(data, err, thrown) {
		console.log(err);
	},
	
	doPost : function(url, data, success, error)
	{
		if (!error)
			error = this.defaultErr;
		$.ajax({
			type: "POST",
			url: url,
			data: data,
			success: success,
			error: error,
			xhrFields: {
			       withCredentials: true
			},
			crossDomain: true
		});
	},
	
	doGet : function(url, data, success, error)
	{
		if (!error)
			error = this.defaultErr;
		$.ajax({
			type: "GET",
			url: url,
			data: data,
			success: success,
			error: error,
			crossDomain: true
		});
	}	
};
