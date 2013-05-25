package library.main;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import library.core.Associate;
import library.core.Book;
import library.core.Librarian;
import library.core.Library;
import library.system.Client;
import library.system.Server;
import middleware.igor_marques_basic_remote_patterns.client.AbstractClientRequestHandler;
import middleware.igor_marques_basic_remote_patterns.client.ClientRequestHandlerFactory;
import middleware.igor_marques_basic_remote_patterns.client.IClientRequestHandler;

public class ClientMain {

	public static void main(String args[]){
		
//		Book book1 = new Book("Chapeuzinho Vermelho", 30);
//		Book book2 = new Book("Bela Adormecida", 20);
//		Book book3 = new Book("Cinderela", 10);
//		
//		ArrayList<Book> bookList = new ArrayList<Book>();
//		bookList.add(book1);
//		bookList.add(book2);
//		bookList.add(book3);
//		
//		Librarian librarian1 = new Librarian("Igor", "rootadmin"); 
//		Librarian librarian2 = new Librarian("Joaquim", "rootadmin");
//		Librarian librarian3 = new Librarian("Joao", "rootadmin");
//		
//		ArrayList<Librarian> librarianList = new ArrayList<Librarian>();
//		librarianList.add(librarian1); 
//		librarianList.add(librarian2); 
//		librarianList.add(librarian3); 
//		
//		Associate associate1 = new Associate("Pele");
//		Associate associate2 = new Associate("Chaves");
//		Associate associate3 = new Associate("Charlinho");
//		
//		ArrayList<Associate> associateList = new ArrayList<Associate>();
//		associateList.add(associate1);
//		associateList.add(associate2);
//		associateList.add(associate3);
//		
//		Library library = new Library(associateList, bookList, librarianList);
//		
//		Server server = new Server(library);
//		Client client = new Client(librarian1, server); 
//		
//		client.login();
//		client.getBook(book1, associate1);
//		client.getBookStatus();		
//		client.returnBook(book1, associate1);
//		client.getBookStatus();
//		client.logoff();
//		
		ClientRequestHandlerFactory fac = new ClientRequestHandlerFactory();
		IClientRequestHandler handinho = null;
		
		fac.registerClass("ZUERA", AbstractClientRequestHandler.class);
		
		try {
			handinho=	(IClientRequestHandler) fac.getImplementation("ZUERA");
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(handinho);
	}
		
}
