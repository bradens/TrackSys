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
			$('#datepicker').datepicker();
		},
		makeBooking : function()
		{
			var date = $("#datepicker").val();
			var start = $("#startTimeSelect").val();
			var end = $("#endTimeSelect").val();
			
			CommHandler.doPost(SERVER_LOC+PORT+"/registration/submitregistration", { date: date, start: start, end: end}, club.bookingSuccess);
		},
		bookingSuccess : function(data)
		{
			if (data == "true")
				window.location.href = "/home/club.html#tabs-1";
			else
			{
				$(".errorPopup").fadeIn('fast');
			}
		},
		getTransactions: function()
		{
			//CommHandler.doPost(SERVER_LOC+PORT+"/home/getTransactions", , );
		}
}

function init() {
	$(".loginForm input").focus(function() {
		$(".errorPopup").fadeOut('fast');
	});
	$(".loginForm input").keyup(function(k) {
		if (k.keyCode == '13')
			register.submit();
	});
}