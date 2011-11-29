var admin = {
	init: function()
	{	
		$('#datepicker').datepicker({ dateFormat: 'yy-mm-dd', currentText: 'Today' });
		$('#datepicker').datepicker().change(function(){
			admin.rewriteDayBookings();
		});
		
		$('#maintenanceDatepicker').datepicker({ dateFormat: 'yy-mm-dd' , currentText: 'Today' });
		
		// Initialize the cancellation dialog
		$("#cancel-dialog").dialog({
			autoOpen: false,
			height: 150,
			width: 300,
			modal: true,
			buttons: {
				"Yes": function() {
					admin.cancelBooking();
				},
				"No": function() {
					$( this ).dialog( "close" );
				}
			}
		});
		
		// Initialize the maintenance dialog
		$("#maint-dialog").dialog({
			autoOpen: false,
			height: 150,
			width: 300,
			modal: true,
			buttons: {
				"Yes": function() {
					admin.flipMaint();
				},
				"No": function() {
					$( this ).dialog( "close" );
				}
			}
		});
		
		// Right pane init
		$("#rightTabs").tabs();
		$("#trackTabs").tabs();
		
		// Get all notifications
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getNotifications", null, this.writeNotifications);
		$(".loadingNotifications").show('fast');
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getRecentBookings", null, this.fillRecentBookings);
		$(".loadingBookings").show('fast');
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getAllClubs", null, this.writeClubsList);
		$(".loadingClubs").show('fast');
		
		//Get all clubs for the fee tab
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getAllClubs", null, admin.writeClubsFeeList);

		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		console.log(year + "-" + month + "-" + day);
		$("#datepicker").val(year + "-" + month + "-" + day);
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getDayBookings", {date: year +'-'+month+'-'+day}, this.writeDayBookings);
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getAllTracks", null, this.fillTracksList);
		$(".loadingTracks").show('fast');
		
		// Hide fee error / success after focus change
		$(".feeForm input").focus(function() {
			$(".successPopup").fadeOut('fast');
			$(".billFail").fadeOut('fast');
		});
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
			$('.notificationsBox').append('<li><div class="notification"><a onclick="admin.removeNotification('+ data[i].id +');" class="button negative removeButton"><span class="icon trash"></span></a>' +
					'<span class="date">' + data[i].timestamp + '</span><span class="title">' + data[i].title + '</span>' + 
					'<span class="message">' + data[i].message + '</span></div></li>');
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
				if(data[i-6][j])
					bookingsArray[j] = data[i-6][j];
				else
					bookingsArray[j] = "";
			}
			$('.bookingsDayTable tr:last').after('<tr class="bookingsDayTableRow">' + 
			'<td class="timeCell">' + i + ':00</td><td>' + bookingsArray[0] + '</td><td>' + bookingsArray[1] + '</td><td>'+ bookingsArray[2] + '</td><td>'
			+ bookingsArray[3] + '</td><td>' + bookingsArray[4] + '</td><td>' + bookingsArray[5] + '</td><td>' + bookingsArray[6] + '</td><td>'
			+ bookingsArray[7] + '</td></tr>');
			$('.bookingsDayTable tr td:not(".timeCell")').each(function(item) {
				if ($(this).html() != "")
					{
						if($(this).html() == "admin")
						{
							$(this).html("Maintenance");
							$(this).addClass("maintenanceBookedCell");
						}
						else if($(this).html() != "Maintenance" && $(this).html() != "admin")
						{
							$(this).html("Booked");
							$(this).addClass("bookedCell");
						}
					}
			});
		}
	},
	nextDay : function()
	{
		var date=new Date($('#datepicker').datepicker('getDate'));
    	date.setDate(date.getDate()+1);
    	$('#datepicker').datepicker('setDate', date);
		this.rewriteDayBookings();
	},
	prevDay : function()
	{
		var date=new Date($('#datepicker').datepicker('getDate'));
    	date.setDate(date.getDate()-1);
    	$('#datepicker').datepicker('setDate', date);
		this.rewriteDayBookings();
	},
	cancelBooking : function()
	{
		var id = $("#cancel-dialog").val();
		
		CommHandler.doPost(SERVER_LOC+PORT+"/home/cancelBooking", { id: id}, admin.cancelSuccess);
	},
	cancelSuccess : function(data)
	{
		$( "#cancel-dialog").dialog("close");
		$(".recentBookingTable tr").remove();
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getRecentBookings", null, admin.fillRecentBookings);
		$(".loadingBookings").show('fast');
	},
	openCancelDialog: function(data)
	{
		console.log(data);
		$("#cancel-dialog").val(data);
		console.log($("#cancel-dialog").val());
		$("#cancel-dialog").dialog( "open" );
	},
	flipMaint : function()
	{
		var id = $("#maint-dialog").val();
		
		CommHandler.doPost(SERVER_LOC+PORT+"/home/flipMaint", { id: id}, admin.flipSuccess);
	},
	flipSuccess : function(data)
	{
		$( "#maint-dialog").dialog("close");
		$('.tracksTable tr').remove();
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getAllTracks", null, admin.fillTracksList);
		$(".loadingTracks").show('fast');
	},
	openMaintDialog: function(data)
	{
		console.log(data);
		$("#maint-dialog").val(data);
		console.log($("#maint-dialog").val());
		$("#maint-dialog").dialog( "open" );
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
	writeClubsFeeList : function(data)
	{
		if (!data)
		{
			console.log("Failed to get clubs");
			return;
		}
		console.log("Wiritng clubs list for fee");
		for (var i = 0;i < data.length;i++)
		{
			console.log("Writing club");
			$('#clubNameSelect').append('<option>' + data[i].name + '</option>');
		}
	},
	billClub : function()
	{
		var club   = $("#clubNameSelect").val();
		var fee    = $("#feeValueInputBox").val();

		CommHandler.doPost(SERVER_LOC+PORT+"/home/billClub", {club: club, fee: fee}, admin.billClubSuccess);
	},
	billClubSuccess : function(data)
	{
		if (!data)
		{
			$(".billFail").fadeIn('fast');
			return;
		}
		$("#feeValueInputBox").val("");
		$(".successPopup").fadeIn('fast');
		console.log("Billed club");
	},
	
	fillTracksList : function(data)
	{
		if (!data)
		{
			console.log("Failed to get tracks");
			return;
		}
		$(".loadingTracks").css('display', 'none');
		$('.tracksTable').append('<tr class="header">' +
								'<th>Track ID</th>' +
								'<th>Maintenance status</th>' +
								'</tr>')
		for (var i = 0;i < data.length;i++)
		{
			$('.tracksTable tr:last').after('<tr class="tracksTableRow" onclick="admin.openMaintDialog(' +
					data[i].trackID + ');">' + 
			'<td>' + data[i].trackID + '</td>' +
			'<td>' + data[i].isBookedForMaintenance + '</td>' + '</tr>');
		}
	},
	
	////////////////////////////////////////
	// track maintenance: same as booking
	createMaintenance : function()
	{
		var track   = $("#trackIDSelect").val();
		var date    = $("#maintenanceDatepicker").val();
		var start   = $("#startTimeSelect").val();
		var end     = $("#endTimeSelect").val();
		var comment = $("#trackCommentInputBox").val();

		CommHandler.doPost(SERVER_LOC+PORT+"/home/submitMaintenance", { track: track, date: date, start: start, end: end, comment: comment}, admin.maintenanceSuccess);
	},
	
	printTrackHistory : function()
	{
		//todo
	},
	
	writeTrackHistory : function()
	{
		var track   = $("#trackHistoryIDSelect").val();
		CommHandler.doPost(SERVER_LOC+PORT+"/home/getTrackHistory", {track: track}, admin.trackHistorySuccess);
	},
	
	trackHistorySuccess : function(data)
	{
		if (!data)
		{
			console.log("Failed to get tracks");
			return;
		}
		
		for (var i = 0;i < data.length;i++)
		{
			$('.tracksHistoryTable tr:last').after('<tr class="trackHistoryRow">' +
				'<td>' + data[i].clubName + '</td>' +
				'<td>' + data[i].startTime + '</td>' + 
				'<td>' + data[i].endTime + '</td>' +
				'<td>' + data[i].comment + '</td>' +
				'</tr>');
		}
	},
	
	maintenanceSuccess : function(data)
	{
		if (!data)
		{
			console.log("Failed to get tracks");
			return;
		}
		
		// no conflict when book maintenance
		if (data.length == 0)
		{
			window.location.href = "/home/admin.html";
		}
		// conflict with some booking, list them out
		else
		{
			$('.conflictBookingTable').append('<tr class="header">' +
						'<th>Club</th>' +
						'<th>StartTime</th>' +
						'<th>EndTime</th>' +
						'<th>Comment</th>' +
						'<th>Delete</th>' +
						'</tr>');
			for (var i = 0;i < data.length;i++)
			{
				$('.conflictBookingTable tr:last').after('<tr class="conflictBookingRow">' +
					'<td>' + data[i].clubName + '</td>' +
					'<td>' + data[i].startTime + '</td>' +
					'<td>' + data[i].endTime + '</td>' +
					'<td>' + data[i].comment + '</td>' +
					'<td><a onclick="admin.openCancelDialog('+ data[i].id +');" class="button negative removeButton"><span class="icon trash"></span></a></td>'+
					'</tr>');
			}
			
			// show warning
			$(".warningPopup").fadeIn('fast');
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
		$('.recentBookingTable').append('<tr class="header">' +
								'<th>Club</th>' +
								'<th>Track</th>' +
								'<th>StartTime</th>' +
								'<th>EndTime</th>' +
								'<th>Comment</th>' +
								'<th>Delete</td>' +
							'</tr>');
		for (var i = 0;i < data.length;i++)
		{
			$('.recentBookingTable tr:last').after('<tr class="recentBookingRow">' +
				'<td>' + data[i].clubName + '</td>' + '<td>' + data[i].trackID + '</td>' + 
				'<td>' + data[i].startTime + '</td>' + '<td>' + data[i].endTime + '</td>' +
				'<td>' + data[i].comment + '</td>' +
				'<td><a onclick="admin.openCancelDialog('+ data[i].id +');" class="button negative removeButton"><span class="icon trash"></span></a></td>' +
				'</tr>');
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