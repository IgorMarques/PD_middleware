package library;

import java.util.ArrayList;

import middleware.igor_marques_basic_remote_patterns.server_side.AbstractRemoteObject;


public class Library extends AbstractRemoteObject {
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
	
	@Url( value = "/book/all")
	public ArrayList<Book> getBooks() {
		return books;
	}
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	@Url( value = "/associate/all")
	public ArrayList<Associate> getAssociates() {
		return associates;
	}
	public void setAssociates(ArrayList<Associate> associates) {
		this.associates = associates;
	}
	
	@Url( value = "/librarian/all")
	public ArrayList<Librarian> getLibrarians() {
		return librarians;
	}
	public void setLibrarians(ArrayList<Librarian> librarians) {
		this.librarians = librarians;
	}
	
	public void bookStatus(){
		System.out.println("\n\n--Status dos Livros---\n");
		for(Book b: books){
			System.out.printf("-T?tulo: %s\n", b.getTitle());
			System.out.printf("--Quantidade dispon?vel para empr?stimo: %d\n", b.stock_amount());
		}
		System.out.println("\n");
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
	
	@Url( value = "/books/lend")
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
	
	@Url( value = "/books/return")
	public boolean returnBook(Book book, Associate associate){
		if (associate.removeBook(book)) {
			for(Book b:books){
				if (book.equals(b)){
					int lentAmount= b.getLentAmount();
					lentAmount--;
					b.setLentAmount(lentAmount);
				}
				
			}
			return true;
		}
		else return false;
	}
}
