package library.main;

import java.util.ArrayList;

import library.core.Associate;
import library.core.Book;
import library.core.Librarian;
import library.core.Library;
import library.system.Client;
import library.system.Server;

public class ClientMain {

	public static void main(String args[]){
		
		Book book1 = new Book("Chapeuzinho Vermelho", 30);
		Book book2 = new Book("Bela Adormecida", 20);
		Book book3 = new Book("Cinderela", 10);
		
		ArrayList<Book> bookList = new ArrayList<Book>();
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		
		Librarian librarian1 = new Librarian("Igor", "rootadmin"); 
		Librarian librarian2 = new Librarian("Joaquim", "rootadmin");
		Librarian librarian3 = new Librarian("Joao", "rootadmin");
		
		ArrayList<Librarian> librarianList = new ArrayList<Librarian>();
		librarianList.add(librarian1); 
		librarianList.add(librarian2); 
		librarianList.add(librarian3); 
		
		Associate associate1 = new Associate("Pelé");
		Associate associate2 = new Associate("Chaves");
		Associate associate3 = new Associate("Charlinho");
		
		ArrayList<Associate> associateList = new ArrayList<Associate>();
		associateList.add(associate1);
		associateList.add(associate2);
		associateList.add(associate3);
		
		Library library = new Library(associateList, bookList, librarianList);
		
		Server server = new Server(library);
		Client client = new Client(librarian1, server); 
		
		client.login();
		client.getBook(book1, associate1);
		client.getBookStatus();		
		client.returnBook(book1, associate1);
		client.getBookStatus();
		client.logoff();
		
		
	}
		
}
