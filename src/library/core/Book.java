package library.core;

public class Book {
	
	private String title;
	private int total_amount;
	private int lent_amount;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	
	public int getLent_amount() {
		return lent_amount;
	}
	public void setLent_amount(int lent_amount) {
		this.lent_amount = lent_amount;
	}
	
	public int stock_amount(){
		return this.getTotal_amount() - this.getLent_amount();
	}
	

}
