/*
 * TrackSys
 * CoreJS to load when the user hits the base url.
 */

$.ajax({
	type: "GET",
	url: "http://localhost:8080/",
	success: onSuccess,
	error: function(data, err, thrown) {
		console.log(err);
	}
});

function onSuccess (data)
{
	document.write(data);
}