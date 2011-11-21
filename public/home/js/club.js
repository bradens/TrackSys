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
			
			$(".loginForm input").focus(function() {
				$(".errorPopup").fadeOut('fast');
			});
			$(".loginForm input").keyup(function(k) {
				if (k.keyCode == '13')
					register.submit();
			});
			
			// get all transactions
			this.getTransactions();
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
		getTransactions : function()
		{
			CommHandler.doPost(SERVER_LOC+PORT+"/home/getTransactions", null, this.fillTransactionsTable);
		},
		fillTransactionsTable : function(data)
		{
			if (!data)
			{
				console.log(err);
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