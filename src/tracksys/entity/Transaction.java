package tracksys.entity;
import java.util.Date;
public class Transaction {
	private int id;
	private int clubID;
	private float paymentFee;
	private Date paymentTime;
	private String comment;
	
	public Transaction(int id, int clubID, float payment, Date start, Date end, Date submitted, Date received, String comment)
	{
		this.id = id;
		this.clubID = clubID;
		this.paymentFee = payment;
		this.paymentTime = submitted;
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
	
	public Date getPaymentTime()
	{
		return paymentTime;
	}
	
	public String getComment()
	{
		return this.comment;
	}
		
	public void setComment(String comment)
	{
		this.comment = comment;
	}
}