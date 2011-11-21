var admin = {
	init: function()
	{
		// Left pane init
		$("#leftAccordion").accordion({
			fillSpace: true
		});
		
		$('.accordion .head').click(function() {
			$(this).next().toggle('slow');
			return false;
		}).next().hide();
		
		// Right pane init
		$("#rightTabs").tabs();
		
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getRecentBookings", null, this.fillRecentBookings);
	},
	
	fillRecentBookings: function(data)
	{
		for (var i = 0;i < data.length;i++)
		{
			$('.recentBookingTable tr:last').after('<tr class="recentBookingRow">' + 
				'<td>' + data[i].clubName + '</td>' + '<td>' + data[i].trackID + '</td>' + 
				'<td>' + data[i].startTime + '</td>' + '<td>' + data[i].endTime + '</td>' +
				'<td>' + data[i].comment + '</td></tr>');
		}
	},
	
	addNotification: function()
	{
		var title = $(".titleInput").val();
		var message = $(".notifInput").val();
		CommHandler.doPost(SERVER_LOC+PORT+"/home/addNotification", {title: title, message: message}, this._notificationAdded);
	},
	
	_notificationAdded: function(data)
	{
		console.log("Successfully added notification.");
	}
	
	
	
}