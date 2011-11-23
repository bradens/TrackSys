package tracksys.entity;

public class Club {
	private int id;
	private String name;
	private String passwd;
	private Address address;
	private String email;
	private String phone;
	private int electronicBilling;
	private int signedWaiver;
	private int admin;
	private float balance;
		
	public Club(int id, String name, String passwd, Address address, String email, String phone, int electronic, int signed, int admin, float balance)
	{
		this.id = id;
		this.name = name;
		this.passwd = passwd;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.electronicBilling = electronic;
		this.signedWaiver = signed;
		this.admin = admin;
		this.balance = balance;
	}
	
	public Club(int id, String name, String passwd, Address address, String email, String phone, int electronic, int signed, int admin)
	{
		this.id = id;
		this.name = name;
		this.passwd = passwd;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.electronicBilling = electronic;
		this.signedWaiver = signed;
		this.admin = admin;
		this.balance = 0;
	}
	
	public Club(String name, String passwd, Address address, String email, String phone, int electronic, int signed)
	{
		this.name = name;
		this.passwd = passwd;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.electronicBilling = electronic;
		this.signedWaiver = signed;
		this.balance = 0;
	}
	
	public Club(int id)
	{
		this.id = id;
		this.name = "";
		this.address = null;
		this.email = "";
		this.electronicBilling = 0;
		this.signedWaiver = 0;
		this.balance = 0;
	}
	
	public int getID()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPassword()
	{
		return passwd;
	}
	
	public Address getAddress()
	{
		return address;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getPhoneNumber()
	{
		return phone;
	}
	
	public int getAdmin()
	{
		return admin;
	}
	
	public int getElectronicBilling()
	{
		return this.electronicBilling;
	}
	
	public int getWaiver()
	{
		return this.signedWaiver;
	}
	
	public float getBalance()
	{
		return balance;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public int setElectronicBilling(int setting)
	{
		this.electronicBilling = setting;
		return this.electronicBilling;
	}
	
	public int setSignedWaiver(int setting)
	{
		this.signedWaiver = setting;
		return this.signedWaiver;
	}
	
	public void setAdmin(int a)
	{
		this.admin = a;
	}
	
	public void setBalance(float balance)
	{
		this.balance = balance;
	}
	
	public void creditBalanceBy(float balance)
	{
		this.balance -= balance;
	}
	public void debitBalanceBy(float balance)
	{
		this.balance += balance;
	}
}