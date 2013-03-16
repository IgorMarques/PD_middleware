package library.core;

import java.util.ArrayList;

public class Library {
	private ArrayList<Associate> associates;
	private ArrayList<Book> books;
	private ArrayList<Librarian> librarians;
	
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
}
