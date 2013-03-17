package library.system;

import java.util.ArrayList;

import library.core.Associate;
import library.core.Book;
import library.core.Library;

public class Server {	
	private Library library;
	private ArrayList<Associate> onlineAssociates;
	
	public Library getLibrary() {
		return library;
	}
	public void setLibrary(Library library) {
		this.library = library;
	}
	
	public ArrayList<Associate> getOnlineAssociates() {
		return onlineAssociates;
	}
	public void setOnlineAssociates(ArrayList<Associate> onlineAssociates) {
		this.onlineAssociates = onlineAssociates;
	}
	
	public boolean login(Associate associate){
		if (this.library.checkAssociateExistance(associate)){
			this.onlineAssociates.add(associate);
			return true;
		}
		else return false;
	}
	
	public boolean checkOnlineAssociateExistance(Associate associate){
		for (Associate a: onlineAssociates){
			if ( associate.equals(a) ){
				return true;
			}
		}
		return false;
	}

	public boolean logoff(Associate associate){
		if (this.checkOnlineAssociateExistance(associate)){ 
			
			for (int i = 0; i < this.onlineAssociates.size(); i++){ 
				
				if (associate.equals( this.onlineAssociates.get(i) )){
					this.onlineAssociates.remove(i);
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
