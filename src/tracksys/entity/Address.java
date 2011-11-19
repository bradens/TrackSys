package tracksys.entity;

public class Address 
{
	public String street;
	public String city;
	public String province;
	public String phone;
	public String postal;
	
	public Address(String street, String city, String province, String phone, String postal)
	{
		this.street = street;
		this.city = city;
		this.province = province;
		this.phone = phone;
		this.postal = postal;
	}
	
	public Address(String street)
	{
		this.street = street;
		city 	= "";
		province = "";
		phone = "";
		postal = "";
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getProvince()
	{
		return province;
	}
	
	public String getPostal()
	{
		return postal;
	}
	
}
