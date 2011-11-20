var club = {
		init : function()
		{
			$("#tabs").tabs();
			
			$("#accordion").accordion();
			
			$('.accordion .head').click(function() {
				$(this).next().toggle('fast');
				return false;
			}).next().hide();
		}
}