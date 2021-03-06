package library;

import java.util.ArrayList;

public class Associate {
	private String name;
	private ArrayList<Book> books;
	
	public Associate (String name){
		this.name=name;
		this.books= new ArrayList<Book>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	public void addBook(Book book){
		this.books.add(book);
	}
	
	public boolean removeBook(Book book){
		for (int i = 0; i < this.books.size(); i++){ 
		
			if (book.equals( this.books.get(i) )){
				this.books.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
}
