package library.core;

import java.util.ArrayList;

public class Library {
	private ArrayList<Associate> associates;
	private ArrayList<Book> books;
	private ArrayList<Librarian> librarians;
	
	public Library(ArrayList<Associate> associates,
				   ArrayList<Book> books, 
				   ArrayList<Librarian> librarians ){
		this.associates= associates;
		this.books= books;
		this.librarians= librarians;
		
	}
	
	public ArrayList<Book> getBooks() {
		return books;
	}
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	public ArrayList<Associate> getAssociates() {
		return associates;
	}
	public void setAssociates(ArrayList<Associate> associates) {
		this.associates = associates;
	}
	
	public ArrayList<Librarian> getLibrarians() {
		return librarians;
	}
	public void setLibrarians(ArrayList<Librarian> librarians) {
		this.librarians = librarians;
	}
	
	public boolean checkAssociateExistance(Associate associate){
		for (Associate a: associates){
			if ( associate.equals(a) ){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkLibrarianExistance(Librarian librarian){
		for (Librarian l: librarians){
			if ( librarian.equals(l) ){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkBookExistance(Book book){
		for (Book b: books){
			if ( book.equals(b) ){
				return true;
			}
		}
		return false;
	}
	
	public boolean lendBook(Book book, Associate associate){
		for (Associate a: associates){
			if (associate.equals(a)){
				for(Book b:books){
					if (book.equals(b)){
						int stockAmount= b.stock_amount();
						if (stockAmount != 0){
							int lentAmount= b.getLentAmount();
							lentAmount++;
							b.setLentAmount(lentAmount);
							associate.addBook(book);
							return true;
						}
						else{
							return false;
						}
					}
				}
			}
		} 
		return false;
	}
	
	public boolean returnBook(Book book, Associate associate){
		if (associate.removeBook(book)) {
			return true;
		}
		else return false;
	}
}
