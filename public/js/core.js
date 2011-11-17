/*
 * TrackSys
 * CoreJS to load when the user hits the base url.
 */

/*
 * This is an example of how to do ajax calls to our jetty servlet.
 */
$.ajax({
	type: "GET",
	url: "http://localhost:5000/login/",
	success: onSuccess,
	error: function(data, err, thrown) {
		console.log(err);
	}
});

function onSuccess (data)
{
	document.write(data);
}