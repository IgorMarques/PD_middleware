package library.system;

import java.util.ArrayList;

import library.core.Associate;
import library.core.Book;
import library.core.Librarian;
import library.core.Library;

public class Server {	
	private Library library;
	private ArrayList<Librarian> onlineLibrarians;
	
	public Server(Library library){
		this.library = library;		
	}
	
	public Library getLibrary() {
		return library;
	}
	public void setLibrary(Library library) {
		this.library = library;
	}
	
	public ArrayList<Librarian> getOnlineLibrarian() {
		return onlineLibrarians;
	}
	public void setOnlineLibrarian(ArrayList<Librarian> onlineLibrarians) {
		this.onlineLibrarians = onlineLibrarians;
	}
	
	public boolean checkOnlineLibrarianExistance(Librarian librarian){
		for (Librarian l: onlineLibrarians){
			if ( librarian.equals(l) ){
				return true;
			}
		}
		return false;
	}

	public boolean login(Librarian librarian){
		if (this.library.checkLibrarianExistance(librarian)){
			this.onlineLibrarians.add(librarian);
			return true;
		}
		else return false;
	}

	public boolean logoff(Librarian librarian){
		if (this.checkOnlineLibrarianExistance(librarian)){ 
			
			for (int i = 0; i < this.onlineLibrarians.size(); i++){ 
				
				if (librarian.equals( this.onlineLibrarians.get(i) )){
					this.onlineLibrarians.remove(i);
					return true;
				}
			}
			
			return false;
					
		}
		else return false;
	}
	
	public boolean lendBook(Book book, Associate associate){
		if( this.library.lendBook(book, associate) ){
			return true;
		}
		return false;
	}
	
	public boolean returnBook(Book book, Associate associate){
		if( this.library.returnBook(book, associate) ){
			return true;
		}
		return false;
	}

}
