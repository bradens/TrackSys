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
		
		// Get all the notifications
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getNotifications", null, this.writeNotifications);
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getRecentBookings", null, this.fillRecentBookings);
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getAllClubs", null, this.writeClubsList);
	},
	
	writeNotifications : function(data)
	{
		if (!data)
		{
			console.log("Failed to get notifications");
			return;
		}
		for (var i = 0;i < data.length;i++)
		{
			$('.notificationsBox').append('<li><div class="notification">' +
					'<span class="date">' + data[i].timestamp + '</span><span class="title">' + data[i].title + '</span>' + 
					'<span class="message">' + data[i].message + '</span></div></li>');
		}
	},

	writeClubsList : function(data)
	{
		if (!data)
			{
				console.log("Failed to get clubs");
				return;
			}
			for (var i = 0;i < data.length;i++)
			{
				$('.clubsTable tr:last').after('<tr class="clubsTableRow">' + 
				'<td>' + data[i].id + '</td>' + '<td>' + data[i].name + '</td>' + 
				'<td>' + data[i].address['city'] + '</td>' + '<td>' + data[i].email + '</td>' +
				'<td>' + data[i].phone + '</td>' + '<td>' + data[i].electronicBilling + '</td>' +
				'<td>' + data[i].signedWaiver + '</td>' + '</tr>');
			}
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
		$(".titleInput").val("");
		$(".notifInput").val("");
	},
	
	_notificationAdded: function(data)
	{
		console.log("Successfully added notification.");
	}
}