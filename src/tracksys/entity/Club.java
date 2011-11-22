package tracksys.entity;

public class Club {
	private int id;
	private String name;
	private String passwd;
	private Address address;
	private String email;
	private String phone;
	private boolean electronicBilling;
	private boolean signedWaiver;
	private int admin;
	private int balance;
		
	public Club(int id, String name, String passwd, Address address, String email, String phone, boolean electronic, boolean signed, int admin, int balance)
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
	
	public Club(int id, String name, String passwd, Address address, String email, String phone, boolean electronic, boolean signed, int admin)
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
	
	public Club(String name, String passwd, Address address, String email, String phone, boolean electronic, boolean signed)
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
		this.electronicBilling = false;
		this.signedWaiver = false;
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
	
	public int getBalance()
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
	
	public boolean setElectronicBilling(boolean setting)
	{
		this.electronicBilling = setting;
		return this.electronicBilling;
	}
	
	public boolean setSignedWaiver(boolean setting)
	{
		this.signedWaiver = setting;
		return this.signedWaiver;
	}
	
	public void setAdmin(int a)
	{
		this.admin = a;
	}
	
	public void setBalance(int balance)
	{
		this.balance = balance;
	}
	
	public void increaseBalanceBy(int balance)
	{
		this.balance += balance;
	}
	
	public void decreaseBalanceBy(int balance)
	{
		this.balance -= balance;
	}
}