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
		}
		
}