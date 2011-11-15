package tracksys.entity;

public class Transaction {
	private int id;
	private int clubID;
	private float paymentFee;
	private Date startPaymentPeriod;
	private Date endPaymentPeriod;
	private Date submittedTime;
	private Date receivedTime;
	private String comment;
	
	public Transaction(int id, int clubID, float payment, Date start, Date end, Date submitted, Date received, String comment)
	{
		this.id = id;
		this.clubID = clubID;
		this.paymentFee = payment;
		this.startPaymentPeriod = start;
		this.endPaymentPeriod = end;
		this.submittedTime = submitted;
		this.receivedTime = received;
		this.comment = comment;
	}
	
	public Transaction(int id)
	{
		this.id = id;
	}
	
	public int getID()
	{
		return id;
	}
	
	public int getClubID()
	{
		return clubID;
	}
	
	public float getPaymentFee()
	{
		return paymentFee;
	}
	
	public Date getReceivedTime()
	{
		return this.receivedTime;
	}
	
	public Date getSubmittedTime()
	{
		return this.submittedTime;
	}
	
	public String getPaymentPeriod()
	{
		return this.startPaymentPeriod.getDate() + " - " + this.endPaymentPeriod.getDate();
	}
	
	public String getComment()
	{
		return this.comment;
	}
	
	public void setReceivedTime(Date time)
	{
		this.receivedTime = time;
	}
	
	public void setPaymentPeriod(Date start, Date end)
	{
		this.startPaymentPeriod = start;
		this.endPaymentPeriod = end;
	}
	
	public void setPaymentFee(float fee)
	{
		this.paymentFee = fee;
	}
	
	public void setComment(String comment)
	{
		this.comment = comment;
	}
}