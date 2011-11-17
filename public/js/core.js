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