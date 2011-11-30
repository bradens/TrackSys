var club = {
		editDialog: null,
		init : function()
		{	
			// get the dialog html
			CommHandler.doGet(SERVER_LOC+"1234/public/registration/editDialog.html", null, function(data) {
				club.editDialog = data;
			});
			
			// Right column initialization
			$("#tabs").tabs();
			
			// Initialize all date pickers here
			$('#datepicker, #datepicker-dayview').datepicker({ dateFormat: 'yy-mm-dd' });
			$('#datepicker-dayview').datepicker().change(function(){
				club.rewriteDayBookings();
			});
						
			// Initialize the cancellation dialog
			$("#cancel-dialog").dialog({
				autoOpen: false,
				show: 'fade',
				hide: 'fade',
				modal: true,
				height: 150,
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
			
			var currentTime = new Date();
			var month = currentTime.getMonth() + 1;
			var day = currentTime.getDate();
			var year = currentTime.getFullYear();
			$("#datepicker-dayview").val(year + "-" + month + "-" + day);
			CommHandler.doPost(SERVER_LOC+PORT+"/home/getDayBookings", {date: year +'-'+month+'-'+day}, this.writeDayBookings);

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
			
			// Hide booking error after focus change
			$(".bookingForm input").focus(function() {
				$(".errorPopup").fadeOut('fast');
			});
			
			// Hide payment error after focus change
			$(".paymentForm input").focus(function() {
				$(".paymenterror").fadeOut('fast');
			});
		},
		
		editClub: function() 
		{
			$(club.editDialog).dialog({
				show: 'fade',
				hide: 'fade',
				width: '450',
				modal: true,
				title: "Edit Profile",
				buttons: {
					"Apply": function() {
						club.finishEdit();
					},
					"Cancel": function() {
						$(this).dialog( "close" );
					}
				}
			});
		},
		
		finishEdit: function(data)
		{
			console.log(data);
		},
		
		rewriteDayBookings : function()
		{
			$('.bookingsDayTableRow').remove()
			CommHandler.doPost(SERVER_LOC+PORT+"/home/getDayBookings", {date: $('#datepicker-dayview').val()}, this.writeDayBookings);
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
			var date=new Date($('#datepicker-dayview').datepicker('getDate'));
	    	date.setDate(date.getDate()+1);
	    	$('#datepicker-dayview').datepicker('setDate', date);
			this.rewriteDayBookings();
		},
		prevDay : function()
		{
			var date=new Date($('#datepicker-dayview').datepicker('getDate'));
	    	date.setDate(date.getDate()-1);
	    	$('#datepicker-dayview').datepicker('setDate', date);
			this.rewriteDayBookings();
		},
		
		writeClubProfile: function(data)
		{
			if (!data)
			{
				console.log("Failed to get current club profile");
				return;
			}
			$(".loadingClubProfile").hide('fast');
			$(".profileTbl").append(
				'<tr class="profileRow"><td><span class="lbl">Club:</span></td><td><span class=lblVal>' + data.name + '</span></td></tr>' +
				'<tr class="profileRow"><td><span class="lbl">Address:</span></td><td><span class=lblVal>' + data.address.street + '</span></td></tr>' +
				'<tr class="profileRow"><td><span class="lbl">City:</span></td><td><span class=lblVal>' + data.address.city + '</span></td></tr>' +
				'<tr class="profileRow"><td><span class="lbl">Postal Code:</span></td><td><span class=lblVal>' + data.address.postal + '</span></td></tr>' +
				'<tr class="profileRow"><td><span class="lbl">Phone:</span></td><td><span class=lblVal>' + data.phone + '</span></td></tr>' +
				'<tr class="profileRow"><td><span class="lbl">Email:</span></td><td><span class=lblVal>' + data.email + '</span></td></tr>' +
				'<tr class="profileRow"><td><span class="lbl">Balance:</span></td><td><span class=lblVal>' + data.balance + '</span></td></tr>'
			);
		},
		
		writeClubBalancePaymentTab: function(data)
		{
			if (!data)
			{
				console.log("Failed to get current club profile");
				return;
			}
			$('.paymentValue').empty();
			$('.paymentValue').append('$' + data.balance);
		},
				
		paynow: function(data)
		{
			var amount  = $("#payInputBox").val();
			var comment = $("#payCommentInputBox").val();
						
			CommHandler.doPost(SERVER_LOC+PORT+"/home/submitPayment", { amount: amount, comment: comment}, club.paymentSuccess);
		},
		
		paymentSuccess : function(data)
		{
			if (data == "true")
			{
				console.log("TEST");
				CommHandler.doPost(SERVER_LOC+PORT+"/home/getTransactions", null, club.fillTransactionsTable);
				$(".loadingTransactions").show('fast');
				CommHandler.doPost(SERVER_LOC+PORT+"/home/getCurrentClubProfile", null, club.writeClubBalancePaymentTab);
				$("#payInputBox").val("");
				$("#payCommentInputBox").val("");
			}
			else
			{
				$(".paymenterror").fadeIn('fast');
			}
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
			console.log("Creating future table");
			$(".loadingBookingFuture").css('display', 'none');
			$('#futureBookings').append('<tr class="header">' + 
											'<th>Track</th>' +
											'<th>Start Time</th>' + 
											'<th>End Time</th>' +
											'<th>Comment</th>' +
											'<th class="futureLastColumn">Delete</th>' +
											'</tr>');
			for (var i = 0;i < data.length;i++)
			{
				$('#futureBookings').append('<tr class="transactionTableRow"' + 
						data[i].id + ');\""><td>' +
						data[i].trackID + '</td><td>' + 
						data[i].startTime + '</td><td>' + 
						data[i].endTime + '</td><td>' + 
						data[i].comment + '</td><td class="futureLastColumn">' +
						'<a onclick="club.openCancelDialog('+ data[i].id +');" class="button negative removeButton"><span class="icon trash"></span></a></td>' +
						'</tr>');
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
			var track = $("#trackIDSelect").val();
			var date = $("#datepicker").val();
			var start = $("#startTimeSelect").val();
			var end = $("#endTimeSelect").val();
			var comment = $("#commentInputBox").val();
			var recurring = document.reccuring.recure.checked;
			
			CommHandler.doPost(SERVER_LOC+PORT+"/home/submitBooking", { track:track, date: date, start: start, end: end, comment: comment, recurring: recurring}, club.bookingSuccess);
		},
		
		bookingSuccess : function(data)
		{
			if (data == "true")
				window.location.href = "/public/home/club.html";
			else
			{
				$(".bookingPopup").fadeIn('fast');
			}
		},
		cancelBooking : function()
		{
			var id = $("#cancel-dialog").val();
			
			CommHandler.doPost(SERVER_LOC+PORT+"/home/cancelBooking", { id: id}, club.cancelSuccess);
		},
		cancelSuccess : function(data)
		{
			$( "#cancel-dialog").dialog("close");
			$("#futureBookings tr").remove();
			CommHandler.doPost(SERVER_LOC+PORT+"/home/getFutureBookings", null, club.writeFutureBookings);
			$(".loadingBookingFuture").show('fast');
		},
		fillTransactionsTable : function(data)
		{
			if (!data)
			{
				console.log("Failed to write transactions.");
				return;
			}
			$(".loadingTransactions").css('display', 'none');
			$("#transactions tr").remove();
			$('#transactions').append('<tr class="header">' +
										'<th>Transaction ID</th>' +
										'<th>Fee</th>' +
										'<th>Time</th>' +
										'<th>Comment</th>' +
									'</tr>')
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