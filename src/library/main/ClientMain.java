package library.main;

import java.util.ArrayList;

import library.core.Associate;
import library.core.Book;
import library.core.Librarian;
import library.system.Client;
import library.system.Server;

public class ClientMain {

	Book book1 = new Book();
	Book book2 = new Book();
	Book book3 = new Book();
	
	ArrayList<Book> bookList = new ArrayList<Book>();
	
	Librarian librarian1 = new Librarian(); 
	Librarian librarian2 = new Librarian();
	Librarian librarian3 = new Librarian();
	
	ArrayList<Librarian> librarianList = new ArrayList<Librarian>();
	
	Associate associate1 = new Associate();
	Associate associate2 = new Associate();
	Associate associate3 = new Associate();
	
	ArrayList<Associate> associateList = new ArrayList<Associate>();
	
	static Server server = new Server();
	static Client client = new Client(); 

	public static void main(String args[]){
		
		client.login();
		client.getBook(book1, associate1);
		client.returnBook(book1, associate1);
		client.logoff();
		
		
	}
		
}
