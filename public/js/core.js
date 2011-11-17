/*
 * TrackSys
 * CoreJS to load when the user hits the base url.
 */

/*
 * This is an example of how to do ajax calls to our jetty servlet.
 *
$.ajax({
	type: "GET",
	url: "http://localhost:1234/login/",
	success: onSuccess,
	error: function(data, err, thrown) {
		console.log(err);
	}
});

function onSuccess (data)
{
	document.write(data);
}*/

var SERVER_LOC 	= "http://localhost:";
var PORT 		= "1234";
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
			error: error
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
			error: error
		});
	}	
};