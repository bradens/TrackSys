package tracksys.entity;

public class Address 
{
	public String street;
	public String city;
	public String province;
	public String phone;
	public String postal;
	
	Address(String street, String city, String province, String phone, String postal)
	{
		this.street = street;
		this.city = city;
		this.province = province;
		this.phone = phone;
		this.postal = postal;
	}
	
	Address(String street)
	{
		this.street = street;
		city 	= "";
		province = "";
		phone = "";
		postal = "";
	}
}
