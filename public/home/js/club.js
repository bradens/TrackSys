var club = {
		init : function()
		{	
			// Right column initialization
			$("#tabs").tabs();
			
			// Initialize all date pickers here
			$('#datepicker').datepicker({ dateFormat: 'yy-mm-dd' });
			
			// Initialize the cancellation dialog
			$("#cancel-dialog").dialog({
				autoOpen: false,
				height: 200,
				width: 300,
				buttons: {
					"Yes": function() {
						club.cancelBooking();
					},
					"No": function() {
						$( this ).dialog( "close" );
					}
				}
			});
			
			// Get all the notifications
			CommHandler.doPost(SERVER_LOC+PORT+"/home/getNotifications", null, this.writeNotifications);
			$(".loadingNotifications").show('fast');
			// Get the current club profile
			CommHandler.doPost(SERVER_LOC+PORT+"/home/getCurrentClubProfile", null, this.writeClubProfile);
			CommHandler.doPost(SERVER_LOC+PORT+"/home/getCurrentClubProfile", null, this.writeClubBalancePaymentTab);
			$(".loadingClubProfile").show('fast');
			// Get all the future bookings
			CommHandler.doPost(SERVER_LOC+PORT+"/home/getFutureBookings", null, this.writeFutureBookings);
			$(".loadingBookingFuture").show('fast');
			// Get all the history bookings
			CommHandler.doPost(SERVER_LOC+PORT+"/home/getHistoricBookings", null, this.writeHistoricBookings);
			$(".loadingBookingHistory").show('fast');
			// get all transactions
			CommHandler.doPost(SERVER_LOC+PORT+"/home/getTransactions", null, this.fillTransactionsTable);
			$(".loadingTransactions").show('fast');
		},
		
		writeClubProfile: function(data)
		{
			if (!data)
			{
				console.log("Failed to get current club profile");
				return;
			}
			$(".loadingClubProfile").hide('fast');
			$('.profileInformation').append(
				'<p>' + 'Club: '+ data.name + '</p>' +
				'<p>' + 'Address: ' + data.address.street +'</p>' +
				'<p>' + 'City: ' + data.address.city +', ' + data.address.province +'</p>' +
				'<p>' + 'Postal Code: ' + data.address.postal + '</p>' +
				'<p>' + 'Phone: ' + data.phone + '</p>' +
				'<p>' + 'Email: ' + data.email + '</p>' +
				'<p>' + 'Balance: $' + data.balance + '</p>' );
		},
		
		writeClubBalancePaymentTab: function(data)
		{
			if (!data)
			{
				console.log("Failed to get current club profile");
				return;
			}
			$('.paymentValue').append(data.balance +'.00' );
		},
				
		paynow: function(data)
		{
			window.location.href = "https://www.paypal.com/";
		},
		
		writeNotifications: function(data)
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
						'<span class="message">' + data[i].message + '</span></div></li>');
			}
		},
		
		writeFutureBookings: function(data)
		{
			if (!data)
			{
				console.log("Failed to get future bookings");
				return;
			}
			$(".loadingBookingFuture").css('display', 'none');
			$('#futureBookings').append('<tr class="header">' + 
											'<th>Track</th>' +
											'<th>Start Time</th>' + 
											'<th>End Time</th>' +
											'<th>Comment</th>' +
											'</tr>');
			for (var i = 0;i < data.length;i++)
			{
				$('#futureBookings').append('<tr class="transactionTableRow" onclick=\"club.openCancelDialog(' + 
						data[i].id + ');\""><td>' +
						data[i].trackID + '</td><td>' + 
						data[i].startTime + '</td><td>' + 
						data[i].endTime + '</td><td>' + 
						data[i].comment + '</td></tr>');
			}
		},
		
		writeHistoricBookings: function(data)
		{
			if (!data)
			{
				console.log("Failed to get future bookings");
				return;
			}
			$(".loadingBookingHistory").css('display', 'none');
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
		},
		
		makeBooking : function()
		{
			var date = $("#datepicker").val();
			var start = $("#startTimeSelect").val();
			var end = $("#endTimeSelect").val();
			var comment = $("#commentInputBox").val();
			var recurring = document.reccuring.recure.checked;
			
			CommHandler.doPost(SERVER_LOC+PORT+"/home/submitBooking", { date: date, start: start, end: end, comment: comment, recurring: recurring}, club.bookingSuccess);
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
		cancelBooking : function()
		{
			var id = $("#cancel-dialog").val();
			
			CommHandler.doPost(SERVER_LOC+PORT+"/home/cancelBooking", { id: id}, club.cancelSuccess);
		},
		cancelSuccess : function(data)
		{
			window.location.href = "/home/club.html";
		},
		fillTransactionsTable : function(data)
		{
			if (!data)
			{
				console.log("Failed to write transactions.");
				return;
			}
			$(".loadingTransactions").css('display', 'none');
			for (var i = 0;i < data.length;i++)
			{
				$('.transactionTable tr:last').after('<tr class="transactionTableRow">' + 
					'<td>' + data[i].id + '</td>' + '<td>' + data[i].paymentFee + '</td>' + 
					'<td>' + data[i].paymentTime + '</td>' + '<td>' + data[i].comment + '</tr>');
			}
		},
		openCancelDialog: function(data)
		{
			console.log(data);
			$("#cancel-dialog").val(data);
			console.log($("#cancel-dialog").val());
			$("#cancel-dialog").dialog( "open" );
		}
}

function init() {
	
}