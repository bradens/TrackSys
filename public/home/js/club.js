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
		},
		
		getTransactions: function()
		{
			//CommHandler.doPost(SERVER_LOC+PORT+"/home/getTransactions", , );
		},
}