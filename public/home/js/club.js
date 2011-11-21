var club = {
		init : function()
		{
			// Left column initialization
			$("#leftAccordion").accordion({
				fillSpace: true
			});
			
			$('.accordion .head').click(function() {
				$(this).next().toggle('slow');
				return false;
			}).next().hide();
			
			// Right column initialization
			$("#tabs").tabs();
			
			// Initialize all date pickers here
			$('#datepicker').datepicker({ dateFormat: 'yy-mm-dd' });
			
			// Get all the notifications
			CommHandler.doPost(SERVER_LOC+PORT+"/home/getNotifications", null, this.writeNotifications);
			// Get all the future bookings
			CommHandler.doPost(SERVER_LOC+PORT+"/home/getFutureBookings", null, this.writeFutureBookings);
			// Get all the history bookings
			CommHandler.doPost(SERVER_LOC+PORT+"/home/getHistoricBookings", null, this.writeHistoricBookings);
			// get all transactions
			CommHandler.doPost(SERVER_LOC+PORT+"/home/getTransactions", null, this.fillTransactionsTable);
		},
		
		writeNotifications: function(data)
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
			$(".loginForm input").focus(function() {
				$(".errorPopup").fadeOut('fast');
			});
			$(".loginForm input").keyup(function(k) {
				if (k.keyCode == '13')
					register.submit();
			});
		},
		
		writeFutureBookings: function(data)
		{
			if (!data)
			{
				console.log("Failed to get future bookings");
				return;
			}
			$('#futureBookings').append('<tr class="header">' + 
											'<th>Track</th>' +
											'<th>Start Time</th>' + 
											'<th>End Time</th>' +
											'<th>Comment</th>' +
											'</tr>');
			for (var i = 0;i < data.length;i++)
			{
				$('#futureBookings').append('<tr class="transactionTableRow"><td>' +
						data[i].trackID + '</td><td>' + 
						data[i].startTime + '</td><td>' + 
						data[i].endTime + '</td><td>' + 
						data[i].comment + '</td></tr>');
			}
			$(".loginForm input").focus(function() {
				$(".errorPopup").fadeOut('fast');
			});
			$(".loginForm input").keyup(function(k) {
				if (k.keyCode == '13')
					register.submit();
			});
		},
		
		writeHistoricBookings: function(data)
		{
			if (!data)
			{
				console.log("Failed to get future bookings");
				return;
			}
			$('#historyBookings').append('<tr class="header">' + 
											'<th>Track</th>' +
											'<th>Start Time</th>' + 
											'<th>End Time</th>' +
											'<th>Comment</th>' +
											'</tr>');
			for (var i = 0;i < data.length;i++)
			{
				$('#historyBookings').append('<tr class="transactionTableRow"><td>' +
						data[i].trackID + '</td><td>' + 
						data[i].startTime + '</td><td>' + 
						data[i].endTime + '</td><td>' + 
						data[i].comment + '</td></tr>');
			}
			$(".loginForm input").focus(function() {
				$(".errorPopup").fadeOut('fast');
			});
			$(".loginForm input").keyup(function(k) {
				if (k.keyCode == '13')
					register.submit();
			});
		},
		
		makeBooking : function()
		{
			var date = $("#datepicker").val();
			var start = $("#startTimeSelect").val();
			var end = $("#endTimeSelect").val();
			var comment = $("#commentInputBox").val();
			
			CommHandler.doPost(SERVER_LOC+PORT+"/home/submitBooking", { date: date, start: start, end: end, comment: comment}, club.bookingSuccess);
		},
		bookingSuccess : function(data)
		{
			if (data == "true")
				window.location.href = "/home/club.html";
			else
			{
				$(".errorPopup").fadeIn('fast');
			}
		},
		fillTransactionsTable : function(data)
		{
			if (!data)
			{
				console.log("Failed to write transactions.");
				return;
			}
			for (var i = 0;i < data.length;i++)
			{
				$('.transactionTable tr:last').after('<tr class="transactionTableRow">' + 
					'<td>' + data[i].id + '</td>' + '<td>' + data[i].paymentFee + '</td>' + 
					'<td>' + data[i].paymentTime + '</td>' + '<td>' + data[i].comment + '</tr>');
			}
		}
}

function init() {
	
}