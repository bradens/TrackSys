var admin = {
	init: function()
	{
		// Left pane init
		/*
		$("#leftAccordion").accordion({
			fillSpace: true
		});
		
		$('.accordion .head').click(function() {
			$(this).next().toggle('slow');
			return false;
		}).next().hide();
		*/
		
		$('#datepicker').datepicker({ dateFormat: 'yy-mm-dd', currentText: 'Today' });
		$('#datepicker').datepicker().change(function(){
			admin.rewriteDayBookings();
		});
		// Right pane init
		$("#rightTabs").tabs();
		// Get all notifications
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getNotifications", null, this.writeNotifications);
		$(".loadingNotifications").show('fast');
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getRecentBookings", null, this.fillRecentBookings);
		$(".loadingBookings").show('fast');
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getAllClubs", null, this.writeClubsList);
		$(".loadingClubs").show('fast');

		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();	
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getDayBookings", {date: year +'-'+month+'-'+day}, this.writeDayBookings);
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getAllTracks", null, this.fillTracksList);
		$(".loadingTracks").show('fast');
	},
	
	rewriteNotifications : function()
	{
		$(".loadingNotifications").show('fast');
		$(".notification").remove();
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getNotifications", null, this.writeNotifications);
	},
	
	writeNotifications : function(data)
	{
		if (!data)
		{
			console.log("Failed to get notifications");
			return;
		}
		$(".loadingNotifications").hide('fast');
		for (var i = 0;i < data.length;i++)
		{
			$('.notificationsBox').append('<li><div class="notification">' +
					'<span class="date">' + data[i].timestamp + '</span><span class="title">' + data[i].title + '</span>' + 
					'<span class="message">' + data[i].message + '</span><a onclick="admin.removeNotification('+ data[i].id +');" class="button negative">X</a></div></li>');
		}
	},
	
	removeNotification : function(id)
	{
		CommHandler.doPost(SERVER_LOC+PORT+"/home/removeNotification", { id : id }, this._notificationChanged);
	},
	
	rewriteDayBookings : function()
	{
		$('.bookingsDayTableRow').remove()
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getDayBookings", {date: $('#datepicker').val()}, this.writeDayBookings);
	},
	
	writeDayBookings : function(data)
	{
		if(!data)
		{
			return;
		}
	
		for (var i = 6; i <= 22; i++)
		{
			var bookingsArray = new Array(8);
			for(var j = 0; j < 8; j++){
				if(data[i-6][j] != 0)
					bookingsArray[j] = data[i-6][j];
				else
					bookingsArray[j] = "";
			}
			$('.bookingsDayTable tr:last').after('<tr class="bookingsDayTableRow">' + 
			'<td>' + i + ':00</td><td>' + bookingsArray[0] + '</td><td>' + bookingsArray[1] + '</td><td>'+ bookingsArray[2] + '</td><td>'
			+ bookingsArray[3] + '</td><td>' + bookingsArray[4] + '</td><td>' + bookingsArray[5] + '</td><td>' + bookingsArray[6] + '</td><td>'
			+ bookingsArray[7] + '</td></tr>');
		}
	},

	writeClubsList : function(data)
	{
		if (!data)
		{
			console.log("Failed to get clubs");
			return;
		}
		$(".loadingClubs").css('display', 'none');
		var elecBilling = "no";
		var waiver = "no";
		for (var i = 0;i < data.length;i++)
		{
			if(data[i].signedWaiver == 1)
				waiver = "yes";
			if(data[i].electronicBilling == 1)
				elecBilling = "yes";
			$('.clubsTable tr:last').after('<tr class="clubsTableRow">' + 
			'<td>' + data[i].id + '</td>' + '<td>' + data[i].name + '</td>' + 
			'<td>' + data[i].address['city'] + '</td>' + '<td>' + data[i].email + '</td>' +
			'<td>' + data[i].phone + '</td>' + '<td>' + elecBilling + '</td>' +
			'<td>' + waiver + '</td>' +
			'<td>' + data[i].balance + '</td>' + '</tr>');
			waiver = "no";
			elecBilling = "no";
		}
	},
	
	fillTracksList : function(data)
	{
		if (!data)
		{
			console.log("Failed to get tracks");
			return;
		}
		$(".loadingTracks").css('display', 'none');
		for (var i = 0;i < data.length;i++)
		{
			$('.tracksTable tr:last').after('<tr class="tracksTableRow">' + 
			'<td>' + data[i].trackID + '</td>' +
			'<td>' + data[i].isBookedForMaintenance + '</td>' + '</tr>');
		}
	},
	
	fillRecentBookings: function(data)
	{
		if (!data)
		{
			console.log(data);
			return;
		}
		$(".loadingBookings").css('display', 'none');
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
		CommHandler.doPost(SERVER_LOC+PORT+"/home/addNotification", {title: title, message: message}, this._notificationChanged);
		$(".titleInput").val("");
		$(".notifInput").val("");
	},
	
	_notificationChanged: function(data)
	{
		
		console.log("Successfully changed notifications.");
		admin.rewriteNotifications();
	}
}