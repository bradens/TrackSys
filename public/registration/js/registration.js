var register = { 
	submit : function()
	{
		var name = $("#nameInputBox").val();
		var passwd = $("#passwdInputBox").val();
		var street = $("#streetInputBox").val();
		var city = $("#cityInputBox").val();
		var province = $("#provinceInputBox").val();
		var postal = $("#postalInputBox").val();
		var email = $("#emailInputBox").val();
		var phone = $("#phoneInputBox").val();
		var waiver = $("#waiverSelect").val();
		var billing = $("#billingSelect").val();
		
		CommHandler.doPost(SERVER_LOC+PORT+"/registration/submitregistration", { name: name, passwd: passwd, street: street, city: city, province: province, postal: postal,
			email: email, phone: phone, waiver: waiver, billing: billing}, register.registerSuccess);
	},
	
	
	registerSuccess : function(data)
	{
		if (data == "true")
			window.location.href = "/home/";
		else
		{
			$(".errorPopup").fadeIn('fast');
		}
	}
};

function init() {
	$("#dialog").dialog({
		autoOpen: false,
		height: 500,
		width: 400
	});
	
	$(".loginForm input").focus(function() {
		$(".errorPopup").fadeOut('fast');
	});
	$(".loginForm input").keyup(function(k) {
		if (k.keyCode == '13')
			register.submit();
	});
	
	$("#view-waiver")
		.button()
		.click(function() {
			$( "#dialog" ).dialog( "open" );
		});
}
