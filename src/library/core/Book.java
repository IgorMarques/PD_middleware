package library.core;

public class Book {
	
	private String title;
	private int totalAmount;
	private int lentAmount;
	
	public Book(String title, int totalAmount){
		this.title = title;
		this.totalAmount = totalAmount;
		this.lentAmount = 0;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getLentAmount() {
		return lentAmount;
	}
	public void setLentAmount(int lentAmount) {
		this.lentAmount = lentAmount;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public int stock_amount(){
		return this.getTotalAmount() - this.getLentAmount();
	}
	

}
